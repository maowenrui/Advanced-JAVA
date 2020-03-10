package OOP;


//º∆À„ª˙¿‡ 
public class Computer {

	String ComputerName;
	
	private CPU cpu = null;
	
	private Disk disk =null;
	
	private RAM ram = null;
	
	private Mainboard mainboard = null;
	
	long price = 0;
	
	public Computer(String name) {
		this.ComputerName = name;
	}
	
	public void AddCPU(String brand,String name,int coreNum,long price) throws UnsupportedException {
		
		if(brand == "Intel") {
			this.cpu = new cpu_Intel().CPUMaker(name, coreNum, price);
			this.price = this.price + price;
		}else if(brand == "AMD") {
			this.cpu = new cpu_AMD().CPUMaker(name, coreNum, price);
			this.price = this.price + price;
		}else {
			throw new UnsupportedException (this.ComputerName+" CPU unsupport");
		}
	}
	
	public void AddDisk(String brand,String name,int volume,long price) throws UnsupportedException {
		
		if(brand == "Seagate") {
			this.disk = new disk_Seagate().DiskMaker(name, volume, price);
			this.price = this.price + price;
		}else if(brand == "WestDigitals") {
			this.disk = new disk_WestDigitals().DiskMaker(name, volume, price);
			this.price = this.price + price;
		}else {
			throw new UnsupportedException (this.ComputerName+" Disk unsupport");
		}
	}
	
	public void AddRAM(String brand,String name,int volume,long price) throws UnsupportedException {
		
		if(brand == "Samsung") {
			this.ram = new ram_Sansung().RAMMaker(name, volume, price);
			this.price = this.price + price;
		}else if(brand == "Kingston") {
			this.ram = new ram_Kingston().RAMMaker(name, volume, price);
			this.price = this.price + price;
		}else {
			throw new UnsupportedException (this.ComputerName+" RAM unsupport");
		}
	}
	
	public void AddMainboard(String brand,String name,int speed,long price) throws UnsupportedException {
		
		if(brand == "Asus") {
			this.mainboard = new mainboard_Asus().MainboardMaker(name, speed, price);
			this.price = this.price + price;
		}else if(brand == "Gigabyte") {
			this.mainboard = new mainboard_Gigabyte().MainboardMaker(name, speed, price);
			this.price = this.price + price;
		}else {
			throw new UnsupportedException (this.ComputerName+" Mainboard unsupport");
		}
	}

	public String getCpuName() {
		// TODO Auto-generated method stub
		return this.cpu.getName() + " work";
	}

	public String getDiskName() {
		return this.disk.getName() + " work";
	}

	public String getRAMName() {
		return this.ram.getName()+ " work";
	}
	
	public String getMainboardName() {
		return this.mainboard.getName()+ " work";
	}

	
}
