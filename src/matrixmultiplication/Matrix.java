package matrixmultiplication;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Double;



/**
 * Matrix is a class to represent abstract matrix.
 * @author Troyano
 * @version 1.0
 * @date 7 mar. 2018
 * @see System
 *
 */
public class Matrix {
    int rows = 0;
	int cols = 0;
    ArrayList<ArrayList<Double>> m;
    
    /**
     * Constructor
     * @param matrix
     */
    public Matrix(ArrayList<String> matrix) {
    	m = createMatrixFromString(matrix);
    	cols = m.get(0).size();
    	rows = m.size(); 
    	squareIt();
    }
    
    /**
     * Void Matrix constructor
     * @param size
     */
    public Matrix(int size) {
    	cols = size;
    	rows = size;
    	m = new ArrayList<ArrayList<Double>>(size);
		for(int i = 0; i < size; i++) {
    		m.add(new  ArrayList<Double>(size));
			for(int j = 0; j < size; j++) {
				m.get(i).add(null);
			}
		}
    }
    
    /**
     * Return position i, j of the Matrix.
     * @param i
     * @param j
     * @return 
     */
    public Double pos(int i, int j) {
    	return m.get(i).get(j);
    }

    
    /**
     * Set position i, j of the Matrix.
     * @param i
     * @param j
     * @return
     */
    public void setPos(int i, int j, Double value) {
    	m.get(i).set(j,value);
    }
    
    /**
     * Create Matrix data from string data.
     * @param matrix
     * @return
     */
    public  ArrayList<ArrayList<Double>> createMatrixFromString(ArrayList<String> matrix) {
    	ArrayList<ArrayList<Double>> numberMatrix = new ArrayList<ArrayList<Double>>();
    	for(String line: matrix){
    		numberMatrix.add(createArray(line));
    	} 
		return numberMatrix;
    }
    
    /**
     * Create array data from string data.
     * @param line
     * @return
     */
    public ArrayList<Double> createArray(String line) {
    	ArrayList<Double> arr = new ArrayList<Double>();
    	
    	 String number = "-?\\d*\\.{0,1}\\d+";
    	 Matcher m = Pattern.compile(number)
    	     .matcher(line);
    	 while (m.find()) {
    	   arr.add(Double.valueOf(m.group()));
    	 }
    	return arr;
    }
    
    /**
     * Set size of matrix columns equal to rows.
     */
    void squareIt() {
    	if(!isSquare()) {
    		if(cols > rows) {
    			for(int i = (cols - rows); i > 0; i--) {
    				m.add(emptyRow());
    				rows++;
    			}
    		} else {
    			for(int i =0; i < rows; i++) {
    				completeCol(i);
    			}
    			cols = rows;
    		}	
    	}
    	pot2Matrix();
    }
    
    /**
     * Set Matrix size to power of 2 number. 
     */
    void pot2Matrix() {
    	while(log2(cols) % 1 != 0.0){
    		m.add(emptyRow());
			rows++;
			for(int i =0; i < rows; i++) {
				completeCol(i);
			}
			cols = rows;
    	}
    }
    
    /**
     * Set empty row.
     * @return
     */
    public ArrayList<Double> emptyRow() {
    	ArrayList<Double> emptyRow = new ArrayList<Double>();
    	for(int i = 0; i < cols; i++) {
    		emptyRow.add(0.0);
    	}
		return emptyRow;
    }
    
    /**
     * Set emty col. 
     * @param col
     */
    public void completeCol(int col) {
    	for(int i = cols; i < rows; i++) {
    		m.get(col).add(0.0);
    	}
    }

    public boolean isSquare() {
        return rows == cols;
    }
    

    public void display() {
        for (int row = 0; row < rows; ++row) {
                System.out.print(" ");
            System.out.print("[");
            for (int col = 0; col < cols; ++col) {
                System.out.printf("%8.3f", pos(row,col));

                if (col != cols - 1) {
                    System.out.print(" ");
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }

	 /**
	  * Logarithm of base 2 opration.
	 * @param d
	 * @return
	 */
	public static double log2(double d) {
	      return Math.log(d)/Math.log(2.0);
	   }
    
    
   
    

   
}