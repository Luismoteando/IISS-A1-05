import java.util.List;

public class Problem {
	private StateSpace action;
	private int [][] field;
	private int k;
	
	public Problem (StateSpace action, int [][]field, int k){
		this.action = action;
		this.field = field;
		this.k = k;
	}	
	
	public boolean isGoalState(int [][] f){		 
		return action.isGoal(f, k);
	}

	public StateSpace getAction() {
		return action;
	}	
	
	public List<Node> successors(Node parent, Movement m, Tractor t, Field f){
		return action.successors(parent, m, t, f);
	}

	public int [][] getField() {
		return field;
	}	
	
}
