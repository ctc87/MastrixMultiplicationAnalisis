package hanoiTower;
/**
 * <h1>Tower</h1> Tower its a class to represent a single tower in the
 * problem <em>Towers of Hanoi puzzle</em> <br/>
 * This class implements DiskStack(stack of disks) and the maxHeight
 * of the tower. Implements methods to move disk and to print entire tower
 * with all of the disks.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see DiskStack
 */

public class Tower {
	/**
	 * Stack of disks.
	 */
	DiskStack disks;
	/**
	 * Max capacity of disks in the tower.
	 */
	int maxHeigth;
	
	/**
	 * Constructor initialize the attributes of the class.
	 * For each disk in the numDisk parameter put a disk with incremental 
	 * radius from 1 to numDisk, in the disks stack. 
	 * @param numDisk Number of disks in the tower.
	 * @param maxHeigth Maxim height of the disks levels in tower.
	 */
	public Tower(int numDisk, int maxHeigth) {
		this.maxHeigth = maxHeigth;
		disks = new DiskStack();
		for(int i = numDisk; i > 0; i--)
			disks.push(new Disk(i));
	}
	
	/**
	 * Moves a disk from the current tower to the last one by parameter dest.
	 * @param dest Destination tower for the disk passed by parameter. 
	 * @throws RuntimeException If you try to stack a disk with a lower radius than 
	 * the one on the top of the stack at that time.
	 */
	void moveDisk(Tower dest) {
		Disk movedDisk = disks.pop();
		if(!dest.disks.isEmpty()) {
			if(movedDisk.radius > dest.disks.get(0).radius) {
				disks.push(movedDisk);
				throw new RuntimeException("The disk in the top of the tower has radius " + dest.disks.get(0).radius + " and the moved disk " + movedDisk.radius);
			} else {
				dest.disks.push(movedDisk);
			}
		} else {
			dest.disks.push(movedDisk);
		}
	}
	
	/* (non-Javadoc)
	 * Prints the tower representation in String format.
	 *   =|= -> Example tower with two disks of radius one an two.
	 *  ==|==
	 * ------- 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str =  "";
		int numNoDiskPositions = maxHeigth - disks.size();
		int numSpaces = maxHeigth + 1;
		for (int i = 0; i < numNoDiskPositions; i++) {
			for (int j = 0; j < numSpaces; j++) {
				str += " ";
			}
			str += "|\n";
		}
		str += disks.toString(maxHeigth);
		for (int i = 0; i < ((maxHeigth + 1)*2)+1; i++) {
			str+= "-";
		}
		return str;
	}
	
	
	
}
