#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <math.h>

int walk(int a,int b,int d);
int boost(int * r, int n, int p, int d, int x);
int * point;
int main(){
	int n,q;
	int i,j;
	int a,b,x;
	int p1[2],p2[2];
	int * root;
	scanf("%d %d",&n,&q);
	point = (int*)malloc(sizeof(int)*n*2);
	for(i=0; i<n*2; i=i+2){
		scanf("%d %d",&point[i],&point[i+1]);
	}
	root = (int*)malloc(sizeof(int)*n);
	for(i=0; i<q; i++){
		memset(root,0,sizeof(int)*n);
		scanf("%d %d %d",&a,&b,&x);
		root[a-1]=1;
		p1[0]=point[2*(a-1)];
		p1[1]=point[2*(a-1)+1];
		p2[0]=point[2*(b-1)];
		p2[1]=point[2*(b-1)+1];
		for(j=0; j<n; j++){
			if(j==b-1) break;
			if((point[2*j]==point[2*(b-1)] || point[2*j+1]==point[2*(b-1)+1]) || walk(j+1,b,x)==1) break;
		}
		if(j==n) printf("NO\n");
		else if(p1[0]==p2[0] || p1[1]==p2[1]) printf("YES\n");
		else if(boost(root,n,a,b,x)==1) printf("YES\n");
		else printf("NO\n");
	}
	Sleep(5000);
}

int boost(int * r, int n, int p, int d, int x){
	int i;
	int hp=x;
	if(point[2*(p-1)]==point[2*(d-1)] || point[2*(p-1)+1]==point[2*(d-1)+1]){
		return 1;
	}
	else if(walk(p,d,x)==1){
		return 1;
	}
	for(i=0; i<n; i++){
		if(r[i]==0){
			if((point[2*i]==point[2*(d-1)] || point[2*i+1]==point[2*(d-1)+1])&&(point[2*i]==point[2*(p-1)] || point[2*i+1]==point[2*(p-1)+1])){
				return 1;
			}
			else if((point[2*i]==point[2*(p-1)] || point[2*i+1]==point[2*(p-1)+1])&&walk(i+1,d,x)==1){
				return 1;
			}
		}
	}
	for(i=0; i<n; i++){
		if(r[i]==0){
			if(point[2*i]==point[2*(p-1)] || point[2*i+1]==point[2*(p-1)+1]){
				r[i]=1;
				if(boost(r,n,i+1,d,x)==1) return 1;
				else r[i]=0;
			}
			else if(walk(p,i+1,x)==1){
				r[i]=1;
				if(boost(r,n,i+1,d,x)==1) return 1;
				else r[i]=0;
			}
		}
	}
	return 0;
}
int walk(int a,int b,int d){
	double x,y;
	x=point[2*(b-1)]-point[2*(a-1)];
	y=point[2*(b-1)+1]-point[2*(a-1)+1];
	if(abs(x)<=d || abs(y)<=d) return 1;
	else return 0;
}