package Lab4;
import static org.junit.Assert.*;

public class Main {

	public static void main(String [] arg) throws InterruptedException {
		
		MatrixGenerator myMatrix = new MatrixGenerator();
		 System.out.println("��һ������------------------------------------------");
		 myMatrix.printMatrix("firstparam");
		 System.out.println("�ڶ�������------------------------------------------");
		 myMatrix.printMatrix("secondparam");
		 double[][] parallelMatrix= myMatrix.parallelCompute();
		 double[][]serialMatrix = myMatrix.serialCompute();
		 
		 assertArrayEquals(parallelMatrix,serialMatrix);
		 System.out.println("����Ŀ�����-��------------------------------------------");
		 myMatrix.printMatrix("Parallel");
		 System.out.println("����Ŀ�����-��------------------------------------------");
		 myMatrix.printMatrix("serial");
		
	  
	}

	
}
