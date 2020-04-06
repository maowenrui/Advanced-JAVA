package Lab3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MyUtil implements SqlUtil {

	 
	/**
     * 根据传入的参数返回查询语句
     * @param user
     * @return 返回查询语句
     */	
	@Override
	public String query(User user) throws Exception {
		// TODO Auto-generated method stub
		String gdbc = null;
		Field field = null;
		Method method =null;
		//获得Class类
		Class<?> u = user.getClass();
		//获得所有的成员变量
		Field[] fields = u.getDeclaredFields();
		//为了get修改权限
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//获得所有变量的get方法
		ArrayList<Method> getmethods= getGetMethods(u);
		//检测user的那个成员变量被用于查询
		for(int i =0;i < getmethods.size();i++) {
			//获得
			method =getmethods.get(i);
			if(method.invoke(user)!=null) {
				//不为空说明是需要查询的内容，去掉get
				String searchparam = getmethods.get(i).getName().substring(3);
				//获得对应的成员变量
				field = u.getDeclaredField(initCap_l(searchparam));
				break;
				}
		}
		//获得注释
		Table  table =u.getAnnotation(Table.class);
	    Column column = field.getAnnotation(Column.class);
	    
	    //还要判断是数字还是字符
	    if(column.ColumnName().equals("id")||column.ColumnName().equals("age")) {
	    gdbc = "SELECT * FROM " + table.value()+ " WHERE " + column.ColumnName() + " = "+method.invoke(user);
	    }else {
	    //System.out.println(column.ColumnName());
	    gdbc = "SELECT * FROM " + table.value()+ " WHERE " + column.ColumnName() + " LIKE "+method.invoke(user);
	    }
		return gdbc;
	}
	
	 /**
     * 根据传入的参数返回插入语句
     * @param user
     * @return 返回插入语句
     */
	// print: INSERT INTO `user` (`username`, `telephone`, `email`, `age`) VALUES ('user', '12345678123', 'user@123.com', 20)
	@Override
	public String insert(User user) throws Exception{
		// TODO Auto-generated method stub
		String gdbc = null;
		//获得Class类
		Class<?> u = user.getClass();
		//获得所有的成员变量
		Field[] fields = u.getDeclaredFields();
		//为了get修改权限
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//获得所有变量的get方法
		ArrayList<Method> getmethods= getGetMethods(u);
		//需要判断要插入的user初始化了什么成员变量
		ArrayList<Field> validfields = new ArrayList<Field>();
		for(Method m:getmethods) {	
		    if(m.invoke(user)!=null) {
		    	Field f = u.getDeclaredField(initCap_l(m.getName().substring(3)));
		    	validfields.add(f);
		    }
		}
		Table  table =u.getAnnotation(Table.class);
		gdbc = "INSERT INTO "+ table.value();
		String Atributelist = "(";
		String Valueslist = "(";
		for(Field field:validfields) {
			Column column = field.getAnnotation(Column.class);
			Atributelist = Atributelist+column.ColumnName()+",";
			Method m = u.getDeclaredMethod("get"+initCap(field.getName()));
			Valueslist = Valueslist +m.invoke(user)+",";
		}
		Atributelist = Atributelist.substring(0,Atributelist.length()-1)+")";
		Valueslist  =Valueslist.substring(0,Valueslist.length()-1)+")";
		gdbc = gdbc+Atributelist+" VALUES " +Valueslist;
		return gdbc;
	}

	/**
     * 根据传入的参数返回插入语句
     * @param users
     * @return 返回插入语句
     */
	/** 插入一个列表应该需要判断插入的对象初始化的变量是否相同，若不相同抛出异常***/
	@Override
	public String insert(List<User> users)throws Exception {
		// TODO Auto-generated method stub
		if(!checkvalid(users)) { throw new Exception("插入属性不明确"); 
		}else {
		String gdbc="" ;
		for(User u:users) {
			gdbc = gdbc+insert(u);
		}
		//设置一个不可能出现的string
		String impossible_str = String.valueOf(Double.MAX_VALUE);
		//替换掉第一个valuse做保护
		gdbc = gdbc.replaceFirst("VALUES",impossible_str);
		//把其他的valuse换成，
		gdbc = gdbc.replace("VALUES", ",");
		//还原第一个valuse;
		gdbc = gdbc.replaceFirst(impossible_str,"VALUES");
		//处理“Insert into 语句”
		String useful_str = gdbc.substring(0, gdbc.indexOf(" VALUES"));
		gdbc = gdbc.replace(useful_str, "");
		gdbc = useful_str +gdbc;
		return gdbc;
		}
	}

	 /**
     * 根据传入的参数返回删除语句（删除id为user.id的记录）
     * @param user
     * @return 返回删除语句
     */
	@Override
	public String delete(User user) throws Exception{
		// TODO Auto-generated method stub
		String gdbc = null;
		Field field = null;
		Method method =null;
		//获得Class类
		Class<?> u = user.getClass();
		//获得所有的成员变量
		Field[] fields = u.getDeclaredFields();
		//为了get修改权限
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//获得所有变量的get方法
		ArrayList<Method> getmethods= getGetMethods(u);
		//检测user的那个成员变量被用于查询
		for(int i =0;i < getmethods.size();i++) {
			//获得
			method =getmethods.get(i);
			if(method.invoke(user)!=null) {
				//不为空说明是需要查询的内容，去掉get
				String searchparam = getmethods.get(i).getName().substring(3);
				//获得对应的成员变量
				field = u.getDeclaredField(initCap_l(searchparam));
				break;
				}
		}
		//获得注释
		Table  table =u.getAnnotation(Table.class);
	    Column column = field.getAnnotation(Column.class);
	    gdbc = "DELETE FROM " +table.value() +" WHERE "+column.ColumnName()+" = "+method.invoke(user);
		return gdbc;
	}

	/**
     * 根据传入的参数返回更新语句（将id为user.id的记录的其它字段更新成user中的对应值）
     * @param user
     * @return 返回更新语句
     */
	@Override
	public String update(User user) throws Exception {
		// TODO Auto-generated method stub
		String gdbc = null;
		String updatecontent =""; 
		//获得Class类
		Class<?> u = user.getClass();
		Method getIdMethod  = u.getDeclaredMethod("getId");;
		//获得所有的成员变量
		Field[] fields = u.getDeclaredFields();
		//为了get修改权限
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//获得所有变量的get方法
		ArrayList<Method> getmethods= getGetMethods(u);
		//获得GETid方法
		
		//检测user的那个成员变量被用于查询
		for(int i =0;i < getmethods.size();i++) {
		//获得
		Method method =getmethods.get(i);
			if(method.invoke(user)!=null&&method.getName()!="getId") {
				updatecontent = updatecontent+" , ";
			//不为空说明是需要更新的内容，去掉get	
			String searchparam = getmethods.get(i).getName().substring(3);
			//获得对应的成员变量
			Field field = u.getDeclaredField(initCap_l(searchparam));
			//生成形如 SET `email` = 'change@123.com'这样的updatecontent语句
			updatecontent = updatecontent+field.getName()+" = "+method.invoke(user);
			}
		}
		updatecontent = updatecontent.replaceFirst(" , ", "");

		//获得注释
		Table  table =u.getAnnotation(Table.class);
	    // print: UPDATE `user` SET `email` = 'change@123.com' WHERE `id` = 1;
	    gdbc = "UPDATE "+table.value()+" SET "+ updatecontent +" WHERE "+" id = " + getIdMethod.invoke(user);
		return gdbc;
	}
	
	
	/***首字母小写变大写***/
	public static String initCap(String str){
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	/***首字母大写变小写***/
	public static String initCap_l(String str) {
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}
	
	/***获得GETmethod方法列表***/
	private ArrayList<Method> getGetMethods(Class<?> u) {
		ArrayList<Method> getmethods = new ArrayList<Method>();  
		Field []fields = u.getDeclaredFields();
		for(Field field:fields) {
			  field.setAccessible(true);
			  String getmethod_name = "get"+initCap(field.getName());
			  try {
					//获得方法getmethod会抛出异常，鉴于接口不抛出异常，所以现抛现处理
					getmethods.add(u.getMethod(getmethod_name));
			   } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}	
		}
		return getmethods;
	}
	
	/***判段传入list的对象时，插入对象的属性是否相同***/
	private boolean checkvalid(List<User>users) throws Exception{
		//获得Class类
		Class<?> u = users.get(0).getClass();
		ArrayList<Method> getmethods = getGetMethods(u);
		HashMap<Method,Integer> map = new HashMap<Method,Integer>();
		for(User user:users) {
			for(Method method: getmethods) {
				if(method.invoke(user)!=null) {
					Integer temp = map.put(method, 1);
					if(temp!=null) {
						map.put(method, temp+1);
					}
				}
			}
		}
		//判断是否map中的key值相同
		int temp = -1;
		for(Entry<Method,Integer> mapper: map.entrySet()){
			int test = temp; 
			temp= mapper.getValue();
			if(test!=-1&&temp!=test) {
				return false;
			}
		}
		return true;
	}
}
