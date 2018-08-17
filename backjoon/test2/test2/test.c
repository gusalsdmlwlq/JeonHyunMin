#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Windows.h>

typedef struct stack{
	int buf[100];
	int top;
}stack;
int push(char ch);
int pop();
int top();
stack st;
int main(){
	char str[30];
	int i,n;
	int t,p;
	int res=0;
	st.top=-1;
	scanf("%s",&str);
	n=strlen(str);
	for(i=0; i<n; i++){
		if(str[i]=='('){
			push(-1);
		}
		else if(str[i]=='['){
			push(-2);
		}
		else if(str[i]==')'){
			if(top()==-1) pop();
			else{
				printf("0\n");
				return 0;
			}
		}
		else if(str[i]==']'){
			if(top()==-2) pop();
			else{
				printf("0\n");
				return 0;
			}
		}
	}
	if(st.top!=-1){
		printf("0\n");
		return 0;
	}
	for(i=0; i<n; i++){
		if(str[i]=='('){
			push(-1);
		}
		else if(str[i]=='['){
			push(-2);
		}
		else if(str[i]==')'){
			if(top()==-1){
				pop();
				push(2);
			}
			else{
				while(1){
					t=pop();
					p=pop();
					if(p==-1){
						push(t*2);
						break;
					}
					else push(t+p);
				}
			}
		}
		else if(str[i]==']'){
			if(top()==-2){
				pop();
				push(3);
			}
			else{
				while(1){
					t=pop();
					p=pop();
					if(p==-2){
						push(t*3);
						break;
					}
					else push(t+p);
				}
			}
		}
	}
	while(st.top!=-1){
		res = res + st.buf[st.top];
		st.top--;
	}
	printf("%d\n",res);
	Sleep(5000);
}
int push(int ch){
	st.top++;
	st.buf[st.top]=ch;
	return 1;
}
int pop(){
	int num=st.buf[st.top];
	st.top--;
	return num;
}
int top(){
	return st.buf[st.top];
}