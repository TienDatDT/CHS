package DAL_CuaHangSach;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;

import DTO_CuaHangSach.BookDTO;
import GUI_CuaHangSach.Books;

public class BookDAO extends Books {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Statement st=null;
	public BookDAO(){
		try(Connection conn=Connect.getCon()){
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	public static ArrayList<BookDTO> bookList() {
		ArrayList<BookDTO> bookList=new ArrayList<>();
		try(Connection conn=Connect.getCon()){
			st=conn.createStatement();
			String sql="select * from booktbl";
			rs=st.executeQuery(sql);
			BookDTO book=null;
			while(rs.next()) {
				book=new BookDTO(rs.getInt("IDSach"),rs.getString("tenSach"),rs.getString("tacGia"),rs.getString("theLoai"),rs.getString("NXB"),rs.getInt("giaTien"),rs.getInt("soLuong"));
				bookList.add(book);
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return bookList;
	}
	public static void addBook() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checksl(t3, sb);
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t7, sb, "Thông tin không để trống");
			CheckValid.checkgt(t8, sb);
			CheckValid.checksl(t9, sb);
			
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai giá trị",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String [] data= {t3.getText(),t4.getText(),t5.getText(),(String) box.getSelectedItem(),t7.getText(),t8.getText(),
					t9.getText()};
			model.addRow(data);
			try(Connection conn=Connect.getCon()){
				String sql="insert into booktbl(IDSach,tenSach,tacGia,theLoai,NXB,giaTien,soLuong)"
						+ "values(?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, t3.getText());
				ps.setString(2, t4.getText());
				ps.setString(3,t5.getText());
				ps.setObject(4, box.getSelectedItem());
				ps.setString(5,t7.getText());
				ps.setString(6,t8.getText());
				ps.setString(7,t9.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Đã thêm vào");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void editBook() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t7, sb, "Thông tin không để trống");
			CheckValid.checkgt(t8, sb);
			CheckValid.checksl(t9, sb);
			
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai gia tri",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(table.getSelectedRowCount()==1) {
				int ID=Integer.valueOf(t3.getText());
				String tensach=t4.getText();
				String tacgia=t5.getText();
				Object theloai=(String)box.getSelectedItem();
				String nxb=t7.getText();
				String giaban=t8.getText();
				String soluong=t9.getText();
				//model thay doi cac gia tri tu cac txtfld vao row tren tbl
               model.setValueAt(ID, table.getSelectedRow(), 0);
               model.setValueAt(tensach, table.getSelectedRow(), 1);
               model.setValueAt(tacgia, table.getSelectedRow(), 2);
               model.setValueAt(theloai, table.getSelectedRow(), 3);
               model.setValueAt(nxb, table.getSelectedRow(), 4);
               model.setValueAt(giaban, table.getSelectedRow(), 5);
               model.setValueAt(soluong, table.getSelectedRow(), 6);
               try(Connection conn=Connect.getCon()){
       			String sql="update booktbl set tenSach=?,tacGia=?,theLoai=?,NXB=?,giaTien=?,soLuong=? where "
       					+ "IDSach=?" ;
       			ps=conn.prepareStatement(sql);
       			ps.setString(7, t3.getText());
       			ps.setString(1, t4.getText());
       			ps.setString(2,t5.getText());
       			ps.setObject(3, box.getSelectedItem());
       			ps.setString(4,t7.getText());
       			ps.setString(5,t8.getText());
       			ps.setString(6,t9.getText());
       			ps.executeUpdate();
       			JOptionPane.showMessageDialog(null, "Đã sửa");
       			BookDAO.bookList();
       		}catch(Exception e) {
       			e.printStackTrace();
       		}
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteBook() {
		if(table.getSelectedRowCount()==1) {
			model.removeRow(table.getSelectedRow());
			try(Connection conn=Connect.getCon()){
				PreparedStatement ps=null;
				String sql1="delete from booktbl where IDSach=?" ;
				ps=conn.prepareStatement(sql1);
				ps.setString(1, t3.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Đã xóa thành công");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			if(table.getSelectedRow()==0) {
				JOptionPane.showMessageDialog(null,"Nội dung bảng đang rỗng");
			}
			else {
				JOptionPane.showMessageDialog(null,"Vui lòng chọn một hàng cần xóa");
			}
		
	  }	
	}
	public static ArrayList<BookDTO> bookfindName(String name){
		name=txtlook.getText();
		ArrayList<BookDTO> findname=new ArrayList<>();
		if(name!="") {
			try(Connection conn=Connect.getCon()){
				String sql="select * from booktbl where tenSach like '%"+name+"%'";
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				BookDTO book1=null;
				while(rs.next()) {
					book1=new BookDTO(rs.getInt("IDSach"),rs.getString("tenSach"),rs.getString("tacGia"),rs.getString("theLoai"),rs.getString("NXB"),rs.getInt("giaTien"),rs.getInt("soLuong"));
					findname.add(book1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Sai giá trị");
		}
		return findname;
}
	public static ArrayList<BookDTO> bookfindID(){
		String id=txtlook.getText();
		ArrayList<BookDTO> findid=new ArrayList<>();
		if( isNum(id)) {
			try(Connection conn=Connect.getCon()){
				String sql1="select * from booktbl where IDSach like '%"+id+"%'";
				st=conn.createStatement();
				rs=st.executeQuery(sql1);
				BookDTO book2=null;
				while(rs.next()) {
					book2=new BookDTO(rs.getInt("IDSach"),rs.getString("tenSach"),rs.getString("tacGia"),rs.getString("theLoai"),rs.getString("NXB"),rs.getInt("giaTien"),rs.getInt("soLuong"));
					findid.add(book2);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Sai giá trị");
		}
		return findid;
	}
	public static boolean isNum(String num) {
        return num != null && num.matches("[-+]?\\d*\\.?\\d+");
    }
	public static void exportBook() {
		//chon vi tri can export file
		JFileChooser exchs=new JFileChooser("C:\\Users\\user1\\Desktop");
		exchs.setDialogTitle("Save as");
		//chi cho phep save cac file co dinh dang sau
		FileNameExtensionFilter dinhdang=new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
		exchs.setFileFilter(dinhdang);
		int choose= exchs.showSaveDialog(null);
		
		//kiem tra khi nut save duoc chon
		if(choose==JFileChooser.APPROVE_OPTION) {
			FileOutputStream excelFou=null;
			BufferedOutputStream excelBou=null;
			XSSFWorkbook exeport=null;
			//them thu vien excel vao java
			try{
				 exeport= new XSSFWorkbook();
				XSSFSheet exsheet=exeport.createSheet("JTable Sheet");
				
				for(int i=0;i<model.getRowCount();i++) {
					XSSFRow exrow=exsheet.createRow(i);
					for(int j=0;j<model.getColumnCount();j++) {
						XSSFCell excell=exrow.createCell(j);
					    
						excell.setCellValue(model.getValueAt(i, j).toString());
					}
				}
				excelFou=new FileOutputStream(exchs.getSelectedFile()+".xlsx");
				excelBou=new BufferedOutputStream(excelFou);
				exeport.write(excelBou);
				JOptionPane.showMessageDialog(null, "Xuất file thành công");
			}catch(FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					if(excelBou!=null) {
						excelBou.close();
					}
					if(excelFou!=null) {
						excelFou.close();
					}
					
					if(exeport!=null) {
						exeport.close();
					}
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}
	}
	public static void importBook() {
		File exfile;
		FileInputStream excelFiu=null;
		BufferedInputStream excelBiu=null;
		XSSFWorkbook eximport=null;
		
		JFileChooser exchs=new JFileChooser("C:\\Users\\user1\\Desktop");
		
		//chi chon file excel
		FileNameExtensionFilter dinhdang=new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
		exchs.setFileFilter(dinhdang);
		
		exchs.setDialogTitle("Select Excel File");
		int choose=exchs.showOpenDialog(null);
		
		//neu chon button
		if(choose==JFileChooser.APPROVE_OPTION) {
			try {
				exfile=exchs.getSelectedFile();
				excelFiu=new FileInputStream(exfile);
				excelBiu=new BufferedInputStream(excelFiu);
				
				eximport=new XSSFWorkbook(excelBiu);
				XSSFSheet exsheet=eximport.getSheetAt(0);
				model = new DefaultTableModel();
				Object[] col={"ID","Tên Sách","Tác Giả","Thể Loại","Nhà Xuất Bản","Giá Tiền","Số Lượng"};
				model.setColumnIdentifiers(col);
				for(int i=0;i<exsheet.getLastRowNum();i++) {
					XSSFRow exrow=exsheet.getRow(i);
						
						XSSFCell exid=exrow.getCell(0);
						XSSFCell extensach=exrow.getCell(1);
						XSSFCell extacgia=exrow.getCell(2);
						XSSFCell extheloai=exrow.getCell(3);
						XSSFCell exnxb=exrow.getCell(4);
						XSSFCell exgiaban=exrow.getCell(5);
						XSSFCell exsl=exrow.getCell(6);
						
						model.addRow(new Object[] {exid,extensach,extacgia,extheloai,exnxb,exgiaban,exsl});
						 
				}
				table.setModel(model);
				
				JOptionPane.showMessageDialog(null, "Nhập file thành công");
			}
			catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(excelFiu!=null) {
						excelFiu.close();
					}
					if(excelBiu!=null) {
						excelBiu.close();
					}
					if(eximport!=null) {
						eximport.close();
					}
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}
	
	}
}
