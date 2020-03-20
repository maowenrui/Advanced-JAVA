package container_IO;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Analysis {

	
	
	 public Analysis(String origin_filepath ,String final_filepath) throws Exception {
		//��ȡԭʼ�����ݵ��ַ���
		String origin_content =this.readFromTxt(origin_filepath);
		//����õ����ַ���ΪNewContent
		String final_Content = this.sortByValue(countwords(origin_content));
			//д�ļ�
		 this.writeToText(final_Content,final_filepath);
	 }
	//�������ж�������Ҫ��ÿһ�е�ǰ��ӿո�
	public String readFromTxt(String filename) throws Exception {
		BufferedReader buf = null;
		try{
			File file = new File(filename);
			// ʹ���ļ�������ʵ����BufferedReader�����
			 buf = new BufferedReader(new FileReader(file));
			String str = null;	// ������������
			String content = null;//Ŀ���ַ���
			while ((str = buf.readLine()) != null) {// ��ȡ���ݲ��ж��Ƿ����
				content = content + " "+str;	// �����ȡ����
			}
			return content;	
		}finally {
		    close(buf);
		}
	}
	
	//�ر�������
	private void close(Closeable inout) {
		if (inout != null) {
			try {
				inout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	//���Ķ��ÿո�ָ�
	private  String[] splitContentToWords(String content) {
		String contents[] = content.split(" ");
		return contents;
	}
	
	//�����String����ת����TreeMap<words,times>
	public TreeMap<String, Integer> countwords(String content) {
	    TreeMap<String,Integer> map = new TreeMap<String, Integer>();
	    Integer temp  = null;
	    String contents[] = splitContentToWords(content);
	    for(int i = 0;i < contents.length;i++) {
	        temp = map.put(contents[i], 1);
	       if(temp !=null) {//��ʾͬKeyֵ�Ѿ�����,temp�᷵��valuesֵ����һ��ʾ�ֳ���һ��
	    		temp = temp+1;
	    		map.put(contents[i], temp);
	    }
	    }
	    //map���п��ܰ��������������Ƴ���
	    map.remove("");
		return map;
	}
	
	//��treemap�������� ����value�Ӵ�С���
	public  String sortByValue(TreeMap<String, Integer> treeMap){	
		String outstr = new String(); 
		List<Entry<String,Integer>> arrayList = new ArrayList<Entry<String,Integer>>(treeMap.entrySet());
		//��sort����һ��Comparatorʵ����ʵ���Զ�������Comparatorֻ��һ����ȵ���(�ӿ�)
		MyComparator myComparator =new MyComparator();
		Collections.sort(arrayList,myComparator);
		for(Entry<String,Integer> mapper: arrayList){
			outstr = outstr + mapper.getKey() +"	"+mapper.getValue()+ "\r\n";
		}
		return outstr;
	 }
	
	
	//������õ����ַ���д���ļ�
	public void writeToText(String content,String filepath) throws IOException {
		File file = new File(filepath);
		OutputStream output = new FileOutputStream(file);
			//��OutputStream����󴫵ݸ�OutputStreamWriter��Ĺ��췽������������ת��ΪWriter
				Writer wrt = new OutputStreamWriter(output);
				
				wrt.write(content);
				wrt.close();
	}
}
	

