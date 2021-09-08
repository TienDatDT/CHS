package GUI_CuaHangSach;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAL_CuaHangSach.UserDAO;
import DTO_CuaHangSach.UserDTO;

public class Signup extends JFrame implements ActionListener{
	private JButton b1,b2 ;
	private JPanel panel;
	private JLabel l,l1,l2,l3,l4,l5;
	private JPasswordField p1,p2;
	private JTextField t1,t2,t3;
	private Font f=new Font(Font.DIALOG_INPUT,Font.PLAIN,30);
	public Signup() {
		setTitle("Sign up");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		panel=new JPanel();
		panel.setBackground(new Color(230,230,250));
		setContentPane(panel);
		panel.setLayout(null);
		
		l=new JLabel("Sign up");
		l.setBounds(120, 30, 300, 40);
		l.setFont(f);
		l.setForeground(new Color(25,25,112));
		panel.add(l);
		
		l1=new JLabel("Họ :");
		l1.setBounds(40, 90, 130, 30);
		panel.add(l1);
		t1=new JTextField();
		t1.setBounds(180, 90, 180, 30);
		panel.add(t1);
		
		l2=new JLabel("Tên và Tên đệm :");
		l2.setBounds(40, 130, 130, 30);
		panel.add(l2);
		t2=new JTextField();
		t2.setBounds(180, 130, 180, 30);
		panel.add(t2);
		
		l3=new JLabel("Tên đăng nhập :");
		l3.setBounds(40, 170, 130, 30);
		panel.add(l3);
		t3=new JTextField();
		t3.setBounds(180, 170, 180, 30);
		panel.add(t3);
		
		l4=new JLabel("Password :");
		l4.setBounds(40, 210, 130, 30);
		panel.add(l4);
		p1=new JPasswordField();
		p1.setBounds(180, 210, 180, 30);
		panel.add(p1);
		l5=new JLabel("Nhập lại Password :");
		l5.setBounds(40, 250, 130, 30);
		panel.add(l5);
		p2=new JPasswordField();
		p2.setBounds(180, 250, 180, 30);
		panel.add(p2);
		
		b1=new JButton("Create");
		b1.setBounds(60	, 300, 90, 30);
		
		panel.add(b1);
		
		b2=new JButton("Back");
		b2.setBounds(200, 300, 90, 30);
		b2.addActionListener(this);
		panel.add(b2);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b2) {
			setVisible(false);
			new Login();
		}
	}
	

}
