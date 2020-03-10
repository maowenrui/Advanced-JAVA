package OOP;

public abstract class Mainboard {	
	protected String brand ;
	protected String name;
	protected int speed;
	protected long price;
	
	abstract Mainboard MainboardMaker(String name,int speed,long price);
	
	public String getBrand() {
		return this.brand;
	}

	public String getName() {
		return this.name;
	}
	
	public int speed() {
		return this.speed;
	}
	
	public long getPrice() {
		return this.price;
	}
}
