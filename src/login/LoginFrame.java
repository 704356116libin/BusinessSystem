package login;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import dao.UserManager;
import fram.MainFrame;

/**
 * ��¼����
 * @author Administrator
 *
 */
public class LoginFrame extends JFrame {
	private JLabel user;
	private JLabel pwd;
	private JLabel imgLabel;//ͼƬ��ǩ
	private JTextField nameField;
	private JPasswordField pwdField;
	private SpringLayout springLayout;//���������õĲ��ַ�ʽ
	private JButton registerBut;
	private JButton loginBut;
	private Container container;//����������
	
	private UserManager userManager;
	String name;
	String passworld;
	public LoginFrame() {
		setTitle("��¼����");
		setResizable(false);
		setBounds(600, 400, 350, 150);
		init();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	private void init() {
		userManager=new UserManager();
		user=new JLabel("�˻�:");
		pwd=new JLabel("����:");
		nameField=new JTextField();
		pwdField=new JPasswordField();
		imgLabel=new JLabel();
		imgLabel.setIcon(new ImageIcon(this.getClass().getResource("/icon/login.png")));
//		imgLabel.setSize(80, 80);
		registerBut=new JButton("ע��");
		loginBut=new JButton("��¼");
		
		container=getContentPane();//�õ���������
//		container.setBackground(new Color(244, 164, 96));
		springLayout=new SpringLayout();
		container.setLayout(springLayout);
		//��ʼ�趨������Ų���ʽ
		container.add(user);
		springLayout.putConstraint(SpringLayout.NORTH, user,20, SpringLayout.NORTH, container);
		springLayout.putConstraint(SpringLayout.WEST, user, 100, SpringLayout.WEST, container);
		//�����ı����λ������
		container.add(nameField);
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, container);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -5, SpringLayout.EAST, container);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 5, SpringLayout.EAST,user);
		//�����ǩ��λ������
		container.add(pwd);
		springLayout.putConstraint(SpringLayout.WEST, pwd, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, pwd, 10, SpringLayout.SOUTH, nameField);
		container.add(pwdField);
		springLayout.putConstraint(SpringLayout.WEST, pwdField, 5, SpringLayout.EAST, pwd);
		springLayout.putConstraint(SpringLayout.NORTH, pwdField, 10, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, pwdField, -5, SpringLayout.EAST,container);
		//��ťλ�õ�ȷ��
		container.add(registerBut);
		springLayout.putConstraint(SpringLayout.EAST, registerBut, -5, SpringLayout.EAST, container);
		springLayout.putConstraint(SpringLayout.SOUTH, registerBut, -10, SpringLayout.SOUTH, container);
		springLayout.putConstraint(SpringLayout.NORTH, registerBut, 10, SpringLayout.SOUTH, pwdField);
		container.add(loginBut);
		springLayout.putConstraint(SpringLayout.EAST, loginBut, -5, SpringLayout.WEST, registerBut);
		springLayout.putConstraint(SpringLayout.SOUTH, loginBut, -10, SpringLayout.SOUTH, container);
		springLayout.putConstraint(SpringLayout.NORTH, loginBut, 10, SpringLayout.SOUTH, pwdField);
		
		container.add(imgLabel);
		springLayout.putConstraint(SpringLayout.EAST, imgLabel, -10, SpringLayout.WEST, user);
		springLayout.putConstraint(SpringLayout.SOUTH, imgLabel, 0, SpringLayout.SOUTH, pwdField);
		springLayout.putConstraint(SpringLayout.NORTH, imgLabel, 10, SpringLayout.NORTH, container);
		//��ť����
		RrgisterListener();
	}
	private void RrgisterListener() {
		loginBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				name=nameField.getText();
				char []c=pwdField.getPassword();
				passworld=new String(c);
				if(userManager.queryByName(name, passworld)){
					new MainFrame();
					setVisible(false);
				}else{
					System.out.println("�˻��������");
				}
			}
		});
		registerBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				setVisible(false);
			}
		});
	}
	public static void main(String[] args) {
		new LoginFrame();
	}
}
