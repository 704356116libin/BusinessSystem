package dao;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * ��װ���ݿ�����
 * @author Administrator
 *
 */
public class Dao {
	//���ݿ�����Ӷ���
	public static Connection connection=null;
	//���ݿ����������
	protected static String dbClassName="com.mysql.jdbc.Driver";
	//�������ݿ��Url
	protected static String dbUrl="jdbc:mysql://127.0.0.1/db_mydatabase";
	//�������ݿ���û���
	protected static String dbUser="root";
	//�������ݿ������
	protected static String dbPwd="704356116...";
	
	/*
	 * �þ�̬ģ��ǳ�ֵ��ѧϰ��
	 * 	��ʵ���������ʱ�����ȼ��ؾ�̬�����ڵ�����
	 */
	static{
		try {
			if(connection==null){
				System.out.println("�������ݿ�����");
				Class.forName(dbClassName).newInstance();
				connection=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				System.out.println("���سɹ�");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * ��չ��췽������ֹ����Dao���ʵ������
	 */
	private Dao(){
		
	}
}
