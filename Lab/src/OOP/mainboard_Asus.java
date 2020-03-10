package OOP;

public class mainboard_Asus extends Mainboard{

	@Override
	Mainboard MainboardMaker(String name, int speed, long price) {
		// TODO Auto-generated method stub
		Mainboard mainboard = new mainboard_Asus();
		mainboard.brand = "Asus";
		mainboard.name = name;
		mainboard.speed= speed;
		mainboard.price = price;
		
		return mainboard;
	}

}
