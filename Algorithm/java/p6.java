package algorithm;

public class p6 {
	public static void main(String[] args){ //50개의 element를 breath first 알고리즘과 best first 알고리즘으로 실행시켜서 방문 노드수와 걸리는 시간을 비교
											//breath first 알고리즘은 level 별로 모든 promising 노드를 방문하고 best first 알고리즘은 bound가 큰 순서대로 노드를 방문하기 때문에
											//best first 알고리즘이 breath first 알고리즘보다 방문 노드수가 적고 걸리는 시간이 작음
		System.out.println("Breath first");
		long starts = System.currentTimeMillis();
		breath_first breath = new breath_first();
		System.out.println("Node count : "+breath.count);
		long ends = System.currentTimeMillis();
		System.out.println("Time : "+(ends-starts));
		System.out.println("Best first");
		starts = System.currentTimeMillis();
		best_first best = new best_first();
		System.out.println("Node count : "+best.count);
		ends = System.currentTimeMillis();
		System.out.println("Time : "+(ends-starts));
	}
}
class breath_first {
	int[] profit = {0,459,300,343,192,235,92,135,44,215,252,164,280,117,304,74,324,35,272,198,128,62,30,87,140,189,156,50,72,92,198,168,140,19,162,85,128,75,56,39,72,33,20,81,64,42,30,35,12,3,8};
	int[] weight = {0,9,6,7,4,5,2,3,1,5,6,4,7,3,8,2,9,1,8,6,4,2,1,3,5,7,6,2,3,4,9,8,7,1,9,5,8,5,4,3,6,3,2,9,8,6,5,7,3,1,4};
	int[] pw = {0,51,50,49,48,47,46,45,44,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2};
	int max_profit=0;
	int total_profit=0;
	int total_weight=0;
	int w=80;
	int nodes=50;
	queue visits = new queue();
	int count=0;
	public class node{
		int profit;
		int weight;
		int bound;
		int level;
		node parent;
		node left;
		node right;
		node next;
		node(int p,int w,int b,int level){
			this.profit = p;
			this.weight = w;
			this.bound = b;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.level = level;
			this.next = null;
		}
	}
	public class queue{
		int size=0;
		node head;
		node tail;
		queue(){
			head = new node(0,0,0,0);
			tail = new node(0,0,0,0);
		}
		void add(node n){
			if(size==0){
				head=n;
				tail=n;
			}
			else{
				tail.next = n;
				n.parent = tail;
				tail = n;
			}
			size++;
		}
		node remove(){
			size--;
			node removenode = head;
			if(size==0){
				head = new node(0,0,0,0);
				tail = new node(0,0,0,0);
			}
			else{
				head = head.next;
				head.parent = null;
			}
			return removenode;
		}
	}
	breath_first(){
		visit();
	}
	void visit(){
		node root = new node(0,0,0,0);
		root.bound = bounds(root,0);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){
			curnode = visits.remove();
			level = curnode.level;
			if(curnode.bound>max_profit && curnode.weight<=w){
				if(curnode.profit>=max_profit) max_profit = curnode.profit;
				if(level<=nodes-1){
					curnode.left = new node(curnode.profit+profit[level+1],curnode.weight+weight[level+1],0,level+1);
					curnode.left.bound = bounds(curnode.left,level+1);
					visits.add(curnode.left);
					curnode.right = new node(curnode.profit,curnode.weight,0,level+1);
					curnode.right.bound = bounds(curnode.right,level+1);
					visits.add(curnode.right);
				}
			}
			count++;
			//System.out.println("profit:"+curnode.profit+" weight:"+curnode.weight+" bound:"+curnode.bound+" max_profit:"+max_profit+" level:"+curnode.level);
		}
		System.out.println("max_profit : "+max_profit);
	}
	
	public int bounds(node n,int level){
		int bound_=n.profit;
		int weight_=n.weight;
		if(level==nodes){
			return n.profit;
		}
		if(weight_>w){
			return 0;
		}
		for(int i=level+1; i<=nodes; i++){
			if(weight_+weight[i] <= w){
				bound_ += profit[i];
				weight_ += weight[i];
			}
			else{
				bound_ += (w-weight_)*pw[i];
				break;
			}
		}
		return bound_;
	}
}
class best_first {
	int[] profit = {0,459,300,343,192,235,92,135,44,215,252,164,280,117,304,74,324,35,272,198,128,62,30,87,140,189,156,50,72,92,198,168,140,19,162,85,128,75,56,39,72,33,20,81,64,42,30,35,12,3,8};
	int[] weight = {0,9,6,7,4,5,2,3,1,5,6,4,7,3,8,2,9,1,8,6,4,2,1,3,5,7,6,2,3,4,9,8,7,1,9,5,8,5,4,3,6,3,2,9,8,6,5,7,3,1,4};
	int[] pw = {0,51,50,49,48,47,46,45,44,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2};
	int max_profit=0;
	int total_profit=0;
	int total_weight=0;
	int w=80;
	pqueue visits = new pqueue();
	int count=0;
	int nodes=50;
	
	public class node{
		int profit;
		int weight;
		int bound;
		int level;
		node parent;
		node left;
		node right;
		node next;
		node(int p,int w,int b,int level){
			this.profit = p;
			this.weight = w;
			this.bound = b;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.level = level;
			this.next = null;
		}
	}
	public class pqueue{
		int size=0;
		node head;
		node tail;
		pqueue(){
			head = new node(0,0,0,0);
			tail = new node(0,0,0,0);
			head.next=tail;
			tail.parent=head;
		}
		void add(node n){
			if(size==0){
				head.next=n;
				n.parent=head;
				n.next=tail;
				tail.parent=n;
			}
			else{
				node curnode=tail.parent;
				node cur_next;
				while(curnode.parent!=null && n.bound>curnode.bound){
					curnode=curnode.parent;
				}
				cur_next=curnode.next;
				curnode.next.parent=n;
				curnode.next=n;
				n.parent=curnode;
				n.next=cur_next;
			}
			size++;
		}
		node remove(){
			size--;
			node removenode = head.next;
			if(size==0){
				head.next=tail;
				tail.parent=head;
			}
			else{
				head.next = head.next.next;
				head.next.parent=head;
			}
			return removenode;
		}
	}
	best_first(){
		visit();
	}
	void visit(){
		node root = new node(0,0,0,0);
		root.bound = bounds(root,0);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){
			curnode = visits.remove();
			level = curnode.level;
			if(curnode.bound>max_profit && curnode.weight<=w){
				if(curnode.profit>=max_profit) max_profit = curnode.profit;
				if(level<=nodes-1){
					curnode.left = new node(curnode.profit+profit[level+1],curnode.weight+weight[level+1],0,level+1);
					curnode.left.bound = bounds(curnode.left,level+1);
					visits.add(curnode.left);
					curnode.right = new node(curnode.profit,curnode.weight,0,level+1);
					curnode.right.bound = bounds(curnode.right,level+1);
					visits.add(curnode.right);
				}
			}
			count++;
			//System.out.println("profit:"+curnode.profit+" weight:"+curnode.weight+" bound:"+curnode.bound+" max_profit:"+max_profit+" level:"+curnode.level);
		}
		System.out.println("max_profit : "+max_profit);
	}
	
	public int bounds(node n,int level){
		int bound_=n.profit;
		int weight_=n.weight;
		if(level==nodes){
			return n.profit;
		}
		if(weight_>w){
			return 0;
		}
		for(int i=level+1; i<=nodes; i++){
			if(weight_+weight[i] <= w){
				bound_ += profit[i];
				weight_ += weight[i];
			}
			else{
				bound_ += (w-weight_)*pw[i];
				break;
			}
		}
		return bound_;
	}
}