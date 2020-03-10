package OOP;

import java.util.ArrayList;

public interface Studio {

	ArrayList<Computer> Computer_List = new ArrayList<Computer>();
	
	Computer CreateComputer(String name);
}
