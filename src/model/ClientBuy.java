package model;
/**
 * 订货单实体类
 * @author Administrator
 *
 */
public class ClientBuy {
	private String id;
	private String client_id;
	private String shop_id;
	private int number;
	private double sun_money;
	private String pay_kind;
	private String state;
	private String operitor_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSun_money() {
		return sun_money;
	}
	public void setSun_money(double sun_money) {
		this.sun_money = sun_money;
	}
	public String getPay_kind() {
		return pay_kind;
	}
	public void setPay_kind(String pay_kind) {
		this.pay_kind = pay_kind;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOperitor_id() {
		return operitor_id;
	}
	public void setOperitor_id(String operitor_id) {
		this.operitor_id = operitor_id;
	}
	
}
