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

import dao.BackShopManager;
import dao.ShopManager;
import dao.ShopProviderManager;
import model.BackShop;
import model.Item;
import model.Shops;

public class BackShopFram extends JInternalFrame{
	private JTextField field_id;//显示供应商编号的文本
	private JTextField field_shopId;
	private JTextField field_sumMoney;
	private JTextField field_backstate;
	private JTextField field_number;
	
	private JLabel id_label;
	private JLabel number_label;
	private JLabel sumMoney_label;
	private JLabel shopID_label;
	private JLabel backstate_label;
	
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
	private static final String columns[]={"退货编号","商品编号","退货数量","合计金额","订单状态"};
	private Vector<Vector<Object>>tabelData=new Vector<>();
	private Vector<Object>columnName=new Vector<>();
	private Vector<BackShop>shopsData=new Vector<>();
	private Vector<Item>itemVector;
	//用来记录文本框的内容
	String id;
	String shop_id;
	String number;
	String sum_money;
	String back_state;
	private ShopProviderManager providerManager;
	private BackShopManager shopManager;
	private int row;//选择表格的行数
	public BackShopFram() {
		setTitle("退货单");
		setResizable(false);
		setBounds(35, 35, 700, 400);
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
	
		//查询商品信息
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
		field_id=new JTextField();id_label=new JLabel("退货编号:");
		field_sumMoney=new JTextField();sumMoney_label=new JLabel("合计金额:");
		field_backstate=new JTextField();backstate_label=new JLabel("订单状态:");
		field_shopId=new JTextField();shopID_label=new JLabel("商品编号:");
		field_number=new JTextField();number_label=new JLabel("退货数量:");
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
		shopManager=new BackShopManager();
		shopsData=shopManager.query();
		for(BackShop shops:shopsData){
			Vector<Object>vector=new Vector<>();
			vector.add(shops.getId());
			vector.add(shops.getShop_id());
			vector.add(shops.getNumber());
			vector.add(shops.getSum_money());
			vector.add(shops.getBack_state());
			tabelData.add(vector);
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
				shop_id=field_shopId.getText();
				number=field_number.getText();
				sum_money=field_sumMoney.getText();
				back_state=field_backstate.getText();
				if(id.isEmpty()||shop_id.isEmpty()||number.isEmpty()||sum_money.isEmpty()
						||back_state.isEmpty()){
				System.out.println("检查是否有信息为空");
				}else{
					Object[]rowdata={id,shop_id,number,sum_money,back_state};
					BackShop backShop=new BackShop();
					backShop.setId(id);
					backShop.setShop_id(shop_id);
					backShop.setBack_state(back_state);
					backShop.setSum_money(Double.valueOf(sum_money));
					backShop.setNumber(Integer.valueOf(number));
					if(shopManager.add(backShop)){
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
				shop_id=field_shopId.getText();
				number=field_number.getText();
				sum_money=field_sumMoney.getText();
				back_state=field_backstate.getText();
				if(id.isEmpty()||shop_id.isEmpty()||number.isEmpty()||sum_money.isEmpty()
						||back_state.isEmpty()){
				System.out.println("检查是否有信息为空");
				}else{
					BackShop backShop=new BackShop();
					backShop.setId(id);
					backShop.setShop_id(shop_id);
					backShop.setBack_state(back_state);
					backShop.setSum_money(Double.valueOf(sum_money));
					backShop.setNumber(Integer.valueOf(number));
					if(shopManager.updata(backShop, updateId)){
						tableModel.setValueAt(id, row, 0);
						tableModel.setValueAt(shop_id, row, 1);
						tableModel.setValueAt(number, row, 2);
						tableModel.setValueAt(sum_money, row, 3);
						tableModel.setValueAt(back_state, row, 4);
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
		//下拉列表的监听
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
		showPanelLayout.putConstraint(SpringLayout.EAST, field_id, -450, SpringLayout.EAST, showPanel);
		
		showPanel.add(shopID_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, shopID_label, 5, SpringLayout.EAST, field_id);
		showPanelLayout.putConstraint(SpringLayout.NORTH, shopID_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_shopId);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_shopId, 5, SpringLayout.EAST, shopID_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_shopId, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_shopId, -200, SpringLayout.EAST, showPanel);
		
		showPanel.add(number_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, number_label, 5, SpringLayout.EAST, field_shopId);
		showPanelLayout.putConstraint(SpringLayout.NORTH, number_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_number);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_number, 5, SpringLayout.EAST, number_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_number, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_number, -10, SpringLayout.EAST, showPanel);
		
		showPanel.add(sumMoney_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, sumMoney_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, sumMoney_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_sumMoney);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_sumMoney, 5, SpringLayout.EAST, sumMoney_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_sumMoney, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_sumMoney, -450, SpringLayout.EAST, showPanel);
		
		showPanel.add(backstate_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, backstate_label, 5, SpringLayout.EAST, field_sumMoney);
		showPanelLayout.putConstraint(SpringLayout.NORTH, backstate_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(field_backstate);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_backstate, 5, SpringLayout.EAST, backstate_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_backstate, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_backstate, -200, SpringLayout.EAST, showPanel);
		
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
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 10, SpringLayout.WEST, number_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, backstate_label);
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
				int row;
				if(table.getSelectedRow()!=-1){
					row=table.getSelectedRow();
					id=(String) tableModel.getValueAt(row, 0);
					shop_id=(String) tableModel.getValueAt(row, 1);
					number= tableModel.getValueAt(row, 2)+"";
					sum_money=tableModel.getValueAt(row, 3)+"";
					back_state=(String) tableModel.getValueAt(row, 4);
					field_id.setText(id);
					field_shopId.setText(shop_id);
					field_number.setText(number);
					field_sumMoney.setText(sum_money);
					field_backstate.setText(back_state);
				}
			}
			
		});
	}
	
}
