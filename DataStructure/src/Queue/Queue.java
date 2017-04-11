package Queue;

import javax.swing.JOptionPane;

import Stack.Stack;

public class Queue extends LinkedList.LinkedList{
	Queue()
	{
		this.head.next = this.tail;
		this.tail.next = this.head;
	}
	public void enqueue(Object data)
	{
		addlast(data);
	}
	
	public Object dequeue()
	{
		return removefirst();
	}
	
	public Object first()
	{
		return head.next.data;
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
		Queue queue = new Queue();
		String menu;
		System.out.println("Queue");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6를 입력하세요.)\n1.enqueue\n2.dequeue\n3.first\n4.size\n5.isEmpty\n6.exit");
			switch(menu)
			{
			case "1": //enqueue
				Object inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("enqueue할 데이터를 입력하세요."));
					queue.enqueue(inputdata);
					System.out.println("enqueue : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				queue.print();
				break;
			case "2": //pop
				System.out.println("dequeue : "+queue.dequeue());
				queue.print();
				break;
			case "3": //top
				System.out.println("first : "+queue.first());
				queue.print();
				break;
			case "4": //size
				System.out.println("size : "+queue.size());
				queue.print();
				break;
			case "5": //isEmpty
				System.out.println(queue.isEmpty());
				queue.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}
