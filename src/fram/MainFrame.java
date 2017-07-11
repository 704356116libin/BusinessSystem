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
	private JMenuBar menuBar;//�����ڵĲ˵���
	private JMenu menu1;//��������Ĳ˵���
	private JMenuItem menu1_item1;
	private JMenu menu2;//��Ʒ����Ĳ˵���
	private JMenuItem menu2_item1;
	private JMenu menu3;//���۹���Ĳ˵���
	private JMenuItem menu3_item1;
	private JMenuItem menu3_item2;
	private JMenu menu4;//��������
	private JMenuItem menu4_item1;
	private JMenu menu5;//���¹���
	private JMenuItem menu5_item1;
	private JMenuItem menu5_item2;
	private JMenu menu6;//
	private JMenuItem menu6_item1;
	private JMenu menu7;//
	private JMenu menu8;//����
	private JMenuItem menu8_item1;
	private Container container;//�����������
	
	private JPanel panel;//�˵����±ߵ�����
	private SpringLayout panellayout;//����panel�Ĳ��ַ�ʽ
	private JToolBar toolBar;//������Ĺ�����
	private JButton  tbut1;//�������ĵ�һ����ť
	private JButton  tbut2;//�������ĵ�һ����ť
	private JButton  tbut3;
	private JButton  tbut4;
	private JButton  tbut5;
	private JButton  tbut6;
	private JButton  tbut7;
	private JButton  tbut8;
	private JDesktopPane desktopPane;//��ʾ�ڲ����ڵ���������
	
	private ShopProviderFrame frameProvider;//��ʾ�����̵��ڲ�����
	private BackShopFram backShopFram;//��ʾ�˻�����
	private ShopFrame framShop;//��Ʒ����
	private BuyShopFram buyShopFram;//��������
	private DepartmentFrame departmentFrame;//���Ŵ���
	private OperatorFrame operatorFrame;//Ա��������
	private ClientFrame clientFrame;//�ͻ�����
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
	 * ʵ�������ֿؼ�
	 */
	private void init() {
		panel=new JPanel();
		panellayout=new SpringLayout();
		panel.setLayout(panellayout);
		/*==============�˵����ĸ����趨==============*/
		addMenu();	
		/*==============�������ĸ����趨==============*/
		addToolBar();
		/*==============�ڲ�����ĸ����趨==============*/
		addDesktopPane();
		
		
			
	}
	private void addMenu() {
		Font font=new Font("΢���ź�", Font.BOLD, 12);
		menuBar=new JMenuBar();//ʵ�����˵���
		menu1=new JMenu("��Ӧ�̹���  |");//ʵ������һ���˵���
		menu1_item1=new JMenuItem("�鿴��Ӧ�� ");
		menu1.add(menu1_item1);
		menu1.setFont(font);
		menu1_item1.setFont(font);
		
		menu2=new JMenu("��Ʒ����  |");
		menu2_item1=new JMenuItem("�鿴��Ʒ");
		menu2.add(menu2_item1);
		menu2.setFont(font);
		menu2_item1.setFont(font);
		
		menu3=new JMenu("���۹���  |");
		menu3_item1=new JMenuItem("�鿴���۵�");
		menu3_item2=new JMenuItem("�鿴�˻���");
		menu3.add(menu3_item1);
		menu3.add(menu3_item2);
		menu3.setFont(font);
		menu3_item1.setFont(font);
		menu3_item2.setFont(font);
		
		menu4=new JMenu("��������  |");
		menu4_item1=new JMenuItem("�鿴������");
		menu4.add(menu4_item1);
		menu4.setFont(font);
		menu4_item1.setFont(font);
		
		menu5=new JMenu("���¹���  |");
		menu5_item1=new JMenuItem("��Ա����");
		menu5_item2=new JMenuItem("����һ��");
		menu5.add(menu5_item1);
		menu5.add(menu5_item2);
		menu5.setFont(font);
		menu5_item1.setFont(font);
		menu5_item2.setFont(font);
		
		menu6=new JMenu("�ͻ�����  |");
		menu6_item1=new JMenuItem("�鿴�ͻ�");
		menu6.add(menu6_item1);
		menu6.setFont(font);
		menu6_item1.setFont(font);
		
		menu7=new JMenu("������ҵ  |");
		menu7.setFont(font);
		
		menu8=new JMenu("  ����   |");
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
	 * �ڲ�����ĸ����趨
	 */
	private void addDesktopPane() {
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon(this.getClass().getResource("/icon/bg.jpg")));
		label.setBounds(0, 0, 900, 600);
		desktopPane=new JDesktopPane();
		desktopPane.add(label,new Integer(Integer.MIN_VALUE));
		frameProvider=new ShopProviderFrame();//�����̴���
		framShop=new ShopFrame();//��Ʒ����
		backShopFram=new BackShopFram();//�˻�����
		
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
	 * �������ĸ����趨
	 */
	private void addToolBar() {
		Font font=new Font("΢���ź�", Font.BOLD, 13);
		toolBar=new JToolBar(JToolBar.HORIZONTAL);//ʵ����������
		toolBar.setFloatable(false);
		toolBar.setSize(0, 40);
		tbut1=new JButton("��Ӧ��   ");
		tbut1.setIcon(new ImageIcon(this.getClass().getResource("/icon/gh.png")));
		tbut1.setFont(font);
		
		toolBar.add(tbut1);
		
		tbut2=new JButton(" ��Ʒ     ");
		tbut2.setIcon(new ImageIcon(this.getClass().getResource("/icon/shop.png")));
		tbut2.setFont(font);
		toolBar.add(tbut2);
		
		tbut3=new JButton("�˻���   ");
		tbut3.setIcon(new ImageIcon(this.getClass().getResource("/icon/back.png")));
		tbut3.setFont(font);
		toolBar.add(tbut3);
		
		tbut4=new JButton("���۵�  ");
		tbut4.setIcon(new ImageIcon(this.getClass().getResource("/icon/sell.png")));
		tbut4.setFont(font);
		toolBar.add(tbut4);
		
		tbut5=new JButton("�ɹ���   ");
		tbut5.setIcon(new ImageIcon(this.getClass().getResource("/icon/buy.png")));
		tbut5.setFont(font);
		toolBar.add(tbut5);
		
		tbut6=new JButton("��Ա���� ");
		tbut6.setIcon(new ImageIcon(this.getClass().getResource("/icon/operator.png")));
		tbut6.setFont(font);
		toolBar.add(tbut6);
		
		tbut7=new JButton("���Ź���");
		tbut7.setIcon(new ImageIcon(this.getClass().getResource("/icon/department.png")));
		tbut7.setFont(font);
		toolBar.add(tbut7);
		
		tbut8=new JButton("�ͻ�һ�� ");
		tbut8.setIcon(new ImageIcon(this.getClass().getResource("/icon/client.png")));
		tbut8.setFont(font);
		toolBar.add(tbut8);
		panel.add(toolBar);
		panellayout.putConstraint(SpringLayout.NORTH, toolBar, 3, SpringLayout.NORTH, panel);
		panellayout.putConstraint(SpringLayout.WEST, toolBar, 3, SpringLayout.WEST, panel);
		panellayout.putConstraint(SpringLayout.EAST, toolBar, -3, SpringLayout.EAST, panel);
		//��Ӧ�̰�ť�ļ���
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
