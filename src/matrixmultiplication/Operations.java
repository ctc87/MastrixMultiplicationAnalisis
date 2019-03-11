package matrixmultiplication;


/**
	 * <h1>Operations</h1> Is a class who contains the matrix products methods.
	 * 
	 * @author Carlos Troyano Carmona
	 * @version 1.0
	 * @date 1 mar. 2017
	 * @see Tower
	 * @see Array
 */
public class Operations {
	
	

 /**
  * IterativeMultiply is a method who multiply matrix with three for with
  * the classical algorithm.
  * @param A Matrix a
  * @param B Matrix b
  * @return a*b
 */
	static Matrix IterativeMultiply(Matrix A, Matrix B) {
		 int n = A.rows;
	     Matrix res = new Matrix(n);
		 for (int i = 0; i < n; i++) {
	          for (int j = 0; j < n; j++) {
	              for (int k = 0; k < n; k++) {
	            	  if(res.pos(i, j) ==  null)
	            		  res.setPos(i, j, 0.0);
	            	  Double a = res.pos(i, j)  +  A.pos(i, k) *  B.pos(k, j);  // c[i][j] += a[i][k] * b[k][j];
	                  res.setPos(i, j, a);
	              }
	          }
	      }
		 return res;
	 }

/**
  * This is the Strassen method two multiply two matrix with only 7 subproblems.
  * @param A Matrix a
  * @param B Matrix b
  * @return a*b
 */
    static Matrix StrassenMultiply(Matrix A, Matrix B) {

        int n = A.rows;

        Matrix res = new Matrix(n);

        // if the input matrix is 1x1
        if (n == 1) {
            res.setPos(0, 0, A.pos(0, 0) * B.pos(0, 0));
        } else {

            // first matrix
            Matrix a = new Matrix(n / 2);
            Matrix b = new Matrix(n / 2);
            Matrix c = new Matrix(n / 2);
            Matrix d = new Matrix(n / 2);
            
            // second matrix
            Matrix e = new Matrix(n / 2);
            Matrix f = new Matrix(n / 2);
            Matrix g = new Matrix(n / 2);
            Matrix h = new Matrix(n / 2);

            // dividing matrix A into 4 parts
            divideArray(A, a, 0, 0);
            divideArray(A, b, 0, n / 2);
            divideArray(A, c, n / 2, 0);
            divideArray(A, d, n / 2, n / 2);

            // dividing matrix B into 4 parts
            divideArray(B, e, 0, 0);
            divideArray(B, f, 0, n / 2);
            divideArray(B, g, n / 2, 0);
            divideArray(B, h, n / 2, n / 2);
            
            /** 
              p1 = (a + d)(e + h)
              p2 = (c + d)e
              p3 = a(f - h)
              p4 = d(g - e)
              p5 = (a + b)h
              p6 = (c - a) (e + f)
              p7 = (b - d) (g + h)
            **/
           
            Matrix p1 = StrassenMultiply(addMatrix(a, d), addMatrix(e, h));
            Matrix p2 = StrassenMultiply(addMatrix(c,d),e);
            Matrix p3 = StrassenMultiply(a, subMatrix(f, h));           
            Matrix p4 = StrassenMultiply(d, subMatrix(g, e));
            Matrix p5 = StrassenMultiply(addMatrix(a,b), h);
            Matrix p6 = StrassenMultiply(subMatrix(c, a), addMatrix(e, f));
            Matrix p7 = StrassenMultiply(subMatrix(b, d), addMatrix(g, h));

            
           /**
              C11 = p1 + p4 - p5 + p7
              C12 = p3 + p5
              C21 = p2 + p4
              C22 = p1 - p2 + p3 + p6
            **/
           
            Matrix C11 = addMatrix(subMatrix(addMatrix(p1, p4), p5), p7);
            Matrix C12 = addMatrix(p3, p5);
            Matrix C21 = addMatrix(p2, p4);
            Matrix C22 = addMatrix(subMatrix(addMatrix(p1, p3), p2), p6);

            // adding all subarray back into one
            copySubArray(C11, res, 0, 0);
            copySubArray(C12, res, 0, n / 2);
            copySubArray(C21, res, n / 2, 0);
            copySubArray(C22, res, n / 2, n / 2);
            
        }
        return res;
    }
    /**
     * Substract two Matrix a and b
	  * @param A Matrix a
	  * @param B Matrix b
	  * @return a-b
     */
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
    
 // Adding 2 matrices
    /**
      * Add two matrix a and b 
	  * @param A Matrix a
	  * @param B Matrix b
	  * @return a-b
     */
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
    
    /**
     * Divide matrix in submatrix.
     * @param P
     * @param C
     * @param iB
     * @param jB
     */
    public static void divideArray(Matrix P, Matrix C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.cols; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.rows; j1++, j2++)
                C.setPos(i1, j1, P.pos(i2,j2));
    }

    /**
     * Copy sub matrix.
     * @param C
     * @param P
     * @param iB
     * @param jB
     */
    public static void copySubArray(Matrix C, Matrix P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.cols; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.rows; j1++, j2++)
            	P.setPos(i2, j2, C.pos(i1,j1));
    }  

}