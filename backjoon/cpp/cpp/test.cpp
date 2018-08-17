#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(){
	fstream fp;
	fp.open("test.txt",ios::in);
	char str[50];
	while(!fp.eof()){
		fp.read(str,sizeof(str));
		cout << str;
	}
	system("pause");
}