#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <fcntl.h>
#include <netinet/in.h>
#include <netdb.h>
#include <time.h>
#include <pthread.h>
#include <sys/stat.h>
#include <dirent.h>
#include <sys/types.h>

#define BUF_SIZE 512
#define SMALL_BUF 256
#define num_threads 20

void handler(void * parameter);
int req_handler(int client_sock,char req[]);
void logging(char * req, char * ip, int fsize);
char *content_type(char *file);
typedef struct param{
	int clnt_sock;
	struct sockaddr_in clnt_adr;
}param; //스레드에 여러 인자를 넘기기 위해 struct를 사용

int main(int argc, char *argv[])
{
	int serv_sock; // 서버 소켓
	int clnt_sock; // 클라이언트 소켓
	struct sockaddr_in serv_adr;
	struct sockaddr_in clnt_adr;
	int clnt_adr_size;
	char buffer[BUF_SIZE];
	char req_line[100];
	int fsize;
	int threadindex = 0;
	pthread_t threads[num_threads];
	param parameter;	

	if(argc != 2) { // 포트번호가 없으면
		printf("Usage : %s <port>\n", argv[0]);
		exit(1);
	}
	/* 서버 소켓 생성 */
	serv_sock=socket(PF_INET, SOCK_STREAM, 0);
	memset(&serv_adr, 0, sizeof(serv_adr));
	serv_adr.sin_family = AF_INET;
	serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_adr.sin_port = htons(atoi(argv[1]));
	/* 소켓에 주소 할당 */
	if(bind(serv_sock, (struct sockaddr*) &serv_adr, sizeof(serv_adr)) == -1){
		perror("bind() error");
		exit(1);
	}
	/* 연결 요청 대기상태로 진입 */
	if(listen(serv_sock, 10) == -1){
		perror("listen() error");
		exit(1);
	}
	/* 연결 요청 수락 */
	while(1){
		clnt_adr_size=sizeof(clnt_adr);
		clnt_sock=accept(serv_sock, (struct sockaddr*) &clnt_adr, &clnt_adr_size);
		if (clnt_sock == -1) {
			perror("accept() error");
			exit(1);
		}
		parameter.clnt_sock = clnt_sock;
		parameter.clnt_adr = clnt_adr;
		pthread_create(&threads[threadindex++],NULL,handler,(void * )&parameter);
		//연결이 되면 스레드를 만들고 스레드로 서버를 실행시
	}
	/* 연결 종료 */
	close(serv_sock);

	return 0;
}

int cachesize(){ //캐시의 크기를 구하는 함
	int fd;
	int size=2;
	DIR * dirptr;
	struct dirent * dir;
	dirptr = opendir("cache");
	chdir("cache");
	while(dir=readdir(dirptr)){
		fd = open(dir->d_name,O_RDONLY);
		if(lseek(fd,(off_t)0,SEEK_END) > 0) size+=lseek(fd,(off_t)0,SEEK_END);
	}
	return size;
}

int caching(char * url){ //캐시를 체크하는 함
	char file[100];
	int fd;

	strcpy(file,url);
	mkdir("cache",0755);
	chdir("cache");
	if((fd=open(file,O_RDONLY))==-1){
		chdir("..");
		return 0;
	}
	else{
		printf("###hit###\n");
		chdir("..");
		return 1;
	}
}

void handler(void * parameter){ //스레드를 만들고 스레드로 프록시 연결을 실행하는 함수
	char buffer[BUF_SIZE];
	char req_line[100];
	int fsize;
	param * myparam = parameter;
	int clnt_sock = myparam->clnt_sock;
	struct sockaddr_in clnt_adr = myparam->clnt_adr;
	memset(buffer, 0, BUF_SIZE);
	read(clnt_sock, buffer, BUF_SIZE);
	if(strstr(buffer,"GET") == NULL) return;
	printf("Connection Request : %s:%d\n\n", inet_ntoa(clnt_adr.sin_addr), ntohs(clnt_adr.sin_port));
	printf(" Request Message : \n");
	printf("%s\n", buffer);
	strcpy(req_line,strtok(buffer,"\n"));
	fsize = req_handler(clnt_sock,req_line);
	logging(buffer,inet_ntoa(clnt_adr.sin_addr),fsize);
	close(clnt_sock);
	pthread_exit(NULL);
}

int req_handler(int client_sock, char req[]) //요청을 처리하는 함
{
	char method[10];
	char ct[15];
	char url[100];
	char file_name[30];
	int fd;
	char header[2024];
	char buffer[BUF_SIZE];
	int n;
	int fsize;
	int sockfd;
	char arr[100];
	char * ins;
	char cachefile[100];
	time_t tstamp;
	struct sockaddr_in serv_addr;
	struct hostent *server;
	
	if(strstr(req, "HTTP/") == NULL) // 잘못된 request인 경우 에러메시지 출력
	{
		sprintf(buffer,"<!DOCTYPE html><html lang=en><meta charset=utf-8><head><title>Error 400 (Bad Request)</title></head><body><h1>잘못된 요청입니다.</h1></body></html>");
		sprintf(header,"HTTP/1.1 400 Bad Request\r\n\r\n");
		write(client_sock,header,strlen(header));
		printf("%s",header);
		write(client_sock,buffer,strlen(buffer));
		return 0;
	}
	strcpy(method, strtok(req, " /"));
	if(strcmp(method, "CONNECT") == 0){ //CONNECT
		return 0;
	}
	strcpy(url,strtok(NULL," "));
	strtok(url,"/");
	strcpy(url,strtok(NULL,"/"));
	if((ins=strtok(NULL," "))==NULL) strcpy(file_name,"index.html"); //요청으로 도메인 주소만 받으면 자동으로 뒤에 /index.html을 붙임
	else strcpy(file_name,ins);
	strcpy(ct, content_type(file_name));
	strcpy(arr,url);
	strcat(arr,"_");
	strcat(arr,file_name);
	strcpy(cachefile,strtok(arr,"/")); //캐시를 만들기 위해 url을 파싱함
	while((ins=strtok(NULL,"/"))!=NULL){
		strcat(cachefile,"_");
		strcat(cachefile,ins);
	}
	if(caching(cachefile)==1){ //캐시에 저장되어 있는지 체크
		chdir("cache");
		fd=open(cachefile,O_RDONLY); //캐시에 저장되어 있으면 요청한 웹서버와 연결하지 않고 캐시된 파일을 불러와 클라이언트에 전달
		while((n=read(fd,buffer,BUF_SIZE))>0){
			write(client_sock,buffer,n);
			ins=strtok(buffer,"\n");
			strcpy(arr,ins);
			while((ins=strtok(NULL,"\n"))!=NULL){
				strcpy(arr,ins);
				if(strstr(arr,"Content-Length")){
					strtok(arr," ");
					fsize = atoi(strtok(NULL," "));
				}
			}
		}
		chdir("..");
		close(fd);
		return fsize;
	}
	else{ //캐시에 저장되어 있지 않으면 웹서버에 연결해 파일을 받아 캐시에 저장하고 클라이언트에 전달
		sockfd = socket(AF_INET, SOCK_STREAM, 0);
		if (sockfd < 0) perror("ERROR opening socket");
		server = gethostbyname(url);
		if (server == NULL) {
			fprintf(stderr,"ERROR, no such host\n");
			return 0;
		}
		bzero((char *) &serv_addr, sizeof(serv_addr));
		serv_addr.sin_family = AF_INET; //initialize server's address
		bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
		serv_addr.sin_port = htons(80);
		if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0) perror("ERROR connecting");
		bzero(buffer,BUF_SIZE);
		sprintf(buffer,"GET /%s HTTP/1.1\r\nHost: %s:80\r\n\r\n",file_name,url);
		write(sockfd,buffer,strlen(buffer));
		bzero(buffer,BUF_SIZE);
		chdir("cache");
		fd = creat(cachefile,0644);
		while((n=read(sockfd,buffer,BUF_SIZE))>0){
			write(client_sock,buffer,n); //read from the socket
			write(fd,buffer,n);
			ins=strtok(buffer,"\n");
			strcpy(arr,ins);
			while((ins=strtok(NULL,"\n"))!=NULL){
				strcpy(arr,ins);
				if(strstr(arr,"Content-Length")){
					strtok(arr," ");
					fsize = atoi(strtok(NULL," "));
				}
			}
			if(n<BUF_SIZE) break;
		}
		if(fsize>512000){ //파일이 512KB 이상이면 캐시에 저장하지 않음
			remove(cachefile);
		}
		chdir("..");
		if(cachesize()>5000000){ //캐시는 최대 5GB 까지만 저
			remove(cachefile);
		}
		chdir("..");
		close(fd);
		close(sockfd); //close socket
		return fsize;
	}
}

void logging(char * req, char * ip, int fsize){ //log 파일을 만들어 기록을 저장함
	char date[100];
	char url[100];
	int fd;
	char log[100];
	time_t rtime;
	time(&rtime);
	strftime(date,100,"%a %d %b %Y %H:%M:%S %Z",localtime(&rtime));
	strcpy(url,req);
	strtok(url," ");
	strcpy(url,strtok(NULL," "));
	fd = open("proxy.log",O_RDWR|O_CREAT|O_APPEND,0666);
	sprintf(log,"%s: %s %s %d\n",date,ip,url,fsize);
	write(fd,log,strlen(log));
}

char* content_type(char* file) //url을 파싱해 파일 형식을 체크함
{
	char extension[SMALL_BUF];
	char file_name[SMALL_BUF];
	strcpy(file_name, file);
	if(strstr(file_name,".")==NULL) return "fail";
	strtok(file_name, ".");
	strcpy(extension, strtok(NULL, "."));

	if(!strcmp(extension, "html")||!strcmp(extension, "htm")) 
		return "text/html";
	else if (!strcmp(extension, "jpg"))
		return "image/jpeg";
	else if (!strcmp(extension, "gif"))
		return "image/gif";
	else if (!strcmp(extension, "png"))
		return "image/png";
	else if (!strcmp(extension, "txt"))
		return "text/plain";
	else if (!strcmp(extension, "pdf"))
		return "application/pdf";
	else if (!strcmp(extension, "mp3"))
		return "audio/mpeg3";
	else
		return "text/html";
}
