package hanoiTower;


/**
 * <h1>Hanoi</h1> Hanoi its a class to represent a recursive solution 
 * of the problem <em>Towers of Hanoi puzzle</em> <br/>
 * This class brings a solution with  divide and conquer algorithm 
 * design paradigm. Implements Array of Towers and the number of disks
 * in the instance of the problem. Also implements hanoi recursive method 
 * to find a solution through divide and conquer as we had already commented.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see Tower
 * @see Array
 */
public class Hanoi {
	static final int NUMBER_TOWERS = 3;
	/**
	 * Number of disks in the instance.
	 */
	int numberOfDisk;
	/**
	 * Array of towers.
	 */
	Tower[] towers;
	
	/**
	 * Number of moves in the solution.
	 */
	int numberOfMoves;
	
	/**
	 * Constructor initialize the data structures towers with the disk at the 
	 * first tower in the array.
	 * @param numberOfDisk the number of disks to put in the source tower.
	 */
	public Hanoi(int numberOfDisk) {
		numberOfMoves = 0;
		this.numberOfDisk = numberOfDisk;
		initializeTowers();
	}
	
	/**
	 * Initialize the first tower of the array with 
	 * all of the disks.
	 */
	protected void initializeTowerWithDisks() {
		this.towers[0] = new Tower(numberOfDisk, numberOfDisk);
	}
	
	/**
	 * Initialize all towers.
	 */
	protected void initializeTowers() {
		this.towers = new Tower[NUMBER_TOWERS];
		initializeTowerWithDisks();
		for(int i = 1; i < towers.length; i++)
			towers[i] = new Tower(0, numberOfDisk);
	}
	
	/**
	 * Search a solution for the N disk instance.
	 * @param N
	 * @param debug
	 */
	public void solve(int N, boolean debug) {
		hanoi(N, towers[0], towers[1], towers[2], debug);
	}
	
	
	/**
	 * Divide and conquer algorithm to solve the hanoi problem.
	 * In each level of recursion that low in the tree moves N-1 
	 * discs of the destination to the auxiliary tower and soon 
	 * of the auxiliary tower to the destination.
	 * @param N The size of the problem.
	 * @param origin The origin tower (contain the disks to move).
	 * @param dest The destination tower (contain disks at the solution).
	 * @param aux the auxiliary tower to move the disks.
	 */
	private void hanoi(int N, Tower origin, Tower dest, Tower aux, boolean debug) {
		if(N == 1) {
			origin.moveDisk(dest);
			numberOfMoves++;
			if(debug)
				System.out.println(toString());
		} else {
			hanoi(N-1, origin, aux, dest, debug);
			origin.moveDisk(dest);
			numberOfMoves++;
			if(debug)
				System.out.println(toString());
			hanoi(N-1, aux, dest, origin, debug);
		}
	}
	
	/* (non-Javadoc)
	 * Print the actual state of the towers.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String aux = "Step: " + numberOfMoves;
		for(int i = 0; i < NUMBER_TOWERS; i++) {
			aux += "\nTower" + (i + 1) + ":\n\n";
			aux += towers[i].toString();
		}
		return aux += "\n*****************************************";
	}
	
	
}
