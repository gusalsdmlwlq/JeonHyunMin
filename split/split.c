#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char ** split(char * str,char * del){
    char strs[100];
    static char * res[100];
    int count = 0;
    strcpy(strs,str);
    res[count] = strtok(strs,del);
    while(1){
        count++;
        if((res[count]=strtok(NULL,del))==NULL){
                break;
        }
    }
    return res;
}

int main()
{
    char str[] = "hello world test";
    char str2[100];
    char str3[100];
    char str4[100];
    strcpy(str2,split(str," ")[0]);
    strcpy(str3,split(str," ")[1]);
    strcpy(str4,split(str," ")[2]);
    printf("%s\n%s\n%s\n",str2,str3,str4);
    printf("%s\n",str);
    return 0;
}
