package OOP;

public class ram_Sansung extends RAM {

	@Override
	RAM RAMMaker(String name, int volume, long price) {
		// TODO Auto-generated method stub
		RAM ram = new ram_Sansung();
		ram.brand = "Samsung";
		ram.name = name;
		ram.volume = volume;
		ram.price = price;
		
		return ram;
	}

}
