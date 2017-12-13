/**
 * 
 * @author Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio
 * Description: This class represents the movements that the tractor can effectuate in the given field.
 */
public class Movement{
	
	private int vertical, horizontal;

	public Movement(int[] moves) {
		this.vertical = moves[0];
		this.horizontal = moves[1];
	}

	public Movement() {
	}

	/**
	 * Description: This method finds the position of the north successor
	 * @param tractorPosition Array containing the current position of the tractor
	 * @return It returns the position of the north successor
	 */
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
	
	/**
	 * Description: This method finds the position of the west successor
	 * @param tractorPosition Array containing the current position of the tractor
	 * @return It returns the position of the west successor
	 */
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
	
	/**
	 * Description: This method finds the position of the east successor
	 * @param tractorPosition Array containing the current position of the tractor
	 * @param f We pass the field to identify the columns and rows  
	 * @return It returns the position of the east successor
	 */
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
	
	/**
	 * Description: This method finds the position of the south successor
	 * @param tractorPosition Array containing the current position of the tractor
	 * @param f We pass the field to identify the columns and rows  
	 * @return It returns the position of the south successor
	 */
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
	
	//getters and setters
	public int getVertical() {
		return vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setVertical(int x) {
		this.vertical = x;
	}

	public void setHorizontal(int y) {
		this.horizontal = y;		
	}
	
	//toStrings
	public String toString() {
		return "Movement [" + vertical + ", " + horizontal + "]";
	}

}
