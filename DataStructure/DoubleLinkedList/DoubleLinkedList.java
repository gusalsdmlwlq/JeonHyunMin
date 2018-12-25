package DoubleLinkedList;

import javax.swing.JOptionPane;

public class DoubleLinkedList {
	Node head = new Node();
	Node tail = new Node();
	int size=0;
	
	public static class Node
	{
		Object data;
		Node next;
		Node prev;
		public Node() {
			this.next = null;
			this.prev = null;
			this.data = null;
		}
		public Node(int data){
			this(data, null, null);
		}
		public Node(int data, Node next, Node prev)
		{
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	private void add(int input)
	{
		Node cur = head;
		Node addnode = new Node(input);
		while(true)
		{
			if(head.next == tail || (cur == head && input <= (int)cur.next.data) || 
					cur.next == tail ||
					(cur != head && input >= (int)cur.data && input <= (int)cur.next.data))
			{
				addnode.next = cur.next;
				cur.next.prev = addnode;
				cur.next = addnode;
				addnode.prev = cur;
				break;
			}
			else
			{
				cur = cur.next;
			}
		}
		this.size++;
	}
	
	private Object remove(int index)
	{
		Object rdata;
		Node cur = head;
		if(index < 1 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=0; i<index-1; i++)
		{
			cur = cur.next;
		}
		rdata = cur.next.data;
		cur.next = cur.next.next;
		cur.next.next.prev = cur;
		this.size--;
		return rdata;
	}
	
	private Object remove_back(int index)
	{
		Object rdata;
		Node cur = tail;
		if(index < 1 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=size; i>index; i--)
		{
			cur = cur.prev;
		}
		rdata = cur.prev.data;
		cur.prev.prev.next = cur;
		cur.prev = cur.prev.prev;
		this.size--;
		return rdata;
	}
	
	private Object get(int index)
	{
		Object gdata;
		Node cur = head;
		if(index <= 0 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		gdata = cur.data;
		return gdata;
	}
	
	private Object get_back(int index)
	{
		Object gdata;
		Node cur = tail;
		if(index <= 0 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=size; i>index-1; i--)
		{
			cur = cur.prev;
		}
		gdata = cur.data;
		return gdata;
	}
	
	private Object set(int index, int input)
	{
		Node cur = head;
		Object predata;
		if(index <= 0 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		predata = cur.data;
		if(cur.data != null) cur.data = input; //빈 데이터는 set 불가
		return predata;
	}
	
	private Object set_back(int index, int input)
	{
		Node cur = tail;
		Object predata;
		if(index <= 0 || index > this.size) //예외
		{
			System.out.println("데이터 범위를 벗어났습니다.");
			return null;
		}
		for(int i=size; i>index-1; i--)
		{
			cur = cur.prev;
		}
		predata = cur.data;
		if(cur.data != null) cur.data = input; //빈 데이터는 set 불가
		return predata;
	}
	
	private void print()
	{
		Node cur = head;
		for(int i=0; i<this.size; i++)
		{
			cur = cur.next;
			if(cur.data != null) System.out.print(cur.data+" "); //빈 데이터는 print x
		}
		System.out.println("");
	}
	
	public static void main(String[] args)
	{
		boolean exit = true;
		DoubleLinkedList list = new DoubleLinkedList();
		String menu;
		list.head.next = list.tail;
		list.tail.prev = list.head;
		System.out.println("DoubleLinkedList");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6를 입력하세요.)\n1.add\n2.remove\n3.get\n4.set\n5.print\n6.exit");
			switch(menu)
			{
			case "1": //add
				int inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("add할 숫자를 입력하세요."));
					list.add(inputdata);
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
					if(removeindex >= list.size/2) removedata = list.remove_back(removeindex);
					else removedata = list.remove(removeindex);
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
					if(getindex >= list.size/2) getdata = list.get_back(getindex);
					else getdata = list.get(getindex);
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
					if(setindex >= list.size/2) predata = list.set_back(setindex,setdata);
					else predata = list.set(setindex,setdata);
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
