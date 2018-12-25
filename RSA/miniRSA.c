/*
 * @file    rsa.c
 * @author  작성자 전현민 / 2014038304
 * @date    2018_11_21
 * @brief   mini RSA implementation code
 * @details 세부 설명
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "miniRSA.h"
#include <math.h>

uint p, q, e, d, n;

/*
 * @brief     모듈러 덧셈 연산을 하는 함수.
 * @param     uint a     : 피연산자1.
 * @param     uint b     : 피연산자2.
 * @param     byte op    : +, - 연산자.
 * @param     uint n      : 모듈러 값.
 * @return    uint result : 피연산자의 덧셈에 대한 모듈러 연산 값. (a op b) mod n
 * @todo      모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */
uint Mod(uint n, uint m){ // 나머지(%) 연산
	uint result = n;
	while(result >= m){
		result = result - m;
	}
	return result;
}
uint Div(uint n, uint d){ // 나누기(/) 연산
	uint count=0;
	while(n>=d){
		n = n-d;
		count++;
	}
	return count;
}

uint ModAdd(uint a, uint b, byte op, uint n) {
	uint result;
	a = Mod(a,n);
	b = Mod(b,n);
	if(op=='+'){
		if (a + b <= a) { //오버플로우가 발생하는 경우
			result = Mod((a - n + b),n);
		}
		else result = Mod(a+b,n);
	}
	else{
		if(a<b){ //음수가 되지 않게 조절
			result = Mod((n+a-b),n);
		}
		else result = Mod(a-b,n);
	}
    return result;
}

/*
 * @brief      모듈러 곱셈 연산을 하는 함수.
 * @param      uint x       : 피연산자1.
 * @param      uint y       : 피연산자2.
 * @param      uint n       : 모듈러 값.
 * @return     uint result  : 피연산자의 곱셈에 대한 모듈러 연산 값. (a x b) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */

uint Modmul(uint a,uint b,uint n){
	uint asq;
	a = Mod(a,n);
	b = Mod(b,n);
	if(a == 0 || b == 0) return 0; //a,b 둘다 0인 경우
    if(a == 1) return b; //a가 1인 경우
    if(b == 1) return a; //b가 1인 경우
	asq = Modmul(a, Div(b,2), n); //b를 반으로 나눠 재귀함수를 실행
	if((b & 1) == 0) return ModAdd(asq,asq,'+',n); //b가 짝수인 경우
	return ModAdd(a,ModAdd(asq, asq,'+',n),'+',n); //b가 홀수인 경우
}

/*
 * @brief      모듈러 거듭제곱 연산을 하는 함수.
 * @param      uint base   : 피연산자1.
 * @param      uint exp    : 피연산자2.
 * @param      uint n      : 모듈러 값.
 * @return     uint result : 피연산자의 연산에 대한 모듈러 연산 값. (base ^ exp) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
               'square and multiply' 알고리즘을 사용하여 작성한다.
 */

uint ModPow(uint base, uint exp, uint n) {
	uint result=1;
	uint index=0; //base의 지수
	uint pows = 1;
	uint * powers = (uint*)malloc(sizeof(uint)*(pows+1)); //base의 2^i승(i=1,2,3...31)...을 저장하기 위한 배열
	base = Mod(base,n);
	powers[pows] = Modmul(base,base,n);
	while(1){ //base의 2^i승을 계산해 배열에 저장함
		pows++;
		powers = (uint*)realloc(powers,sizeof(uint)*(pows+1)); //배열의 동적할당
		powers[pows] = Modmul(powers[pows-1],powers[pows-1],n); //배열의 전 값 
		if(pows==31) break; //base의 2^31승 까지 구함(32bit이기 때문)
		if((1<<(pows+1)) > exp) break; //base의 다음 2의 지수승을 구하면 exp보다 커지는 경우 멈춤
	}
	while(1){ //2^i승들을 조합해 exp와 같게 만들며 result를 갱신함
		if(index+(uint)(1<<pows) <= exp){ //base의 지수인 index에 2^i승들을 더해가며 exp와 같게 만듬
			index = index + (uint)(1<<pows);
			if(pows==0){
				result = Modmul(result,base,n); //2^0승인 경우 base를 곱함
				break;
			}
			else result = Modmul(result,powers[pows],n); //base의 2^i승을 곱함
		}
		pows--;
		if(index==exp) break;
	}
	free(powers);
    return result;
}

/*
 * @brief      입력된 수가 소수인지 입력된 횟수만큼 반복하여 검증하는 함수.
 * @param      uint testNum   : 임의 생성된 홀수.
 * @param      uint repeat    : 판단함수의 반복횟수.
 * @return     uint result    : 판단 결과에 따른 TRUE, FALSE 값.
 * @todo       Miller-Rabin 소수 판별법과 같은 확률적인 방법을 사용하여,
               이론적으로 4N(99.99%) 이상 되는 값을 선택하도록 한다. 
 */
uint randomRange(uint n1, uint n2){ //n1~n2중에서 무작위 수를 뽑는 함수 
	return Mod(rand(),(n2-n1+1)) + n1;
}

bool IsPrime(uint testNum, uint repeat) { //밀러-라빈 방식으로 소수를 판별
    bool result = 0;
	uint n = testNum-1;
	uint d=n;
	uint s=0;
	uint i,r;
	uint a;
	while(Mod(d,2)==0){ //n-1을 d * 2^s 형태로 만듬
		s++;
		d = d>>1;
	}
	for(i=0; i<repeat; i++){ 
		a = randomRange(1,testNum-1); //testNum보다 작고 1보다 크거나 같은 a를 무작위로 뽑음
		if(ModPow(a,d,testNum)==1) result = 1;
		else{
			for(r=0; r<s; r++){
				if(ModPow(a,d*(uint)pow(2.0,(int)r),testNum)==testNum-1) result = 1;
			}
		}
		if(result==0){ //한번의 경우라도 두 조건을 만족하지 못하면 0을 return(소수가 아님)
			return result;
		}
		//printf("repeat : %d  a: %d\n",i+1,a);
	}
	return result; //모든 경우에 조건을 만족하면 1을 return(소수임)
}

/*
 * @brief       모듈러 역 값을 계산하는 함수.
 * @param       uint a      : 피연산자1.
 * @param       uint m      : 모듈러 값.
 * @return      uint result : 피연산자의 모듈러 역수 값.
 * @todo        확장 유클리드 알고리즘을 사용하여 작성하도록 한다.
 */
uint ModInv(uint a, uint m) {
    uint result;
	uint x[10000],y[10000];
	uint r[10000],q[10000];
	int i=2;
	x[0] = 1; x[1] = 0; y[0] = 0; y[1] = 1; r[0] = a; r[1] = m;
	while(1){
		q[i] = Div(r[i-2],r[i-1]);
		r[i] = Mod(r[i-2],r[i-1]);
		x[i] = ModAdd(x[i-2],Modmul(q[i],x[i-1],m),'-',m);
		y[i] = ModAdd(y[i-2],Modmul(q[i],y[i-1],m),'-',m);
		if(r[i]==0){ //나머지가 0이 된 경우 result를 할당하고 멈춤
			result = x[i-1];
			break;
		}
		i++;
	}
	result = Mod(result,m);
	return result; //역수를 return
}

uint GCD(uint a, uint b) {
    uint prev_a;
    while(b != 0) {
        //printf("GCD(%u, %u)\n", a, b);
        prev_a = a;
        a = b;
        while(prev_a >= b) prev_a -= b;
        b = prev_a;
    }
    //printf("GCD(%u, %u)\n\n", a, b);
    return a;
}

/*
 * @brief     RSA 키를 생성하는 함수.
 * @param     uint *p   : 소수 p.
 * @param     uint *q   : 소수 q.
 * @param     uint *e   : 공개키 값.
 * @param     uint *d   : 개인키 값.
 * @param     uint *n   : 모듈러 n 값.
 * @return    void
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */

void showbits(uint num){ //uint를 2진수로 출력해주는 함수
	uint i = 1<<31;
	int count;
	for(count=0; count<32; count++){
		if(num & i) printf("1");
		else printf("0");
		if((count+1)%8==0) printf(" ");
		i = i>>1;
	}
	printf("\n");
}

void miniRSAKeygen(uint *p, uint *q, uint *e, uint *d, uint *n) {
	//p,q,n 생성
	uint gcd_n; //파이(n)을 저장할 변수
	uint temp; //32bit의 e를 만들기 위해 임시로 사용할 변수
	while(1){
		while(1){
			*p = rand();
			*p = *p|(1<<15); //rand는 16번째 bit를 결정하지 못하므로 p의 16번째 bit를 1로 바꿔줌
			if(IsPrime(*p,10)) break; //p가 소수이면 멈추고 아닌 경우 p를 다시 생성함
		}
		while(1){
			*q = rand();
			*q = *q|(1<<15); //q의 16번째 bit를 1로 바꿔줌
			if(IsPrime(*q,10)) break; //p가 소수이면 멈추고 아닌 경우 p를 다시 생성함
		}
		*n = (*p)*(*q);
		if(*n & (1<<31)) break; //n이 2^31보다 크거나 같으면 멈추고 아닌경우 다시 p,q,n을 생성함
	}
	//e,d 생성
	gcd_n = ((*p)-1)*((*q)-1); //파이(n) = (p-1)*(q-1)
	while(1){
		//32bit e 생성
		temp = rand(); //e의 상위 15bit를 무작위로 생성
		if(randomRange(0,100)<=50) temp = temp|(1<<15); //e의 32번째 bit를 무작위로 결정
		*e = temp<<16; //만들어둔 16개의 bit를 상위 16bit로 shift함
		temp = rand(); //e의 하위 15bit를 무작위로 생성
		if(randomRange(0,100)<=50) temp = temp|(1<<15); //e의 16번째 bit를 무작위로 결정
		*e = (*e)|temp; //상위 16bit와 하위 16bit를 합쳐 e를 생성함
		// e조건 검사
		if(*e>1 && *e<gcd_n && GCD(gcd_n,*e)==1) break; //조건을 만족하는 e를 만든경우 멈추고 아닌경우 e를 다시 만듬
	}
	//e^-1인 d 생성
	*d = ModInv(*e,gcd_n); //e의 역수인 d를 생성함
}

/*
 * @brief     RSA 암복호화를 진행하는 함수.
 * @param     uint data   : 키 값.
 * @param     uint key    : 키 값.
 * @param     uint n      : 모듈러 n 값.
 * @return    uint result : 암복호화에 결과값
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */
uint miniRSA(uint data, uint key, uint n) {
	uint result;
	printf("input data : %u\n",data);
	result = ModPow(data,key,n); //지수 계산을 해서 output을 만듬
    printf("output data : %u\n",result);
	return result;
}

int main(int argc, char* argv[]) {

	byte plain_text[4] = {0x12, 0x34, 0x56, 0x78};
    uint plain_data, encrpyted_data, decrpyted_data;
    uint seed = time(NULL);
	int tem;
	int num=10;
	int start,end;
    memcpy(&plain_data, plain_text, 4);

	start = time(NULL);
    // 난수 생성기 시드값 설정
    InitWELLRNG512a(&seed);
	srand(seed);
    // RSA 키 생성
    miniRSAKeygen(&p, &q, &e, &d, &n);
    //p = 54623; q = 62189; e = 3152570619; d = 3028275219; n = 3396949747;
	printf("0. Key generation is Success!\n ");
    printf("p : %u\n q : %u\n e : %u\n d : %u\n N : %u\n", p, q, e, d, n);
	printf(" p : ");showbits(p); //p,q,e,d,n의 2진수 bit를 확인
	printf(" q : ");showbits(q);
	printf(" e : ");showbits(e);
	printf(" d : ");showbits(d);
	printf(" n : ");showbits(n);
	printf("\n p(%u) * q(%u) = n(%u)\n",p,q,n);
	printf(" e(%u) ^ -1 mod <p-1(%u) * q-1(%u)> = d(%u)\n\n",e,p-1,q-1,ModInv(e,(p-1)*(q-1))); //d가 e의 역수인지 검증

    // RSA 암호화 테스트
    encrpyted_data = miniRSA(plain_data, e, n);
    printf("1. plain text : %u\n", plain_data);    
    printf("2. encrypted plain text : %u\n\n", encrpyted_data);

    // RSA 복호화 테스트
    decrpyted_data = miniRSA(encrpyted_data, d, n);
    printf("3. cipher text : %u\n", encrpyted_data);
    printf("4. Decrypted plain text : %u\n\n", decrpyted_data);

    // 결과 출력
    printf("RSA Decryption: %s\n", (decrpyted_data == plain_data) ? "SUCCESS!" : "FAILURE!");
	end = time(NULL);
	printf("%d sec\n",end-start);
	system("pause");
    return 0;
}