package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BackShop;
import model.Client;
import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * �Ա� tb_client�Ĺ���
 * 
 * @author Administrator
 *
 */
public class ClientManager {
	private Client client;
	private Connection connection;
	private Item item;//ͨ���������õ���ɾ�Ĳ��Ŀ������
	private PreparedStatement sql;//Ԥ�������
	private Vector<Client>clientData=new Vector<>();
	public ClientManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		ResultSet res;
		try {
			sql=connection.prepareStatement("select * from tb_client");
			res=sql.executeQuery();
			while(res.next()){
			client=new Client();
			client.setId(res.getString("id"));
			client.setName(res.getString("name"));
			client.setAddress(res.getString("address"));
			client.setTel(res.getString("tel"));
			clientData.add(client);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientData;
	}
	public Vector<Item> queryItem(){
		Vector<Item>vector=new Vector<>();
		try {
			ResultSet res;
			String id="";
			String name="";
			sql=connection.prepareStatement("select id,name from tb_client");
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
			sql=connection.prepareStatement("select name from tb_client where id=?");
			sql.setString(1, id);
			res=sql.executeQuery();
			while(res.next()){
				name=res.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ClientManager queryNameById����ѯ����");
		}
		return name;
	}
	public boolean add(Client client) {
		try {
			sql=connection.prepareStatement("insert into tb_client values(?,?,?,?)");
			sql.setString(1, client.getId());
			sql.setString(2, client.getName());
			sql.setString(3, client.getAddress());
			sql.setString(4, client.getTel());
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
			sql=connection.prepareStatement("delete from tb_client where id=?");
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

	public boolean updata(Client client,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_client set id=?,name=?,address=?,tel=? where id=?");
			sql.setString(1, client.getId());
			sql.setString(2, client.getName());
			sql.setString(3, client.getAddress());
			sql.setString(4, client.getTel());
			sql.setString(5, updateId);
			int row=sql.executeUpdate();
			System.out.println(row+"��������Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�����˻�����Ƿ��ظ�");
			return false;
		}
		return true;
	}

}
