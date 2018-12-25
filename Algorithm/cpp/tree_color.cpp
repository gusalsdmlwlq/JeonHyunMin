#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <stdlib.h>
#include <math.h>

using namespace std;
vector<int> adj[100001];
int dp[100001][18]={0,};

//dp[i][j] = i를 j로 칠할때 최소값

double logB(double x, double base){
	return log(x)/log(base);
}

void visit(int parent, int child){ //이미 방문한 노드 체크
	for(int i=0; i<adj[child].size(); i++){
		if(adj[child].at(i)==parent) adj[child].at(i) = 0;
	}
}

int colors;

int dfs(int root,long long int color){ //return min
	int min;
	for(int i=1; i<=colors; i++){
		int cost=0;
		if(dp[root][i]!=0) continue;
		if((color&(long long int)(1<<(i-1)))==0) continue;
		for(int j=0; j<adj[root].size(); j++){
			min = 987987987;
			visit(root,adj[root].at(j));
			if(adj[root].at(j)==0) continue;
			if(adj[adj[root].at(j)].size() == 1){ //subtree가 노드하나인 경우
				for(int c=1; c<=colors; c++){
					if(c!=i){
						dp[adj[root].at(j)][c] = c; //칠할 수 있는 가장 작은 값을 칠하고 loop를 나옴
						min = dp[adj[root].at(j)][c];
						break;
					}
				}
			}
			else{ //subtree가 tree인 경우
				long long int rest = (long long int)((1<<colors)-1)-(long long int)(1<<(i-1));
				dfs(adj[root].at(j),rest); //i색을 빼고 재귀함수를 돌려 subtree의 dp를 갱신
				for(int c=1; c<=colors; c++){
					if(c!=i){ //그중에 i를 빼고 최소값을 구함
						if(dp[adj[root].at(j)][c] < min) min = dp[adj[root].at(j)][c];
					}
				}
			}
			cost += min;
		}
		dp[root][i] = cost+i;
	}
	min = 987987987;
	for(int i=1; i<=colors; i++){
		if(dp[root][i] < min && dp[root][i]!=0) min = dp[root][i];
	}
	return min;
}

int main() {
	int n;
	scanf("%d",&n);
	colors = logB(n,2);
	if(n > pow(2.0,(double)colors)) colors++;
	for(int i=0; i<n-1; i++){
		int p1,p2;
		scanf("%d %d",&p1,&p2);
		adj[p1].push_back(p2);
		adj[p2].push_back(p1);
	}
	long long int color = (1<<colors)-1;
	printf("%d\n",dfs(1,color));
	system("pause");
}