package hanoiTower;

import timer.Timer;

/**
 * <h1>App</h1> App its the main class. Implements the main method, 
 * the body program.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see Hanoi
 * @see CyclicHanoi
 */
public class App {
	
	/**
	 * Usage program constant.
	 */
	static final String USAGE = "\nError the usage its: \n" +
			  "java hanoi_tower num_disks debug \n" + 
			  "num_disks: The N of the disk number in the instance.\n" + 
			  "debug: if the value its 1 the problems prints the solution step by step.\n" +
			  "If the value its 0 the problems only prints the total number of the steps to solve it\n" + 
			  "--cyclic: solve with cyclic solution.";
	
	/**
	 * Cyclic parameter constant.
	 */
	static final String CYCLIC_PARAMETER = "--cyclic";
	
	/**
	 * Main method initialize the problem and execute the solver.
	 * @param args
	 */
	public static void main(String[] args) {
		Timer.start();
		int[][] matrix = {
			    { 1,2 },
			    { 6,7 }
			};
		int[][] matrix2 = {
			    { 1,1 },
			    { 1,1 }
			};
	    int[][] i = Strassen.StrassenMultiply(matrix, matrix2);
	   Strassen.printMatrix(i);
//		if(args.length > 3 || args.length < 2 || !isInt(args[0]) || !itsBool(args[1])) {
//			throw new RuntimeException(USAGE);
//		} else {
//			int N = Integer.parseInt(args[0]);
//			boolean debug = Integer.parseInt(args[1]) == 1 ;
//			Hanoi instance;
//			if(args.length < 3) {
//				instance = new Hanoi(N);
//			} else {
//				if(!args[2].equals(CYCLIC_PARAMETER)) {
//					throw new RuntimeException(USAGE);
//				} else {
//					instance = new CyclicHanoi(N);
//				}
//			}
//			instance.solve(N, debug);
//			if(!debug)
//				System.out.println("Number of moves for N=" + N + " -> " + instance.numberOfMoves);
//		}
		//System.out.println("N:" + args[0] + "  Time execution:" + Timer.stop() +"sec");
	}
	
	/**
	 * Checks if String its a integer.
	 * @param s String to check
	 * @return
	 */
	static boolean isInt(String s) {
	 try {
		 Integer.parseInt(s); 
		 return true; 
	} catch(NumberFormatException er){
		return false; 
		}
	}
	
	/**
	 * Checks if String its a boolean.
	 * @param s String to check
	 * @return
	 */
	static boolean itsBool(String s) {
		if(isInt(s)) {
			if(Integer.parseInt(s) == 0 || Integer.parseInt(s) == 1 )
				return true;
			else 
				return false;
		} else {
			return false;
		}
	}
}
