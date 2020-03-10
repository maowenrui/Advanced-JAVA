package OOP;


public class cpu_AMD extends CPU{

	@Override
	CPU CPUMaker(String name, int coreNum, long price) {
		// TODO Auto-generated method stub
		CPU cpu = new cpu_AMD();
		cpu.brand = "AMD";
		cpu.name = name;
		cpu.coreNum = coreNum;
		cpu.price = price;
		return cpu;
	}

}
