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
		//读取原始的内容到字符串
		String origin_content =this.readFromTxt(origin_filepath);
		//处理得到的字符串为NewContent
		String final_Content = this.sortByValue(countwords(origin_content));
			//写文件
		 this.writeToText(final_Content,final_filepath);
	 }
	//从文章中读内容需要在每一行的前面加空格
	public String readFromTxt(String filename) throws Exception {
		BufferedReader buf = null;
		try{
			File file = new File(filename);
			// 使用文件输入流实例化BufferedReader类对象
			 buf = new BufferedReader(new FileReader(file));
			String str = null;	// 接收输入数据
			String content = null;//目标字符串
			while ((str = buf.readLine()) != null) {// 读取数据并判断是否存在
				content = content + " "+str;	// 输出读取内容
			}
			return content;	
		}finally {
		    close(buf);
		}
	}
	
	//关闭输入流
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
	
	
	//将文段用空格分隔
	private  String[] splitContentToWords(String content) {
		String contents[] = content.split(" ");
		return contents;
	}
	
	//传入的String【】转换成TreeMap<words,times>
	public TreeMap<String, Integer> countwords(String content) {
	    TreeMap<String,Integer> map = new TreeMap<String, Integer>();
	    Integer temp  = null;
	    String contents[] = splitContentToWords(content);
	    for(int i = 0;i < contents.length;i++) {
	        temp = map.put(contents[i], 1);
	       if(temp !=null) {//表示同Key值已经存在,temp会返回values值，加一表示又出现一次
	    		temp = temp+1;
	    		map.put(contents[i], temp);
	    }
	    }
	    //map中有可能包含“”，所以移除掉
	    map.remove("");
		return map;
	}
	
	//将treemap重新排序 按照value从大到小输出
	public  String sortByValue(TreeMap<String, Integer> treeMap){	
		String outstr = new String(); 
		List<Entry<String,Integer>> arrayList = new ArrayList<Entry<String,Integer>>(treeMap.entrySet());
		//给sort传入一个Comparator实例，实现自定义排序，Comparator只是一个挽救的类(接口)
		MyComparator myComparator =new MyComparator();
		Collections.sort(arrayList,myComparator);
		for(Entry<String,Integer> mapper: arrayList){
			outstr = outstr + mapper.getKey() +"	"+mapper.getValue()+ "\r\n";
		}
		return outstr;
	 }
	
	
	//将处理得到的字符串写入文件
	public void writeToText(String content,String filepath) throws IOException {
		File file = new File(filepath);
		OutputStream output = new FileOutputStream(file);
			//将OutputStream类对象传递给OutputStreamWriter类的构造方法，而后向上转型为Writer
				Writer wrt = new OutputStreamWriter(output);
				
				wrt.write(content);
				wrt.close();
	}
}
	

