#include <iostream>
#include <cstdio>
#include <string>
#include <stack>

using namespace std;

int is_operator(char ch);
double calculator(string input);
double read_operand(string * input);
void show(stack<double> st1, stack<char> st2);

int main() {
	string input;
	cin >> input;
	cout << calculator(input) << endl;
	system("pause");
}
double calculator(string input){
	stack<double> operands;
	stack<char> operators;
	double res = 0;
	double input_operand;
	char input_operator;
	while(1){
		if(input.at(0)=='('){
			int index = 1;
			int count=1;
			while(index<input.size()){
				if(input.at(index)==')'){
					count--;
					if(count==0) break;
				}
				else if(input.at(index)=='(') count++;
				index++;
			}
			string inner = input.substr(1,index-1);
			string outer = input.substr(index+1,input.size());
			char temp[100];
			sprintf(temp,"%lf",calculator(inner));
			input = temp+outer;
		}
		input_operand = read_operand(&input);
		operands.push(input_operand);
		if(input.compare("end")==0){
			while(!operators.empty()){
				double back = operands.top();
				operands.pop();
				double front = operands.top();
				operands.pop();
				char oper = operators.top();
				operators.pop();
				double tem;
				if(oper == '+') tem = front + back;
				else if(oper == '-') tem = front - back;
				else if(oper == '*') tem = front * back;
				else tem = front / back;
				printf("# %lf %c %lf\n",front,oper,back);
				operands.push(tem);
			}
			res = operands.top();
			return res;
		}
		input_operator = input.at(0);
		input = input.substr(1,input.size());
		if(input_operator == '+' || input_operator == '-'){
			while(!operators.empty()){
				double back = operands.top();
				operands.pop();
				double front = operands.top();
				operands.pop();
				char oper = operators.top();
				operators.pop();
				double tem;
				if(oper == '+') tem = front + back;
				else if(oper == '-') tem = front - back;
				else if(oper == '*') tem = front * back;
				else tem = front / back;
				printf("# %lf %c %lf\n",front,oper,back);
				operands.push(tem);
			}
			operators.push(input_operator);
		}
		else if(input_operator == '*' || input_operator == '/'){
			while(!operators.empty()){
				if(operators.top() == '+' ||operators.top() == '-') break;
				double back = operands.top();
				operands.pop();
				double front = operands.top();
				operands.pop();
				char oper = operators.top();
				operators.pop();
				double tem;
				if(oper == '*') tem = front * back;
				else tem = front / back;
				printf("# %lf %c %lf\n",front,oper,back);
				operands.push(tem);
			}
			operators.push(input_operator);
		}
	}
}
double read_operand(string * input){
	string str="";
	double res;
	int index = 0;
	while(1){
		if(index >= (input->size())){
			*input = "end";
			res = atof(str.c_str());
			return res;
		}
		if(index!=0 && is_operator(input->at(index))) break;
		if(input->at(index)=='('){
			res = -1;
			(*input).at(0) = '*';
			return res;
		}
		str = str + input->at(index);
		index++;
	}
	res = atof(str.c_str());
	*input = input->substr(index,input->size());
	return res;
}
int is_operator(char ch){
	return ch=='+' || ch=='-' || ch=='*' || ch=='/';
}
void show(stack<double> st1, stack<char> st2){
	printf("operands : ");
	while(!st1.empty()){
		printf("%f ",st1.top());
		st1.pop();
	}
	printf("\noperators : ");
	while(!st2.empty()){
		printf("%c ",st2.top());
		st2.pop();
	}
	printf("\n");
}