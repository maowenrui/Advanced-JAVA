package copyfile;

import java.io.File;

public class copyfilemain {

	public static void main(String [] arg) {
		try {
			copyfile copyaction = new copyfile();
			 copyaction.copyFile("D:"+File.separator+"temp","D:"+File.separator+"test");
		}catch(Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
