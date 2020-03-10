package OOP;



public class ComputerStore implements Studio{

	
	public ComputerStore() {}

	@Override
	public Computer CreateComputer(String name) {
		// TODO Auto-generated method stub
		Computer com = new Computer(name);
		ComputerStore.Computer_List.add(com);
		return com;
	}
	
	public  void ShowComputer() {

		System.out.println("Welcome ~ Here is computer list ");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		for(int i = 0 ; i <ComputerStore.Computer_List.size();i++) {
			
			System.out.println(i+1 +"."+" ComputerName: " + ComputerStore.Computer_List.get(i).ComputerName);
			System.out.println("CPUtype: " + ComputerStore.Computer_List.get(i).getCpuName());
			System.out.println("Disktype: " + ComputerStore.Computer_List.get(i).getDiskName());
			System.out.println("RAMtype: " + ComputerStore.Computer_List.get(i).getRAMName());
			System.out.println("Mainboardtype: " + ComputerStore.Computer_List.get(i).getMainboardName());
			System.out.println("Price: " + ComputerStore.Computer_List.get(i).price);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}

	

	
}
