package hanoiTower;

/**
 * <h1>TowerCylic</h1> TowerCylic its a class to extends the tower
 * class with index to rotate in cycles.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see Tower
 */
public class TowerCylic extends Tower {

	/**
	 * Index to control the next tower in the rotation.
	 */
	int index;
	
	/**
	 * Constructor initializes Tower attributes and the index.
	 * @param numDisk Number of disks in the tower.
	 * @param maxHeigth Maxim height of the disks levels in tower.
	 * @param index Index to control the next tower in the rotation.
	 */
	public TowerCylic(int numDisk, int maxHeigth, int index) {
		super(numDisk, maxHeigth);
		this.index = index;
	}
	
}
