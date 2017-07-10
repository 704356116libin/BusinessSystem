package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Item;
import model.Problem;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_shops的管理
 * 
 * @author Administrator
 *
 */
public class ProblemManager {
	private Problem problem;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	public ProblemManager() {
		connection=Dao.connection;
	}
	
	public Vector<Item> queryItem(){
		Vector<Item>vector=new Vector<>();
		try {
			ResultSet res;
			String id="";
			String name="";
			sql=connection.prepareStatement("select id,problem from tb_problem");
			res=sql.executeQuery();
			while(res.next()){
				Item item=new Item();
				item.setId(res.getString("id"));
				item.setName(res.getString("problem"));
				vector.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	

	

}
