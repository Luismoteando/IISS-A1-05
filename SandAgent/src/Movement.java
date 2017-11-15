
public class Movement {
	
	private int vertical, horizontal;

	public Movement(int[] moves) {
		this.vertical = moves[0];
		this.horizontal = moves[1];
	}

	public Movement() {
	}

	public int[] getNorthMovement(int[] tractorPosition){
		int x = tractorPosition[0];
		int y = tractorPosition[1];
		int[] northSuccessor = new int[2];
		if(x != 0){
			northSuccessor[0] = x - 1;
			northSuccessor[1] = y;
		}
		return northSuccessor;
	}
	
	public int[] getWestMovement(int[] tractorPosition){
		int x = tractorPosition[0];
		int y = tractorPosition[1];
		int[] westSuccessor = new int[2];
		if(y != 0){
			westSuccessor[0] = x;
			westSuccessor[1] = y - 1;
		}
		return westSuccessor;
	}
	
	public int[] getEastMovement(int[] tractorPosition, Field f){
		int x = tractorPosition[0];
		int y = tractorPosition[1];
		int[] eastSuccessor = new int[2];
		if(y != f.getColumn() - 1){
			eastSuccessor[0] = x;
			eastSuccessor[1] = y + 1;
		}
		return eastSuccessor;
	}
	
	public int[] getSouthMovement(int[] tractorPosition, Field f){
		int x = tractorPosition[0];
		int y = tractorPosition[1];
		int[] southSuccessor = new int[2];
		if(x != f.getRow() - 1){
			southSuccessor[0] = x + 1;
			southSuccessor[1] = y;
		}
		return southSuccessor;
	}
	
	public int getVertical() {
		return vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public String toString() {
		return "Movement [" + vertical + ", " + horizontal + "]";
	}

	public void setVertical(int x) {
		this.vertical = x;
	}

	public void setHorizontal(int y) {
		this.horizontal = y;		
	}

}
