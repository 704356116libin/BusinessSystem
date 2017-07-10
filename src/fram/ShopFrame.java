package fram;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.ShopManager;
import dao.ShopProviderManager;
import model.Item;
import model.Shops;

public class ShopFrame extends JInternalFrame{
	private JTextField field_id;//��ʾ��Ӧ�̱�ŵ��ı�
	private JTextField field_name;//
	private JTextField field_price;
	private JTextField field_ph;
	private JTextField field_pzwh;
	private JTextField field_model;
	private JTextField field_number;
	private JLabel id_label;
	private JLabel name_label;
	private JLabel ph_label;
	private JLabel price_label;
	private JLabel model_label;
	private JLabel pzwh_label;
	private JComboBox<Object> pname_box;//�̼�����ѡ����
	private JLabel pname_label;//�����̱�ǩ
	private JLabel number_label;//�����̱�ǩ
	private JButton add_but;
	private JButton delete_but;
	private JButton updata_but;
	private JPanel showPanel;//ʢ����ʾ�ı���Ŀؼ�
	private SpringLayout showPanelLayout;//showPanel�Ĳ��ַ�ʽ
	private JScrollPane tablePanel;//ʢ�ű�������
	private DefaultTableModel tableModel;
	private JTable table;
	private Container container;//�˴���Ķ�������
	private SpringLayout containerLayout;
	private static final String columns[]={"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ���","����","��׼�ĺ�","���ұ��","�������"};
	private Vector<Vector<Object>>tabelData=new Vector<>();
	private Vector<Object>columnName=new Vector<>();
	private Vector<Object>pnameVector=new Vector<>();
	private Vector<Shops>shopsData=new Vector<>();
	private Vector<Item>itemVector;
	//������¼�ı��������
	String id;
	String name;
	String price;
	String model;
	String ph;
	String pzwh;
	String number;
	String provider_id;
	private ShopProviderManager providerManager;
	private ShopManager shopManager;
	private int row;//ѡ���������
	public ShopFrame() {
		setTitle("��Ʒ��");
		setResizable(false);
		setBounds(20, 20, 700, 400);
		container=getContentPane();
		containerLayout=new SpringLayout();
		container.setLayout(containerLayout);
		init();
		setVisible(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	/**
	 * ������Ϣ�ĳ�ʼ��
	 */
	private void init() {
		//��ѯ�̼�����
		queryProviderItem();
		//��ѯ��Ʒ��Ϣ
		queryShops();
		Font font=new Font("����", Font.PLAIN, 12);
		add_but=new JButton("���");
		add_but.setSize(40, 30);
		add_but.setFont(font);
		delete_but=new JButton("ɾ��");
		delete_but.setFont(font);
		delete_but.setSize(10, 30);
		updata_but=new JButton("����");
		updata_but.setFont(font);
		field_id=new JTextField();id_label=new JLabel("��Ʒ���:");
		field_name=new JTextField();name_label=new JLabel("��Ʒ����:");
		field_pzwh=new JTextField();pzwh_label=new JLabel("��׼�ĺ�:");
		field_ph=new JTextField();ph_label=new JLabel("��Ʒ����:");
		field_model=new JTextField();model_label=new JLabel("��Ʒ���:");
		field_price=new JTextField();price_label=new JLabel("��Ʒ�۸�:");
		field_number=new JTextField();number_label=new JLabel("�������:");
		pname_box=new JComboBox<>(pnameVector);pname_label=new JLabel("��������:");
		tablePanel=new JScrollPane();//��񻬶����
		tablePanel.setBackground(Color.white);
		/*===================��ʾ�̼���Ϣ������������==============*/
		addShowPanel();
		/*===================��ʾtb_shopprovider�ı���������==============*/
		addTable();
		
		//���뵽��������
		addToContainer();
		//ע���¼�����
		registerListener();
	}
	public void queryShops() {
		shopManager=new ShopManager();
		shopsData=shopManager.query();
		for(Shops shops:shopsData){
			Vector<Object>vector=new Vector<>();
			vector.add(shops.getId());
			vector.add(shops.getName());
			vector.add(shops.getPrice());
			vector.add(shops.getModel());
			vector.add(shops.getPh());
			vector.add(shops.getPzwh());
			vector.add(shops.getProvider_id());
			vector.add(shops.getNumber());
			tabelData.add(vector);
		}
		
	}
	public void queryProviderItem() {
		providerManager=new ShopProviderManager();
		itemVector=providerManager.queryItem();
		//���������б������
		for(Item item:itemVector){
			pnameVector.add(item.getName());
		}
	}
	/**
	 * ���,�޸�
	 */
	private void registerListener() {
		//�������ļ���
		
		//��Ӱ�ť�ļ���
		add_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id=field_id.getText();
				name=field_name.getText();
				price=field_price.getText();
				model=field_model.getText();
				ph=field_ph.getText();
				pzwh=field_pzwh.getText();
				number=field_number.getText();
				if(id.isEmpty()||name.isEmpty()||price.isEmpty()||model.isEmpty()
						||ph.isEmpty()||pzwh.isEmpty()||provider_id.isEmpty()||number.isEmpty()){
					System.out.println("�����Ƿ�����ϢΪ��");
				}else{
					Object[]rowdata={id,name,price,model,ph,pzwh,provider_id,number};
					Shops shop=new Shops();
					shop.setId(id);
					shop.setName(name);
					shop.setModel(model);
					shop.setNumber(Integer.valueOf(number));
					shop.setPh(ph);
					shop.setPzwh(pzwh);
					shop.setPrice(Double.valueOf(price));
					shop.setProvider_id(provider_id);
					if(shopManager.add(shop)){
						tableModel.insertRow(row, rowdata);
					}
				}
			}
		});
		updata_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String updateId=id;
				id=field_id.getText();
				name=field_name.getText();
				price=field_price.getText();
				model=field_model.getText();
				ph=field_ph.getText();
				pzwh=field_pzwh.getText();
				number=field_number.getText();
				System.out.println(id+"-"+name+"-"+price+"-"+model+"-"+ph+"-"+pzwh+"-"+number+provider_id);
				if(updateId.isEmpty()||id.isEmpty()||name.isEmpty()||price.isEmpty()||model.isEmpty()
						||ph.isEmpty()||pzwh.isEmpty()||provider_id.isEmpty()||number.isEmpty()){
					System.out.println("�����Ƿ�����ϢΪ��");
				}else{
					Object[]rowdata={id,name,price,model,ph,pzwh,provider_id,number};
					Shops shop=new Shops();
					shop.setId(id);
					shop.setName(name);
					shop.setModel(model);
					shop.setNumber(Integer.valueOf(number));
					shop.setPh(ph);
					shop.setPzwh(pzwh);
					shop.setPrice(Double.valueOf(price));
					shop.setProvider_id(provider_id);
					if(shopManager.updata(shop, updateId)){
						tableModel.setValueAt(id, row, 0);
						tableModel.setValueAt(name, row, 1);
						tableModel.setValueAt(price, row, 2);
						tableModel.setValueAt(model, row, 3);
						tableModel.setValueAt(ph, row, 4);
						tableModel.setValueAt(pzwh, row, 5);
						tableModel.setValueAt(provider_id, row, 6);
						tableModel.setValueAt(number, row, 7);
					}
				}
			}
		});
		delete_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(shopManager.delete(id)){
					tableModel.removeRow(row);
				}
			}
		});
		//�����б�ļ���
		pname_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					provider_id=itemVector.get(pname_box.getSelectedIndex()).getId();
					System.out.println(provider_id);
				}
				
			}
		});
	}	
	/**
	 * �������ӽ���������
	 */
	private void addToContainer() {
		container.add(tablePanel);
		containerLayout.putConstraint(SpringLayout.NORTH, tablePanel, 100, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, tablePanel, 0, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.EAST, tablePanel, 0, SpringLayout.EAST, container);
		containerLayout.putConstraint(SpringLayout.SOUTH, tablePanel, 0, SpringLayout.SOUTH, container);
		
		container.add(showPanel);
		containerLayout.putConstraint(SpringLayout.NORTH, showPanel, 0, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, showPanel, 0, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.EAST, showPanel, 0, SpringLayout.EAST, container);
		containerLayout.putConstraint(SpringLayout.SOUTH, showPanel, 0, SpringLayout.NORTH, tablePanel);
	}
	/**
	 * ��Ϣչʾ���ĳ�ʼ��
	 */
	private void addShowPanel() {
		showPanel=new JPanel();
		showPanelLayout=new SpringLayout();
		showPanel.setLayout(showPanelLayout);
		showPanel.setBackground(new Color(205, 201, 201));
		showPanel.add(id_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, id_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, id_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_id);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_id, 5, SpringLayout.EAST, id_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_id, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_id, -430, SpringLayout.EAST, showPanel);
		
		showPanel.add(name_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, name_label, 5, SpringLayout.EAST, field_id);
		showPanelLayout.putConstraint(SpringLayout.NORTH, name_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_name);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_name, 5, SpringLayout.EAST, name_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_name, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_name, -190, SpringLayout.EAST, showPanel);
		showPanel.add(price_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, price_label, 5, SpringLayout.EAST, field_name);
		showPanelLayout.putConstraint(SpringLayout.NORTH, price_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_price);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_price, 5, SpringLayout.EAST, price_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_price, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_price, -10, SpringLayout.EAST, showPanel);
		
		showPanel.add(ph_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, ph_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, ph_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_ph);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_ph, 5, SpringLayout.EAST, ph_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_ph, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_ph, -430, SpringLayout.EAST, showPanel);
		
		showPanel.add(pzwh_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, pzwh_label, 5, SpringLayout.EAST, field_ph);
		showPanelLayout.putConstraint(SpringLayout.NORTH, pzwh_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_pzwh);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_pzwh, 5, SpringLayout.EAST, pzwh_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_pzwh, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_pzwh, -190, SpringLayout.EAST, showPanel);
		showPanel.add(model_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, model_label, 5, SpringLayout.EAST, field_pzwh);
		showPanelLayout.putConstraint(SpringLayout.NORTH, model_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_model);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_model, 5, SpringLayout.EAST, model_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_model, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_model, -10, SpringLayout.EAST, showPanel);
		showPanel.add(pname_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, pname_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, pname_label, 10, SpringLayout.SOUTH, ph_label);
		showPanel.add(pname_box);
		showPanelLayout.putConstraint(SpringLayout.WEST, pname_box, 5, SpringLayout.EAST, pname_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, pname_box, 10, SpringLayout.SOUTH, ph_label);
		showPanel.add(number_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, number_label, 0, SpringLayout.WEST, pzwh_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, number_label, 10, SpringLayout.SOUTH, pzwh_label);
		showPanel.add(field_number);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_number, 5, SpringLayout.EAST, number_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_number, 10, SpringLayout.SOUTH, pzwh_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_number, -190, SpringLayout.EAST, showPanel);
		JPanel butpanel=new JPanel();
		GridBagLayout bagLayout=new GridBagLayout();
		butpanel.setLayout(bagLayout);
		GridBagConstraints constraints1=new GridBagConstraints();
		constraints1.gridx=0;
		constraints1.gridy=0;
		constraints1.gridwidth=1;
		butpanel.add(add_but, constraints1);
		GridBagConstraints constraints2=new GridBagConstraints();
		constraints2.gridx=1;
		constraints2.gridy=0;
		constraints2.gridwidth=1;
		butpanel.add(updata_but, constraints2);
		GridBagConstraints constraints3=new GridBagConstraints();
		constraints3.gridx=2;
		constraints3.gridy=0;
		constraints3.gridwidth=1;
		butpanel.add(delete_but, constraints3);
		showPanel.add(butpanel);
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 0, SpringLayout.WEST, model_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, model_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, butpanel, -10, SpringLayout.EAST, showPanel);
	}
	/**
	 * ���ĳ�ʼ��
	 */
	private void addTable() {
		for(int i=0;i<columns.length;i++){
			columnName.add(columns[i]);
		}
		tableModel=new DefaultTableModel(tabelData,columnName);
		table=new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablePanel.add(table);
		tablePanel.setViewportView(table);
		//����table
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				row=table.getSelectedRow();
				id=(String) tableModel.getValueAt(row, 0);
				name=(String) tableModel.getValueAt(row, 1);
				price=  tableModel.getValueAt(row, 2)+"";
				model=(String) tableModel.getValueAt(row, 3);
				ph=(String) tableModel.getValueAt(row, 4);
				pzwh=(String) tableModel.getValueAt(row, 5);
				number= tableModel.getValueAt(row,7)+"";
				field_id.setText(id);
				field_name.setText(name);
				field_model.setText(model);
				field_number.setText(number);
				field_ph.setText(ph);
				field_pzwh.setText(pzwh);
				field_price.setText(price);
				
			}
			
		});
	}
}
