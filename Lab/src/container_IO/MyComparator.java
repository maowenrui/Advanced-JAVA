package container_IO;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

//һ����̳���Comparator�ӿڣ�������ڸ�������
public class MyComparator implements Comparator<Map.Entry<String,Integer>>{


	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		// TODO Auto-generated method stub
		return arg1.getValue()-arg0.getValue();
	}
	}


