package Stack;

import javax.swing.JOptionPane;

public class compute {
	public static double operation(double num1, double num2, String operator)
	{
		switch(operator)
		{
		case "+":
			return num1+num2;
		case "-":
			return num1-num2;
		case "*":
			return num1*num2;
		case "/":
			if(num2 == 0) return 0;
			return num1/num2;
		default:
			return 0;
		}
	}
	public static void main(String args[])
	{
		Stack operands = new Stack();
		Stack operators = new Stack();
		Object operand = null;
		Object operator = null;
		double temp = 0;
		String result = "";
		while(true) //수식 입력
		{
			while(true) //피연산자
			{
				try
				{
					operand = (double)Integer.parseInt(JOptionPane.showInputDialog("숫자를 입력하세요."));
					operands.push(operand);
					System.out.print("operands : ");
					operands.print();
					System.out.print("operators : ");
					operators.print();
					result += operand;
					break;
				}
				catch(Exception e)
				{
					System.out.println("<Error>숫자를 입력하세요.");
					continue;
				}
			}
			while(true) //연산자
			{
				operator = JOptionPane.showInputDialog("연산자를 입력하세요.(+,-,*,/,=)");
				if(!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/") && !operator.equals("="))
				{
					System.out.println("<Error>연산자(+,-,*,/,=)를 입력하세요.");
					continue;
				}
				result += operator;
				switch((String)operator)
				{
				case "+":
					while(operators.top() != null)
					{
						temp = (double)operands.pop();
						operands.push(operation((double)operands.pop(),temp,(String)operators.top()));
						operators.pop();
					}
					operators.push(operator);
					break;
				case "-":
					while(operators.top() != null)
					{
						temp = (double)operands.pop();
						operands.push(operation((double)operands.pop(),temp,(String)operators.top()));
						operators.pop();
					}
					operators.push(operator);
					break;
				case "*":
					while(operators.top() != null && (operators.top().equals("*") || operators.top().equals("/")))
					{
						temp = (double)operands.pop();
						operands.push(operation((double)operands.pop(),temp,(String)operators.top()));
						operators.pop();
					}
					operators.push(operator);
					break;
				case "/":
					while(operators.top() != null && (operators.top().equals("*") || operators.top().equals("/")))
					{
						temp = (double)operands.pop();
						operands.push(operation((double)operands.pop(),temp,(String)operators.top()));
						operators.pop();
					}
					operators.push(operator);
					break;
				case "=":
					while(operators.top() != null)
					{
						temp = (double)operands.pop();
						operands.push(operation((double)operands.pop(),temp,(String)operators.top()));
						operators.pop();
					}
					operators.push(operator);
					break;
				}
				System.out.print("operands : ");
				operands.print();
				System.out.print("operators : ");
				operators.print();
				break;
			}
			if(operators.top().equals("="))
			{
				operators.pop();
				break;
			}
		}
		System.out.println(result+""+operands.pop());
	}
}