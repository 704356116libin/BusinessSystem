package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BuyShop;
import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_buyshop的管理
 * 
 * @author Administrator
 *
 */
public class BuyShopManager {
	private BuyShop shop;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	private Vector<BuyShop>shopsData=new Vector<>();
	public BuyShopManager() {
		connection=Dao.connection;
	}
	/**
	 * 查询数据库中该表的所有数据，并返回数据模型对象
	 * @return
	 */
	public Vector query() {
		try {
			sql=connection.prepareStatement("select * from tb_buyshop");
			ResultSet res=sql.executeQuery();
			while(res.next()){
				BuyShop shop=new BuyShop();//实例化管理的数据模型
				shop.setId(res.getString("id"));
				shop.setShop_id(res.getString("shop_id"));
				shop.setNumber(res.getInt("number"));
				shop.setSum_money(res.getDouble("sum_money"));
				
				shop.setDate(res.getString("date"));
				shopsData.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查询出错");
		}
		return shopsData;
	}
	/**
	 * 查询商品id,名称
	 * @return
	 */
	public Vector<Item> queryItem(){
		Vector<Item>vector=new Vector<>();
		try {
			ResultSet res;
			String id="";
			String name="";
			sql=connection.prepareStatement("select id,name from tb_shops");
			res=sql.executeQuery();
			while(res.next()){
				Item item=new Item();
				item.setId(res.getString("id"));
				item.setName(res.getString("name"));
				vector.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	public boolean add(BuyShop shop) {
		try {
			sql=connection.prepareStatement("insert into tb_buyshop values(?,?,?,?,?)");
			sql.setString(1, shop.getId());
			sql.setString(2, shop.getShop_id());
			sql.setInt(3, shop.getNumber());
			sql.setDouble(4, shop.getSum_money());
			sql.setString(5, shop.getDate());
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
			sql=connection.prepareStatement("delete from tb_buyshop where id=?");
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

	public boolean updata(BuyShop shop,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_buyshop set id=?,shop_id=?,number=?,sum_money=?,"
					+ "date=? where id=?");
			sql.setString(1, shop.getId());
			sql.setString(2, shop.getShop_id());
			sql.setInt(3, shop.getNumber());
			sql.setDouble(4, shop.getSum_money());
			sql.setString(5, shop.getDate());
			sql.setString(6, updateId);
			System.out.println(shop.getSum_money());
			int row=sql.executeUpdate();
			System.out.println(row+"行数据受影响");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("请检查商家编号是否重复");
			return false;
		}
		return true;
	}

}
