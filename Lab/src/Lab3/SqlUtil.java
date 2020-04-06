package Lab3;

import java.util.List;

public interface SqlUtil {

	   /**
     * 根据传入的参数返回查询语句
     * @param user
     * @return 返回查询语句
	 * @throws Exception 
     */
    String query(User user) throws Exception;

    /**
     * 根据传入的参数返回插入语句
     * @param user
     * @return 返回插入语句
     * @throws Exception 
     */
    String insert(User user) throws Exception;

    /**
     * 根据传入的参数返回插入语句
     * @param users
     * @return 返回插入语句
     * @throws Exception 
     */
    String insert(List<User> users) throws Exception;

    /**
     * 根据传入的参数返回删除语句（删除id为user.id的记录）
     * @param user
     * @return 返回删除语句
     * @throws Exception 
     */
    String delete(User user) throws Exception;
/**
     * 根据传入的参数返回更新语句（将id为user.id的记录的其它字段更新成user中的对应值）
     * @param user
     * @return 返回更新语句
 * @throws Exception 
     */
    String update(User user) throws Exception;

}
