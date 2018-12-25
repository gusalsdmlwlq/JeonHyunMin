package LinkedList;

import javax.swing.JOptionPane;
//(1)(=head)->(2)->...->()(=tail)
public class LinkedList_<E> {
	public int size=0;
	public Node head;
	public Node tail;
	public LinkedList_()
	{
		this.head = new Node();
		this.tail = new Node();
	}
	public class Node
	{
		public E data;
		public Node next;
		public Node()
		{
			this.data = null;
			this.next = null;
		}
		public Node(E data)
		{
			this.data = data;
			this.next = null;
		}
		public Node(E data,Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	public void add(int index, E data)
	{
		Node curnode = head;
		Node addnode = new Node(data);
		if(size==0)
		{
			head = addnode;
			tail = addnode;
		}
		else if(index > size)
		{
			for(int i=0; i<size-1; i++)
				curnode = curnode.next;
			curnode.next = addnode;
			tail = addnode;
		}
		else if(index <= 1)
		{
			addnode.next = head;
			head = addnode;
		}
		else
		{
			for(int i=0; i<index-2; i++)
				curnode = curnode.next;
			addnode.next = curnode.next;
			curnode.next = addnode;
		}
		size++;
	}
	public void addfirst(E data)
	{
		Node addnode = new Node(data);
		addnode.next = head;
		head = addnode;
		if(size==0)
			tail = addnode;
		size++;
	}
	public void addlast(E data)
	{
		Node addnode = new Node(data);
		tail.next = addnode;
		tail = addnode;
		if(size==0)
			head = addnode;
		size++;
	}
	public E remove(int index)
	{
		Node curnode = head;
		Node removenode;
		if(index > size || index <= 0)
			return null;
		else if(index == 1)
		{
			removenode = head;
			head = head.next;
		}
		else
		{
			for(int i=0; i<index-2; i++)
				curnode = curnode.next;
			removenode = curnode.next;
			curnode.next = curnode.next.next;
			if(index == size)
				tail = curnode;
		}
		size--;
		if(size==0)
		{
			head = new Node();
			tail = new Node();
		}
		return removenode.data;
	}
	public E removefirst()
	{
		if(size == 0)
			return null;
		E removedata;
		removedata = head.data;
		head = head.next;
		size--;
		if(size==0)
		{
			head = new Node();
			tail = new Node();
		}
		return removedata;
	}
	public E removelast()
	{
		if(size == 0)
			return null;
		Node curnode = head;
		E removedata;
		removedata = tail.data;
		for(int i=0; i<size-2; i++)
			curnode = curnode.next;
		tail = curnode;
		tail.next = null;
		size--;
		if(size==0)
		{
			head = new Node();
			tail = new Node();
		}
		return removedata;
	}
	public E get(int index)
	{
		Node curnode = head;
		if(index > size || index <= 0)
			return null;
		else
		{
			for(int i=0; i<index-1; i++)
				curnode = curnode.next;
			return curnode.data;
		}
	}
	public E set(int index, E data)
	{
		Node curnode = head;
		E predata;
		if(index > size || index <= 0)
			return null;
		else
		{
			for(int i=0; i<index-1; i++)
				curnode = curnode.next;
			predata = curnode.data;
			curnode.data = data;
			return predata;
		}
	}
	public void print()
	{
		Node curnode = head;
		for(int i=0; i<size; i++)
		{
			System.out.print(curnode.data+" ");
			curnode = curnode.next;
		}
		System.out.println("");
	}
	public static void main(String[] args)
	{
		boolean exit = true;
		LinkedList_<Object> list = new LinkedList_<Object>();
		String menu;
		System.out.println("LinkedList_");
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
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("add할 데이터를 입력하세요."));
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
