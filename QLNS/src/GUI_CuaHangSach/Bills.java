package GUI_CuaHangSach;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAL_CuaHangSach.BillDAO;
import DAL_CuaHangSach.BookDAO;
import DAL_CuaHangSach.CheckValid;
import DTO_CuaHangSach.BookDTO;


public class Bills extends JFrame implements MouseListener{
	private JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,pricelb,l10;
	protected static JTextField t1,t2,t3,t4,t5,t6,t7;
	private JButton add,reset,print,re,tol,returnb;
	protected static JTable table;
	private JPanel panel,p1,p2,p3;
	private JScrollPane pane,scroll;
	private JTextArea text;
	private ImageIcon reimg,printimg,logo;
	private JMenuBar menu;
	private JMenu m1;
	private ArrayList<BookDTO> list;
	protected static DefaultTableModel model;
	private Font f=new Font(Font.DIALOG_INPUT,Font.TRUETYPE_FONT,30);
	protected static int soluong=0;
	private int i=0;
	protected static float total=0,price=0;
	public Bills() {
		this.setSize(1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		panel=new JPanel();
		setContentPane(panel);
		panel.setBackground(new Color(205, 201 ,165));
		panel.setLayout(null);
		
		p1=new JPanel();
		p1.setBounds(560, 70, 40, 5);
		p1.setBackground(new Color(143,188,143));
		
		p2=new JPanel();
		p2.setBounds(270, 140, 300, 40);
		p2.setLayout(new FlowLayout());
		
		menu=new JMenuBar();
		m1=new JMenu("Đăng xuất");
		m1.addMouseListener(this);

		menu.add(m1);
		setJMenuBar(menu);
		
		logo=new ImageIcon("D:\\eclipese\\QLNS\\image\\logo.png");
		l=new JLabel("Book Store",logo,JLabel.CENTER);
		l.setFont(f);
		l.setBounds(380, 10, 250, 30);
		panel.add(l);
		
		p3=new JPanel();
		p3.setBounds(0, 0, 1000, 60);
		p3.setBackground(new Color(244, 164, 96));
		panel.add(p3);
		
//		l10=new JLabel("Nhan vien ban hang: ");
//		l10.setBounds(800,20,200,20);
//		panel.add(l10);
//		
		
		/*l1=new JLabel("ID Hóa Đơn ");
		l1.setBounds(20, 80, 100,15);
		t1=new JTextField();
		t1.setBounds(20, 100, 100, 25);
		panel.add(l1);
		panel.add(t1);*/
		
		l2=new JLabel("ID Hóa Đơn");
		l2.setBounds(130, 80, 100, 15);
		t2=new JTextField();
		t2.setBounds(130, 100, 100, 25);
		panel.add(l2);
		panel.add(t2);
		
		l3=new JLabel("Tên Khách Hàng");
		l3.setBounds(240, 80, 100, 15);
		t3=new JTextField();
		t3.setBounds(240, 100, 100, 25);
		panel.add(l3);
		panel.add(t3);
		
		l4=new JLabel("ID Sách");
		l4.setBounds(20, 130, 100, 15);
		t4=new JTextField();
		t4.setBounds(20, 150, 100, 25);
		panel.add(l4);
		panel.add(t4);
		
		l5=new JLabel("Tên Sách ");
		l5.setBounds(130, 130, 100, 15);
		t5=new JTextField();
		t5.setBounds(130, 150, 100, 25);
		panel.add(l5);
		panel.add(t5);
		
		l6=new JLabel("Số Lượng");
		l6.setBounds(240, 130, 100, 15);
		t6=new JTextField();
		t6.setBounds(240, 150, 100, 25);
		panel.add(l6);
		panel.add(t6);
		
		l7=new JLabel("Đơn Giá");
		l7.setBounds(20, 180,100, 15);
		t7=new JTextField();
		t7.setBounds(20, 200, 100, 25);
		panel.add(l7);
		panel.add(t7);
		
		t4.setEditable(false); 
		t5.setEditable(false);
		t7.setEditable(false);
		
		add=new JButton("Thêm vào hóa đơn");
		add.setBounds(110, 230,150, 25);
		panel.add(add);
		pricelb=new JLabel();
		pricelb.setBounds(530, 580, 150, 25);
		panel.add(pricelb);
		add.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb=new StringBuilder();
				CheckValid.checksl(t2, sb);
				CheckValid.checkEmpty(t3, sb, "Thông tin không để trống");
				CheckValid.checksl(t6, sb);
				
				if(sb.length()>0) {
					JOptionPane.showMessageDialog(null, sb.toString(),"Sai gia tri",JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				soluong=Integer.valueOf(model.getValueAt(table.getSelectedRow()	, 6).toString());
				// TODO Auto-generated method stub
				if(t2.getText()=="" || t3.getText()=="" || t6.getText()=="") {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
				}
				else if(Integer.valueOf(t6.getText())>soluong) {
					JOptionPane.showMessageDialog(null, "Số lượng không đủ");
				}
				else {
						i++;
						total=Float.valueOf(t7.getText())*Integer.valueOf(t6.getText());
						if(i==1) {
							text.setText(text.getText()+"  Tên Khách Hàng: "+t3.getText()+"                                                       ID hóa đơn: "+ t2.getText()+"\n");
							text.setText(text.getText()+"                                   ===========HÓA ĐƠN SÁCH============\n   STT    ID Sách    Tên Sách                        Số Lượng     Giá Tiền\n   "+i+
									"        "+t4.getText()+"               "+t5.getText()+"                      "+t6.getText()+"                  "+t7.getText()+"\n");
						}
						else {
							text.setText(text.getText()+"   "+i+"        "+t4.getText()+"               "+t5.getText()+"                      "+t6.getText()+"                  "+t7.getText()+"\n");
						}
						price=price+total;
						pricelb.setText("Tính tạm: "+price);
						
					}
				BillDAO.updateBill();
			}
			
			
		});
		
		reset=new JButton("Reset");
		reset.setBounds(270,230, 70	, 25);
		panel.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				t6.setText(null);
				t7.setText(null);
			}
			
		});
		
		
		
		l8=new JLabel("KHO SÁCH");
		l8.setBounds(200,260,100,15);
		panel.add(l8);
		
		pane=new JScrollPane();
		pane.setBounds(20, 280,500, 300);
		panel.add(pane);
		
		table=new JTable();
		table.setBackground(new Color(230,230,250));
		list=BookDAO.bookList();
	    model = new DefaultTableModel();
		Object[] col={"ID","Tên Sách","Tác Giả","Thể Loại","Nhà Xuất Bản","Giá Tiền","Số Lượng"};
		Object[] row=new Object[7];
		model.setColumnIdentifiers(col);
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getIDSach();
			row[1]=list.get(i).getTenSach();
			row[2]=list.get(i).getTacGia();
			row[3]=list.get(i).getTheLoai();
			row[4]=list.get(i).getNXB();
			row[5]=list.get(i).getGiaNhap();
			row[6]=list.get(i).getSoLuong();
			model.addRow(row);		
			
		}
		table.setModel(model);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			
					String idsach=model.getValueAt(table.getSelectedRow(), 0).toString();
					String tensach=model.getValueAt(table.getSelectedRow(), 1).toString();
					String giatien=model.getValueAt(table.getSelectedRow(), 5).toString();
					
					
					t4.setText(idsach);
					t5.setText(tensach);	
					t7.setText(giatien);
					
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		pane.setViewportView(table);
		
		text=new JTextArea();
	
		text.setEditable(false);
		scroll=new JScrollPane(text);
		scroll.setBounds(530, 100, 450, 480);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		printimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\printer.png");
		print=new JButton("Print",printimg);
		print.setBounds(600, 600, 90, 25);
		panel.add(print);
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					text.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		tol=new JButton("Thành tiền");
		tol.setBounds(700, 600, 100, 25);
		panel.add(tol);
		tol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text.setText(text.getText()+"\n                                                     -------------*********----------------\n");
				text.setText(text.getText()+"                                                                Tổng Tiền: "+price+" \n");
			}
			
		});
		
		returnb=new JButton("Return");
		returnb.setBounds(810,600, 90, 25);
		panel.add(returnb);
		returnb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text.setText(null);
				BillDAO.returnBill();
			}
			
		});
		
		 
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Bills();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==m1) {
			int repon=JOptionPane.showConfirmDialog(this,"Bạn có muốn đăng xuất chứ?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(repon==JOptionPane.YES_OPTION) {
				setVisible(false);
				Login l=new Login();
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
