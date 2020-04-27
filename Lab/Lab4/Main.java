package Lab4;
import static org.junit.Assert.*;

public class Main {

	public static void main(String [] arg) throws InterruptedException {
		
		MatrixGenerator myMatrix = new MatrixGenerator();
		 System.out.println("第一个矩阵------------------------------------------");
		 myMatrix.printMatrix("firstparam");
		 System.out.println("第二个矩阵------------------------------------------");
		 myMatrix.printMatrix("secondparam");
		 double[][] parallelMatrix= myMatrix.parallelCompute();
		 double[][]serialMatrix = myMatrix.serialCompute();
		 
		 assertArrayEquals(parallelMatrix,serialMatrix);
		 System.out.println("并行目标矩阵-》------------------------------------------");
		 myMatrix.printMatrix("Parallel");
		 System.out.println("串行目标矩阵-》------------------------------------------");
		 myMatrix.printMatrix("serial");
		
	  
	}

	
}
