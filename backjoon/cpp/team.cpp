#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

using namespace std;

int main(){
	int n,m;
	scanf("%d %d",&n,&m);
	int * score = new int[n];
	int ** dp = (int**)malloc(sizeof(int)*(m+1)*n);
	for(int i=1; i<=m; i++){
		dp[i] = (int*)malloc(sizeof(int)*n);
	}
	int temp;
	for(int i=0; i<n; i++){
		scanf("%d",&temp);
		score[i] = temp;
		if(i==0) dp[1][i] = temp;
		else dp[1][i] = dp[1][i-1]+temp;
	}
	int res;
	for(int i=2; i<=m; i++){
		for(int j=i-1; j<n; j++){
			res = INT_MAX;
			for(int k=j-1; k>=i-2; k--){
				temp = max(dp[i-1][k],dp[1][j]-dp[1][k]);
				if(temp < res) res = temp;
			}
			dp[i][j] = res;
		}
	}
	printf("%d\n",dp[m][n-1]);
	system("pause");
}