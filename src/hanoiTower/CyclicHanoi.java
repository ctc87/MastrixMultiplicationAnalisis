package hanoiTower;

/**
 * <h1>CyclicHanoi</h1> CyclicHanoi its a class to represent a recursive solution 
 * of the problem <em>Cyclic Towers of Hanoi</em> <br/>
 * This class brings a solution with  divide and conquer algorithm 
 * design paradigm. Implements Array of TowerCylic and the number of disks
 * in the instance of the problem. Also implements clock and clockwise 
 * recursive methods to find a solution through divide and conquer
 * as we had already commented. Extends the Hanoi class.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see TowerCylic
 * @see Array
 */
public class CyclicHanoi extends Hanoi{

	/**
	 * Cyclic Towers array
	 */
	TowerCylic[] towers;

	/**
	 * Class constructor calls father constructor.
	 * @param numberOfDisk
	 */
	public CyclicHanoi(int numberOfDisk) {
		super(numberOfDisk);
	}

	

	/* (non-Javadoc)
	 * @see hanoiTower.Hanoi#initializeTowerWithDisks()
	 */
	protected void initializeTowerWithDisks() {
		this.towers[0] = new TowerCylic(numberOfDisk, numberOfDisk, 0);
	}


	/* (non-Javadoc)
	 * @see hanoiTower.Hanoi#initializeTowers()
	 */
	protected void initializeTowers() {
		this.towers = new TowerCylic[NUMBER_TOWERS];
		initializeTowerWithDisks();
		for(int i = 1; i < towers.length; i++)
			towers[i] = new TowerCylic(0, numberOfDisk, i);
	}
	
	/* (non-Javadoc)
	 * @see hanoiTower.Hanoi#solve(int, boolean)
	 */
	public void solve(int N, boolean debug) {
		clock(N, towers[0], towers[1], towers[2], debug);
	}
	
	/**
	 * Single move clockwise direction of a disk object into
	 * the hanoi structure of tower.
	 * @param towerOrigin Tower of move
	 */
	public void moveClock(TowerCylic towerOrigin) {
		int index = (towerOrigin.index + 1 ) % NUMBER_TOWERS;
		towerOrigin.moveDisk(towers[index]);
	}
	
	
	/**
	 * Divide and conquer Cyclic Hanoi. Its a version of the game on you only can move
	 * the disks in one direction. clock direction or clockwise direction. Only one of them.
	 * For solve it you need these two recursive procedures.
	 * Move N rings one position clockwise from tower X to tower Y using tower Z as workspace.  
	 * @param N
	 * @param X
	 * @param Y
	 * @param Z
	 */
	public void clock(int N, TowerCylic X, TowerCylic Y, TowerCylic Z, boolean debug) {
		if(N > 0) {
			anti(N-1, X, Z, Y, debug);
			moveClock(X);
			numberOfMoves++;
			if(debug)
				System.out.println(toString());
			anti(N-1, Z, Y, X, debug);
		}
	}
	
	/**
	 * Divide and conquer Cyclic Hanoi. Its a version of the game on you only can move
	 * the disks in one direction. clock direction or clockwise direction. Only one of them.
	 * For solve it you need these two recursive procedures.
	 * Move N rings one position anticlockwise from tower X to tower Y using tower 
	 * Z as workspace.  
	 * @param N
	 * @param X
	 * @param Y
	 * @param Z
	 */	
	public void anti(int N, TowerCylic X, TowerCylic Y, TowerCylic Z, boolean debug) {
		if (N > 0) {
			anti(N-1, X, Y, Z, debug);
			moveClock(X);
			numberOfMoves++;
			if(debug)
				System.out.println(toString());
			clock(N-1, Y, X, Z, debug);
			moveClock(Z);
			numberOfMoves++;
			if(debug)
				System.out.println(toString());
			anti(N-1, X, Y, Z, debug);
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
