/**
 * 
 * @author Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio
 * Description: This class establishes the field for each state
 */
public class Field{

	private int column, row, k, max;
	private int [][] field;


	public Field(int column, int row, int[][] field, int k, int max) {
		this.column = column;
		this.row = row;
		this.field = field;
		this.k = k;
		this.max = max;
	}

	/**
	 * Description: this method checks if the successors of the next movement exist, and discards possible actions that are not possible.
	 * @param next Represents the array with the all possible permutations with repetition.
	 * @param tractorPosition Represents the current position of the tractor.
	 * @param m Represents the movement in which the tractor can go.
	 * @return It returns a boolean value, depending on whether the successor is possible or not.
	 */
	//checks if the successors exist
	public boolean checkSuccessors(int[] next, int[] tractorPosition, Movement m){
		int x = tractorPosition[0];
		int y = tractorPosition[1];
		if((x == 0 && next[0] != 0) || (x > 0 && max - field[m.getNorthMovement(tractorPosition)[0]][m.getNorthMovement(tractorPosition)[1]] < next[0]))//North
			return false;

		if((y == 0 && next[1] != 0) || (y > 0 && max - field[m.getWestMovement(tractorPosition)[0]][m.getWestMovement(tractorPosition)[1]] < next[1]))//West
			return false;

		if((y == column - 1 && next[2] != 0) || (y < column - 1 && max - field[m.getEastMovement(tractorPosition, this)[0]][m.getEastMovement(tractorPosition, this)[1]] < next[2]))//East
			return false;
		
		if((x == row - 1 && next[3] != 0) || (x < row - 1 && max - field[m.getSouthMovement(tractorPosition, this)[0]][m.getSouthMovement(tractorPosition, this)[1]] < next[3]))//South
			return false;

		return true;
	}	

	/**
	 * Description: it computes the difference between the current amount of sand and the desired amount 'k'.
	 * @param tractorPosition Represents the current position of the tractor in the field.
	 * @return It returns the difference that has been said previously.
	 */
	public int getDifference(int[] tractorPosition){
		if((field[tractorPosition[0]][tractorPosition[1]] - k) < 0)
			return 0;
		return field[tractorPosition[0]][tractorPosition[1]] - k;
	}

	//getters and setters
	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public int[][] getField() {
		return field;
	}

	public int getK(){
		return k;
	}

	public int getMax(){
		return max;
	}

	/**
	 * Description: in this method, we any given field
	 * @param field We pass the 2-dimension array to print it.
	 * @return It returns a string representing the field.
	 */
	public String printField(int[][] field){
		String print = "";
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++){
				print = print + "|" + field[i][j];								
			}//end for
			print = print + "|\n";
		}
		
		return print;
	}
	
	//toStrings
	public String toString() {
		return "Field [column=" + column + ", row=" + row + ", k=" + k + ", max=" + max + "\n" + ", field="
				+ printField(field) + "]";
	}

	public String toString2(){
		return printField(field);
	}

}