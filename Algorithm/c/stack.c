#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node{
	int data;
	struct node * next;
}node;
typedef struct stack{
	int size;
	struct node * top;
}stack;

int main(){
	int n,i;
	char cmd[10];
	struct stack * st=(struct stack*)malloc(sizeof(struct stack));
	struct node * cur;
	st->size=0;
	st->top=NULL;
	scanf("%d",&n);
	for(i=0; i<n; i++){
		scanf("%s",&cmd);
		if(strcmp(cmd,"push")==0){
			cur = (struct node*)malloc(sizeof(struct node));
			scanf("%d",&(cur->data));
			if(st->size!=0){
				cur->next=st->top;
			}
			st->top=cur;
			st->size++;
		}
		else if(strcmp(cmd,"pop")==0){
			if(st->size==0) printf("-1\n");
			else{
				cur = st->top;
				st->size--;
				if(st->size!=0){
					st->top=cur->next;
				}
				printf("%d\n",cur->data);
			}
		}
		else if(strcmp(cmd,"size")==0){
			printf("%d\n",st->size);
		}
		else if(strcmp(cmd,"empty")==0){
			if(st->size==0) printf("1\n");
			else printf("0\n");
		}
		else if(strcmp(cmd,"top")==0){
			if(st->size==0) printf("-1\n");
			else printf("%d\n",(st->top)->data);
		}
	}
}
