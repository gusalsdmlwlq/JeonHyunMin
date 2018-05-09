#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>

#define bufsize 256
#define maxline 50

int main(){
	char input[100]; //입력받는 명령어
	int is_background = 0;
	char cwd[bufsize]; //현재 디렉토리 저장 버퍼
	pid_t pid;
	int i;
	char * argv[maxline]; //exec로 실행할 벡터 
	char * str;
	int status;
	int fd[2]; //pipe
	char * cmd[10]; //pipe로 나누는 명령어 벡터
	int pipes=0; //pipe로 나누는 명령어 개수
	
	while(1){
		getcwd(cwd,bufsize);
		printf("Shell > %s > ",cwd);
		gets(input);
		if(input[strlen(input)-1] == '&'){
			input[strlen(input)-1] = '\0';			
			is_background = 1;
		}
		else is_background = 0;
		
		if(strcmp(input,"exit")==0) exit(1); //exit를 입력하면 종료

		//shell fork
		if((pid=fork()) == 0){
			//파이프로 나눔
			for(int j=0; j<10; j++) cmd[j] = (char*)malloc(sizeof(char*));
			cmd[pipes] = strtok(input,"|");
			pipes++;
			while((cmd[pipes]=strtok(NULL,"|")) != NULL){
				pipes++;
			}
			for(int j=0; j<pipes; j++){
				if(cmd[j][strlen(cmd[j])-1] == ' ') cmd[j][strlen(cmd[j])-1]='\0';
				if(cmd[j][0] == ' ') memmove(cmd[j],cmd[j]+1,strlen(cmd[j]));
			}
			pipes--;
			//파이프를 통해 앞의 명령어들 수행
			for(int j=0; j<pipes; j++){
				pipe(fd);
				if((pid=fork()) == 0){
					close(fd[0]);
					dup2(fd[1],STDOUT_FILENO);
					str = strtok(cmd[j]," ");
					i = 0;
					while(str != NULL){
						argv[i] = str;
						i++;
						str=strtok(NULL," ");
					}
					argv[i] = NULL;
					execvp(argv[0],argv);
				}
				else{
					close(fd[1]);
					dup2(fd[0],STDIN_FILENO);
				}
			}
			//마지막 명령어 수행
			str = strtok(cmd[pipes]," ");
			i = 0;
			while(str != NULL){
				argv[i] = str;
				i++;
				str=strtok(NULL," ");
			}
			argv[i] = NULL;
			execvp(argv[0],argv);
			printf("명령어를 찾을 수 없음 : %s\n",argv[0]);
			sleep(1);
			exit(1);
		}
		else{
			if (!is_background)
			{
				printf("waiting child %d\n",pid);
				waitpid(pid,&status,0);
				printf("child terminate (%d)\n",status);
			}
			else
			{
				printf("child %d exe in background\n",pid);
			}
		}
	}
}