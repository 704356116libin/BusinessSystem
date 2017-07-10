package login;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import dao.ProblemManager;
import dao.UserManager;
import model.Item;
import model.Users;

/**
 * 注册窗体
 * @author Administrator
 *
 */
public class RegisterFrame extends JFrame {
	private JLabel userLabel;//用户名标签
	private JLabel pwdLabel;//密码标签
	private JLabel problemLabel;//安全问题标签
	private JLabel answerLabel;//安全问题答案标签
	private JLabel tipsLabel;//温馨提示标签
	private JButton button;//注册按钮
	private JTextField field_user;
	private JPasswordField field_pwd;
	private JTextField field_answer;
	private JComboBox<String>problem_box;//安全问题下拉列表
	private Container container;//顶级容器
	private SpringLayout containerLayout;//顶层容器的布局方式
	
	private ProblemManager problemManager;//安全问题数据管理
	private UserManager userManager;
	private Vector<String>problemName=new Vector<>();
	private Vector<Item>itemVector=new Vector<>();
	static final String rex="[\\p{Alnum}[%.+-@_*/]]{8,}";
	String name;
	String answer;
	String pwd;
	String problem_id;
	public RegisterFrame() {
		setTitle("用户注册");
		setResizable(false);
		setBounds(600, 400, 400, 300);
		init();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	/**
	 * 初始化组件
	 */
	private void init() {
		queryProblemItem();//给下拉列表设置数据项
		container=getContentPane();
		containerLayout=new SpringLayout();
		container.setLayout(containerLayout);
		Font font1=new Font("微软雅黑", Font.PLAIN, 11);
		Font font2=new Font("微软雅黑", Font.BOLD, 13);
		userLabel=new JLabel("用户名  :");field_user=new JTextField();
		pwdLabel=new JLabel("密   码  :");field_pwd=new JPasswordField();
		problemLabel=new JLabel("密保问题:");problem_box=new JComboBox<>(problemName);
		answerLabel=new JLabel("问题答案:");field_answer=new JTextField();
		tipsLabel=new JLabel("(由字母,数字,常用字符组成)");
		button=new JButton("注册");
		
		tipsLabel.setFont(font1);
		userLabel.setFont(font2);
		pwdLabel.setFont(font2);
		problemLabel.setFont(font2);
		answerLabel.setFont(font2);
		tipsLabel.setAlignmentY(0.5f);
		button.setFont(font2);
		addToContainer();//将组件加入到容器当中
		
		RegisterListener();
	}
	private void RegisterListener() {
		problem_box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					problem_id=itemVector.get(problem_box.getSelectedIndex()).getId();
				}
				
			}
		});
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				name=field_user.getText();
				char [] c=field_pwd.getPassword();
				pwd=new String(c);
				answer=field_answer.getText();
				if(name.isEmpty()||answer.isEmpty()||pwd.isEmpty()||problem_id.isEmpty()){
					System.out.println("请检查是否有数据未填");
				}else{
					if(name.matches(rex)&&pwd.matches(rex)){
					Users users=new Users();
					users.setName(name);
					users.setMima(pwd);
					users.setAnswer(answer);
					users.setProblem_id(problem_id);
					if(userManager.add(users)){
						System.out.println("注册成功");
						setVisible(false);
						new LoginFrame();
					}
					}else{
						System.out.println("用户名/密码填写非法");
					}
				}
			}
		});
		problem_box.setSelectedIndex(1);
		problem_box.setSelectedIndex(0);
	}
	private void queryProblemItem() {
		problemManager=new ProblemManager();
		userManager=new UserManager();
		itemVector=problemManager.queryItem();
		for(Item item:itemVector){
			problemName.add(item.getName());
		}
	}
	private void addToContainer() {
		container.add(userLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, userLabel, 30, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, userLabel, 50, SpringLayout.WEST, container);
		container.add(field_user);
		containerLayout.putConstraint(SpringLayout.NORTH, field_user, 30, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, field_user, 20, SpringLayout.EAST, userLabel);
		containerLayout.putConstraint(SpringLayout.EAST, field_user, -50, SpringLayout.EAST, container);
		
		container.add(pwdLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 10, SpringLayout.SOUTH, userLabel);
		containerLayout.putConstraint(SpringLayout.WEST, pwdLabel, 50, SpringLayout.WEST, container);
		container.add(field_pwd);
		containerLayout.putConstraint(SpringLayout.NORTH, field_pwd, 0, SpringLayout.NORTH, pwdLabel);
		containerLayout.putConstraint(SpringLayout.WEST, field_pwd, 0, SpringLayout.WEST, field_user);
		containerLayout.putConstraint(SpringLayout.EAST, field_pwd, -50, SpringLayout.EAST, container);
		container.add(tipsLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, tipsLabel, 5, SpringLayout.SOUTH, field_pwd);
		containerLayout.putConstraint(SpringLayout.WEST, tipsLabel, 0, SpringLayout.WEST, field_user);
		
		container.add(problemLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, problemLabel, 10, SpringLayout.SOUTH, tipsLabel);
		containerLayout.putConstraint(SpringLayout.WEST, problemLabel, 50, SpringLayout.WEST, container);
		container.add(problem_box);
		containerLayout.putConstraint(SpringLayout.NORTH, problem_box, 0, SpringLayout.NORTH, problemLabel);
		containerLayout.putConstraint(SpringLayout.WEST, problem_box, 0, SpringLayout.WEST, field_user);
		containerLayout.putConstraint(SpringLayout.EAST, problem_box, -50, SpringLayout.EAST, container);
		
		container.add(answerLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, answerLabel, 10, SpringLayout.SOUTH, problemLabel);
		containerLayout.putConstraint(SpringLayout.WEST, answerLabel, 50, SpringLayout.WEST, container);
		container.add(field_answer);
		containerLayout.putConstraint(SpringLayout.NORTH, field_answer, 0, SpringLayout.NORTH, answerLabel);
		containerLayout.putConstraint(SpringLayout.WEST, field_answer, 0, SpringLayout.WEST, field_user);
		containerLayout.putConstraint(SpringLayout.EAST, field_answer, -50, SpringLayout.EAST, container);
		
		container.add(button);
		containerLayout.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.SOUTH, answerLabel);
		containerLayout.putConstraint(SpringLayout.WEST, button, 0, SpringLayout.WEST, field_user);
		containerLayout.putConstraint(SpringLayout.EAST, button, -100, SpringLayout.EAST, container);
	}
}
