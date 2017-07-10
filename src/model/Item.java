package model;
/**
 * Item 公共类用来给 表格,下拉列表,组件的赋值
 * @author Administrator
 *
 */
public class Item {
	private  String id; //id编号属性;
	private String name;//名字属性
	public Item() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
