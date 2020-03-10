package OOP;

public class disk_Seagate extends Disk {

	@Override
	Disk DiskMaker(String name, int volume, long price) {
		// TODO Auto-generated method stub
		Disk disk = new disk_Seagate();
		disk.brand = "Seagate";
		disk.name = name;
		disk.volume = volume;
		disk.price = price;
		
		return disk;
	}

	

}
