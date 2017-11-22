import java.util.List;

public class Problem {
	private StateSpace action;
	private Field field;
	private int k;
	
	public Problem (StateSpace action, Field field, int k){
		this.action = action;
		this.field = field;
		this.k = k;
	}	
	
	public boolean isGoalState(Field f){		 
		return action.isGoal(f, k);
	}

	public StateSpace getAction() {
		return action;
	}	
	
	public List<Node> successors(Node parent, Field state, Movement m, int[] tractorPosition, int strategy, int maxDepth){
		return action.successors(parent, state, m, tractorPosition, strategy, maxDepth);
	}

	public Field getField() {
		return field;
	}	
	
}
