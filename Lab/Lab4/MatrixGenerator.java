package Lab4;

import java.util.Random;

public class MatrixGenerator {

	//为了方便本例使用左矩阵m*n和右矩阵n*m，以左矩阵为参照
	private int Columns;
	private int Rows;
	
	private double[][] matrix1;
	private double[][] matrix2;
	
	//随机生成的矩阵最大范围
	private static int MAX_RANGE = 2000; 
	
	public MatrixGenerator() {
		Random random = new Random();
		
		this.Rows = random.nextInt(MAX_RANGE);
		this.Columns = random.nextInt(MAX_RANGE);
		
		this.matrix1 = new double[this.Rows][this.Columns];
		this.matrix2 = new double[this.Columns][this.Rows];
		
		for(int i =0;i<this.Rows;i++) {
			for(int j=0;j<this.Columns;j++) {
				this.matrix1[i][j] = ((int)(random.nextDouble()*1000)/100);
				this.matrix2[j][i] = ((int)(random.nextDouble()*1000)/100);
			}
		}
					
	}
	
	
	
	
	//输出矩阵规模和计算事件
	public void printMatrix(String s) throws InterruptedException {
		switch(s){
		
		case "firstparam":
			//printMatrix(this.matrix1,this.Rows,this.Columns);
			System.out.println("规模 "+this.Rows+" "+"* "+this.Columns);
			break;
		case "secondparam":
			//printMatrix(this.matrix2,this.Columns,this.Rows);
			System.out.println("规模 "+this.Columns+" "+"* "+this.Rows);
			break;
		case "Parallel":
			long startTime1 = System.nanoTime();
			double[][] parallelMatrix= parallelCompute();
			long endTime1 =System.nanoTime();
			
		    System.out.println("并行计算的执行时间是-> "+ (double)((endTime1-startTime1)/1000000)+"ms");
			break;
		case "serial":
			long startTime2 = System.nanoTime();
			double[][]serialMatrix = serialCompute();
			long endTime2 =System.nanoTime();
			
			System.out.println("串行计算的执行时间是-> "+ (double)((endTime2-startTime2)/1000000)+"ms");
			break;
		default:break;	 
		}
		
	}

	//输出矩阵
	private void printMatrix(double[][]matrix,int rows,int columns) {
		
		for(int i=0;i<rows;i++) {
			for(int j =0;j<columns;j++) {
				System.out.print(matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	//多线程并行
	public  double[][] parallelCompute() throws InterruptedException{
		
		myThread threadSeed  = new myThread(this.matrix1,this.matrix2,this.Rows,this.Columns,8);
		
		Thread thread1 = new Thread(threadSeed);
		
		Thread thread2 = new Thread(threadSeed);
		
		Thread thread3 = new Thread(threadSeed);
		
		Thread thread4 = new Thread(threadSeed);
        
		Thread thread5 = new Thread(threadSeed);
		
		Thread thread6 = new Thread(threadSeed);
		
		Thread thread7 = new Thread(threadSeed);
		
		Thread thread8 = new Thread(threadSeed);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		
		//子线程执行完再执行主线程
		thread1.join();
		thread2.join();
	   return threadSeed.getAnsRow();	
	}
	
	//串行
	public  double[][] serialCompute() {
	
		double[][] ansMatrix = new double[this.Rows][this.Rows];
		double temp=0;
		for(int i=0;i<this.Rows;i++) {
			for (int j=0;j<this.Rows;j++) {
				temp=0;
				for(int k=0;k<this.Columns;k++) {
					temp = temp + this.matrix1[i][k]*this.matrix2[k][j];
				}
				ansMatrix[i][j]=temp;
			}
		}
		return ansMatrix;
	}
	
}

