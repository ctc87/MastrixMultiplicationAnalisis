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
import timer.Timer;

/**
 * <h1>App</h1> App its the main class. Implements the main method, 
 * the body program.
 * 
 * @author Carlos Troyano Carmona
 * @version 1.0
 * @date 1 mar. 2017
 * @see Hanoi
 * @see CyclicHanoi
 */
public class App {
	
	/**
	 * Usage program constant.
	 */
	static final String USAGE = "\nError the usage its: \n" +
			  "java hanoi_tower num_disks debug \n" + 
			  "num_disks: The N of the disk number in the instance.\n" + 
			  "debug: if the value its 1 the problems prints the solution step by step.\n" +
			  "If the value its 0 the problems only prints the total number of the steps to solve it\n" + 
			  "--cyclic: solve with cyclic solution.";
	
	/**
	 * Cyclic parameter constant.
	 */
	static final String CYCLIC_PARAMETER = "--cyclic";
	static final float MIN = -10000;
	static final float MAX = 10000;
	static String file = "matrix.m";
	static int size = 500;
	/**
	 * Main method initialize the problem and execute the solver.
	 * @param args
	 * @throws SintaxError 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, SintaxError {
		menu();
	}
	
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
	
	public static float randFloat() {
	    Random rand = new Random();
	    float result = rand.nextFloat() * (MAX - MIN) + MIN;
	    return result;
	}
	
	public static void menu() throws FileNotFoundException, IOException, SintaxError {
		Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Crear matriz de tamaño " + size + " en " + file + " .");
            System.out.println("2. Probar producto de las matrizes de " + file + " Strassen.");
            System.out.println("3. Probar producto de las matrizes de " + file + " Iteratívo.");
            System.out.println("4. Salir");
 
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
                        System.out.println("Iniciando las pruebas con " + file);
                        ArrayList<ArrayList<String>> matrixs = readInputMatrix(file);
            			Matrix m1 = new Matrix(matrixs.get(0));
            			Matrix m2 = new Matrix(matrixs.get(1));
            			Timer.start();
                        Matrix mr = Operations.StrassenMultiply(m1,m2);
                        if(size <= 50)
                        	mr.display();
                		System.out.println("STOP " + Timer.stop() + "SEG");
                        break;
                    case 3:
                        System.out.println("Iniciando las pruebas con " + file);
                        ArrayList<ArrayList<String>> matrixx = readInputMatrix(file);
            			Matrix m11 = new Matrix(matrixx.get(0));
            			Matrix m22 = new Matrix(matrixx.get(1));
            			Timer.start();
                        Matrix mrr = Operations.StrassenMultiply(m11,m22);
                        if(size <= 50)
                        	mrr.display();
                		System.out.println("STOP " + Timer.stop() + "SEG");
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
	}
	
}
