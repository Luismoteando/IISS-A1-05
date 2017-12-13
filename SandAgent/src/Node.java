/**
 * 
 * @author Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio
 * Description: This class is used for creating nodes, containing everything related to a given node (parent node, state, action, cost, depth
 * and value)
 */
public class Node implements Comparable<Node>{

	private Node parent;
	private Field state;
	private StateSpace action;
	private int cost, depth, value;

	//Constructor
	public Node(Field state){
		this.state = state;
	}//end Node
	
	//Constructor
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
		}//end if
		if(strategy == 1)
			this.value = depth;
		if(strategy == 2 || strategy == 3 || strategy == 4)
			this.value = -depth;
		if(strategy == 5)
			this.value = cost;
		if(strategy == 6)
			this.value = cost + heuristic();
	}//end Node
	
	/**
	 * Description: this method calculates the heuristic, in which we take the cells of the initial field that don't have the the same amount as the
	 * requested 'k' value.
	 * @return it returns the number resulting from the calculation of the heuristic.
	 */
	public int heuristic() {
		int heuristic = 0;
		int[][] field = state.getField();
		for (int i=0; i < field.length; i++) {
			for (int j=0; j < field[i].length; j++) {
				if(field[i][j] != state.getK())
					heuristic++;
			}//end for
		}//end for
		return heuristic;		
	}//end heuristic
	
	/**
	 * Description: this method converts a given state with its action to a string, so it can be easier to search in the priority queue
	 * @return returns a string containing the state along with its movement.
	 */
	public String serialize() {
		String ID = "$" + this.getAction().getMoves().toString() + "#";
		
		for(int i = 0; i < state.getField().length; i++)
			for(int j = 0; j < state.getField()[i].length; j++)
				ID += state.getField()[i][j];
		return ID;
	}//end serialize
	
	/**
	 * Description: this method is used for comparing two nodes depending on their values
	 */
	public int compareTo(Node node){
		if(this.getValue() < node.getValue())
			return -1;
		if(this.getValue() > node.getValue())
			return 1;
		return 0;		
	}//end compareTo

	//Getters and setters
	public Field getState() {
		return state;
	}//end getState
	
	public int getCost() {
		return cost;
	}//end getCost
	
	public int getDepth() {
		return depth;
	}//end getDepth

	public int getValue() {
		return value;
	}//end getValue

	public StateSpace getAction() {
		return action;
	}//end getAction
	
	public Node getParent() {
		return parent;
	}//end getParent

	
	//Tostrings 
	public String toString() {
		return "\nNode [state=" + state.toString() + ", action=" + action.toString() + ", cost=" + cost + ", depth=" + depth + ", value="
				+ value + "]";
	}//end toString
	
	public String toString2(){
		return "----------\nState = \n" + state.toString2() + action.toString() + "\nCost = " + cost + "\nDepth = " + depth + "\nValue = "
				+ value + "\n----------\n";
	}//end toString2
	
}
