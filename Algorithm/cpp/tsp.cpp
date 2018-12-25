#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#include <math.h>
#include <bitset>

using namespace std;

int n;
int cost[16][16];
int dp[16][1<<16]={0,};

int tsp(int start,int visit){
	int min = 987987987;
	if(dp[start][visit]!=0) return dp[start][visit];
	if(visit == (1<<n)-1){
		if(cost[start][0]==0) return min;
		else return cost[start][0];
	}
	for(int i=1; i<n; i++){
		if((visit & (1<<i))==0 && cost[start][i]!=0){
			if(cost[start][i]<min){
				int tem = cost[start][i]+tsp(i,visit|(1<<i));
				if(tem<min) min = tem;
			}
		}
	}
	dp[start][visit] = min;
	return min;
}

int main(){
	scanf("%d",&n);
	int * cities = new int[n];
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			scanf("%d",&cost[i][j]);
		}
	}
	int visit = 1;
	printf("%d\n",tsp(0,visit));
	system("pause");
}