package container_IO;

import java.io.File;

public class AnalysisMain {

	public static void main(String[] arg) throws Exception {
		String origin_filepath ="D:/temp/�˲���ĸǴı�Ӣ��.txt"; 
		String final_filepath = "D:"+ File.separator + "temp"+File.separator+"output.txt";
		Analysis analysis = new Analysis(origin_filepath,final_filepath);
	}
}
