import java.util.*;

//Leave sand
public class Action {
	private int northSand, westSand, eastSand, southSand;
	private Movement moves;
	private static List<Action> actions = new ArrayList<Action>();


	public Action(int northSand, int westSand, int eastSand, int southSand) {
		this.northSand = northSand;
		this.westSand = westSand;
		this.eastSand = eastSand;
		this.southSand = southSand;
	}

	public Action(Movement moves, List<Action> action) {
		this.moves = moves;
		Action.actions = action;
	}

	public static void generateActions(Tractor t, Field f, Movement m){
		int[] elementos = possibilities(t, f);
		int n = 4;                  //Tipos para escoger
		int r = elementos.length;   //Elementos elegidos

		recursiveActions(elementos, "", n, r, f, t, m);
	}//end generateActions

	public static void recursiveActions(int[] elem, String act, int n, int r, Field f, Tractor t, Movement m) {
		int integer;
		int[] next = null;
		Action na;
		if (n == 0) {
			integer = Integer.parseInt(act);
			next = splitNumbers(integer);
			if(validActions(next, f, t, m)){
				na = new Action(next[0], next[1], next[2], next[3]);
				actions.add(na);
			}
		} else {
			for (int i = 0; i < r; i++) {
				recursiveActions(elem, act + elem[i], n - 1, r, f, t, m);
			}
		}
	}//end Perm1

	public static int[] possibilities(Tractor t, Field f){
		int difference;
		difference = f.getDifference(t);

		if(f.getField()[t.getX()][t.getY()] < f.getK()){
			System.out.println("Trying to move sand from a box that is lesser than 'k' (The desired quantity)");
			System.exit(0);
		}
		int [] possibilities = new int[difference + 1];
		for(int i = 0; i < possibilities.length; i++){
			possibilities[i] = difference - i;
		}
		return possibilities;
	}
	public static int[] splitNumbers(int integer){
		int[] comb = new int[4];

		comb[3] = integer%10;

		integer = integer/10;
		comb[2] = integer%10;

		integer = integer/10;
		comb[1] = integer%10;

		integer = integer/10;
		comb[0] = integer%10;

		return comb;
	}

	public static boolean validActions(int[] next, Field f, Tractor t, Movement m){
		int difference = f.getDifference(t);		

		if((next[0] + next[1] + next[2] + next[3]) != difference)
			return false;
		//metodo para comprobar el maximo (cogemos los metodos get de los movimientos y vemos si su valor supera el maximo)
		//if()
		if(!f.checkSuccessors(next, t, m))
			return false;

		return true;		
	}
	public static  List<Action> actionsWithMovements(Movement m, Tractor t, Field f){
		List<Action> actionsWithMoves = new ArrayList<Action>();
		Action action = null;
		int[] moves = new int[2];
		Movement mv;
		System.out.println("\nThe list containing the valid actions is:");

		if(t.getX() != 0){			
			moves[0] = m.getNorthMovement(t)[0];
			moves[1] = m.getNorthMovement(t)[1];
			mv = new Movement(moves);
			action = new Action(mv, actions);
			actionsWithMoves.add(action);

		}if(t.getY() != 0){
			moves[0] = m.getWestMovement(t)[0];
			moves[1] = m.getWestMovement(t)[1];			
			mv = new Movement(moves);
			action = new Action(mv, actions);
			actionsWithMoves.add(action);

		}if(t.getY() != f.getColumn() - 1){
			moves[0] = m.getEastMovement(t, f)[0];
			moves[1] = m.getEastMovement(t, f)[1];
			mv = new Movement(moves);
			action = new Action(mv, actions);
			actionsWithMoves.add(action);

		}if(t.getX() != f.getRow() - 1){
			moves[0] = m.getSouthMovement(t, f)[0];
			moves[1] = m.getSouthMovement(t, f)[1];
			mv = new Movement(moves);
			action = new Action(mv, actions);
			actionsWithMoves.add(action);
		}		
		printActions(actionsWithMoves);	
		tryActions(m, actionsWithMoves, f, t);
		return actionsWithMoves;		
	}

	public static void tryActions(Movement m, List<Action> a, Field f, Tractor t){
		Action aux, ac;
		Movement mv;
		for(int i = 0; i < a.size(); i++){
			aux = a.get(i);
			mv = aux.getMoves();
			for(int j = 0; j < aux.getActions().size(); j++){
				int[][] possible = createPossibleField(f);
				possible[t.getX()][t.getY()] = possible[t.getX()][t.getY()] - f.getDifference(t);
				ac = aux.getActions().get(j);
				if(ac.getNorthSand() != 0)
					possible[m.getNorthMovement(t)[0]][m.getNorthMovement(t)[1]] = possible[m.getNorthMovement(t)[0]][m.getNorthMovement(t)[1]] + ac.getNorthSand();		

				if(ac.getWestSand() != 0)
					possible[m.getWestMovement(t)[0]][m.getWestMovement(t)[1]] = possible[m.getWestMovement(t)[0]][m.getWestMovement(t)[1]] + ac.getWestSand();	

				if(ac.getEastSand() != 0)
					possible[m.getEastMovement(t, f)[0]][m.getEastMovement(t, f)[1]] = possible[m.getEastMovement(t, f)[0]][m.getEastMovement(t, f)[1]] + ac.getEastSand();

				if(ac.getSouthSand() != 0)
					possible[m.getSouthMovement(t, f)[0]][m.getSouthMovement(t, f)[1]] = possible[m.getSouthMovement(t, f)[0]][m.getSouthMovement(t, f)[1]] + ac.getSouthSand();

				System.out.println(mv.toString());
				for(int x = 0; x < possible.length; x++){
					for(int y = 0; y < possible[x].length; y++){
						System.out.print("|" + possible[x][y]);
					}
					System.out.print("|\n");
				}
			}
		}//end for
		System.out.print("\n");		
	}

	public static int[][] createPossibleField(Field f){
		int[][] possible = new int[f.getField().length][f.getField().length];
		for(int x = 0; x < f.getField().length; x++){
			for(int y = 0; y < f.getField()[x].length; y++){
				possible[x][y] = f.getField()[x][y];
			}
		}
		return possible;
	}

	public int getNorthSand() {
		return northSand;
	}

	public void setNorthSand(int northSand) {
		this.northSand = northSand;
	}

	public int getWestSand() {
		return westSand;
	}

	public void setWestSand(int westSand) {
		this.westSand = westSand;
	}

	public int getEastSand() {
		return eastSand;
	}

	public void setEastSand(int eastSand) {
		this.eastSand = eastSand;
	}

	public int getSouthSand() {
		return southSand;
	}

	public void setSouthSand(int southSand) {
		this.southSand = southSand;
	}

	public Movement getMoves() {
		return moves;
	}

	public void setMoves(Movement moves) {
		this.moves = moves;
	}

	public List<Action> getActions() {
		return actions;
	}

	public static void setActions(List<Action> actions) {
		Action.actions = actions;
	}

	public static void printActions(List<Action> actionsWithMoves){
		for(int i = 0; i < actionsWithMoves.size(); i++)
			System.out.println(actionsWithMoves.get(i).printWithMoves() + "\n");
	}

	@Override
	public String toString() {
		return "\nAction [northSand=" + northSand + ", westSand=" + westSand + ", eastSand=" + eastSand + ", southSand="
				+ southSand + "]";
	}
	public String printWithMoves(){
		return moves.toString() + actions.toString();
	}


}
