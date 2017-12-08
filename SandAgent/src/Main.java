import java.io.*;
import java.util.*;
/**
 * 
 * @author Lydia Prado
 *
 */
public class Main {
	private static int x = 0, y = 0, k = 0, max = 0, column = 0,row = 0;
	private static int [][] field;
	private static Scanner scan;
	private static Field f;
	private static int spatialComplexity;
	static Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();

	public static void main(String[] args) throws IOException{

		storeValues();	

		System.out.println("Position of the tractor: [" + x + ", " + y + "]\n"
				+ "Optimum amount of sand per square (k): " + k + "\n"
				+ "Maxumum amount of sand per square (Max): "+ max + "\n"
				+"Dimensions of the field: " + column + " x " + row + "\n");

		printField();

		f = new Field(column, row, field, k, max);
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
				System.out.println("\nError. There're invalid values.");
				System.exit(0);
			}
			if(field.length != row || field[0].length != column){
				System.out.println("The length of the field doesn't correspond to the number of columns or rows.");
				System.exit(0);
			}//end if	
			aux = true;
		}while(!aux);

	}//end storeValues

	public static void printField(){
		double sum  = 0, total = 0;
		scan.nextLine();
		System.out.println("Distribution of the field:");

		for(int i=0; i<field.length; i++){
			for(int j=0; j<field[i].length; j++){
				try{
					field[i][j] = scan.nextInt();
					if(field[i][j] > max){
						System.out.println("\nThe cell [" + i + ", " + j + "] exceeds the maximum amount of possible sand.");
						System.exit(0);
					}
				}catch(Exception e){
					System.out.println("\nError. There're invalid values in cell [" + i + ", " + j + "]");
					System.exit(0);
				}
				System.out.print("|" + field[i][j]);									
			}//end for
			System.out.print("|\n");
		}//end for
		for(int i  = 0; i< field.length; i++) 
			for(int j = 0; j < field[0].length; j++) 
				sum += field[i][j];
		total = sum / (row * column);
		if(total != k) {
			System.out.println("This field has no possible solution.");
			System.exit(0);
		}
	}//end printField

	private static void strategySelection() throws IOException {
		int strategy, option;
		boolean optimization = true;
		Long initialTime, finalTime;
		String route = "Solution.txt";
		File file = new File(route);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		StateSpace stateSpace = new StateSpace();
		List<Node> solution = new ArrayList<Node>();
		Problem prob = new Problem(stateSpace, f, k);
		scan = new Scanner(System.in);

		System.out.println("\nWould you like to use optimization? - (Use: 1 for yes || 2 for no)");
		option = scan.nextInt();
		if(option == 1)
			optimization = true;
		else
			optimization = false;

		System.out.println("\n- Strategies -\n"
				+ "1. Breath-first search\n"
				+ "2. Depth-first search\n"
				+ "3. Depth-limit search\n"
				+ "4. Iterative-depth search\n"
				+ "5. Uniform-cost search\n"
				+ "6. A* search\n");
		strategy = scan.nextInt();

		switch (strategy) {
		case 1:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20, optimization);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for BFS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for BFS is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		case 2:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20, optimization);
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
			solution = boundedSearch(prob, strategy, maxDepth, optimization);
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
			solution = search(prob, strategy, 20, incDepth, optimization);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for IDS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for IDS is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		case 5:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20, optimization);
			printSolution(solution, bw);
			finalTime = (System.nanoTime() - initialTime) / 1000000000;
			System.out.println("The temporal complexity for UCS is: " + finalTime + " seconds.");
			bw.write("The temporal complexity for UCS is: " + finalTime + " seconds.");
			bw.newLine();
			break;

		case 6:
			initialTime = System.nanoTime();
			solution = boundedSearch(prob, strategy, 20, optimization);
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
	/**
	 * 
	 * @param prob
	 * @param strategy
	 * @param maxDepth
	 * @param opt
	 * @return 
	 */

	private static List<Node> boundedSearch(Problem prob, int strategy, int maxDepth, boolean opt){
		boolean solution = false;
		int[] tractorPosition = new int[2];
		Frontier front = new Frontier();
		front.createFrontierQueue();
		Node parent = new Node(f);
		Node actual = null;
		StateSpace initialAction = new StateSpace();
		Movement moves = new Movement(tractorPosition);
		Node initialNode = new Node(parent, prob.getField(), strategy, initialAction);
		initialNode.getAction().setMoves(moves);
		initialNode.getAction().getMoves().setVertical(x);
		initialNode.getAction().getMoves().setHorizontal(y);
		List<Node> successorList = new ArrayList<Node>();
		front.insertInQueue(initialNode);

		while(solution == false && !front.isEmptyQueue()){ 		
			actual = front.getFrontierQueue().remove();			
			if(prob.isGoalState(actual.getState())){
				solution = true;
			}//end if
			else{
				tractorPosition = evaluateTractorPosition(actual, initialNode);
				successorList = prob.successors(actual, actual.getState(), actual.getAction().getMoves(), tractorPosition, strategy, maxDepth);
				for(int i = 0; i < successorList.size(); i++) {
					if(opt) {
						if(optimization(successorList.get(i), strategy)) {
							spatialComplexity++;
							front.insertInQueue(successorList.get(i));
						}//end if
					}else {
						spatialComplexity++;
						front.insertInQueue(successorList.get(i));
					}//end if
				}//end for
			}//end if
		}//end while

		if(solution)
			return createSolution(actual);
		else
			return null;
	}// end boundedSearch
	
	public static int[] evaluateTractorPosition(Node actual, Node initialNode) {
		int[] tractorPosition = new int[2];
		if(actual == initialNode) {
			tractorPosition[0] = x;
			tractorPosition[1] = y;
		}else {
			tractorPosition[0] = actual.getAction().getMoves().getVertical();
			tractorPosition[1] = actual.getAction().getMoves().getHorizontal();
		}
		return tractorPosition;
	}
	
	private static boolean optimization(Node possibleSuccessor, int strategy) {
		String serialize = possibleSuccessor.serialize();
		
		if(!hashTable.containsKey(serialize)) {
			if(strategy == 1 || strategy == 2 || strategy == 3 || strategy == 4)
				hashTable.put(serialize, possibleSuccessor.getCost());
			else
				hashTable.put(serialize, possibleSuccessor.getValue());
			return true;
		}else {
			if(strategy == 1 || strategy == 2 || strategy == 3 || strategy == 4) 
				if(hashTable.get(serialize) > possibleSuccessor.getCost()) {
					hashTable.replace(serialize, possibleSuccessor.getCost());
					return true;									
				}else
					return false;
			else
				if(hashTable.get(serialize) > possibleSuccessor.getValue()) {
					hashTable.replace(serialize, possibleSuccessor.getValue());
					return true;									
				}else
					return false;
		}
	}

	public static List<Node> search(Problem prob, int strategy, int maxDepth, int incDepth, boolean opt) {
		int actualDepth = incDepth;
		List<Node> solution = new ArrayList<Node>();
		while(actualDepth <= maxDepth) {
			solution = boundedSearch(prob, strategy, actualDepth, opt);
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
		Field newField, auxField = null;
		Movement auxMovement = null;
		int[][] fieldArray;
		System.out.println("Final solution:\n");
		bw.write("Final Solution:");
		bw.newLine();
		for(int i = solution.size(); i > 0; i--) {
			auxNode = solution.get(i - 1);
			newField = auxNode.getState();
			fieldArray = newField.getField();
			bw.write(auxNode.getAction().getMoves().toString());
			System.out.print(auxNode.getAction().getMoves().toString());
			if(auxField == null) {
				System.out.println("\nAction [northSand=0, westSand=0, eastSand=0, southSand=0]");
				bw.write("\nAction [northSand=0, westSand=0, eastSand=0, southSand=0]");
				bw.newLine();
			}else {
				System.out.println(solutionActions(auxField, newField, auxMovement));
				bw.write(solutionActions(auxField, newField, auxMovement));
				bw.newLine();				
			}
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

			auxField = newField;
			auxMovement = auxNode.getAction().getMoves();
		}

		System.out.println("Total depth of the search: " + solution.get(0).getDepth());
		System.out.println("Total cost of the search: " + solution.get(0).getCost());
		System.out.println("Spatial complexity: " + spatialComplexity);
		bw.write("Total depth of the search: " + solution.get(0).getDepth());
		bw.newLine();
		bw.write("Total cost of the search: " + solution.get(0).getCost());
		bw.newLine();
		bw.write("Spatial complexity: " + spatialComplexity);
		bw.newLine();
	}

	public static String solutionActions(Field parentField, Field currentField, Movement parentPosition) {
		int vertical = parentPosition.getVertical();
		int horizontal = parentPosition.getHorizontal();
		int[] moves = {vertical, horizontal};
		int childSand;
		int parentSand;
		int northSand = 0, westSand = 0, eastSand = 0, southSand = 0;
		if(vertical != 0) {
			parentSand = parentField.getField()[parentPosition.getNorthMovement(moves)[0]][parentPosition.getNorthMovement(moves)[1]];
			childSand = currentField.getField()[parentPosition.getNorthMovement(moves)[0]][parentPosition.getNorthMovement(moves)[1]];
			northSand = childSand - parentSand;	
		}
		if(horizontal != 0) {
			parentSand = parentField.getField()[parentPosition.getWestMovement(moves)[0]][parentPosition.getWestMovement(moves)[1]];
			childSand = currentField.getField()[parentPosition.getWestMovement(moves)[0]][parentPosition.getWestMovement(moves)[1]];
			westSand = childSand - parentSand;	
		}
		if(horizontal != column - 1) {
			parentSand = parentField.getField()[parentPosition.getEastMovement(moves, parentField)[0]][parentPosition.getEastMovement(moves, parentField)[1]];
			childSand = currentField.getField()[parentPosition.getEastMovement(moves, parentField)[0]][parentPosition.getEastMovement(moves, parentField)[1]];
			eastSand = childSand - parentSand;	
		}
		if(vertical != row - 1) {
			parentSand = parentField.getField()[parentPosition.getSouthMovement(moves, parentField)[0]][parentPosition.getSouthMovement(moves, parentField)[1]];
			childSand = currentField.getField()[parentPosition.getSouthMovement(moves, parentField)[0]][parentPosition.getSouthMovement(moves, parentField)[1]];
			southSand = childSand - parentSand;	
		}

		return "\nAction [northSand=" + northSand + ", westSand=" + westSand + ", eastSand=" + eastSand + ", southSand="
		+ southSand + "]";
	}
	
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

}//end Main

