package matrixmultiplication;


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



public class Operations {
	
	

	 static Matrix IterativeMultiply(Matrix A, Matrix B) {
		 int n = A.rows;
	     Matrix res = new Matrix(n);
		 for (int i = 0; i < n; i++) {
	          for (int j = 0; j < n; j++) {
	              for (int k = 0; k < n; k++) {
	            	  Double a = res.pos(i, j)  +  A.pos(i, k) *  B.pos(k, j);  // c[i][j] += a[i][k] * b[k][j];
	                  res.setPos(i, j, a);
	              }
	          }
	      }
		 return res;
	 }

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
            Matrix.divideArray(A, a, 0, 0);
            Matrix.divideArray(A, b, 0, n / 2);
            Matrix.divideArray(A, c, n / 2, 0);
            Matrix.divideArray(A, d, n / 2, n / 2);

            // dividing matrix B into 4 parts
            Matrix.divideArray(B, e, 0, 0);
            Matrix.divideArray(B, f, 0, n / 2);
            Matrix.divideArray(B, g, n / 2, 0);
            Matrix.divideArray(B, h, n / 2, n / 2);
            
            /** 
              p1 = (a + d)(e + h)
              p2 = (c + d)e
              p3 = a(f - h)
              p4 = d(g - e)
              p5 = (a + b)h
              p6 = (c - a) (e + f)
              p7 = (b - d) (g + h)
            **/
           
            Matrix p1 = StrassenMultiply(Matrix.addMatrix(a, d), Matrix.addMatrix(e, h));
            Matrix p2 = StrassenMultiply(Matrix.addMatrix(c,d),e);
            Matrix p3 = StrassenMultiply(a, Matrix.subMatrix(f, h));           
            Matrix p4 = StrassenMultiply(d, Matrix.subMatrix(g, e));
            Matrix p5 = StrassenMultiply(Matrix.addMatrix(a,b), h);
            Matrix p6 = StrassenMultiply(Matrix.subMatrix(c, a), Matrix.addMatrix(e, f));
            Matrix p7 = StrassenMultiply(Matrix.subMatrix(b, d), Matrix.addMatrix(g, h));

            
           /**
              C11 = p1 + p4 - p5 + p7
              C12 = p3 + p5
              C21 = p2 + p4
              C22 = p1 - p2 + p3 + p6
            **/
           
            Matrix C11 = Matrix.addMatrix(Matrix.subMatrix(Matrix.addMatrix(p1, p4), p5), p7);
            Matrix C12 = Matrix.addMatrix(p3, p5);
            Matrix C21 = Matrix.addMatrix(p2, p4);
            Matrix C22 = Matrix.addMatrix(Matrix.subMatrix(Matrix.addMatrix(p1, p3), p2), p6);

            // adding all subarray back into one
            Matrix.copySubArray(C11, res, 0, 0);
            Matrix.copySubArray(C12, res, 0, n / 2);
            Matrix.copySubArray(C21, res, n / 2, 0);
            Matrix.copySubArray(C22, res, n / 2, n / 2);
        }
        return res;
    }


   
    
    

}