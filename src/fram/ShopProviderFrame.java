package fram;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
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

import dao.ShopProviderManager;

public class ShopProviderFrame extends JInternalFrame{
	private JTextField field_id;//��ʾ��Ӧ�̱�ŵ��ı�
	private JTextField field_name;//
	private JTextField field_tel;
	private JTextField field_address;
	private JTextField field_fax;
	private JTextField field_postcode;
	private JLabel id_label;
	private JLabel name_label;
	private JLabel address_label;
	private JLabel tel_label;
	private JLabel postcode_label;
	private JLabel fax_label;
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
	private static final String columns[]={"�̼ұ��","�̼�����","�绰","��ַ","����","�ʱ�"};
	private Vector<Vector<Object>>tabelData=new Vector<>();
	private Vector<Object>columnName=new Vector<>();
	//������¼�ı��������
	String id;
	String name;
	String tel;
	String address;
	String fax;
	String post_code;
	private ShopProviderManager providerManager;//������ѯ����
	private int row;
	public ShopProviderFrame() {
		setTitle("��Ӧ��");
		setClosable(true);
		setResizable(false);
		setBounds(5, 5, 700, 400);
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
		providerManager=new ShopProviderManager();//����ʵ�������ݹ������
		Font font=new Font("����", Font.PLAIN, 12);
		add_but=new JButton("���");
		add_but.setSize(40, 30);
		add_but.setFont(font);
		delete_but=new JButton("ɾ��");
		delete_but.setFont(font);
		delete_but.setSize(10, 30);
		updata_but=new JButton("����");
		updata_but.setFont(font);
		field_id=new JTextField();id_label=new JLabel("�̼ұ��:");
		field_name=new JTextField();name_label=new JLabel("�̼�����:");
		field_fax=new JTextField();fax_label=new JLabel("�̼Ҵ���:");
		field_address=new JTextField();address_label=new JLabel("�̼ҵ�ַ:");
		field_postcode=new JTextField();postcode_label=new JLabel("�̼��ʱ�:");
		field_tel=new JTextField();tel_label=new JLabel("�̼ҵ绰:");
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
				tel=field_tel.getText();
				address=field_address.getText();
				fax=field_fax.getText();
				post_code=field_postcode.getText();
				if(id.isEmpty()||name.isEmpty()||tel.isEmpty()||address.isEmpty()
						||fax.isEmpty()||post_code.isEmpty()){
					System.out.println("�����Ƿ�©����Ϣ.");
				}else{
					Object[]data={id,name,tel,address,fax,post_code};
					if(providerManager.add(id, name, tel, address, fax, post_code)){
						tableModel.insertRow(row, data);
					}else{
						System.out.println("���������̼ұ���Ƿ��ظ�");
					}
					
				}
			}
		});
		updata_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id=field_id.getText();
				String name=field_name.getText();
				String tel=field_tel.getText();
				String address=field_address.getText();
				String fax=field_fax.getText();
				String post_code=field_postcode.getText();
				if(providerManager.updata(id, name, tel, address, fax, post_code)){
					tableModel.setValueAt(id, row, 0);
					tableModel.setValueAt(name, row, 1);
					tableModel.setValueAt(tel, row, 2);
					tableModel.setValueAt(address, row, 3);
					tableModel.setValueAt(fax, row,4);
					tableModel.setValueAt(post_code, row, 5);
				}else{
					
				}
				 
			}
		});
		delete_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					if(providerManager.delete(id)){
						tableModel.removeRow(row);
					}
					else{
						System.out.println("����ɾ���쳣����");
					}
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
		showPanelLayout.putConstraint(SpringLayout.EAST, field_id, -390, SpringLayout.EAST, showPanel);
		
		showPanel.add(name_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, name_label, 5, SpringLayout.EAST, field_id);
		showPanelLayout.putConstraint(SpringLayout.NORTH, name_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_name);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_name, 5, SpringLayout.EAST, name_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_name, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_name, -190, SpringLayout.EAST, showPanel);
		showPanel.add(tel_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, tel_label, 5, SpringLayout.EAST, field_name);
		showPanelLayout.putConstraint(SpringLayout.NORTH, tel_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_tel);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_tel, 5, SpringLayout.EAST, tel_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_tel, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_tel, -10, SpringLayout.EAST, showPanel);
		
		showPanel.add(address_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, address_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, address_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_address);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_address, 5, SpringLayout.EAST, address_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_address, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_address, -390, SpringLayout.EAST, showPanel);
		
		showPanel.add(fax_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, fax_label, 5, SpringLayout.EAST, field_address);
		showPanelLayout.putConstraint(SpringLayout.NORTH, fax_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_fax);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_fax, 5, SpringLayout.EAST, fax_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_fax, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_fax, -190, SpringLayout.EAST, showPanel);
		showPanel.add(postcode_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, postcode_label, 5, SpringLayout.EAST, field_fax);
		showPanelLayout.putConstraint(SpringLayout.NORTH, postcode_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_postcode);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_postcode, 5, SpringLayout.EAST, postcode_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_postcode, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_postcode, -10, SpringLayout.EAST, showPanel);
		
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
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 0, SpringLayout.WEST, postcode_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, postcode_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, butpanel, -10, SpringLayout.EAST, showPanel);
	}
	/**
	 * ���ĳ�ʼ��
	 */
	private void addTable() {
		for(int i=0;i<columns.length;i++){
			columnName.add(columns[i]);
		}
		tabelData=providerManager.query();
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
				 tel=(String) tableModel.getValueAt(row, 2);
				 address=(String) tableModel.getValueAt(row, 3);
				 fax=(String) tableModel.getValueAt(row, 4);
				 post_code=(String) tableModel.getValueAt(row, 5);
				 System.out.println(id+" "+name+" ");
				field_id.setText(id);
				field_name.setText(name);
				field_tel.setText(tel);
				field_address.setText(address);
				if(fax==null){
					field_fax.setText("��");
				}else{
					field_fax.setText(fax.toString()+"");
				}
				
				field_postcode.setText(post_code.toString());
				
			}
			
		});
	}
	
}
