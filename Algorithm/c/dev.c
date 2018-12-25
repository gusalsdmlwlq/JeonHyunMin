#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <math.h>

double dev(int * nums,int n);
int main(){
	int n,k,i,j,l;
	int * num;
	int * arr;
	double min;
	scanf("%d %d",&n,&k);
	num=(int*)malloc(sizeof(int)*n);
	for(i=0; i<n; i++){
		scanf("%d",&num[i]);
	}
	for(i=k; i<=n; i++){
		arr=(int*)malloc(sizeof(int)*i);
		memset(arr,0,i);
		for(j=0; j<=n-i; j++){
			for(l=0; l<i; l++){
				arr[l]=num[j+l];
			}
			if(i==k && j==0) min=dev(arr,i);
			else if(dev(arr,i)<min) min=dev(arr,i);
		}
	}
	printf("%.11f\n",min);
	Sleep(5000);
}
double dev(int * nums,int n){
	int i;
	double mean;
	double total=0;
	double dev;
	for(i=0; i<n; i++){
		total+=nums[i]; 
	}
	mean=total/n;
	total=0.0;
	for(i=0; i<n; i++){
		total=total+((nums[i]-mean)*(nums[i]-mean));
	}
	dev=total/n;
	dev=sqrt(dev);
	return dev;
}