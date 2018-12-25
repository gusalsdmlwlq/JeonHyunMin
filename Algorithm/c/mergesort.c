#include <stdio.h>
#include <string.h>
#include <windows.h>

int merge(int * arr, int n);
int main(){
	int n;
	int i;
	int * arr;
	scanf("%d",&n);
	arr = (int *)malloc(sizeof(int)*n);
	for(i=0; i<n; i++){
		scanf("%d",&arr[i]);
	}
	merge(arr,n);
	for(i=0; i<n; i++){
		printf("%d\n",arr[i]);
	}
	Sleep(5000);
}
int merge(int * arr, int n){
	int l=0,r=0;
	int i=0;
	int h = n/2;
	int * tarr = (int*)malloc(sizeof(int)*n);
	int * larr = (int*)malloc(sizeof(int)*h);
	int * rarr = (int*)malloc(sizeof(int)*(n-h));
	if(n==1) return 1;
	memcpy(larr,arr,sizeof(int)*h);
	memcpy(rarr,arr+h,sizeof(int)*(n-h));
	merge(larr,h);
	merge(rarr,n-h);
	while(1){
		if(l==h){
			tarr[i] = rarr[r];
			i++;
			r++;
		}
		else if(r==n-h){
			tarr[i] = larr[l];
			i++;
			l++;
		}
		else{
			if(larr[l]<rarr[r]){
				tarr[i] = larr[l];
				i++;
				l++;
			}
			else{
				tarr[i] = rarr[r];
				i++;
				r++;
			}
		}
		if(i==n) break;
	}
	memcpy(arr,tarr,sizeof(int)*n);
	free(tarr);
	free(larr);
	free(rarr);
}