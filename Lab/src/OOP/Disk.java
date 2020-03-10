package OOP;

abstract class Disk {

	protected String brand ;
	protected String name;
	protected int volume;
	protected long price;
	
	abstract Disk DiskMaker(String name,int volume,long price);
	
	public String getBrand() {
		return this.brand;
	}

	public String getName() {
		return this.name;
	}
	
	public int getVloume() {
		return this.volume;
	}
	
	public long getPrice() {
		return this.price;
	}
}
