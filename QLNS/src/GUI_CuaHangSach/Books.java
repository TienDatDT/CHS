package GUI_CuaHangSach;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import DAL_CuaHangSach.BookDAO;
import DAL_CuaHangSach.Connect;
import DTO_CuaHangSach.BookDTO;

public class Books extends JFrame implements MouseListener {

	private JButton save,edit,del,re,look,exp,imp,chartbt;
	private JLabel l,lnv,lusr,lbook,lcus,l3,l4,l5,l6,l7,l8,l9;
	protected static JTextField t3,t4,t5,t6,t7,t8,t9,txtlook;
	private JPanel panel,p1,p2,p3;
	protected static JTable table;
	private JScrollPane pane; 
	protected static JComboBox box;
	protected static JRadioButton b1,b2;
	protected static ButtonGroup grp;
	private ImageIcon usrimg,bookimg,empimg,customimg,logo;
	private JMenuBar menu;
	private JMenu m1;
	JFreeChart chart1;
	private ArrayList<BookDTO> list,list1,list2;
	protected static DefaultTableModel model,model1;
	private String[] cate= {"Khoa Học","Kinh Tế","Giáo Dục","Thiếu Nhi","Nghệ Thuật","Nấu Ăn","Lịch Sử","Thể Thao","Văn Học"};
	private Font f=new Font(Font.DIALOG_INPUT,Font.TRUETYPE_FONT,30);
	public Books() {
		this.setTitle("Quản lý sách");
		this.setSize(880,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		panel=new JPanel();
		setContentPane(panel);
		panel.setBackground(new Color(205, 201 ,165));
		panel.setLayout(null);
		
		p1=new JPanel();
		p1.setBounds(500, 73, 60, 5);
		p1.setBackground(new Color(143,188,143));
		
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
		l3.setBounds(20, 80, 100,15);
		t3=new JTextField();
		t3.setBounds(20, 100, 100, 25);
		panel.add(l3);
		panel.add(t3);
		
		l4=new JLabel("Tên Sách");
		l4.setBounds(140, 80 , 100, 15);
		panel.add(l4);
		t4=new JTextField();
		t4.setBounds(140, 100, 100, 25);
		panel.add(t4);
		
		l5=new JLabel("Tác Giả");
		l5.setBounds(260, 80 , 100, 15);
		panel.add(l5);
		t5=new JTextField();
		t5.setBounds(260, 100, 100, 25);
		panel.add(t5);
		
		l6=new JLabel("Thể Loại");
		l6.setBounds(380, 80 , 100, 15);
		panel.add(l6);
		box=new JComboBox(cate);
		box.setBounds(380, 100, 100, 25);
		panel.add(box);
		
		l7=new JLabel("Nhà Xuất Bản");
		l7.setBounds(500, 80 , 100, 15);
		panel.add(l7);
		t7=new JTextField();
		t7.setBounds(500, 100, 100, 25);
		panel.add(t7);
		
		
		l8=new JLabel("Giá Tiền");
		l8.setBounds(620, 80 , 100, 15);
		panel.add(l8);
		t8=new JTextField();
		t8.setBounds(620, 100, 100, 25);
		panel.add(t8);
		
		l9=new JLabel("Số Lượng");
		l9.setBounds(740, 80 , 100, 15);
		panel.add(l9);
		t9=new JTextField();
		t9.setBounds(740, 100, 100, 25);
		panel.add(t9);
		
		
		pane=new JScrollPane();
		pane.setBounds(20, 200, 820, 300);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
		pane.setViewportView(table);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectedRowCount()==1) {
					String IDr= model.getValueAt(table.getSelectedRow(), 0).toString();
					String tenSachr=model.getValueAt(table.getSelectedRow(), 1).toString();
					String tacGiar=model.getValueAt(table.getSelectedRow(), 2).toString();
					Object theLoair=model.getValueAt(table.getSelectedRow(), 3);
					String NXBr=model.getValueAt(table.getSelectedRow(), 4).toString();
					String giaBanr=model.getValueAt(table.getSelectedRow(), 5).toString();
					String soLuongr=model.getValueAt(table.getSelectedRow(),6).toString();
					//txtfield nhan gia tri tu cac chuoi
					t3.setText(IDr);
					t4.setText(tenSachr);
					t5.setText(tacGiar);
					box.setSelectedItem(theLoair);
					t7.setText(NXBr);
					t8.setText(giaBanr);
					t9.setText(soLuongr);
				}else {
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
		
		save=new JButton("Thêm");
		p2.add(save);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDAO.addBook();
					
				
			}
			
		});
		
		edit=new JButton("Sửa");
		p2.add(edit);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDAO.editBook();
					
			}
					
			
		});
		
		del=new JButton("Xóa");
		p2.add(del);
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDAO.deleteBook();
			}
			
		});
		
		re=new JButton("Tạo mới");
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
				t9.setText(null);
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
				
				
			}
			
		});
		
		exp=new	JButton("Xuất File");
		exp.setBounds(490, 505, 100, 30);
		panel.add(exp);
		exp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDAO.exportBook();
				
			}
			
		});
		
		imp=new JButton("Nhập File");
		imp.setBounds(600, 505, 100, 30);
		panel.add(imp);
		imp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDAO.importBook();
			}
			
		});
		
		chartbt=new JButton("Thống kê");
		chartbt.setBounds(710,505,100,30);
		panel.add(chartbt);
		chartbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<BookDTO> list=BookDAO.bookList();
				DefaultCategoryDataset data=new DefaultCategoryDataset();
				if(list!=null) {
					for(BookDTO i:list) {
					data.addValue(i.getSoLuong(), "Số Lượng Sách", i.getTenSach());
					}
				}
				chart1=ChartFactory.createBarChart("Bảng thống kê tên sách".toUpperCase(), "Tên sách", "Số Lượng", data,PlotOrientation.HORIZONTAL,false,true,false);
				CategoryPlot plot=chart1.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.MAGENTA);
				
				ChartFrame cf=new ChartFrame("Thống kê số lượng sách",chart1,true);
				cf.setVisible(true);
				cf.setSize(700,500);
				cf.setLocationRelativeTo(null);
				
			}
			
		});
		
		txtlook=new JTextField();
		txtlook.setBounds(480,140,150,25);
		panel.add(txtlook);
		look=new JButton("Tìm Kiếm");
		look.setBounds(640, 140, 90, 30);
		panel.add(look);
		
		b1=new JRadioButton("ID");
		b1.setBounds(500, 170, 40,25);
		panel.add(b1);
		
		b2=new JRadioButton("Tên Sách");
		b2.setBounds(550, 170, 80, 25);
		panel.add(b2);
		
		grp=new ButtonGroup();
		grp.add(b1);
		grp.add(b2);
		
		look.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b1.isSelected()) {
						list1=BookDAO.bookfindID();
						model1 = new DefaultTableModel();
						model1.setColumnIdentifiers(col);
						Object[] col={"ID","Tên Sách","Tác Giả","Thể Loại","Nhà Xuất Bản","Giá Tiền","Số Lượng"};
						Object[] row=new Object[7];
						for(int i=0;i<list1.size();i++) {
							row[0]=list1.get(i).getIDSach();
							row[1]=list1.get(i).getTenSach();
							row[2]=list1.get(i).getTacGia();
							row[3]=list1.get(i).getTheLoai();
							row[4]=list1.get(i).getNXB();
							row[5]=list1.get(i).getGiaNhap();
							row[6]=list1.get(i).getSoLuong();
							model1.addRow(row);		
						}
						table.setModel(model1);
			
				}
				if(b2.isSelected()) {
						list1=BookDAO.bookfindName(getName());
						model1 = new DefaultTableModel();
						model1.setColumnIdentifiers(col);
						Object[] col={"ID","Tên Sách","Tác Giả","Thể Loại","Nhà Xuất Bản","Giá Tiền","Số Lượng"};
						Object[] row=new Object[7];
						for(int i=0;i<list1.size();i++) {
							row[0]=list1.get(i).getIDSach();
							row[1]=list1.get(i).getTenSach();
							row[2]=list1.get(i).getTacGia();
							row[3]=list1.get(i).getTheLoai();
							row[4]=list1.get(i).getNXB();
							row[5]=list1.get(i).getGiaNhap();
							row[6]=list1.get(i).getSoLuong();
							model1.addRow(row);		
						}
						table.setModel(model1);
						
				}
		}	
		});
		

		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Books();
	}
	public void mouseClicked(MouseEvent e1) {
		// TODO Auto-generated method stub
		if(e1.getSource()==lusr) {
			setVisible(false);
			Users user=new Users();
		}
		if(e1.getSource()==lnv) {
			setVisible(false);
			Employee employ=new Employee();
		}
		if(e1.getSource()==lcus) {
			setVisible(false);
			Customer cus=new Customer();
		}
		if(e1.getSource()==m1) {
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
