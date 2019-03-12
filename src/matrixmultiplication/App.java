package matrixmultiplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import exceptions.SintaxError;
import mod.Modificacion;
import timer.Timer;

/**
 * <h1>App</h1> App its the main class. Implements the main method, 
 * the body program.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 6 mar. 2018
 * @see Matrix
 */
public class App {
	
	/**
	 * Usage program constant.
	 */
	static final String USAGE = "\nError the usage its: \n" +
			  "java APP fileName  \n" + 
			  "filename: Name of the file to get matrix or to create matrix.";
	
	/**
	 * Cyclic parameter constant.
	 */
	static final String CYCLIC_PARAMETER = "--cyclic";
	static final float MIN = -1000;
	static final float MAX = 1000;
	static String file = "matrix.m";
	static int size = 10;
	/**
	 * Main method initialize the problem and execute the menú.
	 * @param args
	 * @throws SintaxError 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, SintaxError {
		menu();
	}
	
	/**
	 * Read matrix from file to create data struct.
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SintaxError
	 */
	public static ArrayList<ArrayList<String>> readInputMatrix(String fileName) throws FileNotFoundException, IOException, SintaxError {
		boolean secondMatrix = false;
		
		ArrayList<ArrayList<String>> TwoMatrixStringArray = new ArrayList<ArrayList<String>>();
		TwoMatrixStringArray.add(new ArrayList<String>());
		TwoMatrixStringArray.add(new ArrayList<String>());
		ArrayList<String> matrixString = new ArrayList<String>();
		String str;
		FileReader f = new FileReader(fileName);
		BufferedReader b = new BufferedReader(f);
		while ((str = b.readLine()) != null) {
			matrixString.add(str);
		}
		b.close();
		int lineNumber = 0;
		int matrix = 0;
		for (String line:matrixString) {
			lineNumber++;
			if(isNotEmptyLine(line) && isNotComment(line) && !itsBlockOpen(line) && itsValidLine(line)) {
				TwoMatrixStringArray.get(matrix).add(line);
			} else if(itsBlockClousre(line) && !secondMatrix){
				secondMatrix = true;
				matrix++;
			} else {
				if(isNotEmptyLine(line) && isNotComment(line) && itsBlockOpenOrClousre(line))
					throw new SintaxError(lineNumber, "Not valid characters", fileName);
			}
		}
	
		return TwoMatrixStringArray;
	}
	
	
	private static boolean isNotEmptyLine(String str) {
		return !str.trim().isEmpty();
	}

	private static boolean isNotComment(String str) {
		return str.charAt(0) != '#';
	}
	
	/**
	 * Parse the line of file to see if the line its a valid line to create matrix.
	 * @param str
	 * @return
	 */
	private static boolean itsValidLine(String str) {
		String number = "-?\\d*\\.{0,1}\\d+";
		String stringPattern = "\\{(\\s)*(" + number + "\\,?(\\s)*)*" + number +"(\\s)*\\}(\\s)*\\,";
		return java.util.regex.Pattern.matches(stringPattern, str.trim());
	}	
	private static boolean itsBlockOpenOrClousre(String str) {
		return (itsBlockClousre(str) && itsBlockOpen(str));
	}
	private static boolean itsBlockOpen(String str) {
		return java.util.regex.Pattern.matches("\\{", str.trim());
	}
	private static boolean itsBlockClousre(String str) {
		return java.util.regex.Pattern.matches("\\}", str.trim());
	}
	
	
	/**
	 * Checks if String its a integer.
	 * @param s String to check
	 * @return
	 */
	static boolean isInt(String s) {
	 try {
		 Integer.parseInt(s); 
		 return true; 
	} catch(NumberFormatException er){
		return false; 
		}
	}
	
	/**
	 * Checks if String its a boolean.
	 * @param s String to check
	 * @return
	 */
	static boolean itsBool(String s) {
		if(isInt(s)) {
			if(Integer.parseInt(s) == 0 || Integer.parseInt(s) == 1 )
				return true;
			else 
				return false;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Generate matrix in file of the filename variable.
	 */
	static void genarteMatrixFile() {
		
			PrintWriter writer;
			try {
				writer = new PrintWriter(file, "UTF-8");
				writer.println("# Admite comentarios y líneas en blanco.");
				writer.println("# matrix 1");
						 StringMatrix(writer); 
			    writer.println("# matrix2");
						 StringMatrix(writer);
				
				
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Create string matrix.
	 * @param writer
	 */
	static void StringMatrix(PrintWriter writer) {
		writer.println("{");
		for(int i = 0; i < size; i++) {
			writer.print("{");
			for(int j = 0; j < size; j++) {
				writer.print(randFloat());
				if(j < size-1) {
					writer.print(",");
				}
			}
			
			if(i < size-1)  {
				writer.println("},");
			} else  {

				writer.println("},");
			}
		}
		writer.println();
		writer.println("}");
	}
	
	/**
	 * Get random float number.
	 * @return
	 */
	public static float randFloat() {
	    Random rand = new Random();
	    float result = rand.nextFloat() * (MAX - MIN) + MIN;
	    return result;
	}
	
	/**
	 * Menu uf the program options to interact with the program.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SintaxError
	 */
	public static void menu() throws FileNotFoundException, IOException, SintaxError {
		Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Crear matriz de tamaño " + size + " en " + file + " .");
            System.out.println("2. Probar producto de las matrizes de " + file + " Strassen.");
            System.out.println("3. Probar producto de las matrizes de " + file + " Iteratívo.");
            System.out.println("4. Cambiar el tamaño de la N.");
            System.out.println("5. Modificación contar números array.");
            System.out.println("6. Salir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Generando la matriz... espere.");
                		genarteMatrixFile();
                        System.out.println("Matriz generada de " + size + "x" + size + " en "+ file + " .");
                        break;
                    case 2:
                    	strassenOpt();
                    case 3:
                    	iterativeOpt();
                        break;
                    case 4:
                    	System.out.println("Inserta la nueva N. \n");
                    	size = sn.nextInt();
                        break;
                    case 5:
                    	int[] p = {1,1,1,1,1,1,1,0,0,0,0,0};
                    	int val = Modificacion.countZ(p);
                    	val = Modificacion.countZ(p, 0, p.length);
                    	System.out.println("Numero de zeros " + val);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
	}
	
	/**
	 * Strassen option in menu.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SintaxError
	 */
	static void strassenOpt() throws FileNotFoundException, IOException, SintaxError {
		 System.out.println("Iniciando las pruebas Strassen con " + file);
         ArrayList<ArrayList<String>> matrixs = readInputMatrix(file);
			Matrix m1 = new Matrix(matrixs.get(0));
			Matrix m2 = new Matrix(matrixs.get(1));
			Timer.start();
         Matrix mr = Operations.StrassenMultiply(m1,m2);
         //if(size <= 50)
         	//mr.display();
 		System.out.println("STOP " + Timer.stop() + " SEG");
	}
	
	/**
	 * Iterative option in menu.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SintaxError
	 */
	static void iterativeOpt() throws FileNotFoundException, IOException, SintaxError {
		System.out.println("Iniciando las pruebas Iterativas con " + file);
        ArrayList<ArrayList<String>> matrixx = readInputMatrix(file);
		Matrix m11 = new Matrix(matrixx.get(0));
		Matrix m22 = new Matrix(matrixx.get(1));
		Timer.start();
        Matrix mrr = Operations.IterativeMultiply(m11,m22);
        //if(size <= 50)
        	//mrr.display();
		System.out.println("STOP " + Timer.stop() + " SEG");
	}
	
}
