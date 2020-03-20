package container_IO;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

//一个类继承了Comparator接口，方便后期更改需求
public class MyComparator implements Comparator<Map.Entry<String,Integer>>{


	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		// TODO Auto-generated method stub
		return arg1.getValue()-arg0.getValue();
	}
	}


