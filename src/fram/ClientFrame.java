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

import dao.ClientManager;
import dao.ShopProviderManager;
import model.Client;

public class ClientFrame extends JInternalFrame{
	private JTextField field_id;//��ʾ��Ӧ�̱�ŵ��ı�
	private JTextField field_name;//
	private JTextField field_tel;
	private JTextField field_address;
	private JLabel id_label;
	private JLabel name_label;
	private JLabel address_label;
	private JLabel tel_label;
	
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
	private static final String columns[]={"�ͻ����","�ͻ�����","�ͻ��绰","�ͻ���ַ"};
	private Vector<Vector<Object>>tabelData=new Vector<>();
	private Vector<Client>clientVector=new Vector<>();
	private Vector<Object>columnName=new Vector<>();
	//������¼�ı��������
	String id;
	String name;
	String tel;
	String address;
	private ClientManager clientManager;//������ѯ����
	private int row;
	public ClientFrame() {
		setTitle("�ͻ���");
//		setClosable(true);
		setResizable(false);
		setBounds(110, 110, 700, 400);
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
		clientManager=new ClientManager();//����ʵ�������ݹ������
		Font font=new Font("����", Font.PLAIN, 12);
		add_but=new JButton("���");
		add_but.setSize(40, 30);
		add_but.setFont(font);
		delete_but=new JButton("ɾ��");
		delete_but.setFont(font);
		delete_but.setSize(10, 30);
		updata_but=new JButton("����");
		updata_but.setFont(font);
		field_id=new JTextField();id_label=new JLabel("�ͻ����:");
		field_name=new JTextField();name_label=new JLabel("�ͻ�����:");
		field_address=new JTextField();address_label=new JLabel("�ͻ���ַ:");
		field_tel=new JTextField();tel_label=new JLabel("�ͻ��绰:");
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
				if(id.isEmpty()||name.isEmpty()||tel.isEmpty()||address.isEmpty()){
					System.out.println("�����Ƿ�©����Ϣ.");
				}else{
					Object[]data={id,name,tel,address};
					Client client=new Client();
					client.setId(id);
					client.setName(name);
					client.setAddress(address);
					client.setTel(tel);
					if(clientManager.add(client)){
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
				String updateId=id;
				id=field_id.getText();
				name=field_name.getText();
				tel=field_tel.getText();
				address=field_address.getText();
				if(id.isEmpty()||name.isEmpty()||tel.isEmpty()||address.isEmpty()){
					System.out.println("�����Ƿ�©����Ϣ.");
				}else{
					Client client=new Client();
					client.setId(id);
					client.setName(name);
					client.setAddress(address);
					client.setTel(tel);
					if(clientManager.updata(client, updateId)){
						tableModel.setValueAt(id, row, 0);
						tableModel.setValueAt(name, row, 1);
						tableModel.setValueAt(tel, row, 2);
						tableModel.setValueAt(address, row, 3);
					}
				
				}
				 
			}
		});
		delete_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					if(clientManager.delete(id)){
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
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 0, SpringLayout.WEST, tel_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, tel_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, butpanel, -10, SpringLayout.EAST, showPanel);
	}
	/**
	 * ���ĳ�ʼ��
	 */
	private void addTable() {
		//����������
		addTableData();
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
				 System.out.println(id+" "+name+" ");
				field_id.setText(id);
				field_name.setText(name);
				field_tel.setText(tel);
				field_address.setText(address);
				
			}
			
		});
	}
	private void addTableData() {
		for(int i=0;i<columns.length;i++){
			columnName.add(columns[i]);
		}
		clientVector=clientManager.query();
		for(Client client:clientVector){
			Vector<Object>vector=new Vector<>();
			vector.add(client.getId());
			vector.add(client.getName());
			vector.add(client.getAddress());
			vector.add(client.getTel());
			tabelData.add(vector);
		}
	}
	
}
