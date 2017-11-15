import java.io.*;
import java.util.*;

public class Main {
	static int x = 0, y = 0, k = 0, max = 0, column = 0,row = 0;
	static int[] prueba = {1, 2};
	static int [][] field;
	static Scanner scan;
	static Field f;
	static int[] tp = new int [2];
	static Movement m;
	static String str;	

	public static void main(String[] args) throws FileNotFoundException{

		storeValues();

		if(field.length != row || field[0].length != column){
			System.out.println("The length of the field doesn't correspond to the number of columns or rows");
			System.exit(0);
		}//end if

		System.out.println("Position of the tractor: [" + x + ", " + y + "]\n"
				+ "Optimum amount of sand per square (k): " + k + "\n"
				+ "Maxumum amount of sand per square (Max): "+ max + "\n"
				+"Dimensions of the field: " + column + " x " + row + "\n");

		printField();

		f = new Field(column, row, field, k, max);
		tp[0] = x;
		tp[1] = y;
		m = new Movement(prueba);
//		printSand();
		StateSpace.generateActions(tp, f, m);
//		compareOrderingTime();
		strategySelection();

	}//end main

	public static void storeValues() throws FileNotFoundException{

		scan = new Scanner(new FileReader("SandAgent.txt"));
		boolean aux = false;
		do{
			try{
				x = scan.nextInt();
				y = scan.nextInt();
				k = scan.nextInt();
				max = scan.nextInt();
				column = scan.nextInt();
				row = scan.nextInt();
				field= new int[column][row];
			}catch(Exception e){
				System.out.println("Error. There're invalid values.");
				System.exit(0);
			}
			aux = true;
		}while(!aux);

	}//end storeValues

	public static void printField(){

		scan.nextLine();
		System.out.println("Distribution of the field:");

		for(int i=0; i<field.length; i++){
			for(int j=0; j<field[i].length; j++){
				try{
					field[i][j] = scan.nextInt();
					if(field[i][j] > max){
						System.out.println("\nThere's a cell of the field with more quantity of sand than the max required.");
						System.exit(0);
					}
				}catch(Exception e){
					System.out.println("Error. There're invalid values.");
					System.exit(0);
				}
				System.out.print("|" + field[i][j]);									
			}//end for
			System.out.print("|\n");
		}//end for
	}//end printField

//	public static void printSand(){
//		System.out.println("\nList of successors:");
//
//		if(x != 0)
//			System.out.println("North: " + "[" + m.getNorthMovement(t)[0] + ", " + m.getNorthMovement(t)[1] + "]" + " // Sand amount: " + f.getField()[m.getNorthMovement(t)[0]][m.getNorthMovement(t)[1]]);
//		if(y != 0)
//			System.out.println("West: " + "[" + m.getWestMovement(t)[0] + ", " + m.getWestMovement(t)[1] + "]" + " // Sand amount: " + f.getField()[m.getWestMovement(t)[0]][m.getWestMovement(t)[1]]);
//		if(y != column - 1)
//			System.out.println("East: " + "[" + m.getEastMovement(t, f)[0] + ", " + m.getEastMovement(t, f)[1] + "]" + " // Sand amount: " + f.getField()[m.getEastMovement(t, f)[0]][m.getEastMovement(t, f)[1]]);
//		if(x != row - 1)
//			System.out.println("South: " + "[" + m.getSouthMovement(t, f)[0] + ", " + m.getSouthMovement(t, f)[1] + "]" + " // Sand amount: " + f.getField()[m.getSouthMovement(t, f)[0]][m.getSouthMovement(t, f)[1]]);
//	}//end printSand

//	public static void compareOrderingTime(){
//		Long initialTime, finalTimeList, finalTimeQueue;
//		int size;
//		Node parent = new Node(f);
//		Node auxNode;
//		Frontier frontList = new Frontier();
//		Frontier frontQueue = new Frontier();
//		StateSpace action = new StateSpace();
//		List<Node> actionList;
//		actionList = action.successors(parent, m, t, f);
//
//		//Ordering List
//		frontList.createFrontierList();
//
//		initialTime = System.nanoTime();
//		for(int i = 0; i < actionList.size(); i++){
//			auxNode = actionList.get(i);
//			frontList.insertInList(auxNode);
//
//		}
//		finalTimeList = System.nanoTime() - initialTime;
//		System.out.println("Linked list");
//		for(int i = 0; i < frontList.getFrontierList().size(); i++){
//			auxNode = frontList.getFrontierList().get(i);
//			System.out.println(auxNode.toString2());
//
//		}
//
//		//Ordering Queue
//		frontQueue.createFrontierQueue();
//		initialTime = System.nanoTime();
//		for(int i = 0; i < actionList.size(); i++){
//			auxNode = actionList.get(i);
//			frontQueue.insertInQueue(auxNode);
//		}
//		finalTimeQueue = System.nanoTime() - initialTime;
//		System.out.println("Priority Queue");
//		size = frontQueue.getFrontierQueue().size();
//		for(int i = 0; i < size; i++){
//			auxNode = frontQueue.getFrontierQueue().remove();
//			System.out.println(auxNode.toString2());
//		}
//		System.out.println("Time for the Linked List = " + finalTimeList + "ns");
//		System.out.println("Time for the Priority Queue = " + finalTimeQueue + "ns");
//		if(finalTimeList < finalTimeQueue)
//			System.out.println("The best option is to use a Linked List");
//		else
//			System.out.println("The best option is to use a Priority Queue");
//	}

	private static void strategySelection() {
		int strategy;
		StateSpace stateSpace = new StateSpace();
		List<Node> solution = new ArrayList<Node>();
		Problem prob = new Problem(stateSpace, f, k);

		System.out.println("\n- Strategies -\n"
				+ "1. Breath-first search\n"
				+ "2. Depth-first search\n"
				+ "3. Depth-limit search\n"
				+ "4. Iterative-depth search\n"
				+ "5. Uniform-cost search\n");

		scan = new Scanner(System.in);

		strategy = scan.nextInt();
		switch (strategy) {

		case 1:
			solution = boundedSearch(prob, strategy, 20);
			printSolution(solution);
			break;

		case 2:
			boundedSearch(prob, strategy, 20);
			break;
			
		case 3:
			System.out.println("Type the maximum depth");
			int maxDepth = scan.nextInt();
			boundedSearch(prob, strategy, maxDepth);		
			break;
			
		case 4:
			System.out.println("Type the incrementally depth");
			int incDepth = scan.nextInt();
			search(prob, strategy, 20, incDepth);		
			break;

		case 5:
			boundedSearch(prob, strategy, 20);
			break;

		default:				
			System.out.println("Wrong character! Type a number from 1 to 3.");
			strategySelection();

		}		
	}

	private static List<Node> boundedSearch(Problem prob, int strategy, int maxDepth){
		boolean solution = false;
		int[] tractorPosition = new int[2];
		Frontier front = new Frontier();
		front.createFrontierQueue();
		Node parent = new Node(f);
		Node actual = null;
		StateSpace initialAction = new StateSpace();
		Movement moves = new Movement(tractorPosition);
		Node initialNode = new Node(parent, prob.getField(), strategy, initialAction);
		initialNode.setCost(0);
		initialNode.setDepth(0);
		initialNode.setValue(0);
		initialNode.getAction().setMoves(moves);
		initialNode.getAction().getMoves().setVertical(x);
		initialNode.getAction().getMoves().setHorizontal(y);
		List<Node> successorList = new ArrayList<Node>();
		front.insertInQueue(initialNode);
		
		while(solution == false && !front.isEmptyQueue()){
			actual = front.getFrontierQueue().remove();
			if(prob.isGoalState(actual.getState())){
				solution = true;
			}
			else{
				if(actual == initialNode) {
					tractorPosition[0] = x;
					tractorPosition[1] = y;
				}else {
					tractorPosition[0] = actual.getAction().getMoves().getVertical();
					tractorPosition[1] = actual.getAction().getMoves().getHorizontal();
				}
				actual.getState().setColumn(column);
				actual.getState().setRow(row);
				successorList = prob.successors(actual, actual.getState(), actual.getAction().getMoves(), tractorPosition, strategy);
				for(int i = 0; i < successorList.size(); i++)
					front.insertInQueue(successorList.get(i));
			}
		}

		if(solution)
			return createSolution(actual);
		else
			return null;
		
		
	}
	
	public static List<Node> search(Problem prob, int strategy, int maxDepth, int incDepth) {
		int actualDepth = incDepth;
		List<Node> solution = new ArrayList<Node>();
		while(actualDepth <= maxDepth) {
			solution = boundedSearch(prob, strategy, actualDepth);
			actualDepth += incDepth;
		}
		return solution;
		
	}
	
	public static List<Node> createSolution(Node actual){
		List<Node> solution = new ArrayList<Node>();
		while(actual.getParent() != null && solution != null) {
			solution.add(actual);
			actual = actual.getParent();	
		}
		return solution;
	}
	
	public static void printSolution(List<Node> solution) {
		Node auxNode;
		Field campo;
		int[][] tierras;
		System.out.println("Final solution:\n");
		for(int i = solution.size(); i > 0; i--) {
			auxNode = solution.get(i - 1);
			campo = auxNode.getState();
			tierras = campo.getField();
			System.out.println(auxNode.getAction().getMoves().toString());
			System.out.println(auxNode.getAction().toString());
			for(int j = 0; j < tierras.length; j++) {
				for(int k = 0; k < tierras[j].length; k++) {
					System.out.print("|" + tierras[j][k]);									
				}
				System.out.print("|\n");
			}
			System.out.println();
		}
	}



}//end Main




