import java.util.Random;

public class Node implements Comparable<Node>{

	@SuppressWarnings("unused")
	private Node parent;
	private Field state;
	private Action action;
	private int cost, depth, value;
	private Random rn = new Random();

	public Node(Field state){
		this.state = state;
	}
	
	public Node(Node parent, Field state, int cost, int depth, Action action) {
		this.parent = parent;
		this.state = state;
		this.cost = cost;
		this.depth = depth;
		this.value = rn.nextInt(1000);
		this.action = action;
	}
	
	public int compareTo(Node node){
		if(this.getValue() < node.getValue())
			return -1;
		if(this.getValue() > node.getValue())
			return 1;
		return 0;		
	}
	
	public Field getState() {
		return state;
	}
	public void setState(Field state) {
		this.state = state;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}	
	
	public String toString() {
		return "\nNode [state=" + state.toString() + ", action=" + action.toString() + ", cost=" + cost + ", depth=" + depth + ", value="
				+ value + "]";
	}
	
	public String toString2(){
		return "----------\nState = \n" + state.toString2() + action.toString() + "\nCost = " + cost + "\nDepth = " + depth + "\nValue = "
				+ value + "\n----------\n";
	}
	
}
