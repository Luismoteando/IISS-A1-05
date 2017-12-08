
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
	

	public int getDifference(int[] tractorPosition){
		if((field[tractorPosition[0]][tractorPosition[1]] - k) < 0)
			return 0;
		return field[tractorPosition[0]][tractorPosition[1]] - k;
	}

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
	
	public String toString() {
		return "Field [column=" + column + ", row=" + row + ", k=" + k + ", max=" + max + "\n" + ", field="
				+ printField(field) + "]";
	}

	public String toString2(){
		return printField(field);
	}

}