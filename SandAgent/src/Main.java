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
	static int spatialComplexity;

	public static void main(String[] args) throws IOException{

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
//		StateSpace.generateActions(tp, f, m);
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

	private static void strategySelection() throws IOException {
		int strategy;
		Long initialTime, finalTime;
		String route = "Solution.txt";
		File file = new File(route);
		@SuppressWarnings("resource")
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		StateSpace stateSpace = new StateSpace();
		List<Node> solution = new ArrayList<Node>();
		Problem prob = new Problem(stateSpace, f, k);

		System.out.println("\n- Strategies -\n"
				+ "1. Breath-first search\n"
				+ "2. Depth-first search\n"
				+ "3. Depth-limit search\n"
				+ "4. Iterative-depth search\n"
				+ "5. Uniform-cost search\n"
				+ "6. A* search\n");

		scan = new Scanner(System.in);
///////////////////////////////////////////////Meter límite/////////////////////////////////////////////////
		strategy = scan.nextInt();
		switch (strategy) {

		case 1:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for BFS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for BFS is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		case 2:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for DFS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for DFS is: " + finalTime + " seconds.");
			bw.newLine();
			break;
			
		case 3:
			System.out.println("Type the maximum depth");
			int maxDepth = scan.nextInt();
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, maxDepth);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for DLS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for DLS is: " + finalTime + " seconds.");
			bw.newLine();
			break;
			
		case 4:
			System.out.println("Type the incrementally depth");
			int incDepth = scan.nextInt();
			initialTime = System.nanoTime();
			solution = search(prob, strategy, 20, incDepth);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for IDS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for IDS is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		case 5:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for UCS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for UCS is: " + finalTime + " seconds.");
			bw.newLine();
			break;
			
		case 6:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for A* is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for A* is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		default:				
			System.out.println("Wrong character! Type a number from 1 to 3.");
			strategySelection();

		}

		bw.close();	
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
				successorList = prob.successors(actual, actual.getState(), actual.getAction().getMoves(), tractorPosition, strategy, maxDepth);
				for(int i = 0; i < successorList.size(); i++) {
					spatialComplexity++;
					front.insertInQueue(successorList.get(i));
				}
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
	
	public static void printSolution(List<Node> solution, BufferedWriter bw) throws IOException {
		Node auxNode;
		Field newField;
		int[][] fieldArray;
		System.out.println("Final solution:\n");
		bw.write("Final Solution:");
		bw.newLine();
		for(int i = solution.size(); i > 0; i--) {
			auxNode = solution.get(i - 1);
			newField = auxNode.getState();
			fieldArray = newField.getField();
			bw.write(auxNode.getAction().getMoves().toString());
//			bw.write(auxNode.getAction().toString());
			bw.newLine();
			System.out.println(auxNode.getAction().getMoves().toString());
//			System.out.println(auxNode.getAction().getActions().get(0).getActions().get(0).getNorthSand());
//			System.out.println(auxNode.getAction().getActions().get(0).getActions().get(0).getWestSand());
//			System.out.println(auxNode.getAction().getActions().get(0).getActions().get(0).getEastSand());
//			System.out.println(auxNode.getAction().getActions().get(0).getActions().get(0).getSouthSand());
			System.out.println("Cost of the action: " + auxNode.getCost());
			bw.write("Cost of the action: " +auxNode.getCost());
			bw.newLine();
			for(int j = 0; j < fieldArray.length; j++) {
				for(int k = 0; k < fieldArray[j].length; k++) {
					System.out.print("|" + fieldArray[j][k]);
					bw.write("|" + fieldArray[j][k]);
				}
				bw.write("|");
		        bw.newLine();
				System.out.print("|\n");
			}
			System.out.println();
			bw.newLine();
		}
		System.out.println("Total cost of the search: " + solution.get(0).getCost());
		System.out.println("Spatial complexity: " + spatialComplexity);
		bw.write("Total cost of the search: " + solution.get(0).getCost());
		bw.newLine();
		bw.write("Spatial complexity: " + spatialComplexity);
		bw.newLine();
	}

}//end Main




