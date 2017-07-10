package fram;

import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import dao.Dao;

public class BackupAndRestore extends JFrame{
	private JLabel label1;
	private JLabel label2;
	private TextField field_label1;
	private TextField field_label2;
	private JButton backBut;//备份按钮
	private JButton browseBut;//浏览按钮
	private JButton recoverBut;//恢复按钮
	private Container container;
	private SpringLayout containerLayout;
	public BackupAndRestore() {
		setBounds(200, 200, 500, 200);
		init();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	private void init() {
		container=getContentPane();
		containerLayout=new SpringLayout();
		container.setLayout(containerLayout);
		label1=new JLabel("数据库备份");
		label2=new JLabel("数据库恢复");
		field_label1=new TextField();
		field_label1.setEditable(false);
		field_label2=new TextField();
		backBut=new JButton("备份");
		browseBut=new JButton("浏览....");
		recoverBut=new JButton("恢复");
		
		addToContainer();//组件加入容器
	}
	private void addToContainer() {
		container.add(label1);
		containerLayout.putConstraint(SpringLayout.NORTH, label1, 10, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
		container.add(field_label1);
		containerLayout.putConstraint(SpringLayout.NORTH, field_label1, 10, SpringLayout.NORTH, container);
		containerLayout.putConstraint(SpringLayout.WEST, field_label1, 10, SpringLayout.EAST, label1);
		containerLayout.putConstraint(SpringLayout.EAST, field_label1, -20, SpringLayout.EAST, container);
		container.add(backBut);
		containerLayout.putConstraint(SpringLayout.NORTH, backBut, 5, SpringLayout.SOUTH, field_label1);
		containerLayout.putConstraint(SpringLayout.EAST, backBut, -20, SpringLayout.EAST, container);
		containerLayout.putConstraint(SpringLayout.SOUTH, backBut, -100, SpringLayout.SOUTH, container);
		
		container.add(label2);
		containerLayout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, backBut);
		containerLayout.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
		container.add(field_label2);
		containerLayout.putConstraint(SpringLayout.NORTH, field_label2, 0, SpringLayout.NORTH, label2);
		containerLayout.putConstraint(SpringLayout.WEST, field_label2, 10, SpringLayout.EAST, label2);
		containerLayout.putConstraint(SpringLayout.EAST, field_label2, -20, SpringLayout.EAST, container);
		container.add(recoverBut);
		containerLayout.putConstraint(SpringLayout.NORTH, recoverBut, 10, SpringLayout.SOUTH, field_label2);
		containerLayout.putConstraint(SpringLayout.EAST, recoverBut, 0, SpringLayout.EAST, field_label2);
		containerLayout.putConstraint(SpringLayout.SOUTH, recoverBut, -33, SpringLayout.SOUTH, container);
		container.add(browseBut);
		containerLayout.putConstraint(SpringLayout.NORTH, browseBut, 0, SpringLayout.NORTH, recoverBut);
		containerLayout.putConstraint(SpringLayout.EAST, browseBut, -5, SpringLayout.WEST, recoverBut);
		containerLayout.putConstraint(SpringLayout.SOUTH, browseBut, 0, SpringLayout.SOUTH, recoverBut);
		
		registerListener();
	}
	private void registerListener() {
		browseBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser(".");
				
				int option=chooser.showOpenDialog(BackupAndRestore.this);
				if(option==JFileChooser.APPROVE_OPTION){
					File selectFile=chooser.getSelectedFile();
					field_label2.setText(selectFile.getAbsolutePath());
				}
			}
		});
		backBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
