package GUI_CuaHangSach;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import DAL_CuaHangSach.CustomerDAO;
import DAL_CuaHangSach.NhanVienDAO;
import DTO_CuaHangSach.NhanVienDTO;


public class Employee extends JFrame implements MouseListener{
	private JButton save,edit,del,re,look,imp,exp;
	private JLabel l,lnv,lbook,lusr,lcus,l3,l4,l5,l6,l7,l8;
	protected static JTextField t3;
	protected static JTextField t4;
	protected static JTextField t5;
	protected static JTextField t7;
	protected static JTextField t8;
	protected static JTextField t9;
	protected static JTextField t10,txtlook;
	protected static ButtonGroup grp;
	private ImageIcon usrimg,bookimg,empimg,customimg,logo;
	private JMenuBar menu;
	private JMenu m1;
	protected static JRadioButton b1,b2;
	private JPanel panel,p1,p2,p3;
	protected static JComboBox box;
	private String gen[]= {"Nam","Nữ","Khác"};
	protected static JTable table;
	private JScrollPane pane;
	protected static DefaultTableModel model;
	private ArrayList<NhanVienDTO> list1;
	private Font f=new Font(Font.DIALOG_INPUT,Font.TRUETYPE_FONT,30);
	public Employee() {
		this.setTitle("Quản lý nhân viên");
		this.setSize(880,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		panel=new JPanel();
		setContentPane(panel);
		panel.setBackground(new Color(205, 201 ,165));
		panel.setLayout(null);
		
		p1=new JPanel();
		p1.setBounds(400, 70, 90, 5);
		p1.setBackground(new Color(205 ,183, 158));
		
		p2=new JPanel();
		p2.setBounds(160, 140, 300, 40);
		p2.setBackground(new Color(205 ,183, 158));
		p2.setLayout(new FlowLayout());
		
		menu=new JMenuBar();
		m1=new JMenu("Đăng xuất");
		m1.addMouseListener(this);

		menu.add(m1);
		setJMenuBar(menu);
		
		logo=new ImageIcon("D:\\eclipese\\QLNS\\image\\logo.png");
		l=new JLabel("Book Store",logo,JLabel.CENTER);
		l.setFont(f);
		l.setBounds(180, 5, 250, 30);
		panel.add(l);
		
		p3=new JPanel();
		p3.setBounds(0, 0, 880, 40);
		p3.setBackground(new Color(244, 164, 96));
		panel.add(p3);
		
		empimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\emplyimg.png");
		lnv=new JLabel("Nhân Viên",empimg,JLabel.CENTER);
		lnv.setBounds(400, 40, 90 , 30);
		lnv.setForeground(new Color(119,136,153));
		lnv.addMouseListener(this);
		panel.add(lnv);
		
		bookimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\bookimg.png");
		lbook=new JLabel("Sách",bookimg,JLabel.CENTER);
		lbook.setBounds(500, 40, 60, 30);
		lbook.setForeground(new Color(119,136,153));
		lbook.addMouseListener(this);
		panel.add(lbook);
		panel.add(p1);
		
		usrimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\usrimg.png");
		lusr=new JLabel("Người Dùng",usrimg,JLabel.CENTER);
		lusr.setBounds(570, 40, 100, 30);
		lusr.setForeground(new Color(119,136,153));
		lusr.addMouseListener(this);
		panel.add(lusr);
		
		customimg=new ImageIcon("D:\\eclipese\\QLNS\\image\\custom.png");
		lcus=new JLabel("Khách Hàng",customimg,JLabel.CENTER);
		lcus.setBounds(680, 40, 100, 30);
		lcus.setForeground(new Color(119,136,153));
		lcus.addMouseListener(this);
		panel.add(lcus);
		
		l3=new JLabel("ID");
		l3.setBounds(20, 80, 50,15);
		t3=new JTextField();
		t3.setBounds(20, 100, 50, 25);
		panel.add(l3);
		panel.add(t3);
		
		l4=new JLabel("Tên Nhân Viên");
		l4.setBounds(80, 80 , 150, 15);
		panel.add(l4);
		t4=new JTextField();
		t4.setBounds(80, 100, 150, 25);
		panel.add(t4);
		
		l5=new JLabel("Ngày Sinh");
		l5.setBounds(240, 80 , 100, 15);
		panel.add(l5);
		t5=new JTextField();
		t5.setBounds(240, 100, 100, 25);
		panel.add(t5);
		
		l6=new JLabel("Giới Tính");
		l6.setBounds(350, 80 ,80, 15);
		panel.add(l6);
		box=new JComboBox(gen);
		box.setBounds(350, 100, 80, 25);
		panel.add(box);
		
		l7=new JLabel("Địa Chỉ");
		l7.setBounds(440, 80 , 150, 15);
		panel.add(l7);
		t7=new JTextField();
		t7.setBounds(440, 100, 150, 25);
		panel.add(t7);
		
		
		l8=new JLabel("Số Điện Thoại");
		l8.setBounds(600, 80 , 120, 15);
		panel.add(l8);
		t8=new JTextField();
		t8.setBounds(600, 100, 120, 25);
		panel.add(t8);
		
		
		
		pane=new JScrollPane();
		pane.setBounds(20, 200, 820, 300);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(pane);
		
		
		table=new JTable();
		table.setBackground(new Color(230,230,250));
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectedColumnCount()==1) {
					//khi table get 1 row thi model se lan luot lay gia tri tung row tren table
					String id=model.getValueAt(table.getSelectedRow(), 0).toString();
					String tennv=model.getValueAt(table.getSelectedRow(), 1).toString();
					String ngaysinh=model.getValueAt(table.getSelectedRow(), 2).toString();
					Object gioitinh=model.getValueAt(table.getSelectedRow(), 3).toString();
					String diachi=model.getValueAt(table.getSelectedRow(), 4).toString();
					String sdt=model.getValueAt(table.getSelectedRow(), 5).toString();
					//cac textfield se lay gia tri cua tung thuoc tinh gan len text
					t3.setText(id);
					t4.setText(tennv);
					t5.setText(ngaysinh);
					box.setSelectedItem(gioitinh);
					t7.setText(diachi);
					t8.setText(sdt);
					}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng cần sửa");
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
			
		});
		list1= NhanVienDAO.nvList();
		model = new DefaultTableModel();
		Object[] col={"Mã Nhân Viên","Tên Nhân Viên","Ngày Sinh","Giới Tính","Địa Chỉ","SDT"};
		Object[] row=new Object[6];
		model.setColumnIdentifiers(col);
			for(int i=0;i<list1.size();i++) {
				row[0]=list1.get(i).getID();
				row[1]=list1.get(i).getTenNV();
				row[2]=list1.get(i).getNgaySinh();
				row[3]=list1.get(i).getGioiTinh();
				row[4]=list1.get(i).getDiaChi();
				row[5]=list1.get(i).getSDT();
				model.addRow(row);
			}
			
		save=new JButton("Thêm");
		p2.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVienDAO.addNV();
				
			}
			
		});
		
		edit=new JButton("Sửa");
		p2.add(edit);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVienDAO.editNV();
			}
			
		});
		
		del=new JButton("Xóa");
		p2.add(del);
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVienDAO.deleteNV();
			}
			
		});
		
		re=new JButton("Tạo Mới");
		p2.add(re);
		panel.add(p2);
		re.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				t7.setText(null);
				t8.setText(null);
				list1= NhanVienDAO.nvList();
				model = new DefaultTableModel();
				Object[] col={"Mã Nhân Viên","Tên Nhân Viên","Ngày Sinh","Giới Tính","Địa Chỉ","SDT"};
				Object[] row=new Object[6];
				model.setColumnIdentifiers(col);
					for(int i=0;i<list1.size();i++) {
						row[0]=list1.get(i).getID();
						row[1]=list1.get(i).getTenNV();
						row[2]=list1.get(i).getNgaySinh();
						row[3]=list1.get(i).getGioiTinh();
						row[4]=list1.get(i).getDiaChi();
						row[5]=list1.get(i).getSDT();
						model.addRow(row);
					}
					table.setModel(model);
			}
			
		});
		

		txtlook=new JTextField();
		txtlook.setBounds(480,140,150,25);
		panel.add(txtlook);
		look=new JButton("Tìm Kiếm");
		look.setBounds(640, 140, 90, 30);
		panel.add(look);
		look.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b1.isSelected()) {
					list1=NhanVienDAO.findid();
					model = new DefaultTableModel();
					Object[] col={"Mã Nhân Viên","Tên Nhân Viên","Ngày Sinh","Giới Tính","Địa Chỉ","SDT"};
					Object[] row=new Object[6];
					model.setColumnIdentifiers(col);
						for(int i=0;i<list1.size();i++) {
							row[0]=list1.get(i).getID();
							row[1]=list1.get(i).getTenNV();
							row[2]=list1.get(i).getNgaySinh();
							row[3]=list1.get(i).getGioiTinh();
							row[4]=list1.get(i).getDiaChi();
							row[5]=list1.get(i).getSDT();
							model.addRow(row);
						}
						table.setModel(model);
				}
				if(b2.isSelected()) {
					list1=NhanVienDAO.findtennv(getName());
					model = new DefaultTableModel();
					Object[] col={"Mã Nhân Viên","Tên Nhân Viên","Ngày Sinh","Giới Tính","Địa Chỉ","SDT"};
					Object[] row=new Object[6];
					model.setColumnIdentifiers(col);
						for(int i=0;i<list1.size();i++) {
							row[0]=list1.get(i).getID();
							row[1]=list1.get(i).getTenNV();
							row[2]=list1.get(i).getNgaySinh();
							row[3]=list1.get(i).getGioiTinh();
							row[4]=list1.get(i).getDiaChi();
							row[5]=list1.get(i).getSDT();
							model.addRow(row);
						}
						table.setModel(model);
				}
			}
			
		});
		
		b1=new JRadioButton("ID");
		b1.setBounds(500, 170, 40,25);
		panel.add(b1);
		
		b2=new JRadioButton("Tên Nhân Viên");
		b2.setBounds(550, 170, 110, 25);
		panel.add(b2);
		
		grp=new ButtonGroup();
		grp.add(b1);
		grp.add(b2);
		
		table.setModel(model);
		pane.setViewportView(table);
		
		exp=new	JButton("Xuất File");
		exp.setBounds(600, 505, 100, 30);
		panel.add(exp);
		exp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVienDAO.exportEmploy();
				
			}
			
		});
		
		imp=new JButton("Nhập File");
		imp.setBounds(710, 505, 100, 30);
		panel.add(imp);
		imp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVienDAO.importEmploy();
			}
			
		});
		

		this.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new Employee();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==lbook) {
			setVisible(false);
			Books book=new Books();
		}
		if(e.getSource()==lusr) {
			setVisible(false);
			Users usr=new Users();
		}
		if(e.getSource()==lcus) {
			setVisible(false);
			Customer cus=new Customer();
		}
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
