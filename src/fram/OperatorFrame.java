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

import dao.ClientManager;
import dao.DepartmentManager;
import dao.OperatorManager;
import dao.ShopProviderManager;
import model.Client;
import model.Item;
import model.Operator;
/**
 * 操作员窗口
 * @author Administrator
 *
 */
public class OperatorFrame extends JInternalFrame{
	private JTextField field_id;//显示供应商编号的文本
	private JTextField field_name;//
	private JTextField field_tel;
	private JComboBox<String> departId_box;//显示部门信息的下拉列表
	private JLabel id_label;
	private JLabel name_label;
	private JLabel departId_label;
	private JLabel tel_label;
	
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
	private static final String columns[]={"员工编号","员工姓名","所属部门","联系方式"};
	private Vector<Vector<Object>>tabelData=new Vector<>();//设置表数据
	private Vector<Operator>operatorVector=new Vector<>();//存放查询的数据
	private Vector<Object>columnName=new Vector<>();//存放表列名
	private Vector<Item>itemVector=new Vector<>();//存放下拉列表
	private Vector<String>dname=new Vector<>();
	//用来记录文本框的内容
	String id;
	String name;
	String tel;
	String department_id;
	private OperatorManager operatorManager;//用来查询数据
	private DepartmentManager departmentManager;//查询部门信息
	private int row;
	public OperatorFrame() {
		setClosable(true);
		setResizable(false);
		setBounds(80, 80, 700, 400);
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
		operatorManager=new OperatorManager();//首先实例化数据管理对象
		departmentManager=new DepartmentManager();
		//给表格加数据
		addTableData();
		//给下拉列表设置数据
		itemVector=departmentManager.queryItem();
		for(Item item:itemVector){
			dname.add(item.getName());
		}
		
		Font font=new Font("宋体", Font.PLAIN, 12);
		add_but=new JButton("添加");
		add_but.setSize(40, 30);
		add_but.setFont(font);
		delete_but=new JButton("删除");
		delete_but.setFont(font);
		delete_but.setSize(10, 30);
		updata_but=new JButton("更改");
		updata_but.setFont(font);
		
		field_id=new JTextField();id_label=new JLabel("员工编号:");
		field_name=new JTextField();name_label=new JLabel("员工姓名:");
		departId_box=new JComboBox<>(dname);departId_label=new JLabel("所属部门:");
		field_tel=new JTextField();tel_label=new JLabel("员工电话:");
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
				name=field_name.getText();
				tel=field_tel.getText();
				System.out.println(tel);
				if(id.isEmpty()||name.isEmpty()||tel.isEmpty()||department_id.isEmpty()){
					System.out.println("请检查是否漏填信息.");
				}else{
					Object[]data={id,name,department_id,tel};
					Operator operator=new Operator();
					operator.setId(id);
					operator.setName(name);
					operator.setDepartment_id(department_id);
					operator.setTel(tel);
					if(operatorManager.add(operator)){
						tableModel.insertRow(row, data);
					}else{
						System.out.println("请检查数据商家编号是否重复");
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
				if(id.isEmpty()||name.isEmpty()||tel.isEmpty()||department_id.isEmpty()){
					System.out.println("请检查是否漏填信息.");
				}else{
					Operator operator=new Operator();
					operator.setId(id);
					operator.setName(name);
					operator.setDepartment_id(department_id);
					operator.setTel(tel);
					if(operatorManager.updata(operator, updateId)){
						tableModel.setValueAt(id, row, 0);
						tableModel.setValueAt(name, row, 1);
						tableModel.setValueAt(department_id, row, 2);
						tableModel.setValueAt(tel, row, 3);
					}
				
				}
				 
			}
		});
		delete_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					if(operatorManager.delete(id)){
						tableModel.removeRow(row);
					}
					else{
						System.out.println("数据删除异常请检查");
					}
				}
				
			}
		});
		departId_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					department_id=itemVector.get(departId_box.getSelectedIndex()).getId();
					System.out.println(department_id);
				}
				
			}
		});
		//设置列表默认选中项
		departId_box.setSelectedIndex(1);
		departId_box.setSelectedIndex(0);
	}
	/**
	 * 将组件添加进顶级容器
	 */
	private void addToContainer() {
		container.add(tablePanel);
		containerLayout.putConstraint(SpringLayout.NORTH, tablePanel, 80, SpringLayout.NORTH, container);
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
		showPanelLayout.putConstraint(SpringLayout.EAST, field_id, -390, SpringLayout.EAST, showPanel);
		
		showPanel.add(name_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, name_label, 5, SpringLayout.EAST, field_id);
		showPanelLayout.putConstraint(SpringLayout.NORTH, name_label, 10, SpringLayout.NORTH, showPanel);
		showPanel.add(field_name);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_name, 5, SpringLayout.EAST, name_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_name, 10, SpringLayout.NORTH, showPanel);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_name, -190, SpringLayout.EAST, showPanel);
		
		showPanel.add(departId_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, departId_label, 10, SpringLayout.WEST, showPanel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, departId_label, 10, SpringLayout.SOUTH, id_label);
		showPanel.add(departId_box);
		showPanelLayout.putConstraint(SpringLayout.WEST, departId_box, 5, SpringLayout.EAST, departId_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, departId_box, 10, SpringLayout.SOUTH, id_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, departId_box, -390, SpringLayout.EAST, showPanel);
		
		showPanel.add(tel_label);
		showPanelLayout.putConstraint(SpringLayout.WEST, tel_label, 5, SpringLayout.EAST, departId_box);
		showPanelLayout.putConstraint(SpringLayout.NORTH, tel_label, 10, SpringLayout.SOUTH, field_name);
		showPanel.add(field_tel);
		showPanelLayout.putConstraint(SpringLayout.WEST, field_tel, 5, SpringLayout.EAST, tel_label);
		showPanelLayout.putConstraint(SpringLayout.NORTH, field_tel, 10, SpringLayout.SOUTH, field_name);
		showPanelLayout.putConstraint(SpringLayout.EAST, field_tel, 0, SpringLayout.EAST, field_name);
		
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
		showPanelLayout.putConstraint(SpringLayout.WEST, butpanel, 5, SpringLayout.EAST, field_tel);
		showPanelLayout.putConstraint(SpringLayout.NORTH, butpanel, 10, SpringLayout.SOUTH, name_label);
		showPanelLayout.putConstraint(SpringLayout.EAST, butpanel, -10, SpringLayout.EAST, showPanel);
	}
	/**
	 * 表格的初始化
	 */
	private void addTable() {
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
				 name=(String) tableModel.getValueAt(row, 1);
				 tel=(String) tableModel.getValueAt(row, 3);
				 System.out.println(id+" "+name+" ");
				field_id.setText(id);
				field_name.setText(name);
				field_tel.setText(tel);
				
			}
			
		});
	}
	private void addTableData() {
		for(int i=0;i<columns.length;i++){
			columnName.add(columns[i]);
		}
		operatorVector=operatorManager.query();
		for(Operator operator:operatorVector){
			Vector<Object>vector=new Vector<>();
			vector.add(operator.getId());
			vector.add(operator.getName());
			vector.add(operator.getDepartment_id());
			vector.add(operator.getTel());
			tabelData.add(vector);
		}
	}
	
}
