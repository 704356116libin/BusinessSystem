package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Item;
import model.ShopProvider;

/**
 * �Ա� tb_shopprovider�Ĺ���
 * 
 * @author Administrator
 *
 */
public class ShopProviderManager {
	private ShopProvider provider;// ��Ӧ�̶���
	private Connection connection;
	private Item item;//ͨ���������õ���ɾ�Ĳ��Ŀ������
	private PreparedStatement sql;//Ԥ�������
	private Vector<Vector<Object>>data=new Vector<>();
	public ShopProviderManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		try {
			sql=connection.prepareStatement("select * from tb_shopprovider");
			ResultSet res=sql.executeQuery();
			while(res.next()){
				Vector<Object>vector=new Vector<>();
				vector.add(res.getString("id"));
				vector.add(res.getString("name"));
				vector.add(res.getString("tel"));
				vector.add(res.getString("address"));
				vector.add(res.getString("fax"));
				vector.add(res.getString("post_code"));
				data.add(vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public Vector<Item> queryItem(){
		Vector<Item>vector=new Vector<>();
		try {
			ResultSet res;
			String id="";
			String name="";
			sql=connection.prepareStatement("select id,name from tb_shopprovider");
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
	public boolean add(String id,String name,String tel,String address,String fax,String post_code) {
		try {
			sql=connection.prepareStatement("insert into tb_shopprovider values(?,?,?,?,?,?)");
			sql.setString(1, id);
			sql.setString(2, name);
			sql.setString(3, tel);
			sql.setString(4, address);
			sql.setString(5, fax);
			sql.setString(6, post_code);
			int row=sql.executeUpdate();
			System.out.println(row+"�����ݱ�Ӱ��");
		} catch (SQLException e) {
			System.out.println("���ݲ������");
			return false;
		}
		return true;
	}

	public boolean delete(String id) {
		try {
			sql=connection.prepareStatement("delete from tb_shopprovider where id=?");
			sql.setString(1, id);
			sql.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updata(String id,String name,String tel,String address,String fax,String post_code) {
		try {
			sql=connection.prepareStatement("update tb_shopprovider set name=?,tel=?,address=?,fax=?,post_code=? where id=?");
			sql.setString(1, name);
			sql.setString(2, tel);
			sql.setString(3, address);
			sql.setString(4, fax);
			sql.setString(5, post_code);
			sql.setString(6, id);
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
