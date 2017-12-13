import java.util.List;

/**
 * 
 * @author Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio
 * Description: This class is used for creating problem objects in which we can declare the state space of the problem and if there exists a solution
 * of that given problem
 */
public class Problem {
	private StateSpace action;
	private Field field;
	private int k;
	
	public Problem (StateSpace action, Field field, int k){
		this.action = action;
		this.field = field;
		this.k = k;
	}//end Problem
	
	/**
	 * Description: This method checks if the problem has a final solution or not.
	 * @param f We pass a field object, or also known as a state, to check if there is a final state for the problem.
	 * @return This method returns a boolean value depending on if the problem has a solution or not.
	 */
	public boolean isGoalState(Field f){		 
		return action.isGoal(f, k);
	}//end isGoalState
	
	/**
	 * Description: This method generates a list of node successors, given a parent node.
	 * @param parent A parent node is passed, so we can produce its successors.
	 * @param state A field as state is passed
	 * @param m We pass the movement of the tractor
	 * @param tractorPosition We pass the current position of the tractor in the field
	 * @param strategy We pass an int to determine the type of search strategy we want to implement
	 * @param maxDepth We pass the maximum depth that we want to explore in a given problem
	 * @return It returns a list of successors every time we insert a parent node.
	 */
	public List<Node> successors(Node parent, int[] tractorPosition, int strategy, int maxDepth){
		return action.successors(parent, tractorPosition, strategy, maxDepth);
	}//end successors
	
	
	//Getters and setters
	public Field getField() {
		return field;
	}//end getField
	
	public StateSpace getAction() {
		return action;
	}//end getAction
}//end class
