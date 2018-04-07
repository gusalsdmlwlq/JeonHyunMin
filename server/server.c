#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <fcntl.h>

#define BUF_SIZE 512
#define SMALL_BUF 256

void req_handler(int client_sock, struct sockaddr_in * client_addr, socklen_t client_addr_len,char req[]);
char *content_type(char *file);

int main(int argc, char *argv[])
{
	int serv_sock; //서버 소켓
	int clnt_sock; //클라이언트 소켓
	struct sockaddr_in serv_adr;
	struct sockaddr_in clnt_adr;
	int clnt_adr_size;
	char buffer[BUF_SIZE];
	char req_line[100]; //Request 메세지의 첫번째 줄을 저장
	if(argc != 2) { //실행 시 포트 번호를 같이 입력
		printf("Usage : %s <port>\n", argv[0]);
		exit(1);
	}

	//서버 소켓 생성
	serv_sock=socket(PF_INET, SOCK_STREAM, 0);
	memset(&serv_adr, 0, sizeof(serv_adr));
	serv_adr.sin_family = AF_INET;
	serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_adr.sin_port = htons(atoi(argv[1]));

	//소켓에 주소 할당
	if(bind(serv_sock, (struct sockaddr*) &serv_adr, sizeof(serv_adr)) == -1){
		perror("bind() error");
		exit(1);
	}

	//연결 요청 대기
	if(listen(serv_sock, 5) == -1){
		perror("listen() error");
		exit(1);
	}

	//연결 요청 수락
	while(1){
		clnt_adr_size=sizeof(clnt_adr);
		clnt_sock=accept(serv_sock, (struct sockaddr*) &clnt_adr, &clnt_adr_size);
		if (clnt_sock == -1) {
			perror("accept() error");
			exit(1);
		}

		//클라이언트의 ip와 port 주소값을 출력
		printf("Connection Request : %s:%d\n\n", inet_ntoa(clnt_adr.sin_addr), ntohs(clnt_adr.sin_port));
		memset(buffer, 0, BUF_SIZE);
		read(clnt_sock, buffer, BUF_SIZE); //클라이언트의 요청을 읽음
		printf(" Request Message : \n");
		printf("%s\n", buffer);
		strcpy(req_line,strtok(buffer,"\n"));
		req_handler(clnt_sock,&clnt_adr,clnt_adr_size,req_line); //요청을 처리
		close(clnt_sock);
	}
	//연결 종료
	close(serv_sock);
	return 0;
}

void req_handler(int client_sock, struct sockaddr_in * client_addr, socklen_t client_addr_len, char req[])
{
	char method[10]; //요청 method 방식
	char ct[15]; //요청한 파일의 type
	char file_name[30]; //요청한 파일의 이름
	int fd;
	char header[2024];
	char buffer[BUF_SIZE];
	int n;
	int fsize;
	if(strstr(req, "HTTP/") == NULL) // 잘못된 request인 경우 에러메시지 출력
	{
		sprintf(buffer,"<!DOCTYPE html><html lang=en><meta charset=utf-8><head><title>Error 400 (Bad Request)</title></head><body><h1>잘못된 요청입니다.</h1></body></html>");
		sprintf(header,"HTTP/1.1 400 Bad Request\r\n\r\n");
		write(client_sock,header,strlen(header));
		printf("%s",header);
		write(client_sock,buffer,strlen(buffer));
		return;
	}
	strcpy(method, strtok(req, " /"));
	strcpy(file_name, strtok(NULL, " /"));
	strcpy(ct, content_type(file_name));
	if(strcmp(method, "GET") != 0) // 잘못된 request인 경우 에러메시지 출력
	{
		sprintf(buffer,"<!DOCTYPE html><html lang=en><meta charset=utf-8><head><title>Error 400 (Bad Request)</title></head><body><h1>잘못된 요청입니다.</h1></body></html>");
		sprintf(header,"HTTP/1.1 400 Bad Request\r\n\r\n");
		write(client_sock,header,strlen(header));
		printf("%s",header);
		write(client_sock,buffer,strlen(buffer));
		return;
	}
	if(strcmp(ct,"fail")==0 || (fd=open(file_name,O_RDONLY))<0){ //클라이언트가 요청한 파일을 열음
		sprintf(buffer,"<!DOCTYPE html><html lang=en><meta charset=utf-8><head><title>Error 404 (Not Found)</title></head><body><h1>파일을 찾을 수 없습니다.</h1></body></html>");
		sprintf(header,"HTTP/1.1 404 Not Found\r\nServer: Linux Web Server\r\nContent-length:%ld\r\nContent-Language: en-US\r\nContent-type: text/html\r\n\r\n",strlen(buffer));
		write(client_sock,header,strlen(header));
		printf("%s",header);
		write(client_sock,buffer,strlen(buffer));
		return;
	}
	else fsize = lseek(fd,(off_t)0,SEEK_END); //파일의 크기를 저장
	sprintf(header,"HTTP/1.1 200 OK\r\nServer: Linux Web Server\r\nContent-length:%d\r\nContent-Language: en-US\r\nContent-type: %s\r\n\r\n",fsize,ct);
	write(client_sock,header,strlen(header)); //header 메세지를 클라이언트의 소켓에 전송함
	lseek(fd,(off_t)0,SEEK_SET); //descriptor를 다시 파일의 맨 앞으로 설정함
	while((n=read(fd,buffer,BUF_SIZE))>0){ //클라이언트가 요청한 파일의 데이터를 읽어 클라이언트의 소켓에 전송함
		write(client_sock,buffer,n);
	}
	close(fd);
	printf("%s",header);
}

char* content_type(char* file) //파일이름을 인자로 받아 확장자를 출력해주는 함수
{
	char extension[SMALL_BUF];
	char file_name[SMALL_BUF];
	strcpy(file_name, file);
	if(strstr(file_name,".")==NULL) return "fail"; //확장자가 없는 경우 fail을 return
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
