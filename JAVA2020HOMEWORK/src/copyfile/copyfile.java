package copyfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class copyfile {

	public  void copyFile(String old_path,String new_path)throws IOException {
		File oldfile = new File(old_path);
		File newfile = new File(new_path);
		//������ƵĲ���һ���ļ�
		if(!oldfile.isFile()) {
			newfile.mkdirs();
			File[] filelist = oldfile.listFiles();
			for(File files :filelist) {
				String filepath = new_path + File.separator + files.getName();
				File file = new File(filepath);
				if(files.isDirectory()) {
					file.mkdir();
					copyFile(files.toString(),file.toString());
				}else {
					FileInputStream in = new FileInputStream(files);
					FileOutputStream out = new FileOutputStream(file);
					OutByte(in,out);
					System.out.println(files.toString()+" ��ɸ���");
				}
			}
		}else {
			FileInputStream in = new FileInputStream(old_path);
			FileOutputStream out = new FileOutputStream(new_path + File.separator + oldfile.getName());
			OutByte(in,out);
			System.out.println(oldfile.toString()+" ��ɸ���");
				}
			}
	
	public static void OutByte(FileInputStream in,FileOutputStream out)throws IOException{
		byte[] bytes = new byte [1024];
		int temp = 0;
		while((temp = in.read(bytes,0,bytes.length))>-1) {
			out.write(bytes,0,temp);
		}
		in.close();
		out.flush();
		out.close();
	}
}
