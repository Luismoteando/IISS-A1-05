
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
		if(parent.getParent() == null) {
			this.cost = 0;
			this.depth = 0;
		}else {
			this.cost = parent.getCost() + action.totalSand() + 1;
			this.depth = parent.getDepth() + 1;
		}
		if(strategy == 1)
			this.value = depth;
		if(strategy == 2 || strategy == 3 || strategy == 4)
			this.value = -depth;
		if(strategy == 5)
			this.value = cost;
		if(strategy == 6)
			this.value = cost + heuristic();
	}
	
	public int heuristic() {
		int heuristic = 0;
		int[][] field = state.getField();
		for (int i=0; i < field.length; i++) {
			for (int j=0; j < field[i].length; j++) {
				if(field[i][j] != state.getK())
					heuristic++;
			}
		}
		return heuristic;		
	}
	
	public String serialize() {
		String ID = "$" + this.getAction().getMoves().toString() + "#";
		
		for(int i = 0; i < state.getField().length; i++)
			for(int j = 0; j < state.getField()[i].length; j++)
				ID += state.getField()[i][j];
		return ID;
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
	
	public int getCost() {
		return cost;
	}
	
	public int getDepth() {
		return depth;
	}

	public int getValue() {
		return value;
	}

	public StateSpace getAction() {
		return action;
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
