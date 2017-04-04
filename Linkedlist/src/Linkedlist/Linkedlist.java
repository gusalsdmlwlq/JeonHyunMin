package Linkedlist;

import javax.swing.JOptionPane;

public class Linkedlist {
	int size;
	Node head = new Node();
	Node tail = new Node();
	public static class Node
	{
		Object data;
		Node next;
		Node()
		{
			this.data = null;
			this.next = null;
		}
		Node(Object data)
		{
			this(data,null);
		}
		Node(Object data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	private void add(int index, Object data)
	{
		Node cur = head;
		Node addnode = new Node(data);
		if(index > this.size)
		{
			for(int i=0; i<size; i++)
				cur = cur.next;
			addnode.next = cur.next;
			cur.next = addnode;
		}
		else if(index <= 0)
		{
			addnode.next = cur.next;
			cur.next = addnode;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			addnode.next = cur.next;
			cur.next = addnode;
		}
		if(addnode.next == tail)
			tail.next = addnode;
		this.size++;
	}
	private void addfirst(Object data)
	{
		Node addnode = new Node(data);
		addnode.next = head.next;
		head.next = addnode;
		if(addnode.next == tail)
			tail.next = addnode;
		this.size++;
	}
	private void addlast(Object data)
	{
		Node addnode = new Node(data);
		tail.next.next = addnode;
		addnode.next = tail;
		tail.next = addnode;
		this.size++;
	}
	private Object remove(int index)
	{
		Node cur = head;
		Object removedata;
		if(index <= 0 || index > size)
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			removedata = cur.next.data;
			cur.next = cur.next.next;
			this.size--;
			return removedata;
		}
	}
	private Object removefirst()
	{
		Object removedata;
		if(head.next == tail)
			return null;
		else
		{
			removedata = head.next.data;
			head.next = head.next.next;
			if(head.next == tail)
				tail.next = head;
			this.size--;
			return removedata;
		}
	}
	private Object removelast()
	{
		Node cur = head;
		Object removedata;
		if(tail.next == head)
			return null;
		else
		{
			removedata = tail.next.data;
			for(int i=0; i<size-1; i++)
				cur = cur.next;
			cur.next = tail;
			tail.next = cur;
			this.size--;
			return removedata;
		}
	}
	private Object get(int index)
	{
		Node cur = head;
		if(index <= 0 || index > size)
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		else
		{
			for(int i=0; i<index; i++)
				cur = cur.next;
			return cur.data;
		}
	}
	private Object set(int index, Object data)
	{
		Node cur = head;
		Object predata = null;
		if(index <= 0 || index > size)
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			predata = cur.next.data;
			cur.next.data = data;
			return predata;
		}
	}
	private void print()
	{
		Node cur = head;
		for(int i=0; i<this.size; i++)
		{
			cur = cur.next;
			System.out.print(cur.data+" ");
		}
		System.out.println("");
	}
	public static void main(String[] args)
	{
		boolean exit = true;
		Linkedlist list = new Linkedlist();
		String menu;
		list.head.next = list.tail;
		list.tail.next = list.head;
		System.out.println("Linkedlist");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6를 입력하세요.)\n1.add\n2.remove\n3.get\n4.set\n5.print\n6.exit");
			switch(menu)
			{
			case "1": //add
				int inputindex;
				Object inputdata;
				try
				{
					inputindex = Integer.parseInt(JOptionPane.showInputDialog("add할 위치를 입력하세요."));
					inputdata = (Object)Integer.parseInt(JOptionPane.showInputDialog("add할 데이터를 입력하세요."));
					list.add(inputindex,inputdata);
					System.out.println("add : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				break;
			case "2": //remove
				int removeindex;
				Object removedata;
				try
				{
					removeindex = Integer.parseInt(JOptionPane.showInputDialog("remove할 index를 입력하세요."));
					removedata = list.remove(removeindex);
					System.out.println("remove : "+removedata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				break;
			case "3": //get
				int getindex; 
				Object getdata;
				try
				{
					getindex = Integer.parseInt(JOptionPane.showInputDialog("get할 index를 입력하세요."));
					getdata = list.get(getindex);
					System.out.println("get : "+getdata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				break;
			case "4": //set
				int setindex, setdata;
				Object predata;
				try
				{
					setindex = Integer.parseInt(JOptionPane.showInputDialog("set할 index를 입력하세요."));
					setdata = Integer.parseInt(JOptionPane.showInputDialog("set할 data를 입력하세요."));
					predata = list.set(setindex,setdata);
					if(predata != null) System.out.println("set : "+predata+" -> "+setdata);
				}
				catch(Exception e)
				{
					System.out.println("숫자를 입력하세요.");
				}
				break;
			case "5": //print
				list.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}
