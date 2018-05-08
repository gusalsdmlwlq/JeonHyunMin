#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>

#define bufsize 256
#define maxline 50

int main(){
	char input[100];
	int is_background = 0;
	char * cwd;
	pid_t pid;
	int i;
	int argc;
	char * argv[maxline];
	char * ins;
	char * str;
	int status;
	while(1){
		memset(argv,NULL,maxline);
		printf("Shell > ");
		gets(input);
		if(input[strlen(input)-1] == '&'){
			input[strlen(input)-1] = '\0';			
			is_background = 1;
		}
		else is_background = 0;
		
		if((pid=fork()) == 0){
			str = strtok(input," ");
			i = 0;
			while(str != NULL){
				argv[i] = str;
				i++;
				str=strtok(NULL," ");
			}
			argv[i] = NULL;
			ins = argv[0];
			memset(argv,"",1);
			sprintf(argv[0],"/bin/%s",ins);
			execv(argv[0],argv);
		}
		else{
			if (!is_background)
			{
				/* Wait child process */
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