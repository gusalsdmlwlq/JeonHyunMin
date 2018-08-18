#include <iostream>
#include <string>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

int * num;
void quick(int l,int r);
void swap(int i,int j);
int main(){
	int n;
	scanf("%d",&n);
	num = (int*)malloc(sizeof(int)*n);
	for(int i=0; i<n; i++){
		scanf("%d",&num[i]);
	}
	quick(0,n-1);
	for(int i=0; i<n; i++){
		printf("%d\n",num[i]);
	}
	system("pause");
}
void quick(int l,int r){
	if(l>=r) return;
	int pivot = l;
	int i=l+1;
	int j=r;
	while(1){
		while(i<r && num[i]<num[pivot]){
			i++;
		}
		while(j>l && num[j]>=num[pivot]){
			j--;
		}
		if(i<j){
			swap(i,j);
		}
		else{
			break;
		}
	}
	swap(pivot,j);
	quick(l,j-1);
	quick(j+1,r);
}
void swap(int i,int j){
	int temp = num[i];
	num[i] = num[j];
	num[j] = temp;
}