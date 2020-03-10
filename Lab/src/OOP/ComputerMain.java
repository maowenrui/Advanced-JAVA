package OOP;

public class ComputerMain {

	public static void main(String[] arg) {
		
		try{
		
		ComputerStore Mystore = new ComputerStore();
		 
		Computer com1 = Mystore.CreateComputer("com1");
		
		com1.AddCPU("Intel", "cpu1", 3, 200);
		com1.AddDisk("WestDigitals", "disk1", 200, 500);
		com1.AddRAM("Samsung", "ram1", 300, 100);
		com1.AddMainboard("Asus", "mainboard1", 3000, 1000);
		
		
		Computer com2 = Mystore.CreateComputer("com2");
		
		com2.AddCPU("Intel", "cpu2", 3, 200);
		com2.AddDisk("WestDigitals", "disk2", 200, 520);
		com2.AddRAM("Samsung", "ram2", 300, 100);
		com2.AddMainboard("Asus", "mainboard2", 3000, 1200);
	
		Mystore.ShowComputer();
		
		}catch (UnsupportedException e) {
			
			System.out.println(e.getMessage());
		}
		 
	}
}
