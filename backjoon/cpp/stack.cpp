#include <iostream>
#include <string.h>
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
class Stack{
	Node * top;
	int size;
public:
	Stack(){
		top = NULL;
		size = 0;
	}
	Stack(Node * t){
		top = t;
	}
	void push(int d){
		Node * cur = new Node();
		cur->setdata(d);
		cur->setnext(top);
		top = cur;
		size++;
	}
	int pop(){
		if(size==0) return -1;
		int d = top->getdata();
		Node * cur = top;
		top = cur->getnext();
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
};
int main(){
	Stack * st = new Stack();
	int n;
	int data;
	cin >> n;
	char * cmd;
	for(int i=0; i<n; i++){
		cmd = new char[10];
		cin >> cmd;
		if(strcmp(cmd,"push")==0){
			cin >> data;
			st->push(data);
		}
		else if(strcmp(cmd,"pop")==0){
			data = st->pop();
			cout << data << endl;
		}
		else if(strcmp(cmd,"top")==0){
			data = st->gettop();
			cout << data << endl;
		}
		else if(strcmp(cmd,"size")==0){
			data = st->getsize();
			cout << data << endl;
		}
		else if(strcmp(cmd,"empty")==0){
			data = st->empty();
			cout << data << endl;
		}
	}
	system("pause");
}