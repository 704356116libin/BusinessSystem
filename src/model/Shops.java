package model;
/**
 * ��Ʒ��ʵ����
 * @author Administrator
 *
 */
public class Shops {
	private String id;
	private String  name;
	private double  price;
	private String model;//���
	private String ph;//��Ʒ����
	private String pzwh;//��Ʒ��׼�ĺ�
	private String provider_id;//�����̱��
	private int number;//�������
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
