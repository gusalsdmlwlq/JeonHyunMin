package Stack;

import javax.swing.JOptionPane;

public class compute {
	public static void main(String args[])
	{
		Stack operands = new Stack();
		Stack operators = new Stack();
		Stack compute = new Stack();
		Object operand = null;
		Object operator = null;
		int opers;
		int result = 0;
		String result_str = "";
		while(true) //수식 입력
		{
			while(true) //피연산자
			{
				try
				{
					operand = (Object)Integer.parseInt(JOptionPane.showInputDialog("숫자를 입력하세요."));
					operands.push((Object)operand);
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
				operators.push(operator);
				break;
			}
			if(operator.equals("="))
				break;
		}
		opers = operands.size();
		for(int i=0; i<opers; i++)
		{
			compute.push(operators.pop());
			System.out.print("Operators ");
			operators.print();
			System.out.print("Operands ");
			operands.print();
			compute.push(operands.pop());
			System.out.print("Operators ");
			operators.print();
			System.out.print("Operands ");
			operands.print();
		}
		result = (int)compute.pop();
		result_str += result;
		while(true)
		{
			operator = compute.pop();
			result_str += operator;
			operand = compute.pop();
			if(operand != null) result_str += operand;
			switch((String)operator)
			{
			case "+":
				result += (int)operand;
				break;
			case "-":
				result -= (int)operand;
				break;
			case "*":
				result *= (int)operand;
				break;
			case "/":
				result /= (int)operand;
				break;
			case "=":
				System.out.println("결과 : "+result_str+result);
				return;
			}
		}
	}
}
