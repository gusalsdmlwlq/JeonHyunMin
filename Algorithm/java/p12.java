package algorithm;
import java.util.Collections;
import java.util.Vector;

public class p12 { //9개의 city들을 방문하는 최소 길이를 branch and bound 알고리즘과 dynamic 알고리즘으로 계산해서 걸리는 시간을 비교
					//dynamic 알고리즘은 계속해서 배열을 변경해가며 최소 길이를 갱신하기 때문에 city의 개수가 많아질수록 걸리는 시간이 점점 커짐
					//branch and bound 알고리즘은 bound를 계산하여 bound가 작은 노드들부터 방문하며 불필요한 수행을 없애기 때문에 city의 개수가 많아져도 dynamic 알고리즘의 경우보다 걸리는 시간이 작음
public class dynamic{
  public int N = 9;
  public int index;
  public Vector<Integer> V = new Vector<Integer>() ;
  private int cost; //이동한 거리
  private int cities; //순회한 city들의 수
  private int[] visited = new int[N]; //방문한 city들의 배열
  int [][]M = {{999,1,7,4,5,9,2,3,6},
		  {5,999,4,6,2,7,3,9,8},
		  {8,2,999,3,6,1,5,9,4},
		  {8,1,6,999,4,3,2,5,7},
		  {9,7,5,6,999,2,1,3,8},
		  {6,8,5,4,2,999,3,7,1},
		  {3,5,8,6,9,4,999,7,2},
		  {3,2,9,1,4,6,7,999,5},
		  {3,1,2,4,6,9,5,8,999}
		  };
  public dynamic() {
   cities = 1; 
         cost = 0;
         index = 0;
  }
  public void tsp(int cur) {   //cur : 현재 city의 index
   int i;
   if(cities == N && M[cur][0] != 0){  //지나간 city의 수가 N개이고 현재위치에서 0번째 index의 city로 가는 길이 있을 때
    V.addElement(cost + M[cur][0]);
    return;
   }
   for(i = 1; i<N ; ++i) {
    if(i==cur) {
     continue;
    }
    if(M[cur][i] != 999 && visited[i] == 0){ // cur번째 index의 city에서 i번째 city로 갈수있고 방문한적이 없을 때
     visited[i] = 1;  //i번째 index의 city에 방문함
     cities++;   //방문한 city의 수 증가
     cost+=M[cur][i]; //이동한 거리 증가
     tsp(i);    //i번째 index의 city로 이동
     cities--;
     visited[i] = 0;
     cost -=M[cur][i];    
    }   
   } 
  }
  public void getvalue() {
   Collections.sort(V); //vector를 오름차순으로 정렬함
   System.out.println("minimum length :"+ V.elementAt(0)); //city들을 순회하는 최소 거리를 출력
  }
}
class bound_branch {
	public int[][] lengths = {{999,1,7,4,5,9,2,3,6},
			  {5,999,4,6,2,7,3,9,8},
			  {8,2,999,3,6,1,5,9,4},
			  {8,1,6,999,4,3,2,5,7},
			  {9,7,5,6,999,2,1,3,8},
			  {6,8,5,4,2,999,3,7,1},
			  {3,5,8,6,9,4,999,7,2},
			  {3,2,9,1,4,6,7,999,5},
			  {3,1,2,4,6,9,5,8,999} //city사이의 길이 없는 경우 999로 표현
			  };
	pqueue visits = new pqueue();
	int minimumlength=999;
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
		int[] cities = {0,1,1,1,1,1,1,1,1,1}; //방문한 city는 0으로 표현
		String travel = n.travel;
		for(int i=0; i<travel.length()-1; i++){ //지나온 path들의 길이들을 모두 더함
			bound_ += lengths[Integer.parseInt(travel.charAt(i)+"")-1][Integer.parseInt(travel.charAt(i+1)+"")-1];
			cities[Integer.parseInt(travel.charAt(i)+"")-1] = 0;
		}
		cities[last-1] = 0;
		for(int j=0; j<9; j++){
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
			if(level<=8 && curnode.bound<minimumlength){
				//System.out.println("travel : "+curnode.travel+" bound:"+curnode.bound+" level:"+curnode.level);
				//수행 시간을 비교하기 위해 노드들의 출력은 하지 않음
				curnode.child = new node[9]; //노드마다 9개의 child노드를 만듬
				for(int i=0; i<9; i++){
					if(lengths[curnode.last-1][i] < 999 && nottravel(i,curnode.travel)){ //해당 노드에서 갈 수 있는 child 노드들을 방문함
						curnode.child[i] = new node(0,level+1);
						curnode.child[i].travel = curnode.travel+(i+1);
						curnode.child[i].last = i+1;
						curnode.child[i].bound = bounds(curnode.child[i],level+1,curnode.child[i].last);
						if(curnode.child[i].bound<999) visits.add(curnode.child[i]); //child 노드가 promising인 경우 queue에 추가함
					}
				}
				if(level==8) minimumlength=curnode.bound; //마지막 레벨에 도달하면 minlength를 갱신
			} 
		}
		System.out.println("minimun length : "+minimumlength); //city들을 순회하는 최소 거리를 출력
	}
	public boolean nottravel(int child,String travel){ //child노드를 방문한적이 있는지 없는지 반환
		for(int i=0; i<travel.length(); i++){
			if(Integer.parseInt(travel.charAt(i)+"")==child+1) return false;
		}
		return true;
	}
	public int minlength(int start,int[] cities){ //한 city에서 다른 city들 까지의 최소 거리를 반환
		int min=999;
		for(int i=0; i<9; i++){
			if(cities[i]==1){
				if(lengths[start][i]<min) min=lengths[start][i];
			}
		}
		return min;
	}
}
 public static void main(String[] argc){
	 p12 test = new p12();
	 System.out.println("Dynamic");
	 long starts = System.currentTimeMillis();
	 dynamic a = test.new dynamic();
	 a.tsp(0);
	 a.getvalue();
	 long ends = System.currentTimeMillis();
	 System.out.println("Time : "+(ends-starts));
	 System.out.println("Branch and Bound");
	 starts = System.currentTimeMillis();
	 bound_branch b = test.new bound_branch();
	 b.visit();
	 ends = System.currentTimeMillis();
	 System.out.println("Time : "+(ends-starts));
 }
}