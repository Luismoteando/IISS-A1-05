import java.io.*;
import java.util.*;

public class Main {
	private static int x = 0, y = 0, k = 0, max = 0, column = 0,row = 0;
	private static int[] prueba = {1, 2};
	private static int [][] field;
	private static Scanner scan;
	private static Field f;
	private static Tractor t;
//	private static Random randomGenerator;
	public static Movement m;
	

	public static void main(String[] args) throws FileNotFoundException{
		@SuppressWarnings("unused")
		List<Action> actionsWithMoves;
		
		storeValues();

		if(field.length != row || field[0].length != column){
			//We still have to control if there're more squares in the field
			System.out.println("The length of the field doesn't correspond to the number of columns or rows");
			System.exit(0);
		}//end if

		System.out.println("Position of the tractor: [" + x + ", " + y + "]\n"
				+ "Optimum amount of sand per square (k): " + k + "\n"
				+ "Maxumum amount of sand per square (Max): "+ max + "\n"
				+"Dimensions of the field: " + column + " x " + row + "\n");

		printField();

		f = new Field(column, row, field, k, max);
		t = new Tractor(x, y);
		m = new Movement(prueba);
		printSand();
		Action.generateActions(t, f, m);
		actionsWithMoves = Action.actionsWithMovements(m, t, f);
		f.isGoal();
		Node n = new Node();
		System.out.println(n.getValue());
//		System.out.println("\nRandom action selected\n" + generateRandom());

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

	public static void printSand(){
		System.out.println("\nList of successors:");

		if(x != 0)
			System.out.println("North: " + "[" + m.getNorthMovement(t)[0] + ", " + m.getNorthMovement(t)[1] + "]" + " // Sand amount: " + f.getField()[m.getNorthMovement(t)[0]][m.getNorthMovement(t)[1]]);
		if(y != 0)
			System.out.println("West: " + "[" + m.getWestMovement(t)[0] + ", " + m.getWestMovement(t)[1] + "]" + " // Sand amount: " + f.getField()[m.getWestMovement(t)[0]][m.getWestMovement(t)[1]]);
		if(y != column - 1)
			System.out.println("East: " + "[" + m.getEastMovement(t, f)[0] + ", " + m.getEastMovement(t, f)[1] + "]" + " // Sand amount: " + f.getField()[m.getEastMovement(t, f)[0]][m.getEastMovement(t, f)[1]]);
		if(x != row - 1)
			System.out.println("South: " + "[" + m.getSouthMovement(t, f)[0] + ", " + m.getSouthMovement(t, f)[1] + "]" + " // Sand amount: " + f.getField()[m.getSouthMovement(t, f)[0]][m.getSouthMovement(t, f)[1]]);
	}//end printSand

	
//	public static Action generateRandom(){
//		Action a;
//		int index;
//		List<Action> ma;
//		randomGenerator = new Random();
//		ma = actionsWithMovements();
//		index = randomGenerator.nextInt(ma.size());
//		a = ma.get(index);
//		return a;
//	}


}//end Main




