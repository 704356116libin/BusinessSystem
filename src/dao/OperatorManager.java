package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BackShop;
import model.Client;
import model.Item;
import model.Operator;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_operator的管理
 * 
 * @author Administrator
 *
 */
public class OperatorManager {
	private Operator operator;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	private Vector<Operator>operatorData=new Vector<>();
	public OperatorManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		ResultSet res;
		try {
			sql=connection.prepareStatement("select * from tb_operator");
			res=sql.executeQuery();
			while(res.next()){
			operator=new Operator();
			operator.setId(res.getString("id"));
			operator.setName(res.getString("name"));
			operator.setDepartment_id(res.getString("department_id"));
			operator.setTel(res.getString("tel"));
			operatorData.add(operator);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operatorData;
	}
	public Vector<Item> queryItem(){
		Vector<Item>vector=new Vector<>();
		try {
			ResultSet res;
			String id="";
			String name="";
			sql=connection.prepareStatement("select id,name from tb_operator");
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
	public boolean add(Operator operator) {
		try {
			sql=connection.prepareStatement("insert into tb_operator values(?,?,?,?)");
			sql.setString(1, operator.getId());
			sql.setString(2, operator.getName());
			sql.setString(3, operator.getTel());
			sql.setString(4, operator.getDepartment_id());
			int row=sql.executeUpdate();
			System.out.println(row+"行数据被影响");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据插入错误");
			return false;
		}
		return true;
	}
	public Object queryNameById(String id){
		ResultSet res;
		String name = null;
		try {
			sql=connection.prepareStatement("select name from tb_operator where id=?");
			sql.setString(1, id);
			res=sql.executeQuery();
			while(res.next()){
				name=res.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("OperatorManager queryNameById：查询错误");
		}
		return name;
	}
	public boolean delete(String id) {
		try {
			sql=connection.prepareStatement("delete from tb_operator where id=?");
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

	public boolean updata(Operator operator,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_operator set id=?,name=?,department_id=?,tel=? where id=?");
			sql.setString(1, operator.getId());
			sql.setString(2, operator.getName());
			sql.setString(3, operator.getDepartment_id());
			sql.setString(4, operator.getTel());
			sql.setString(5, updateId);
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
