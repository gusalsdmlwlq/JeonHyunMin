package Stack;

import javax.swing.JOptionPane;

public class Stack extends LinkedList.LinkedList {
	Stack()
	{
		this.head.next = this.tail;
		this.tail.next = this.head;
	}
	public void push(Object data)
	{
		addlast(data);
	}
	
	public Object pop()
	{
		return removelast();
	}
	
	public Object top()
	{
		return tail.next.data;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return (head.next == tail);
	}
	
	public static void main(String args[])
	{
		boolean exit = true;
		Stack stack = new Stack();
		String menu;
		System.out.println("Stack");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6를 입력하세요.)\n1.push\n2.pop\n3.top\n4.size\n5.isEmpty\n6.exit");
			switch(menu)
			{
			case "1": //push
				Object inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("push할 데이터를 입력하세요."));
					stack.push(inputdata);
					System.out.println("push : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				stack.print();
				break;
			case "2": //pop
				System.out.println("pop : "+stack.pop());
				stack.print();
				break;
			case "3": //top
				System.out.println("top : "+stack.top());
				stack.print();
				break;
			case "4": //size
				System.out.println("size : "+stack.size());
				stack.print();
				break;
			case "5": //isEmpty
				System.out.println(stack.isEmpty());
				stack.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}
