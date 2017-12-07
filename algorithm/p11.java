package algorithm;

public class p11 {
	public int[][] lengths = {{99,5,8,99,99,99,99,99},
	                   {99,99,4,99,4,99,99,99},
	                   {99,99,99,2,99,99,5,99},
	                   {99,99,99,99,99,99,7,7},
	                   {1,99,99,99,99,99,99,99},
	                   {99,6,99,99,2,99,99,99},
	                   {99,99,99,3,99,8,99,99},
	                   {99,99,99,99,99,5,4,99}}; //거리가 무한인 길이는 99로 표현
	pqueue visits = new pqueue();
	int minlength=99;
	public class node{
		String travel="";
		node[] child = null;
		int length;
		int bound;
		int level;
		int last;
		node parent;
		node left;
		node right;
		node next;
		node(int b,int level){
			this.bound = b;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.level = level;
			this.next = null;
		}
	}
	public class pqueue{ //bound가 작은 노드 순서대로 방문하기 위해 우선순위 queue를 사용
		int size=0;
		node head;
		node tail;
		pqueue(){
			head = new node(0,0);
			tail = new node(0,0);
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
			else{ //queue에 노드를 추가하면 동시에 bound가 가장 작은 노드가 맨 앞으로 가도록 정렬
				node curnode=tail.parent;
				node cur_next;
				while(curnode.parent!=null && n.bound<curnode.bound){
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
	public int bounds(node n,int level,int last){
		int bound_=0;
		int[] cities = {0,1,1,1,1,1,1,1,1}; //방문한 city는 0으로 표현
		String travel = n.travel;
		for(int i=0; i<travel.length()-1; i++){ //지나온 path들의 길이들을 모두 더함
			bound_ += lengths[Integer.parseInt(travel.charAt(i)+"")-1][Integer.parseInt(travel.charAt(i+1)+"")-1];
			cities[Integer.parseInt(travel.charAt(i)+"")-1] = 0;
		}
		cities[last-1] = 0;
		for(int j=0; j<8; j++){
			if(j==last-1){ //마지막으로 방문한 city에서 갈 수 있는 최소 길이를 더함
				bound_ += minlength(j,cities);
			}
			else if(cities[j]==1){ //방문하지 않은 city들의 길이를 더함
				if(minlength(j,cities)>lengths[j][0]){ //출발점으로 돌아가는 경우
					bound_ += lengths[j][0];
				}
				else{ //출발점이 아닌 다른 city로 가는 경우
					bound_ += minlength(j,cities);
				}
			}
		}
		return bound_;
	}
	public void visit(){
		node root = new node(0,1);
		root.travel="1";
		root.last=1;
		root.bound = bounds(root,1,1);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){ //queue에 bound가 작은 순서대로 node를 추가하고 앞에서부터 하나씩 방문하면서 solution을 구함
			curnode = visits.remove();
			level = curnode.level;
			if(level<=7 && curnode.bound<minlength){ 
				System.out.println("travel : "+curnode.travel+" bound:"+curnode.bound+" level:"+curnode.level);
				curnode.child = new node[8]; //노드마다 8개의 child노드를 만듬
				for(int i=0; i<8; i++){
					if(lengths[curnode.last-1][i] < 99 && nottravel(i,curnode.travel)){ //해당 노드에서 갈 수 있는 child 노드들을 방문함
						curnode.child[i] = new node(0,level+1);
						curnode.child[i].travel = curnode.travel+(i+1);
						curnode.child[i].last = i+1;
						curnode.child[i].bound = bounds(curnode.child[i],level+1,curnode.child[i].last);
						if(curnode.child[i].bound<99) visits.add(curnode.child[i]); //child 노드가 promising인 경우 queue에 추가함
					}
				}
				if(level==7) minlength=curnode.bound; //마지막 레벨에 도달하면 minlength를 갱신
			} 
		}
		System.out.println("minimun length : "+minlength); //city들을 순회하는 최소 거리를 출력
	}
	public boolean nottravel(int child,String travel){ //child노드를 방문한적이 있는지 없는지 반환
		for(int i=0; i<travel.length(); i++){
			if(Integer.parseInt(travel.charAt(i)+"")==child+1) return false;
		}
		return true;
	}
	public int minlength(int start,int[] cities){ //한 city에서 다른 city들 까지의 최소 거리를 반환
		int min=99;
		for(int i=0; i<8; i++){
			if(cities[i]==1){
				if(lengths[start][i]<min) min=lengths[start][i];
			}
		}
		return min;
	}
	public static void main(String[] args){
		p11 test = new p11();
		test.visit();
	}
}
