#include <iostream>
#include <cstdio>  // uncomment to use scanf/printf instead of cin/cout
#include <sstream>
#include <string>

using namespace std;

int main() {
	int map[1001]={0,};
    int n;
    scanf("%d",&n);
	fflush(stdin);
	for(int i=0; i<n; i++) {
		char str[20];
		gets(str);
		int pre=0;
		char * tok = strtok(str," ");
        while(tok!=NULL) {  // read forks from a note
            // @todo Write your code here.
			int num = atoi(tok);
					if(pre != 0){
						if(pre>=num){
								cout << "-1" << endl;
								system("pause");
								return 0;
						}
						int tem = map[pre];
						if(tem!=0 && tem!=-1 && num!=tem){
							cout << "-1" << endl;
							system("pause");
							return 0;
						}
						if(map[num]==0) map[num]=-1;
						map[pre] = num;
					}
					else{
						if(map[num]==0) map[num]=-1;
					}
					pre = num;
					tok = strtok(NULL," ");
        }
        // @todo Write your code here.
    }
	for(int i=1; i<=1001; i++){
		if(map[i]==-1) cout << i << endl;
	}
	system("pause");
    // @todo Write your code here.
    return 0;
}