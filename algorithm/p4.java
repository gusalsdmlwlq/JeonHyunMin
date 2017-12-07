package algorithm;

public class p4 {
	int[] profit = {0,20,30,35,12,3}; //element들의 profit
	int[] weight = {0,2,5,7,3,1}; //element들의 weight
	int[] pw = {0,10,6,5,4,3}; //element들의 profit/weight
	int max_profit=0;
	int total_profit=0;
	int total_weight=0;
	int w=13;
	pqueue visits = new pqueue();
	
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
	public class pqueue{ //우선순위 queue
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
			else{ //queue에 노드를 추가하면 동시에 bound가 가장 큰 노드가 맨 앞으로 가도록 정렬
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
	p4(){
		visit();
	}
	void visit(){
		node root = new node(0,0,0,0);
		root.bound = bounds(root,0);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){ //queue에 bound가 큰 순서대로 node를 추가하고 앞에서부터 하나씩 방문하면서 solution을 구함
			curnode = visits.remove();
			level = curnode.level;
			if(curnode.bound>max_profit && curnode.weight<=w){
				if(curnode.profit>=max_profit) max_profit = curnode.profit;
				if(level<=4){ //노드를 방문하면 그 다음 element를 집는 경우와 집지 않는 2가지 경우의 노드를 queue에 추가함
					curnode.left = new node(curnode.profit+profit[level+1],curnode.weight+weight[level+1],0,level+1);
					curnode.left.bound = bounds(curnode.left,level+1);
					visits.add(curnode.left);
					curnode.right = new node(curnode.profit,curnode.weight,0,level+1);
					curnode.right.bound = bounds(curnode.right,level+1);
					visits.add(curnode.right);
				}
			}
			System.out.println("profit:"+curnode.profit+" weight:"+curnode.weight+" bound:"+curnode.bound+" max_profit:"+max_profit+" level:"+curnode.level);
			//방문하는 노드들을 출력
		}
		System.out.println("max_profit : "+max_profit); //최종으로 얻을 수 있는 profit을 출력
	}
	
	public int bounds(node n,int level){ //노드들의 bound를 구함
		int bound_=n.profit;
		int weight_=n.weight;
		if(level==5){
			return n.profit;
		}
		if(weight_>w){
			return 0;
		}
		for(int i=level+1; i<=5; i++){ //총 weight가 허용 무게인 w를 넘지 않도록 노드를 방문하며 bound를 갱신
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
	public static void main(String[] args){
		p4 test = new p4();
	}
}