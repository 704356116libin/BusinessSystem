package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * �Ա� tb_shops�Ĺ���
 * 
 * @author Administrator
 *
 */
public class ShopManager {
	private Shops shop;
	private Connection connection;
	private Item item;//ͨ���������õ���ɾ�Ĳ��Ŀ������
	private PreparedStatement sql;//Ԥ�������
	private Vector<Shops>shopsData=new Vector<>();
	public ShopManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		try {
			sql=connection.prepareStatement("select * from tb_shops");
			ResultSet res=sql.executeQuery();
			while(res.next()){
				shop=new Shops();
				shop.setId(res.getString("id"));
				shop.setName(res.getString("name"));
				shop.setPrice(res.getDouble("price"));
				shop.setModel(res.getString("model"));
				shop.setPh(res.getString("ph"));
				shop.setPzwh(res.getString("pzwh"));
				shop.setProvider_id(res.getString("provider_id"));
				shop.setNumber(res.getInt("number"));
				shopsData.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ѯ����");
		}
		return shopsData;
	}
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
	public Object queryNameById(String id){
		ResultSet res;
		String name = null;
		try {
			sql=connection.prepareStatement("select name from tb_shops where id=?");
			sql.setString(1, id);
			res=sql.executeQuery();
			while(res.next()){
				name=res.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ShopManager queryNameById����ѯ����");
		}
		return name;
	}
	public boolean add(Shops shop) {
		try {
			sql=connection.prepareStatement("insert into tb_shops values(?,?,?,?,?,?,?,?)");
			sql.setString(1, shop.getId());
			sql.setString(2, shop.getName());
			sql.setDouble(3, shop.getPrice());
			sql.setString(4, shop.getModel());
			sql.setString(5, shop.getPh());
			sql.setString(6, shop.getPzwh());
			sql.setString(7, shop.getProvider_id());
			sql.setInt(8, shop.getNumber());
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
			sql=connection.prepareStatement("delete from tb_shops where id=?");
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

	public boolean updata(Shops shop,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_shops set id=?,name=?,price=?,model=?,ph=?,pzwh=?"
					+ ",provider_id=?,number=? where id=?");
			sql.setString(1, shop.getId());
			sql.setString(2, shop.getName());
			sql.setDouble(3, shop.getPrice());
			sql.setString(4, shop.getModel());
			sql.setString(5, shop.getPh());
			sql.setString(6, shop.getPzwh());
			sql.setString(7, shop.getProvider_id());
			sql.setInt(8, shop.getNumber());
			sql.setString(9, updateId);
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
