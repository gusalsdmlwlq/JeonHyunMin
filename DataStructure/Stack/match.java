package Stack;

import java.util.Scanner;

public class match {
	public static void main(String[] args)
	{
		Stack<Character> s = new Stack<Character>();
		Scanner scan = new Scanner(System.in);
		String input;
		input = scan.next();
		scan.close();
		int size = input.length();
		for(int i=0; i<size; i++)
		{
			char c = input.charAt(i);
			if(c == '(' || c == '{' || c == '[')
			{
				s.push(c);
			}
			else if(c == ')' || c =='}' || c ==']')
			{
				if(s.size() == 0)
				{
					System.out.println("False");
					return;
				}
				else if(!((c==')' && s.pop()=='(') || (c=='}' && s.pop()=='{') || (c==']' && s.pop()=='[')))
				{
					System.out.println("False");
					return;
				}
			}
		}
		if(s.isEmpty())
		{
			System.out.println("True");
			return;
		}
		else
		{
			System.out.println("False");
			return;
		}
	}
}
