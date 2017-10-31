
public class Movement {
	
	private int vertical, horizontal;

	public Movement(int[] moves) {
		this.vertical = moves[0];
		this.horizontal = moves[1];
	}

	public int[] getNorthMovement(Tractor t){
		int x = t.getX();
		int y = t.getY();
		int[] northSuccessor = new int[2];
		if(x != 0){
			northSuccessor[0] = x - 1;
			northSuccessor[1] = y;
		}
		return northSuccessor;
	}
	
	public int[] getWestMovement(Tractor t){
		int x = t.getX();
		int y = t.getY();
		int[] westSuccessor = new int[2];
		if(y != 0){
			westSuccessor[0] = x;
			westSuccessor[1] = y - 1;
		}
		return westSuccessor;
	}
	
	public int[] getEastMovement(Tractor t, Field f){
		int x = t.getX();
		int y = t.getY();
		int[] eastSuccessor = new int[2];
		if(y != f.getColumn() - 1){
			eastSuccessor[0] = x;
			eastSuccessor[1] = y + 1;
		}
		return eastSuccessor;
	}
	
	public int[] getSouthMovement(Tractor t, Field f){
		int x = t.getX();
		int y = t.getY();
		int[] southSuccessor = new int[2];
		if(x != f.getRow() - 1){
			southSuccessor[0] = x + 1;
			southSuccessor[1] = y;
		}
		return southSuccessor;
	}
	
	@Override
	public String toString() {
		return "Movement [" + vertical + ", " + horizontal + "]";
	}

}
