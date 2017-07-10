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
 * 登录窗体
 * @author Administrator
 *
 */
public class LoginFrame extends JFrame {
	private JLabel user;
	private JLabel pwd;
	private JLabel imgLabel;//图片标签
	private JTextField nameField;
	private JPasswordField pwdField;
	private SpringLayout springLayout;//主容器采用的布局方式
	private JButton registerBut;
	private JButton loginBut;
	private Container container;//窗口主容器
	
	private UserManager userManager;
	String name;
	String passworld;
	public LoginFrame() {
		setTitle("登录界面");
		setResizable(false);
		setBounds(600, 400, 350, 150);
		init();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	private void init() {
		userManager=new UserManager();
		user=new JLabel("账户:");
		pwd=new JLabel("密码:");
		nameField=new JTextField();
		pwdField=new JPasswordField();
		imgLabel=new JLabel();
		imgLabel.setIcon(new ImageIcon(this.getClass().getResource("/icon/login.png")));
//		imgLabel.setSize(80, 80);
		registerBut=new JButton("注册");
		loginBut=new JButton("登录");
		
		container=getContentPane();//拿到顶级容器
//		container.setBackground(new Color(244, 164, 96));
		springLayout=new SpringLayout();
		container.setLayout(springLayout);
		//开始设定组件的排布方式
		container.add(user);
		springLayout.putConstraint(SpringLayout.NORTH, user,20, SpringLayout.NORTH, container);
		springLayout.putConstraint(SpringLayout.WEST, user, 100, SpringLayout.WEST, container);
		//标题文本框的位置设置
		container.add(nameField);
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, container);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -5, SpringLayout.EAST, container);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 5, SpringLayout.EAST,user);
		//密码标签的位置设置
		container.add(pwd);
		springLayout.putConstraint(SpringLayout.WEST, pwd, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, pwd, 10, SpringLayout.SOUTH, nameField);
		container.add(pwdField);
		springLayout.putConstraint(SpringLayout.WEST, pwdField, 5, SpringLayout.EAST, pwd);
		springLayout.putConstraint(SpringLayout.NORTH, pwdField, 10, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, pwdField, -5, SpringLayout.EAST,container);
		//按钮位置的确定
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
		//按钮监听
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
					System.out.println("账户密码错误");
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
