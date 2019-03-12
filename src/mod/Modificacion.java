package mod;

import java.util.Arrays;

public class Modificacion {
	public final static int  ZERO = 0;
	
	public static int rCountZArray(int array[], int n)
	{
	    int found = 0;
	    if (n < 0) 
	        return 0;
	    if (array[n] == ZERO)
	        ++found;
	    return (found + rCountZArray(array, n - 1));
	}
	
	public static int countZ(int[] array) {
		if(array.length == 1) {
			return array[0] == ZERO ? 1 : 0;
		} else {
			int[] left = Arrays.copyOfRange(array, 0, array.length/2);
			int[] rigth = Arrays.copyOfRange(array, array.length/2, array.length);
			return countZ(left) + countZ(rigth);
		}
	}
	
	public static int countZ(int A[], int i, int j)
	{
		int medio;
		if (i>j) return 0;
			medio = (i+j) / 2;
		if (A[medio] > ZERO) 
			return countZ(A,medio+1,j);
		else
			return countZ(A,i,medio-1);
		
	}


}
