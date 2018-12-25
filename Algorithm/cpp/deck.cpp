#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
class Node{
	Node * pre;
	Node * next;
	int data;
public:
	Node(){
		pre = NULL;
		next = NULL;
		data = NULL;
	}
	Node(Node * p,Node * n, int d){
		pre = p;
		next = n;
		data = d;
	}
	void setpre(Node * p){
		pre = p;
	}
	void setnext(Node * n){
		next = n;
	}
	void setdata(int d){
		data = d;
	}
	int getdata(){
		return data;
	}
	Node * getnext(){
		return next;
	}
	Node * getpre(){
		return pre;
	}
};
class Deck{
	Node * head;
	Node * top;
	int size;
public:
	Deck(){
		head = NULL;
		top = NULL;
		size = 0;
	}
	void push_front(int d){
		Node * cur = new Node();
		cur->setdata(d);
		if(size!=0){
			head->setpre(cur);
			cur->setnext(head);
		}
		else top = cur;
		head = cur;
		size++;
	}
	void push_back(int d){
		Node * cur = new Node();
		cur->setdata(d);
		if(size!=0){
			top->setnext(cur);
			cur->setpre(top);
		}
		else head = cur;
		top = cur;
		size++;
	}
	int pop_front(){
		if(size==0) return -1;
		int d = head->getdata();
		Node * cur = head;
		head = cur->getnext();
		delete(cur);
		size--;
		return d;
	}
	int pop_back(){
		if(size==0) return -1;
		int d = top->getdata();
		Node * cur = top;
		top = cur->getpre();
		delete(cur);
		size--;
		return d;
	}
	int getsize(){
		return size;
	}
	int empty(){
		if(size==0) return 1;
		else return 0;
	}
	int gettop(){
		if(size==0) return -1;
		int d = top->getdata();
		return d;
	}
	int gethead(){
		if(size==0) return -1;
		int d = head->getdata();
		return d;
	}
};

int main(){
	Deck * d = new Deck();
	int t;
	int n;
	int * arr;
	int count;
	int r;
	int er;
	int x;
	scanf("%d",&t);
	char cmd[100000];
	for(int i=0; i<t; i++){
		scanf("%s %d\n[",&cmd,&n);
		arr = new int[n];
		count = 0;
		r=1;
		er=0;
		x=0;
		while(1){
			char c = getchar();
			if(c >= '0' && c <= '9'){
				x = x*10 + c-'0';
			}
			else{
				if(x>0){
					d->push_back(x);
					x=0;
				}
				if(c==']') break;
			}
		}
        int len = strlen(cmd);
		for(int i=0; i<len; i++){
			if(cmd[i]=='R') r=r*(-1);
			else{
				if(r==1){
					if((d->pop_front())==-1){
						er=1;
						break;
					}
				}
				else{
					if((d->pop_back())==-1){
						er=1;
						break;
					}
				}
			}
		}
		if(er){
			printf("error\n");
		}
		else{
			printf("[");
			if(d->empty()) printf("]\n");
			else{
				if(r==1){
					while((d->getsize())>1){
						printf("%d,",d->pop_front());
					}
					printf("%d]\n",d->pop_front());
				}
				else{
					while((d->getsize())>1){
						printf("%d,",d->pop_back());
					}
					printf("%d]\n",d->pop_back());
				}
			}
		}
	}
}