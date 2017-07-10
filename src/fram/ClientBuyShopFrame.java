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

import dao.ClientBuyManager;
import dao.ClientManager;
import dao.OperatorManager;
import dao.ShopManager;
import dao.ShopProviderManager;
import model.ClientBuy;
import model.Item;
import model.Shops;

public class ClientBuyShopFrame extends JInternalFrame{
	private JTextField field_id;//显示供应商编号的文本
	private JTextField field_sumMoney;//
	private JTextField field_number;
	private JTextField field_paykind;
	private JTextField field_state;
	private JComboBox<Object> sname_box;//商品名称选择器
	private JComboBox<Object> cname_box;//客户名称选择器
	private JComboBox<Object> oname_box;//操作员名称选择器
	
	private JLabel id_label;
	private JLabel sumMoney_label;
	private JLabel paykind_label;
	private JLabel state_label;
	private JLabel sname_label;
	private JLabel number_label;
	private JLabel client_label;
	private JLabel operator_label;
	
	private JButton add_but;
	private JButton delete_but;
	private JButton updata_but;
	private JPanel showPanel;//盛放显示文本框的控件
	private SpringLayout showPanelLayout;//showPanel的布局方式
	private JScrollPane tablePanel;//盛放表格的容器
	private DefaultTableModel tableModel;
	private JTable table;
	private Container container;//此窗体的顶级容器
	private SpringLayout containerLayout;
	private static final String columns[]={"订单编号","客户编号","商品编号","数量","合计金额","支付方式","订单状态","操作人员"};
	private Vector<Vector<Object>>tabelData=new Vector<>();
	private Vector<Object>columnName=new Vector<>();
	private Vector<ClientBuy>shopsData=new Vector<>();
	
	private Vector<Item>shopItemVector;
	private Vector<Item>clientItemVector;
	private Vector<Item>operatorItemVector;
	private Vector<Object>cnameVector=new Vector<>();//客户下拉列表的选项
	private Vector<Object>onameVector=new Vector<>();//操作员下拉列表的选项
	private Vector<Object>snameVector=new Vector<>();//商品下拉列表的选项
	//用来记录文本框的内容
	String id;
	String number;
	String sum_money;
	String pay_kind;
	String state;
	
	String client_id;
	String shop_id;
	String operator_id;
	private ShopManager shopManager;
	private ClientManager clientManager;
	private OperatorManager operatorManager;
	private ClientBuyManager clientBuyManager;
	private int row;//选择表格的行数
	public ClientBuyShopFrame() {
		setTitle("销售定单");
		setResizable(false);
		setBounds(50, 50, 700, 400);
		container=getContentPane();
		containerLayout=new SpringLayout();
		container.setLayout(containerLayout);
		init();
		setVisible(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	/**
	 * 各种信息的初始化
	 */
	private void init() {
		//查询商家名字
		queryProviderItem();
		//查询订单信息
		queryShops();
		Font font=new Font("宋体", Font.PLAIN, 12);
		add_but=new JButton("添加");
		add_but.setSize(40, 30);
		add_but.setFont(font);
		delete_but=new JButton("删除");
		delete_but.setFont(font);
		delete_but.setSize(10, 30);
		updata_but=new JButton("更改");
		updata_but.setFont(font);
		field_id=new JTextField();id_label=new JLabel("订单编号:");
		field_sumMoney=new JTextField();sumMoney_label=new JLabel("合计金额:");
		field_state=new JTextField();state_label=new JLabel("订单状态:");
		field_paykind=new JTextField();paykind_label=new JLabel("支付方式:");
		field_number=new JTextField();number_label=new JLabel("订货数量:");
		sname_box=new JComboBox<>(snameVector);sname_label=new JLabel("商品名字:");
		cname_box=new JComboBox<>(cnameVector);client_label=new JLabel("客户名称:");
		oname_box=new JComboBox<>(onameVector);operator_label=new JLabel("操作人员:");
		tablePanel=new JScrollPane();//表格滑动面板
		tablePanel.setBackground(Color.white);
		/*===================显示商家信息组件的容器填充==============*/
		addShowPanel();
		/*===================显示tb_shopprovider的表格容器填充==============*/
		addTable();
		
		//加入到顶级容器
		addToContainer();
		//注册事件监听
		registerListener();
	}
	public void queryShops() {
		
		shopsData=clientBuyManager.query();
		for(ClientBuy clientBuy:shopsData){
			Vector<Object>vector=new Vector<>();
			vector.add(clientBuy.getId());
			vector.add(clientBuy.getClient_id());
			vector.add(clientBuy.getShop_id());
			vector.add(clientBuy.getNumber());
			vector.add(clientBuy.getSun_money());
			vector.add(clientBuy.getPay_kind());
			vector.add(clientBuy.getState());
			vector.add(clientBuy.getOperitor_id());
			tabelData.add(vector);
		}
		
	}
	public void queryProviderItem() {
		//初始化数据管理对象
		clientBuyManager=new ClientBuyManager();
		clientManager=new ClientManager();
		shopManager=new ShopManager();
		operatorManager=new OperatorManager();
		
		clientItemVector=clientManager.queryItem();
		shopItemVector=shopManager.queryItem();
		operatorItemVector=operatorManager.queryItem();
		//设置下拉列表的数据
		for(Item item:clientItemVector){
			cnameVector.add(item.getName());
		}
		for(Item item:shopItemVector){
			snameVector.add(item.getName());
		}
		for(Item item:operatorItemVector){
			onameVector.add(item.getName());
		}
		
	}
	/**
	 * 添加,修改
	 */
	private void registerListener() {
		//工具条的监听
		
		//添加按钮的监听
		add_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id=field_id.getText();
				sum_money=field_sumMoney.getText();
				number=field_number.getText();
				pay_kind=field_paykind.getText();
				state=field_state.getText();
				if(id.isEmpty()||sum_money.isEmpty()||number.isEmpty()||pay_kind.isEmpty()
						||state.isEmpty()||shop_id.isEmpty()||operator_id.isEmpty()||client_id.isEmpty()){
					System.out.println("请检查是否有信息为空");
				}else{
					ClientBuy clientBuy=new ClientBuy();
					clientBuy.setId(id);
					clientBuy.setClient_id(client_id);
					clientBuy.setShop_id(shop_id);
					clientBuy.setNumber(Integer.valueOf(number));
					clientBuy.setSun_money(Double.valueOf(sum_money));
					clientBuy.setPay_kind(pay_kind);
					clientBuy.setState(state);
					clientBuy.setOperitor_id(operator_id);
					Object[]rowdata={id,client_id,shop_id,number,sum_money,pay_kind,state,operator_id};
					if(clientBuyManager.add(clientBuy)){
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
				sum_money=field_sumMoney.getText();
				number=field_number.getText();
				pay_kind=field_paykind.getText();
				state=field_state.getText();
				if(id.isEmpty()||sum_money.isEmpty()||number.isEmpty()||pay_kind.isEmpty()
						||state.isEmpty()||shop_id.isEmpty()||operator_id.isEmpty()||client_id.isEmpty()){
					System.out.println("请检查是否有信息为空");
				}else{
					ClientBuy clientBuy=new ClientBuy();
					clientBuy.setId(id);
					clientBuy.setClient_id(client_id);
					clientBuy.setShop_id(shop_id);
					clientBuy.setNumber(Integer.valueOf(number));
					clientBuy.setSun_money(Double.valueOf(sum_money));
					clientBuy.setPay_kind(pay_kind);
					clientBuy.setState(state);
					clientBuy.setOperitor_id(operator_id);
					if(clientBuyManager.updata(clientBuy, updateId)){
						tableModel.setValueAt(id, row, 0);
						tableModel.setValueAt(client_id, row, 1);
						tableModel.setValueAt(shop_id, row, 2);
						tableModel.setValueAt(number, row, 3);
						tableModel.setValueAt(sum_money, row, 4);
						tableModel.setValueAt(pay_kind, row, 5);
						tableModel.setValueAt(state, row, 6);
						tableModel.setValueAt(operator_id, row, 7);
					}
					
				}
			}
		});
		delete_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(clientBuyManager.delete(id)){
					tableModel.removeRow(row);
				}
			}
		});
		//下拉列表的监听
		sname_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					shop_id=shopItemVector.get(sname_box.getSelectedIndex()).getId();
				}
				
			}
		});
		oname_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					operator_id=operatorItemVector.get(oname_box.getSelectedIndex()).getId();
				}
			}
		});
		cname_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					client_id=clientItemVector.get(cname_box.getSelectedIndex()).getId();
				}
			}
		});
	}	
	/**
	 * 将组件添加进顶级容器
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
	 * 信息展示面板的初始化
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
		
		showPanel.add(sumMoney_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, sumMoney_label, 5, SpringLayout.EAST, field_id);
		showPanelLayout.putConstraint(SpringLayout.NORTH, sumMoney_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_sumMoney);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_sumMoney, 5, SpringLayout.EAST, sumMoney_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_sumMoney, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_sumMoney, -190, SpringLayout.EAST, showPanel);
		showPanel.add(number_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, number_label, 5, SpringLayout.EAST, field_sumMoney);
		showPanelLayout.putConstraint(SpringLayout.NORTH, number_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_number);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_number, 5, SpringLayout.EAST, number_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_number, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_number, -10, SpringLayout.EAST, showPanel);
		
		showPanel.add(paykind_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, paykind_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, paykind_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_paykind);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_paykind, 5, SpringLayout.EAST, paykind_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_paykind, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_paykind, -430, SpringLayout.EAST, showPanel);
		
		showPanel.add(state_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, state_label, 5, SpringLayout.EAST, field_paykind);
		showPanelLayout.putConstraint(SpringLayout.NORTH, state_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_state);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_state, 5, SpringLayout.EAST, state_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_state, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_state, -190, SpringLayout.EAST, showPanel);
		showPanel.add(sname_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, sname_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, sname_label, 10, SpringLayout.SOUTH, paykind_label);
		showPanel.add(sname_box);
		showPanelLayout.putConstraint(SpringLayout.WEST, sname_box, 5, SpringLayout.EAST, sname_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, sname_box, 10, SpringLayout.SOUTH, paykind_label);
		
		showPanel.add(client_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, client_label, 10, SpringLayout.EAST, sname_box);
		showPanelLayout.putConstraint(SpringLayout.NORTH, client_label, 10, SpringLayout.SOUTH, paykind_label);
		showPanel.add(cname_box);
		showPanelLayout.putConstraint(SpringLayout.WEST, cname_box, 5, SpringLayout.EAST, client_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, cname_box, 10, SpringLayout.SOUTH, paykind_label);
		
		showPanel.add(operator_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, operator_label, 10, SpringLayout.EAST, cname_box);
		showPanelLayout.putConstraint(SpringLayout.NORTH, operator_label, 10, SpringLayout.SOUTH, paykind_label);
		showPanel.add(oname_box);
		showPanelLayout.putConstraint(SpringLayout.WEST, oname_box, 5, SpringLayout.EAST, operator_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, oname_box, 10, SpringLayout.SOUTH, paykind_label);
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
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 0, SpringLayout.WEST, number_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, paykind_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, butpanel, -10, SpringLayout.EAST, showPanel);
	}
	/**
	 * 表格的初始化
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
		//监听table
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				row=table.getSelectedRow();
				id=(String) tableModel.getValueAt(row, 0);
				client_id=(String) tableModel.getValueAt(row, 1);
				shop_id=(String) tableModel.getValueAt(row, 2);
				number=String.valueOf(tableModel.getValueAt(row, 3));
				sum_money= String.valueOf(tableModel.getValueAt(row, 4));
				pay_kind=(String) tableModel.getValueAt(row, 5);
				state= (String) tableModel.getValueAt(row,6);
				operator_id=(String) tableModel.getValueAt(row, 7);
				
				sname_box.setSelectedItem(shopManager.queryNameById(shop_id));
				cname_box.setSelectedItem(clientManager.queryNameById(client_id));
				oname_box.setSelectedItem(operatorManager.queryNameById(operator_id));
				field_id.setText(id);
				field_number.setText(number);
				field_sumMoney.setText(sum_money);
				field_paykind.setText(pay_kind);
				field_state.setText(state);
				
			}
			
		});
	}
}
