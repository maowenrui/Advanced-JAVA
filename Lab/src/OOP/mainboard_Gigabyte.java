package OOP;

public class mainboard_Gigabyte extends Mainboard{

	@Override
	Mainboard MainboardMaker(String name, int speed, long price) {
		// TODO Auto-generated method stub
		Mainboard mainboard = new mainboard_Gigabyte();
		mainboard.brand = "Gigabyte";
		mainboard.name = name;
		mainboard.speed= speed;
		mainboard.price = price;
		
		return mainboard;
	}

}
