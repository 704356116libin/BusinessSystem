package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.BackShop;
import model.Client;
import model.Department;
import model.Item;
import model.ShopProvider;
import model.Shops;

/**
 * 对表 tb_department的管理
 * 
 * @author Administrator
 *
 */
public class DepartmentManager {
	private Department department;
	private Connection connection;
	private Item item;//通过该项来得到增删改查的目标数据
	private PreparedStatement sql;//预处理对象
	private Vector<Department>departmentData=new Vector<>();
	private Vector<Item>itemVector=new Vector<>();
	public DepartmentManager() {
		connection=Dao.connection;
	}
	public Vector query() {
		ResultSet res;
		try {
			sql=connection.prepareStatement("select * from tb_department");
			res=sql.executeQuery();
			while(res.next()){
			department=new Department();
			department.setId(res.getString("id"));
			department.setName(res.getString("name"));
			departmentData.add(department);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departmentData;
	}
	public Vector<Item> queryItem(){
		try {
			sql=connection.prepareStatement("select id,name from tb_department");
			ResultSet res=sql.executeQuery();
			while(res.next()){
				item=new Item();
				item.setId(res.getString("id"));
				item.setName(res.getString("name"));
				itemVector.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemVector;
	}
	public boolean add(Department department) {
		try {
			sql=connection.prepareStatement("insert into tb_department values(?,?)");
			sql.setString(1, department.getId());
			sql.setString(2, department.getName());
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
			sql=connection.prepareStatement("delete from tb_department where id=?");
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

	public boolean updata(Department department,String updateId) {
		try {
			sql=connection.prepareStatement("update tb_department set id=?,name=? where id=?");
			sql.setString(1, department.getId());
			sql.setString(2, department.getName());
			sql.setString(3, updateId);
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
