package OOP;

public class ram_Kingston extends RAM {

	@Override
	RAM RAMMaker(String name, int volume, long price) {
		// TODO Auto-generated method stub
		RAM ram = new ram_Kingston();
		ram.brand = "Kingston";
		ram.name = name;
		ram.volume = volume;
		ram.price = price;
		
		return ram;
	}

}
