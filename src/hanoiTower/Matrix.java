package hanoiTower;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Double;



public class Matrix {
    int rows = 0;
	private int cols = 0;
    ArrayList<ArrayList<Double>> m;
    
    public Matrix(ArrayList<String> matrix) {
    	m = createMatrixFromString(matrix);
    	cols = m.get(0).size();
    	rows = m.size(); 
    	squareIt();
    }
    
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
    
    public Double pos(int i, int j) {
    	return m.get(i).get(j);
    }
    
    public void setPos(int i, int j, Double value) {
    	m.get(i).set(j,value);
    }
    
    public  ArrayList<ArrayList<Double>> createMatrixFromString(ArrayList<String> matrix) {
    	ArrayList<ArrayList<Double>> numberMatrix = new ArrayList<ArrayList<Double>>();
    	for(String line: matrix){
    		numberMatrix.add(createArray(line));
    	} 
		return numberMatrix;
    }
    
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
    
    public ArrayList<Double> emptyRow() {
    	ArrayList<Double> emptyRow = new ArrayList<Double>();
    	for(int i = 0; i < cols; i++) {
    		emptyRow.add(0.0);
    	}
		return emptyRow;
    }
    
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

	 public static double log2(double d) {
	      return Math.log(d)/Math.log(2.0);
	   }
    
    // Subtracting 2 matrices
    public static Matrix subMatrix(Matrix a, Matrix b) {
        int n = a.rows;
        Matrix res = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res.setPos(i, j, (a.pos(i, j) - b.pos(i, j)));
            }
        }
        return res;
    }
    
    // divides array
    public static void divideArray(Matrix P, Matrix C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.cols; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.rows; j1++, j2++)
                C.setPos(i1, j1, P.pos(i2,j2));
    }

    // copies
    public static void copySubArray(Matrix C, Matrix P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.cols; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.rows; j1++, j2++)
            	P.setPos(i2, j2, C.pos(i1,j1));
    }  
    
 // Adding 2 matrices
    public static Matrix addMatrix(Matrix a, Matrix b) {

        int n = a.cols;
        Matrix res = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	 res.setPos(i, j, (a.pos(i, j) + b.pos(i, j)));
            }
        }
        return res;
    }
    

   
}