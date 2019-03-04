package hanoiTower;

/**
 * <h1>Disk</h1> Disk its a class to represent a single disk in the
 * problem <em>Towers of Hanoi puzzle</em> <br/>
 * This class implements radius size of the disk and toString 
 * method to print a disk in the solution.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 */

public class Disk {
	/**
	 * Represent the radius of the disk.
	 */
	int radius;
	
	/**
	 * Constructor initializes radius.
	 * @param radius The radius of the disk.
	 */
	public Disk(int radius) {
		this.radius = radius;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Prints the disk representation in String format.
	 * =|= -> Example disk radius one.
	 * ==|== -> Example disk radius two.
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < this.radius; i++)
			str += "=";
		str += "|"; 
		for(int i = 0; i < this.radius; i++)
			str += "=";
		return str;
	}
}
