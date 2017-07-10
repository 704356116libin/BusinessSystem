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
 * �Ա� tb_buyshop�Ĺ���
 * 
 * @author Administrator
 *
 */
public class BuyShopManager {
	private BuyShop shop;
	private Connection connection;
	private Item item;//ͨ���������õ���ɾ�Ĳ��Ŀ������
	private PreparedStatement sql;//Ԥ�������
	private Vector<BuyShop>shopsData=new Vector<>();
	public BuyShopManager() {
		connection=Dao.connection;
	}
	/**
	 * ��ѯ���ݿ��иñ���������ݣ�����������ģ�Ͷ���
	 * @return
	 */
	public Vector query() {
		try {
			sql=connection.prepareStatement("select * from tb_buyshop");
			ResultSet res=sql.executeQuery();
			while(res.next()){
				BuyShop shop=new BuyShop();//ʵ�������������ģ��
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
			System.out.println("��ѯ����");
		}
		return shopsData;
	}
	/**
	 * ��ѯ��Ʒid,����
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
			System.out.println(row+"�����ݱ�Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݲ������");
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
			System.out.println(row+"��������Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�����̼ұ���Ƿ��ظ�");
			return false;
		}
		return true;
	}

}
