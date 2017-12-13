import java.util.*;

/**
 * 
 * @author  Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio 
 * Description: this class is used for doing everything related to the successors and the state space
 */
public class StateSpace{
	private int northSand, westSand, eastSand, southSand;
	private Movement move;
	@SuppressWarnings("unused")
	private StateSpace singleAction;
	private static List<StateSpace> actions;

	//Constructor
	public StateSpace(int northSand, int westSand, int eastSand, int southSand) {
		this.northSand = northSand;
		this.westSand = westSand;
		this.eastSand = eastSand;
		this.southSand = southSand;
	}

	//Constructor
	public StateSpace(Movement move, List<StateSpace> action) {
		this.move = move;
		StateSpace.actions = action;
	}

	//Constructor
	public StateSpace(Movement move, StateSpace singleAction){
		this.move = move;
		this.singleAction = singleAction;
	}
	
	//Constructor
	public StateSpace() {
		
	}

	/**
	 * Description: this method calls the method 'recursiveActions' in order to generate all possible actions given the amount sand
	 * @param tractorPosition Current position of the tractor
	 * @param f Field for generating the actions of that field
	 * @param m Movement for evaluating the actions
	 */
	public static void generateActions(int[] tractorPosition, Field f, Movement m){
		int[] elements = possibilities(tractorPosition, f);
		int n = 4;
		int r = elements.length;
		actions = new ArrayList<StateSpace>();

		recursiveActions(elements, "", n, r, f, tractorPosition, m);
	}//end generateActions

	/**
	 * Description: recursive method for generating permutations with repetition for generating all possible combinations of sand
	 * @param elem Array containing the number to form combinations of sand
	 * @param act string for transforming the number in order to generate combinations
	 * @param n all possible directions (north, south, east and west)
	 * @param r size of the array of all possible elements for creating generations
	 * @param f field object
	 * @param tractorPosition current position of the field
	 * @param m Movement for evaluating the actions
	 */
	public static void recursiveActions(int[] elem, String act, int n, int r, Field f, int[] tractorPosition, Movement m) {
		int integer;
		int[] next = null;
		StateSpace na;
		if (n == 0) {
			integer = Integer.parseInt(act);
			next = splitNumbers(integer);
			if(validActions(next, f, tractorPosition, m)){
				na = new StateSpace(next[0], next[1], next[2], next[3]);
				actions.add(na);
			}//end if
		} else {
			for (int i = 0; i < r; i++) {
				recursiveActions(elem, act + elem[i], n - 1, r, f, tractorPosition, m);
			}//end for
		}//end if
	}//end recursiveActions

	/**
	 * Description: This method creates all the possible combinations obtained from the difference of sand that exceeds a current position
	 * @param tractorPosition Current position of the tractor
	 * @param f Field object
	 * @return Returns an array of ints containing all possible values and distributions of sand
	 */
	public static int[] possibilities(int[] tractorPosition, Field f){
		int difference;
		difference = f.getDifference(tractorPosition);
		int [] possibilities = new int[difference + 1];
		for(int i = 0; i < possibilities.length; i++){
			possibilities[i] = difference - i;
		}//end for
		return possibilities;
	}//end possibilities
	
	/**
	 * Description: This method gets the numbers for the combinations combined and separates them
	 * @param integer Combination of the four numbers
	 * @return It returns the array with the numbers separated
	 */
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
	}//end splitNumbers

	/**
	 * Description: This method discards actions that are not possible and checks if the successor is correct for use
	 * @param next Array with the numbers of sand possible for each direction
	 * @param f Field object
	 * @param tractorPosition Current position of the tractor
	 * @param m Movement for evaluating the actions
	 * @return It returns a boolean value that says if that action is possible or not
	 */
	public static boolean validActions(int[] next, Field f, int[] tractorPosition, Movement m){
		int difference = f.getDifference(tractorPosition);		

		if((next[0] + next[1] + next[2] + next[3]) != difference)
			return false;
		if(!f.checkSuccessors(next, tractorPosition, m))
			return false;

		return true;		
	}//end validActions

	/**
	 * Description: this method associates all the possible actions for each movement
	 * @param m Movement for evaluating the actions
	 * @param tractorPosition Current position of the tractor
	 * @param f Field object
	 * @return It returns a list containing the actions for each movement
	 */
	public static  List<StateSpace> actionsWithMovements(Movement m, int[] tractorPosition, Field f){
		List<StateSpace> actionsWithMoves = new ArrayList<StateSpace>();
		Movement mv = null;
//		System.out.println("\nThe list containing the valid actions is:");

		if(tractorPosition[0] != 0)//north
			actionsWithMoves.add(actionsForEachMovement(mv, m.getNorthMovement(tractorPosition)[0], m.getNorthMovement(tractorPosition)[1]));

		if(tractorPosition[1] != 0)//west
				actionsWithMoves.add(actionsForEachMovement(mv, m.getWestMovement(tractorPosition)[0], m.getWestMovement(tractorPosition)[1]));
		
		if(tractorPosition[1] != f.getColumn() - 1)//east
			actionsWithMoves.add(actionsForEachMovement(mv, m.getEastMovement(tractorPosition, f)[0], m.getEastMovement(tractorPosition, f)[1]));
		
		if(tractorPosition[0] != f.getRow() - 1)//south
				actionsWithMoves.add(actionsForEachMovement(mv, m.getSouthMovement(tractorPosition, f)[0], m.getSouthMovement(tractorPosition, f)[1]));
			
//		printActions(actionsWithMoves);
		return actionsWithMoves;		
	}//end actionsWithMovements

	/**
	 * Description: this method is responsible for generating the actions for a given movement
	 * @param mv Movement for generating the actions
	 * @param hor Horizontal vector of the movement
	 * @param ver Vertical vector of the movement
	 * @return It returns the movement with its actions
	 */
	public static StateSpace actionsForEachMovement(Movement mv, int hor, int ver){
		StateSpace action;
		int[] moves = new int[2];
		moves[0] = hor;
		moves[1] = ver;
		mv = new Movement(moves);
		action = new StateSpace(mv, actions);
		return action;		
	}//end actionsForEachMovement

	/**
	 * Description: this method is in charge of assigning to an auxiliary field the new amount of sand by trying the generated actions
	 * @param m Movement object for getting the directions
	 * @param a List of actions
	 * @param f Field object
	 * @param tractorPosition Current position of the tractor
	 * @return It returns a list of possible fields
	 */
	public static List<Field> tryActions(Movement m, List<StateSpace> a, Field f, int[] tractorPosition){
		StateSpace auxAction, act;
		List<Field> fieldList= new ArrayList<Field>();
		Field auxField;
		for(int i = 0; i < a.size(); i++){
			auxAction = a.get(i);
			for(int j = 0; j < auxAction.getActions().size(); j++){
				int[][] possible = createPossibleField(f);
				possible[tractorPosition[0]][tractorPosition[1]] = possible[tractorPosition[0]][tractorPosition[1]] - f.getDifference(tractorPosition);
				act = auxAction.getActions().get(j);
				if(act.getNorthSand() != 0)
					possible[m.getNorthMovement(tractorPosition)[0]][m.getNorthMovement(tractorPosition)[1]] += act.getNorthSand();		

				if(act.getWestSand() != 0)
					possible[m.getWestMovement(tractorPosition)[0]][m.getWestMovement(tractorPosition)[1]] += act.getWestSand();	

				if(act.getEastSand() != 0)
					possible[m.getEastMovement(tractorPosition, f)[0]][m.getEastMovement(tractorPosition, f)[1]] += act.getEastSand();

				if(act.getSouthSand() != 0)
					possible[m.getSouthMovement(tractorPosition, f)[0]][m.getSouthMovement(tractorPosition, f)[1]] += act.getSouthSand();

				auxField = new Field(f.getColumn(), f.getRow(), possible, f.getK(), f.getMax());
				fieldList.add(auxField);

//				System.out.println(mv.toString());
//				for(int x = 0; x < possible.length; x++){
//					for(int y = 0; y < possible[x].length; y++){
//						System.out.print("|" + possible[x][y]);
//					}
//					System.out.print("|\n");
//				}
			}//end for
		}//end for
//		System.out.print("\n");

		return fieldList;		
	}//end tryActions

	/**
	 * Description: This method is in charge of generating the successors of a parent node
	 * @param parent Node that is going to be explored
	 * @param state Field object of the parent
	 * @param strategy Int that represents a strategy selected from the menu
	 * @param maxDepth Int that represents the maximum depth that the user wants selected from the menu
	 * @return It returns a list containing all the successors of a node
	 */
	public List<Node> successors(Node parent, int[] tractorPosition, int strategy, int maxDepth){
		Field state = parent.getState();
		Movement m = parent.getAction().getMoves();
		generateActions(tractorPosition, state, m);
		List<StateSpace> actionList = actionsWithMovements(m, tractorPosition, state);
		List<Node> successors = new ArrayList<Node>();
		List<Field> fieldList = tryActions(m, actionList, state, tractorPosition);
		StateSpace auxAction, auxAction2;
		Movement auxMovement;
		Node node;
		Field auxField;
		for(int i = 0 ; i < actionList.size(); i++){
			auxAction = actionList.get(i);
			auxMovement = new Movement();
			for(int j = 0; j < auxAction.getActions().size(); j++){
				auxMovement = auxAction.getMoves();
				auxAction2 = new StateSpace(auxMovement, auxAction.getActions().get(j));
				auxField = fieldList.get(j);
				node = new Node(parent, auxField, strategy, auxAction2);
				if(node.getDepth() <= maxDepth) {			
						successors.add(node);					
				}//end if
			}//end for
		}//end for

		return successors;	
	}//end successors

	/**
	 * Description: this method is in charge of creating an auxiliary field in order to not to overwrite the original field we are dealing with 
	 * @param f Field object
	 * @return It returns a copy of the passed field to work with it
	 */
	public static int[][] createPossibleField(Field f){
		int[][] possible = new int[f.getField().length][f.getField().length];
		for(int x = 0; x < f.getField().length; x++){
			for(int y = 0; y < f.getField()[x].length; y++){
				possible[x][y] = f.getField()[x][y];
			}
		}
		return possible;
	}
	
	/**
	 * Description: this method is in charge of checking if a given field has a possible solution, by checking if all the amounts of sand have
	 * been uniformly distributed
	 * @param field Field object
	 * @param k Desired amount in each cell of the field
	 * @return It returns a boolean value saying if there is a solution or not
	 */
	public boolean isGoal(Field field, int k){		
		for(int i=0; i<field.getField().length; i++){
			for(int j=0; j<field.getField()[i].length; j++){				
				if(field.getField()[i][j] != k)
					return false;
			}//end for
		}//end for
		return true;
	}//end isGoal
	
	/**
	 * Description: this method calculates the total sand distributed for each direction
	 * @return It returns the total amount of sand
	 */
	public int totalSand() {
		int sand[] = new int [4];
		sand[0] = this.getActions().get(0).getActions().get(0).getNorthSand();
		sand[1] = this.getActions().get(0).getActions().get(0).getWestSand();
		sand[2] = this.getActions().get(0).getActions().get(0).getEastSand();
		sand[3] = this.getActions().get(0).getActions().get(0).getSouthSand();
		return sand[0] + sand[1] + sand[2] + sand[3];
	}//end totalSand

	
	//Getters and setters
	public int getNorthSand() {
		return northSand;
	}

	public int getWestSand() {
		return westSand;
	}

	public int getEastSand() {
		return eastSand;
	}

	public int getSouthSand() {
		return southSand;
	}

	public Movement getMoves() {
		return move;
	}

	public void setMoves(Movement moves) {
		this.move = moves;
	}

	public List<StateSpace> getActions() {
		return actions;
	}
	
	
	//toStrings
	public String toString() {
		return "\nAction [northSand=" + northSand + ", westSand=" + westSand + ", eastSand=" + eastSand + ", southSand="
				+ southSand + "]";
	}

//	public static void printActions(List<StateSpace> actionsWithMoves){
//		for(int i = 0; i < actionsWithMoves.size(); i++)
//			System.out.println(actionsWithMoves.get(i).printWithMoves() + "\n");
//	}
	
//	public String printWithMoves(){
//		return move.toString() + actions.toString();
//	}


}
