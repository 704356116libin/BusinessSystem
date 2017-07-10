package model;
/**
 * 供货商的实体类
 * @author Administrator
 *
 */
public class ShopProvider {
	private String id;//供货商的编码
	private String name;//供货商的名字
	private String tel;//供货商的电话
	private String address;//供货商的地址
	private String fax;//供货商的传真
	private String post_dode;//供货商的邮编
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPost_dode() {
		return post_dode;
	}
	public void setPost_dode(String post_dode) {
		this.post_dode = post_dode;
	}
	
	
	
}
