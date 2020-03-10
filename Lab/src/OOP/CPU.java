package OOP;

 abstract class CPU {

	protected String brand ;
	protected String name;
	protected int coreNum;
	protected long price;
	
	abstract CPU CPUMaker(String name,int coreNum,long price);
	

	public String getName() {
		return this.name;
	}
	
	public int getCoreNum() {
		return this.coreNum;
	}
	
	public long getPrice() {
		return this.price;
	}

	
	
	
	

	
}