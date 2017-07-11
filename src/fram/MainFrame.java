package fram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.crypto.Cipher;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import model.Client;

public class MainFrame extends JFrame{
	private JMenuBar menuBar;//主窗口的菜单条
	private JMenu menu1;//进货管理的菜单项
	private JMenuItem menu1_item1;
	private JMenu menu2;//商品管理的菜单项
	private JMenuItem menu2_item1;
	private JMenu menu3;//销售管理的菜单项
	private JMenuItem menu3_item1;
	private JMenuItem menu3_item2;
	private JMenu menu4;//进货管理
	private JMenuItem menu4_item1;
	private JMenu menu5;//人事管理
	private JMenuItem menu5_item1;
	private JMenuItem menu5_item2;
	private JMenu menu6;//
	private JMenuItem menu6_item1;
	private JMenu menu7;//
	private JMenu menu8;//帮主
	private JMenuItem menu8_item1;
	private Container container;//窗体的主容器
	
	private JPanel panel;//菜单项下边的容器
	private SpringLayout panellayout;//容器panel的布局方式
	private JToolBar toolBar;//主窗体的工具条
	private JButton  tbut1;//工具条的第一个按钮
	private JButton  tbut2;//工具条的第一个按钮
	private JButton  tbut3;
	private JButton  tbut4;
	private JButton  tbut5;
	private JButton  tbut6;
	private JButton  tbut7;
	private JButton  tbut8;
	private JDesktopPane desktopPane;//显示内部窗口的桌面容器
	
	private ShopProviderFrame frameProvider;//显示供货商的内部窗体
	private BackShopFram backShopFram;//显示退货窗体
	private ShopFrame framShop;//商品窗体
	private BuyShopFram buyShopFram;//进货窗体
	private DepartmentFrame departmentFrame;//部门窗体
	private OperatorFrame operatorFrame;//员工管理窗体
	private ClientFrame clientFrame;//客户窗体
	private ClientBuyShopFrame clientBuyShopFrame;
  	public MainFrame() {
		setBounds(300, 100, 900, 650);
		container=getContentPane();
		container.setLayout(new BorderLayout());
		init();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	/**
	 * 实例化各种控件
	 */
	private void init() {
		panel=new JPanel();
		panellayout=new SpringLayout();
		panel.setLayout(panellayout);
		/*==============菜单栏的各种设定==============*/
		addMenu();	
		/*==============工具栏的各种设定==============*/
		addToolBar();
		/*==============内部窗体的各种设定==============*/
		addDesktopPane();
		
		
			
	}
	private void addMenu() {
		Font font=new Font("微软雅黑", Font.BOLD, 12);
		menuBar=new JMenuBar();//实例化菜单条
		menu1=new JMenu("供应商管理  |");//实例化第一个菜单项
		menu1_item1=new JMenuItem("查看供应商 ");
		menu1.add(menu1_item1);
		menu1.setFont(font);
		menu1_item1.setFont(font);
		
		menu2=new JMenu("商品管理  |");
		menu2_item1=new JMenuItem("查看商品");
		menu2.add(menu2_item1);
		menu2.setFont(font);
		menu2_item1.setFont(font);
		
		menu3=new JMenu("销售管理  |");
		menu3_item1=new JMenuItem("查看销售单");
		menu3_item2=new JMenuItem("查看退货单");
		menu3.add(menu3_item1);
		menu3.add(menu3_item2);
		menu3.setFont(font);
		menu3_item1.setFont(font);
		menu3_item2.setFont(font);
		
		menu4=new JMenu("进货管理  |");
		menu4_item1=new JMenuItem("查看进货单");
		menu4.add(menu4_item1);
		menu4.setFont(font);
		menu4_item1.setFont(font);
		
		menu5=new JMenu("人事管理  |");
		menu5_item1=new JMenuItem("人员调动");
		menu5_item2=new JMenuItem("部门一览");
		menu5.add(menu5_item1);
		menu5.add(menu5_item2);
		menu5.setFont(font);
		menu5_item1.setFont(font);
		menu5_item2.setFont(font);
		
		menu6=new JMenu("客户管理  |");
		menu6_item1=new JMenuItem("查看客户");
		menu6.add(menu6_item1);
		menu6.setFont(font);
		menu6_item1.setFont(font);
		
		menu7=new JMenu("关于企业  |");
		menu7.setFont(font);
		
		menu8=new JMenu("  帮助   |");
		menu8.setFont(font);
		
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.add(menu5);
		menuBar.add(menu6);
		menuBar.add(menu7);
		menuBar.add(menu8);
	}
	/**
	 * 内部窗体的各种设定
	 */
	private void addDesktopPane() {
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon(this.getClass().getResource("/icon/bg.jpg")));
		label.setBounds(0, 0, 900, 600);
		desktopPane=new JDesktopPane();
		desktopPane.add(label,new Integer(Integer.MIN_VALUE));
		frameProvider=new ShopProviderFrame();//供货商窗体
		framShop=new ShopFrame();//商品窗体
		backShopFram=new BackShopFram();//退货窗体
		
		operatorFrame=new OperatorFrame();
		clientFrame=new ClientFrame();
		departmentFrame=new DepartmentFrame();
		buyShopFram=new BuyShopFram();
		clientBuyShopFrame=new ClientBuyShopFrame();
		
		desktopPane.add(frameProvider);
		desktopPane.add(framShop);
		desktopPane.add(backShopFram);
		
		desktopPane.add(operatorFrame);
		desktopPane.add(clientFrame);
		desktopPane.add(departmentFrame);
		desktopPane.add(buyShopFram);
		desktopPane.add(clientBuyShopFrame);
		
		panel.add(desktopPane);
		panellayout.putConstraint(SpringLayout.NORTH, desktopPane, 3, SpringLayout.SOUTH, toolBar);
		panellayout.putConstraint(SpringLayout.WEST, desktopPane, 0, SpringLayout.WEST, panel);
		panellayout.putConstraint(SpringLayout.EAST, desktopPane, 0, SpringLayout.EAST, panel);
		panellayout.putConstraint(SpringLayout.SOUTH, desktopPane, 0, SpringLayout.SOUTH, panel);
		
		container.add(menuBar,BorderLayout.NORTH);
		container.add(panel, BorderLayout.CENTER);
	}
	/**
	 * 工具条的各种设定
	 */
	private void addToolBar() {
		Font font=new Font("微软雅黑", Font.BOLD, 13);
		toolBar=new JToolBar(JToolBar.HORIZONTAL);//实例化工具栏
		toolBar.setFloatable(false);
		toolBar.setSize(0, 40);
		tbut1=new JButton("供应商   ");
		tbut1.setIcon(new ImageIcon(this.getClass().getResource("/icon/gh.png")));
		tbut1.setFont(font);
		
		toolBar.add(tbut1);
		
		tbut2=new JButton(" 商品     ");
		tbut2.setIcon(new ImageIcon(this.getClass().getResource("/icon/shop.png")));
		tbut2.setFont(font);
		toolBar.add(tbut2);
		
		tbut3=new JButton("退货单   ");
		tbut3.setIcon(new ImageIcon(this.getClass().getResource("/icon/back.png")));
		tbut3.setFont(font);
		toolBar.add(tbut3);
		
		tbut4=new JButton("销售单  ");
		tbut4.setIcon(new ImageIcon(this.getClass().getResource("/icon/sell.png")));
		tbut4.setFont(font);
		toolBar.add(tbut4);
		
		tbut5=new JButton("采购单   ");
		tbut5.setIcon(new ImageIcon(this.getClass().getResource("/icon/buy.png")));
		tbut5.setFont(font);
		toolBar.add(tbut5);
		
		tbut6=new JButton("人员调动 ");
		tbut6.setIcon(new ImageIcon(this.getClass().getResource("/icon/operator.png")));
		tbut6.setFont(font);
		toolBar.add(tbut6);
		
		tbut7=new JButton("部门管理");
		tbut7.setIcon(new ImageIcon(this.getClass().getResource("/icon/department.png")));
		tbut7.setFont(font);
		toolBar.add(tbut7);
		
		tbut8=new JButton("客户一览 ");
		tbut8.setIcon(new ImageIcon(this.getClass().getResource("/icon/client.png")));
		tbut8.setFont(font);
		toolBar.add(tbut8);
		panel.add(toolBar);
		panellayout.putConstraint(SpringLayout.NORTH, toolBar, 3, SpringLayout.NORTH, panel);
		panellayout.putConstraint(SpringLayout.WEST, toolBar, 3, SpringLayout.WEST, panel);
		panellayout.putConstraint(SpringLayout.EAST, toolBar, -3, SpringLayout.EAST, panel);
		//供应商按钮的监听
		toolButRegisterListener();
	}
	private void toolButRegisterListener() {
		tbut1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!frameProvider.isVisible()){
					frameProvider.setVisible(true);
				}else{
					frameProvider.setVisible(false);
				}
			}
		});
		tbut2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!framShop.isVisible()){
//					framShop.queryProviderItem();
					framShop.setVisible(true);
				}else{
					framShop.setVisible(false);
				}
			}
		});
		tbut3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!backShopFram.isVisible()){
					backShopFram.setVisible(true);
				}else{
					backShopFram.setVisible(false);
				}
				
			}
		});
		tbut4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!clientBuyShopFrame.isVisible()){
					clientBuyShopFrame.setVisible(true);
				}else{
					clientBuyShopFrame.setVisible(false);
				}
				
			}
		});
		tbut5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!buyShopFram.isVisible()){
					buyShopFram.setVisible(true);
				}else{
					buyShopFram.setVisible(false);
				}
				
			}
		});
		tbut6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!operatorFrame.isVisible()){
					operatorFrame.setVisible(true);
				}else{
					operatorFrame.setVisible(false);
				}
				
			}
		});
		tbut7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!departmentFrame.isVisible()){
					departmentFrame.setVisible(true);
				}else{
					departmentFrame.setVisible(false);
				}
				
			}
		});
		tbut8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!clientFrame.isVisible()){
					clientFrame.setVisible(true);
				}else{
					clientFrame.setVisible(false);
				}
				
			}
		});
	}
	public JButton getToolBut(String name,String path){
		JButton button=new JButton(name);
		button.setIcon(new ImageIcon(this.getClass().getResource(name)));
		return null;
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}
