package DAL_CuaHangSach;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DTO_CuaHangSach.CustomerDTO;
import DTO_CuaHangSach.NhanVienDTO;
import GUI_CuaHangSach.Customer;



public class CustomerDAO extends Customer {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Statement st=null;
	public CustomerDAO() {
		try(Connection conn=Connect.getCon()){
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static ArrayList<CustomerDTO> khList(){
		ArrayList<CustomerDTO> nvlist=new ArrayList<>();
		try(Connection conn=Connect.getCon()){
			st=conn.createStatement();
			String sql="select * from khachhangtbl";
			rs=st.executeQuery(sql);
			CustomerDTO nv=null;
			while(rs.next()) {
				nv=new CustomerDTO(rs.getInt("IDKH"),rs.getString("tenKH"),rs.getString("diaChi"),rs.getInt("SDT"));
				nvlist.add(nv);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nvlist;
	}
	public static void addkh() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checksl(t3, sb);
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			CheckValid.checksl(t7, sb);
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai giá trị",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String[] data= {t3.getText(),t4.getText(),t5.getText(),t7.getText()};
			model.addRow(data);
			try(Connection conn=Connect.getCon()){
				String sql="insert into khachhangtbl(IDKH,tenKH,diachi,SDT) values(?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, t3.getText());
				ps.setString(2, t4.getText());
				ps.setString(3, t5.getText());
				ps.setString(4, t7.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Đã thêm thành công");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
	}
	public static void editkh() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checksl(t3, sb);
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			CheckValid.checksl(t7, sb);
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai giá trị",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(table.getSelectedColumnCount()==1) {
				int id=Integer.valueOf(t3.getText());
				String tennv=t4.getText();
				String diachi=t5.getText();
				String sdt=t7.getText();
				
				model.setValueAt(id	, table.getSelectedRow(), 0);
				model.setValueAt(tennv, table.getSelectedRow(),1);
				model.setValueAt(diachi, table.getSelectedRow(),2);
				model.setValueAt(sdt, table.getSelectedRow(),3);
				
				try(Connection conn=Connect.getCon()){
					String sql="update khachhangtbl set tenKH=?,diaChi=?,SDT=? where IDKH=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, t4.getText());
					ps.setString(2, t5.getText());
					ps.setString(3, t7.getText());
					ps.setString(4, t3.getText());
					ps.executeUpdate();
					NhanVienDAO.nvList();
					JOptionPane.showMessageDialog(null, "Đã sửa");
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin cần sửa");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	public static void deletekh() {
		if(table.getSelectedColumnCount()==1) {
			model.removeRow(table.getSelectedRow());
			try(Connection con=Connect.getCon()){
				String sql="delete from khachhangtbl where IDKH=?";
				ps=con.prepareStatement(sql);
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
	public static ArrayList<CustomerDTO> findid(){
		ArrayList<CustomerDTO> findid=new ArrayList<>();
		String id=txtlook.getText();
		if(isNum(id)) {
			try(Connection con=Connect.getCon()){
				String sql="select * from khachhangtbl where IDKH like'%"+id+"%'";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				CustomerDTO kh=null;
				while(rs.next()) {
					kh=new CustomerDTO(rs.getInt("IDKH"),rs.getString("tenKH"),rs.getString("diaChi"),rs.getInt("SDT"));
					findid.add(kh);
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
	public static ArrayList<CustomerDTO> findtenkh(String name){
		ArrayList<CustomerDTO> findten=new ArrayList<>();
		name=txtlook.getText();
		if(name!="") {
			try(Connection con=Connect.getCon()){
				String sql="select * from khachhangtbl where tenKH like'%"+name+"%'";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				CustomerDTO kh=null;
				while(rs.next()) {
					kh=new CustomerDTO(rs.getInt("IDKH"),rs.getString("tenKH"),rs.getString("diaChi"),rs.getInt("SDT"));
					findten.add(kh);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Sai giá trị");
		}
		return findten;
	}
	public static boolean isNum(String num) {
		return num!=null && num.matches("[-+]?\\d*\\.?\\d+");
	}
	public static void exportCus() {
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
	public static void importCus() {
		File exfile;
		FileInputStream excelFiu=null;
		BufferedInputStream excelBiu=null;
		XSSFWorkbook eximport=null;
		
		JFileChooser exchs=new JFileChooser("C:\\Users\\user1\\Desktop");
		
		//chi chon file excl
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
				for(int i=0;i<exsheet.getLastRowNum();i++) {
					XSSFRow exrow=exsheet.getRow(i);
						
						XSSFCell exid=exrow.getCell(0);
						XSSFCell extenkh=exrow.getCell(1);
						XSSFCell exdiachi=exrow.getCell(2);
						XSSFCell exsdt=exrow.getCell(3);
						
						 model.addRow(new Object[] {exid,extenkh,exdiachi,exsdt});
						 
				}
				
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
