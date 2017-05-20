package Stack;

import javax.swing.JOptionPane;

public class Stack<E> extends LinkedList.LinkedList_<E>{
	public Stack()
	{
		//this.head.next = tail;
		//this.tail.next = head;
		this.head = new Node();
		this.tail = new Node();
	}

	public void push(E data)
	{
		addlast(data);
	}
	
	public E pop()
	{
		return removelast();
	}
	
	public E top()
	{
		//return tail.next.data;
		return tail.data;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public static void main(String args[])
	{
		boolean exit = true;
		Stack<Object> stack = new Stack<Object>();
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
