package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BackShop;
import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_backshop的管理
 * 
 * @author Administrator
 *
 */
public class BackShopManager {
	private BackShop backShop;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	private Vector<BackShop>bcshopData=new Vector<>();
	public BackShopManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		ResultSet res;
		try {
			sql=connection.prepareStatement("select * from tb_backshop");
			res=sql.executeQuery();
			while(res.next()){
			backShop=new BackShop();
			backShop.setId(res.getString("id"));
			backShop.setShop_id(res.getString("shop_id"));
			backShop.setNumber(res.getInt("number"));
			backShop.setSum_money(res.getDouble("sum_money"));
			backShop.setBack_state(res.getString("back_state"));
			bcshopData.add(backShop);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bcshopData;
	}
	
	public boolean add(BackShop backShop) {
		try {
			sql=connection.prepareStatement("insert into tb_backshop values(?,?,?,?,?)");
			sql.setString(1, backShop.getId());
			sql.setString(2, backShop.getShop_id());
			sql.setInt(3, backShop.getNumber());
			sql.setDouble(4, backShop.getSum_money());
			sql.setString(5, backShop.getBack_state());
			int row=sql.executeUpdate();
			System.out.println(row+"行数据被影响");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据插入错误");
			return false;
		}
		return true;
	}

	public boolean delete(String id) {
		try {
			sql=connection.prepareStatement("delete from tb_backshop where id=?");
			sql.setString(1, id);
			int row=sql.executeUpdate();
			System.out.println(row);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updata(BackShop shop,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_backshop set id=?,shop_id=?,number=?,sum_money=?,back_state=? where id=?");
			sql.setString(1, shop.getId());
			sql.setString(2, backShop.getShop_id());
			sql.setInt(3, backShop.getNumber());
			sql.setDouble(4, backShop.getSum_money());
			sql.setString(5, backShop.getBack_state());
			sql.setString(6, updateId);
			int row=sql.executeUpdate();
			System.out.println(row+"行数据受影响");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("请检查退货编号是否重复");
			return false;
		}
		return true;
	}

}
