package Lab4;

public class myThread implements Runnable{

	int rows;
	int columns;
	int computingRows=0;
	double [][]firstMatrix;
	double[][]secondMatrix;
	int threadcount;
	double[][] ansRow ;
	
	//������������������Ҿ�������������Ҫ���߳�����
	public myThread(double[][] firstMatrix,double[][] secondMatirx,int rows,int column,int threadcount) {
		this.columns=column;
		this.secondMatrix = secondMatirx;
		this.rows= rows;
		this.firstMatrix = firstMatrix;
		this.threadcount = threadcount;
		this.ansRow= new double[this.rows][this.rows];
	}
	//����Ŀ�����
	public double[][] getAnsRow() {
		return this.ansRow;
	}
	//ͨ�����ķ�ʽ����ֹ����֮�侺�����������ͬһ��
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
