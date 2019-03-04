package hanoiTower;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h1>DiskStack</h1> DiskStack its a class to  extends ArrayList data structure
 * to represent a stack of Disks in the problem of <em>Towers of Hanoi puzzle</em> <br/>
 * This class implements push disk, pop disk and toString method to print the disk stack.
 * 
 * @version 1.0
 * @author root
 * @date 1 mar. 2017
 * @see ArrayList
 * @see Disk
 */
public class DiskStack extends ArrayList<Disk> {
	
	/**
	 * Constant of {@link Serializable} interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Put a disk at the top of the stack.
	 * @param disk disk to put at the top of the stack.
	 */
	void push(Disk disk) {
		add(size(), disk);
	}
	
	/**
	 * Remove Disk from the top of the stack and return it.
	 * @return Disk
	 */
	Disk pop() {
		return remove(size() - 1);
	}
	
	/* (non-Javadoc)
	 * Prints the stack representation in String format.
	 *   =|= -> Example stack with two disks of radius one an two.
	 *  ==|==
	 * @see java.lang.Object#toString()
	 * @param maxSize The max size of the Tower to put the necessary spaces
	 * in the format.
	 */
	public String toString(int maxSize) {
		String str = "";
		int numberSpaces = 0;
		for(int i = this.size() - 1; i >= 0; i--) {
			numberSpaces = maxSize - this.get(i).radius + 1;
			for(int j = 0; j < numberSpaces; j++) 
				str += " ";
			str += this.get(i).toString() + "\n";
		}
		return str;
	}
}
