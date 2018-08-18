#include <iostream>
#include <string>
#include <stdio.h>
using namespace std;

class Node{
	Node * next;
	int data;
public:
	Node(){
		next = NULL;
		data = NULL;
	}
	Node(Node * n, int d){
		next = n;
		data = d;
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
};
class Queue{
	Node * head;
	Node * top;
	int size;
public:
	Queue(){
		head = NULL;
		top = NULL;
		size = 0;
	}
	Queue(Node * h){
		head = h;
	}
	void enqueue(int d){
		Node * cur = new Node();
		cur->setdata(d);
		if(size!=0) top->setnext(cur);
		else head = cur;
		top = cur;
		size++;
	}
	int dequeue(){
		if(size==0) return -1;
		int d = head->getdata();
		Node * cur = head;
		head = cur->getnext();
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
int arr[1000][1000];
int root[1000];
Queue * q = new Queue();
void dfs(int d,int m);
void bfs(int d,int m);
int main(){
	int m,n,v;
	int v1,v2;
	scanf("%d %d %d",&m,&n,&v);
	memset(root,0,sizeof(int)*m);
	for(int i=0; i<m; i++){
		memset(arr[i],0,sizeof(int)*m);
	}
	for(int i=0; i<n; i++){
		scanf("%d %d",&v1,&v2);
		arr[v1-1][v2-1] = 1;
		arr[v2-1][v1-1] = 1;
	}
	dfs(v,m);
	printf("\n");
	memset(root,0,sizeof(int)*m);
	bfs(v,m);
	system("pause");
}
void dfs(int d,int m){
	root[d-1] = 1;
	printf("%d ",d);
	for(int i=0; i<m; i++){
		if(arr[d-1][i] == 1 && root[i] == 0){
			dfs(i+1,m);
		}
	}
}
void bfs(int d,int m){
	q->enqueue(d);
	root[d-1] = 1;
	while(!q->empty()){
		int x = q->dequeue();
		printf("%d ",x);
		for(int i=0; i<m; i++){
			if(arr[x-1][i] == 1 && root[i] == 0){
				q->enqueue(i+1);
				root[i] = 1;
			}
		}
	}
}