package Lab3;

import java.util.List;

public interface SqlUtil {

	   /**
     * ���ݴ���Ĳ������ز�ѯ���
     * @param user
     * @return ���ز�ѯ���
	 * @throws Exception 
     */
    String query(User user) throws Exception;

    /**
     * ���ݴ���Ĳ������ز������
     * @param user
     * @return ���ز������
     * @throws Exception 
     */
    String insert(User user) throws Exception;

    /**
     * ���ݴ���Ĳ������ز������
     * @param users
     * @return ���ز������
     * @throws Exception 
     */
    String insert(List<User> users) throws Exception;

    /**
     * ���ݴ���Ĳ�������ɾ����䣨ɾ��idΪuser.id�ļ�¼��
     * @param user
     * @return ����ɾ�����
     * @throws Exception 
     */
    String delete(User user) throws Exception;
/**
     * ���ݴ���Ĳ������ظ�����䣨��idΪuser.id�ļ�¼�������ֶθ��³�user�еĶ�Ӧֵ��
     * @param user
     * @return ���ظ������
 * @throws Exception 
     */
    String update(User user) throws Exception;

}
