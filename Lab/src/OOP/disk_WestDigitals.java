package OOP;

public class disk_WestDigitals extends Disk {

	@Override
	Disk DiskMaker(String name, int volume, long price) {
		// TODO Auto-generated method stub
		Disk disk = new disk_WestDigitals();
		disk.brand = "WestDigitals";
		disk.name = name;
		disk.volume = volume;
		disk.price = price;
		
		return disk;
	}

	

}
