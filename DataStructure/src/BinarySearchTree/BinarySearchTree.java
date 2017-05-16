package BinarySearchTree;

import Stack.Stack;

public class BinarySearchTree<E> {
	Node root = null;
	BinarySearchTree()
	{
		this.root = null;
	}
	public class Node{
		public Node parent;
		public Node left;
		public Node right;
		public int key;
		public E value;
		Node(int key,E value)
		{
			this.parent = null;
			this.left = null;
			this.right = null;
			this.key = key;
			this.value = value;
		}
		public boolean isexternal(Node node)
		{
			return (node.left == null && node.right == null);
		}
	}
	int height(Node root)
	{
		if(root == null) return 0;
		else if(root.left == null && root.right == null) return 0;
		else
		{
			if(height(root.left) >= height(root.right)) return height(root.left)+1;
			else return height(root.right)+1;
		}
	}
	int depth(Node root)
	{
		if(root.parent == null) return 0;
		else return depth(root.parent)+1;
	}
	Node search(Node root, int key)
	{
		if(root.key == key)
			return root;
		else if(root.key > key)
		{
			if(root.left == null)
			{				
				return root;
			}
			else return search(root.left,key);
		}
		else
		{
			if(root.right == null)
			{
				return root;
			}
			else return search(root.right,key);
		}
	}
	Node leftmost(Node root)
	{
		if(root.left == null) return root;
		else return leftmost(root.left);
	}
	Node rightmost(Node root)
	{
		if(root.right == null) return root;
		else return rightmost(root.right);
	}
	Object get(int key)
	{
		Node getnode = search(this.root,key);
		if(getnode.key == key) return getnode.value;
		else return null;
	}
	void put(int key,E value)
	{
		Node newnode = new Node(key,value);
		if(this.root == null) root = new Node(key,value); //처음으로 input을 받는 경우
		else
		{
			Node curnode = search(this.root,key); //key 값을 가진 노드를 찾음
			if(curnode.key == newnode.key) //이미 key 값의 노드가 존재
			{
				newnode.value = value; //value를 대체
			}
			else if(curnode.key > newnode.key) //key 값을 가진 노드가 없음 -> left에 새로운 노드를 만듬
			{
				newnode.parent = curnode;
				curnode.left = newnode;
			}
			else if(curnode.key < newnode.key) //right에 새로운 노드를 만듬
			{
				newnode.parent = curnode;
				curnode.right = newnode;
			}
		}
	}
	E remove(int key)
	{
		Node removenode = search(this.root,key);
		if(removenode.key == key) //remove하려는 key가 존재
		{
			if(removenode.left == null && removenode.right == null) //removenode의 child가 없는 경우
			{
				if(removenode.parent.key > removenode.key) //removenode가 left child인 경우
					removenode.parent.left = null;
				else //removenode가 right child인 경우
					removenode.parent.right = null;
				return removenode.value;
			}
			else if(removenode.left == null & removenode.right != null) //left child만 없는 경우
			{
				if(removenode.parent.key > removenode.key) //removenode가 left child인 경우
				{
					removenode.parent.left = removenode.right;
					removenode.right.parent = removenode.parent;
				}
				else //removenode가 right child인 경우
				{
					removenode.parent.right = removenode.right;
					removenode.right.parent = removenode.parent;
				}
				return removenode.value;
			}
			else if(removenode.left != null && removenode.right == null) //right child만 없는 경우
			{
				if(removenode.parent.key > removenode.key) //removenode가 left child인 경우
				{
					removenode.parent.left = removenode.left;
					removenode.left.parent = removenode.parent;
				}
				else //removenode가 right child인 경우
				{
					removenode.parent.right = removenode.left;
					removenode.left.parent = removenode.parent;
				}
				return removenode.value;
			}
			else //child가 둘 다 있는 경우
			{
				E removevalue = removenode.value;
				Node leftmostnode = leftmost(removenode.right);
				Node rightmostnode = rightmost(removenode.left);
				if(depth(leftmostnode) >= depth(rightmostnode)) //지우려는 노드의 바로 전 노드와 바로 다음 노드중 depth가 큰 노드를 찾음
				{
					removenode.key = leftmostnode.key;
					removenode.value = leftmostnode.value;
					if(leftmostnode.right != null) //leftmostnode가 child를 가진 경우
					{
						leftmostnode.parent.left = leftmostnode.right;
						leftmostnode.right.parent = leftmostnode.parent;
						/**if(leftmostnode.parent.key > leftmostnode.right.key) //leftmostnode가 leftchild인 경우
						{
							leftmostnode.parent.left = leftmostnode.right;
							leftmostnode.right.parent = leftmostnode.parent;
						}
						else //leftmostnode가 rightchild인 경우
						{
							leftmostnode.parent.right = leftmostnode.right;
							leftmostnode.right.parent = leftmostnode.parent;
						}**/
					}
				}
				else
				{
					removenode.key = rightmostnode.key;
					removenode.value = rightmostnode.value;
					if(rightmostnode.left != null) //rightmostnode가 child를 가진 경우
					{
						rightmostnode.parent.right = rightmostnode.left;
						rightmostnode.left.parent = rightmostnode.parent;
						/**if(rightmostnode.parent.key > rightmostnode.left.key) //rightmostnode가 leftchild인 경우
						{
							rightmostnode.parent.left = rightmostnode.left;
							rightmostnode.left.parent = rightmostnode.parent;
						}
						else //rightmostnode가 rightchild인 경우
						{
							rightmostnode.parent.right = rightmostnode.left;
							rightmostnode.left.parent = rightmostnode.parent;
						}**/
					}
				}
				return removevalue;
			}
		}
		else return null;
	}
	Node firstentry()
	{
		return leftmost(root);
	}
	Node lastentry()
	{
		return rightmost(root);
	}
	Node floorentry(int key)
	{
		Node curnode = root;
		if(leftmost(root).key > key) return null;
		while(true)
		{
			if(curnode.right == null) return curnode;
			if(curnode.key == key) return curnode;
			else if(curnode.key > key)
			{
				curnode = curnode.left;
				if(curnode.key < key) return curnode;
			}
			else
			{
				if(curnode.right.key > key) return curnode;
				curnode = curnode.right;
			}
		}
	}
	Node seilingentry(int key)
	{
		Node curnode = root;
		if(rightmost(root).key < key) return null;
		while(true)
		{
			if(curnode.left == null) return curnode;
			if(curnode.key == key) return curnode;
			else if(curnode.key > key)
			{
				if(curnode.left.key < key) return curnode;
				curnode = curnode.left;
			}
			else
			{
				curnode = curnode.right;
				if(curnode.key > key) return curnode;
			}
		}
	}
	void printtree()
	{
		print(this.root);
	}
	void print(Node root)
	{
		if(root == null) return;
		else
		{
			print(root.left);
			System.out.println(root.value);
			print(root.right);
		}
	}
	void inordertraversal(Node root)
	{
		Stack<Node> s = new Stack<Node>(); //노드를 저장할 스택
		Node curnode = root;
		if(curnode == null) //빈 트리인 경우
		{
			return;
		}
		while(!s.isEmpty() || curnode != null) //모든 노드를 한번씩 체크
		{
			if(curnode != null) //leftmost 노드까지 내려가면서 스택에 노드를 차례대로 저장
			{
				s.push(curnode);
				curnode = curnode.left;
			}
			else //leftmost 노드에 도달하면 leftmost 노드를 스택에서 빼면서 체크하고 right를 스택에 저장
			{
				Node n = s.pop();
				System.out.println(n.value);
				curnode = n.right;
			}
		}
		
	}
	public static void main(String[] args)
	{
		BinarySearchTree<Object> tree = new BinarySearchTree<Object>();
		tree.put(5,5);
		tree.put(2,2);
		tree.put(4,4);
		tree.put(1,1);
		tree.put(3,3);
		tree.put(6,6);
		tree.put(7,7);
		tree.put(0,0);
		tree.remove(5);
		tree.remove(0);
		tree.printtree();
		//tree.inordertraversal(tree.root);
		System.out.println(tree.floorentry(5).value);
	}
}
