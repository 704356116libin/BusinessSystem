package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BackShop;
import model.Client;
import model.ClientBuy;
import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_client的管理
 * 
 * @author Administrator
 *
 */
public class ClientBuyManager {
	private ClientBuy clientBuy;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	private Vector<ClientBuy>clientBuyData=new Vector<>();
	public ClientBuyManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		ResultSet res;
		try {
			sql=connection.prepareStatement("select * from tb_clientbuy");
			res=sql.executeQuery();
			while(res.next()){
			clientBuy=new ClientBuy();
			clientBuy.setId(res.getString("id"));
			clientBuy.setClient_id(res.getString("client_id"));
			clientBuy.setShop_id(res.getString("shop_id"));
			clientBuy.setNumber(res.getInt("number"));
			clientBuy.setSun_money(res.getDouble("sum_money"));
			clientBuy.setOperitor_id(res.getString("operator_id"));
			clientBuy.setPay_kind(res.getString("pay_kind"));
			clientBuy.setState(res.getString("state"));
			clientBuyData.add(clientBuy);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientBuyData;
	}
	public boolean add(ClientBuy clientBuy) {
		try {
			sql=connection.prepareStatement("insert into tb_clientbuy values(?,?,?,?,?,?,?,?)");
			sql.setString(1, clientBuy.getId());
			sql.setString(2, clientBuy.getClient_id());
			sql.setString(3, clientBuy.getShop_id());
			sql.setInt(4, clientBuy.getNumber());
			sql.setDouble(5, clientBuy.getSun_money());
			sql.setString(6, clientBuy.getPay_kind());
			sql.setString(7, clientBuy.getState());
			sql.setString(8, clientBuy.getOperitor_id());
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
			sql=connection.prepareStatement("delete from tb_clientbuy where id=?");
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

	public boolean updata(ClientBuy clientBuy,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_clientbuy set id=?,client_id=?,shop_id=?,number=?,"
					+ "sum_money=?,pay_kind=?,state=?,operator_id=? where id=?");
			sql.setString(1, clientBuy.getId());
			sql.setString(2, clientBuy.getClient_id());
			sql.setString(3, clientBuy.getShop_id());
			sql.setInt(4, clientBuy.getNumber());
			sql.setDouble(5, clientBuy.getSun_money());
			sql.setString(6, clientBuy.getPay_kind());
			sql.setString(7, clientBuy.getState());
			sql.setString(8, clientBuy.getOperitor_id());
			sql.setString(9, updateId);
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
