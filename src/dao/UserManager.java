package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Item;
import model.ShopProvider;
import model.Shops;
import model.Users;

/**
 * 对表 tb_users的管理
 * 
 * @author Administrator
 *
 */
public class UserManager {
	private Users users;
	private Connection connection;
	private PreparedStatement sql;//预处理对象
	public UserManager() {
		connection=Dao.connection;
	}
	public boolean add(Users users) {
		try {
			sql=connection.prepareStatement("insert into tb_users values(?,?,?,?)");
			sql.setString(1, users.getName());
			sql.setString(2, users.getAnswer());
			sql.setString(3, users.getMima());
			sql.setString(4, users.getProblem_id());
		
			int row=sql.executeUpdate();
			System.out.println(row+"行数据被影响");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据插入错误");
			return false;
		}
		return true;
	}
	public boolean queryByName(String name,String pwd){
		ResultSet res;
		String passworld = null;
		try {
			sql=connection.prepareStatement("select pwd from tb_users where name=?");
			sql.setString(1, name);
			res=sql.executeQuery();
			if(res.wasNull()){
				return false;
			}else{
				while(res.next()){
					passworld=res.getString("pwd");
				}
				if(passworld.equals(pwd)){
					return true;
				}else{
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean delete(String id) {
		return true;
	}

	public boolean updata(Shops shop,String updateId) {
		return true;
	}

}
