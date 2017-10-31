import java.util.Random;

public class Node {

	Field state;
	int cost, depth, value;
	Random rn = new Random();
	
	public Node(Field state, int cost, int depth, Action action /*int value (de momento este valor es random*/) {
		this.state = state;
		this.cost = cost;
		this.depth = depth;
		this.value = rn.nextInt(1000);
		this.action = action;
	}
	public Node(){
		this.value = rn.nextInt(1000);
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
	//Qué es esto?
	Action action;
	
}
