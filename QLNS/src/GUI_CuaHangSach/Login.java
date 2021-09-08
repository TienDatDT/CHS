package GUI_CuaHangSach;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAL_CuaHangSach.UserDAO;
import DTO_CuaHangSach.UserDTO;

public class Login extends JFrame {
	private JLabel l,l1,l2,l3;
	private JButton b1,b2;
	private JButton[] func=new JButton[2];
	private JPasswordField p;
	protected static String[] quyenlist= {"Admin","Nhân Viên"};
	protected static JComboBox box;
	private ImageIcon usrimg,passimg,logo;
	private JTextField t;
	private JPanel panel;

	private Font f=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
	String user="admin"; 
	 String pass="123456"; 
	public Login(){
		setTitle("Login");
		setSize(400,300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		setContentPane(panel);
		panel.setBackground(new Color(205, 201 ,165));
		panel.setLayout(null);
	
		
		logo=new ImageIcon("D:\\eclipese\\QLNS\\image\\logo.png");
		l=new JLabel("BOOK STORE",logo,JLabel.CENTER);
		l.setBounds(30, 20, 360, 30);
		l.setFont(f);
		l.setForeground(new Color(128,128,0));
		
		box=new JComboBox(quyenlist);
		box.setBounds(150, 70, 90, 30);
		panel.add(box);
		
		usrimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\usrname.png");
		l1=new JLabel("Tên đăng nhập :",usrimg,JLabel.CENTER);
		l1.setBounds(20, 110, 120, 30);
		panel.add(l1);
		t=new JTextField();
		t.setBounds(150, 110, 200, 30);
		panel.add(t);
		
		passimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\lockimg.png");
		l2=new JLabel("Password :",passimg,JLabel.CENTER);
		l2.setBounds(20, 150, 120, 30);
		panel.add(l2);
		p=new JPasswordField();
		p.setBounds(150, 150, 200, 30);
		panel.add(p);
		
		b1=new JButton("Đăng nhập",new ImageIcon("D:\\eclipese\\QLNS\\image\\login.png"));
		b1.setBounds(150, 190, 130, 30);
		b1.setBackground(new Color(108 ,166, 205));
		panel.add(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user=t.getText();
				String pass=new String(p.getPassword());
				Object quyen=box.getSelectedItem();
				UserDTO users=UserDAO.users(user, pass,quyen);
				if(users!=null && box.getSelectedItem()=="Admin" ) {
					JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công");
					Books book=new Books();
					setVisible(false);
				}
				else if(users!=null && box.getSelectedItem()=="Nhân Viên") {
					JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công");
					Bills bill=new Bills();
					setVisible(false);

				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Sai tên đăng nhập hoặc password");
				}
			}
			
		});
				
		add(l);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Login l=new Login();
	}

	
}
