package Lab3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MyUtil implements SqlUtil {

	 
	/**
     * ���ݴ���Ĳ������ز�ѯ���
     * @param user
     * @return ���ز�ѯ���
     */	
	@Override
	public String query(User user) throws Exception {
		// TODO Auto-generated method stub
		String gdbc = null;
		Field field = null;
		Method method =null;
		//���Class��
		Class<?> u = user.getClass();
		//������еĳ�Ա����
		Field[] fields = u.getDeclaredFields();
		//Ϊ��get�޸�Ȩ��
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//������б�����get����
		ArrayList<Method> getmethods= getGetMethods(u);
		//���user���Ǹ���Ա���������ڲ�ѯ
		for(int i =0;i < getmethods.size();i++) {
			//���
			method =getmethods.get(i);
			if(method.invoke(user)!=null) {
				//��Ϊ��˵������Ҫ��ѯ�����ݣ�ȥ��get
				String searchparam = getmethods.get(i).getName().substring(3);
				//��ö�Ӧ�ĳ�Ա����
				field = u.getDeclaredField(initCap_l(searchparam));
				break;
				}
		}
		//���ע��
		Table  table =u.getAnnotation(Table.class);
	    Column column = field.getAnnotation(Column.class);
	    
	    //��Ҫ�ж������ֻ����ַ�
	    if(column.ColumnName().equals("id")||column.ColumnName().equals("age")) {
	    gdbc = "SELECT * FROM " + table.value()+ " WHERE " + column.ColumnName() + " = "+method.invoke(user);
	    }else {
	    //System.out.println(column.ColumnName());
	    gdbc = "SELECT * FROM " + table.value()+ " WHERE " + column.ColumnName() + " LIKE "+method.invoke(user);
	    }
		return gdbc;
	}
	
	 /**
     * ���ݴ���Ĳ������ز������
     * @param user
     * @return ���ز������
     */
	// print: INSERT INTO `user` (`username`, `telephone`, `email`, `age`) VALUES ('user', '12345678123', 'user@123.com', 20)
	@Override
	public String insert(User user) throws Exception{
		// TODO Auto-generated method stub
		String gdbc = null;
		//���Class��
		Class<?> u = user.getClass();
		//������еĳ�Ա����
		Field[] fields = u.getDeclaredFields();
		//Ϊ��get�޸�Ȩ��
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//������б�����get����
		ArrayList<Method> getmethods= getGetMethods(u);
		//��Ҫ�ж�Ҫ�����user��ʼ����ʲô��Ա����
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
     * ���ݴ���Ĳ������ز������
     * @param users
     * @return ���ز������
     */
	/** ����һ���б�Ӧ����Ҫ�жϲ���Ķ����ʼ���ı����Ƿ���ͬ��������ͬ�׳��쳣***/
	@Override
	public String insert(List<User> users)throws Exception {
		// TODO Auto-generated method stub
		if(!checkvalid(users)) { throw new Exception("�������Բ���ȷ"); 
		}else {
		String gdbc="" ;
		for(User u:users) {
			gdbc = gdbc+insert(u);
		}
		//����һ�������ܳ��ֵ�string
		String impossible_str = String.valueOf(Double.MAX_VALUE);
		//�滻����һ��valuse������
		gdbc = gdbc.replaceFirst("VALUES",impossible_str);
		//��������valuse���ɣ�
		gdbc = gdbc.replace("VALUES", ",");
		//��ԭ��һ��valuse;
		gdbc = gdbc.replaceFirst(impossible_str,"VALUES");
		//����Insert into ��䡱
		String useful_str = gdbc.substring(0, gdbc.indexOf(" VALUES"));
		gdbc = gdbc.replace(useful_str, "");
		gdbc = useful_str +gdbc;
		return gdbc;
		}
	}

	 /**
     * ���ݴ���Ĳ�������ɾ����䣨ɾ��idΪuser.id�ļ�¼��
     * @param user
     * @return ����ɾ�����
     */
	@Override
	public String delete(User user) throws Exception{
		// TODO Auto-generated method stub
		String gdbc = null;
		Field field = null;
		Method method =null;
		//���Class��
		Class<?> u = user.getClass();
		//������еĳ�Ա����
		Field[] fields = u.getDeclaredFields();
		//Ϊ��get�޸�Ȩ��
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//������б�����get����
		ArrayList<Method> getmethods= getGetMethods(u);
		//���user���Ǹ���Ա���������ڲ�ѯ
		for(int i =0;i < getmethods.size();i++) {
			//���
			method =getmethods.get(i);
			if(method.invoke(user)!=null) {
				//��Ϊ��˵������Ҫ��ѯ�����ݣ�ȥ��get
				String searchparam = getmethods.get(i).getName().substring(3);
				//��ö�Ӧ�ĳ�Ա����
				field = u.getDeclaredField(initCap_l(searchparam));
				break;
				}
		}
		//���ע��
		Table  table =u.getAnnotation(Table.class);
	    Column column = field.getAnnotation(Column.class);
	    gdbc = "DELETE FROM " +table.value() +" WHERE "+column.ColumnName()+" = "+method.invoke(user);
		return gdbc;
	}

	/**
     * ���ݴ���Ĳ������ظ�����䣨��idΪuser.id�ļ�¼�������ֶθ��³�user�еĶ�Ӧֵ��
     * @param user
     * @return ���ظ������
     */
	@Override
	public String update(User user) throws Exception {
		// TODO Auto-generated method stub
		String gdbc = null;
		String updatecontent =""; 
		//���Class��
		Class<?> u = user.getClass();
		Method getIdMethod  = u.getDeclaredMethod("getId");;
		//������еĳ�Ա����
		Field[] fields = u.getDeclaredFields();
		//Ϊ��get�޸�Ȩ��
		for(Field f :fields) {
			  f.setAccessible(true);
		}
		//������б�����get����
		ArrayList<Method> getmethods= getGetMethods(u);
		//���GETid����
		
		//���user���Ǹ���Ա���������ڲ�ѯ
		for(int i =0;i < getmethods.size();i++) {
		//���
		Method method =getmethods.get(i);
			if(method.invoke(user)!=null&&method.getName()!="getId") {
				updatecontent = updatecontent+" , ";
			//��Ϊ��˵������Ҫ���µ����ݣ�ȥ��get	
			String searchparam = getmethods.get(i).getName().substring(3);
			//��ö�Ӧ�ĳ�Ա����
			Field field = u.getDeclaredField(initCap_l(searchparam));
			//�������� SET `email` = 'change@123.com'������updatecontent���
			updatecontent = updatecontent+field.getName()+" = "+method.invoke(user);
			}
		}
		updatecontent = updatecontent.replaceFirst(" , ", "");

		//���ע��
		Table  table =u.getAnnotation(Table.class);
	    // print: UPDATE `user` SET `email` = 'change@123.com' WHERE `id` = 1;
	    gdbc = "UPDATE "+table.value()+" SET "+ updatecontent +" WHERE "+" id = " + getIdMethod.invoke(user);
		return gdbc;
	}
	
	
	/***����ĸСд���д***/
	public static String initCap(String str){
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	/***����ĸ��д��Сд***/
	public static String initCap_l(String str) {
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}
	
	/***���GETmethod�����б�***/
	private ArrayList<Method> getGetMethods(Class<?> u) {
		ArrayList<Method> getmethods = new ArrayList<Method>();  
		Field []fields = u.getDeclaredFields();
		for(Field field:fields) {
			  field.setAccessible(true);
			  String getmethod_name = "get"+initCap(field.getName());
			  try {
					//��÷���getmethod���׳��쳣�����ڽӿڲ��׳��쳣�����������ִ���
					getmethods.add(u.getMethod(getmethod_name));
			   } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}	
		}
		return getmethods;
	}
	
	/***�жδ���list�Ķ���ʱ���������������Ƿ���ͬ***/
	private boolean checkvalid(List<User>users) throws Exception{
		//���Class��
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
		//�ж��Ƿ�map�е�keyֵ��ͬ
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
