package Lab4;

public class myThread implements Runnable{

	int rows;
	int columns;
	int computingRows=0;
	double [][]firstMatrix;
	double[][]secondMatrix;
	int threadcount;
	double[][] ansRow ;
	
	//传入参与计算的左矩阵和右矩阵，行列数和需要的线程数量
	public myThread(double[][] firstMatrix,double[][] secondMatirx,int rows,int column,int threadcount) {
		this.columns=column;
		this.secondMatrix = secondMatirx;
		this.rows= rows;
		this.firstMatrix = firstMatrix;
		this.threadcount = threadcount;
		this.ansRow= new double[this.rows][this.rows];
	}
	//返回目标矩阵
	public double[][] getAnsRow() {
		return this.ansRow;
	}
	//通过锁的方式，防止进程之间竞争计算左矩阵同一行
	public synchronized int getComputingRow(){
			return this.computingRows++;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double temp;
		for(int oneThreadRunTimes=0;oneThreadRunTimes<(rows/threadcount)+1;oneThreadRunTimes++) {
			
			int thisCountRow = getComputingRow();
			
			if(thisCountRow<this.rows) {
				
				for(int i=0;i<this.rows;i++){
					temp=0;
					for(int j=0;j<this.columns;j++) {
						temp =temp + this.firstMatrix[thisCountRow][j]*this.secondMatrix[j][i];
					}
					
					this.ansRow[thisCountRow][i] = temp;
				}
			}
		
	    }
	}
}
