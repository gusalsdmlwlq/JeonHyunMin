#include <stdio.h>
#include <string.h>
#include <windows.h>

int main(){
	int arr[26][10];
	int i,j;
	char str[1000];
	char * res;
	char inner='0';
	char * ins;
	for(i=0; i<26; i++){
		arr[i][0] = 0;
	}
	gets(str);
	res = (char*)malloc(sizeof(char)*strlen(str));
	strcpy(res,str);
	for(i=0; i<strlen(str); i++){
		if(issmall(str[i])){
			//arr[str[i]-97][0]++;
			arr[str[i]-97][++arr[str[i]-97][0]]=i;
		}
	}
	for(i=0; i<26; i++){
		inner='0';
		if(arr[i][0] != 0){
			if(arr[i][0] == 2){
				if(issmall(str[(arr[i][1])+1]) || issmall(str[(arr[i][2])-1])){ //ab~~ba
					printf("invalid\n");
					return 1;
				}
				for(j=(arr[i][1])+1; j<arr[i][2]; j++){
					if(issmall(str[j])){
						if(inner=='0'){
							inner = str[j];
							if(arr[str[j]-97][0]==1 && arr[i][2]-arr[i][1]!=4){
								printf("invalid\n");
								return 1;	
							}
						}
						else if(str[j]!=inner){ //a~b~c~a
							printf("invalid\n");
							return 1;
						}
						else{
							if(arr[str[j]-97][0]==2 && arr[i][2]-arr[i][1]!=6){
								printf("invalid\n");
								return 1;
							}
						}
					}
				}
				res[arr[i][1]]=' ';
				res[arr[i][2]]=' ';
			}
			else if(arr[i][0] == 1){
				if(arr[i][1]==0 || arr[i][1]==strlen(str)-1){ //a~~~    ~~~a
					printf("invalid\n");
					return 1;
				}
				else if(issmall(str[(arr[i][1])-1]) || issmall(str[(arr[i][1])+1])){ //~bad~~~
					printf("invalid\n");
					return 1;
				}
			}
			else{
				for(j=1; j<arr[i][0]; j++){
					if(arr[i][j]!=(arr[i][j+1])-2){ //~a~a~~~a~
						printf("invalid\n");
						return 1;
					}
				}
				if(arr[i][1]==0 || arr[i][(arr[i][0])]==strlen(str)-1){ //a~a~a~   ~a~a~a
					printf("invalid\n");
					return 1;
				}
				else if(arr[i][1]>1 && issmall(str[arr[i][1]-2])){
					if(arr[str[arr[i][1]-2]-97][0]==1 || arr[str[arr[i][1]-2]-97][0]>=3){
						printf("invalid\n");
						return 1;
					}
					else if(arr[str[arr[i][1]-2]-97][0]==2 && arr[str[arr[i][1]-2]-97][2]-arr[str[arr[i][1]-2]-97][1]==2){
						printf("invalid\n");
						return 1;
					}
				}
				else if(arr[i][arr[i][0]]<strlen(str)-2 && issmall(str[arr[i][arr[i][0]]+2])){
					if(arr[str[arr[i][arr[i][0]]+2]-97][0]==1 || arr[str[arr[i][arr[i][0]]+2]-97][0]>=3){
						printf("invalid\n");
						return 1;
					}
					else if(arr[str[arr[i][arr[i][0]]+2]-97][0]==2 && arr[str[arr[i][arr[i][0]]+2]-97][2]-arr[str[arr[i][arr[i][0]]+2]-97][1]==2){
						printf("invalid\n");
						return 1;
					}
				}
				if(issmall(str[arr[i][1]-1]) || issmall(str[arr[i][arr[i][0]]+1])){
					printf("invalid\n");
					return 1;
				}
			}
		}
	}
	printf("%s\n",res);
	ins=strtok(res," ");
	for(i=0; i<strlen(ins); i++){
		if(issmall(ins[i])==0){
			printf("%c",ins[i]);
		}
	}
	while((ins=strtok(NULL," "))!=NULL){
		printf(" ");
		for(i=0; i<strlen(ins); i++){
			if(issmall(ins[i])==0){
				printf("%c",ins[i]);
			}
		}
	}
	Sleep(5000);
	return 1;
}

int issmall(char ch){
	if(ch>=97 && ch<=122) return 1;
	else return 0;
}