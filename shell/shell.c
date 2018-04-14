/**
 * Simple shell interface program.
 *
 * Operating System Concepts - Ninth Edition
 * Copyright John Wiley & Sons - 2013
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

#define MAX_LINE 80

int main(void)
{
	char *args[MAX_LINE/2 + 1];
    	int should_run = 1;
	int background = 0;
	int i=0;
	int status;
	char sc[100];
	char * str;
	char * ins;
	char * sig = "sig";
	pid_t pid;
    while (should_run){   
        printf("osh>");
        fflush(stdout);
	status = NULL;
        gets(sc);
	args[0] = "";
	memset(args,NULL,MAX_LINE/2 + 1);
	i=0;
	if(sc[strlen(sc)-1] == '&'){
		sc[strlen(sc)-1] = '\0';			
		background = 1;
	}
	else background = 0;
	//printf("%s\n",sc);
        /**
         * After reading user input, the steps are:
         * (1) fork a child process
         * (2) the child process will invoke execv()
         * (3) if command included &, parent will invoke wait()
         */
	if((pid=fork()) <0){
		perror("fork error");
	}
	else if(pid == 0){
		str = strtok(sc," ");
		while(str != NULL){
			args[i] = str;
			i++;
			str=strtok(NULL," ");
		}
		args[i] = NULL;
		ins=args[0];
		memset(args,"",1);
		sprintf(args[0],"/bin/%s",ins);
		printf("child exe\n");
		execv(args[0],args);
		printf("child exe fail\n");
		exit(-1);
	}
	else{
		if (!background)
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
    
	return 0;
}