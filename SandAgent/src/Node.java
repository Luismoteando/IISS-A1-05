
public class Node implements Comparable<Node>{

	private Node parent;
	private Field state;
	private StateSpace action;
	private int cost, depth, value;

	public Node(Field state){
		this.state = state;
	}

	
	public Node(Node parent, Field state, int strategy, StateSpace action){
		this.parent = parent;
		this.state = state;
		this.action = action;
		this.cost = cost + 1;
		this.depth = depth + 1;
		if(strategy == 1)
			this.value = this.depth;
		if(strategy == 2 || strategy == 3 || strategy == 4)
			this.value = -(this.depth);
		if(strategy == 5)
			this.value = this.cost;
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
	public StateSpace getAction() {
		return action;
	}
	
	public void setAction(StateSpace action) {
		this.action = action;
	}	
	
	public Node getParent() {
		return parent;
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
