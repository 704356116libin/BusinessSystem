package dao;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * 封装数据库连接
 * @author Administrator
 *
 */
public class Dao {
	//数据库的连接对象
	public static Connection connection=null;
	//数据库的驱动名称
	protected static String dbClassName="com.mysql.jdbc.Driver";
	//连接数据库的Url
	protected static String dbUrl="jdbc:mysql://127.0.0.1/db_mydatabase";
	//访问数据库的用户名
	protected static String dbUser="root";
	//访问数据库的密码
	protected static String dbPwd="704356116...";
	
	/*
	 * 该静态模块非常值得学习：
	 * 	在实例化该类的时候首先加载静态区域内的内容
	 */
	static{
		try {
			if(connection==null){
				System.out.println("加载数据库驱动");
				Class.forName(dbClassName).newInstance();
				connection=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				System.out.println("加载成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 封闭构造方法，禁止创建Dao类的实例对象
	 */
	private Dao(){
		
	}
}
