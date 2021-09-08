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

import DTO_CuaHangSach.NhanVienDTO;
import DTO_CuaHangSach.UserDTO;
import GUI_CuaHangSach.Users;

public class UserDAO extends Users {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Statement st=null;
	 
	public static UserDTO users(String tenDangNhap,String password,Object quyen) {
		UserDTO users=null;
			try(Connection connect=Connect.getCon()){
			st=connect.createStatement();
			String sql="select * from usertbl where tenDangNhap='"+tenDangNhap+"' and password='"+password+"' and quyen='"+quyen+"'";
		    rs=st.executeQuery(sql);
			if(rs.next()) {
				users=new UserDTO();
				users.setTenDangNhap(rs.getString("tenDangNhap"));
				users.setPassword(rs.getString("password"));
			}
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
		return users;
	}

	public static ArrayList<UserDTO> userList(){
		ArrayList<UserDTO> userList=new ArrayList<>();
		try(Connection connect=Connect.getCon()){
			st=connect.createStatement();
			String sql2="select * from usertbl";
			ResultSet rs=st.executeQuery(sql2);
			UserDTO user=null;
			while(rs.next()) {
				user=new UserDTO(rs.getInt("maNV"),rs.getString("tenDangNhap"),rs.getString("password"),rs.getString("quyen"));
				userList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public static void addUser() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checksl(t3, sb);
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai giá trị",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String[] data= {t3.getText(),t4.getText(),t5.getText(),(String)box.getSelectedItem()};
			model.addRow(data);
		
		try(Connection conn=Connect.getCon()){
			String sql3="insert into usertbl(maNV,tenDangNhap,password,quyen)"
					+ "values(?,?,?,?)";
			ps=conn.prepareStatement(sql3);
			ps.setString(1, t3.getText());
			ps.setString(2, t4.getText());
			ps.setString(3, t5.getText());
			ps.setObject(4, box.getSelectedItem());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm người dùng thành công");
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
			
	}
	public static void deleteUser() {
		if(table.getSelectedRowCount()==1) {
			model.removeRow(table.getSelectedRow());
			try(Connection conn=Connect.getCon()){
				String sql="delete from usertbl where maNV=?";
		        ps=conn.prepareStatement(sql);
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
	public static void editUser() {
		try {
			StringBuilder sb=new StringBuilder();
			CheckValid.checksl(t3, sb);
			CheckValid.checkEmpty(t4, sb, "Thông tin không để trống");
			CheckValid.checkEmpty(t5, sb, "Thông tin không để trống");
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(null, sb.toString(),"Sai giá trị",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(table.getSelectedRowCount()==1) {
			int manv=Integer.valueOf(t3.getText());
			String usrname=t4.getText();
			String pass=t5.getText();
			Object quyen=box.getSelectedItem();
			
			model.setValueAt(manv, table.getSelectedRow(), 0);
			model.setValueAt(usrname, table.getSelectedRow(), 1);
			model.setValueAt(pass, table.getSelectedRow(), 2);
			model.setValueAt(quyen, table.getSelectedRow(), 3);
			
			try(Connection conn=Connect.getCon()){
				String sql="update usertbl set tenDangNhap=?,password=?,quyen=? where maNV=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, t4.getText());
				ps.setString(2, t5.getText());
				ps.setObject(3, box.getSelectedItem());
				ps.setString(4, t3.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Đã sửa");
				UserDAO.userList();
			}catch(Exception e) {
				e.printStackTrace();
			}
		  }
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	public static ArrayList<UserDTO> findID() {
		String id=txtlook.getText();
		ArrayList<UserDTO> findid=new ArrayList<>();
		if(isNum(id)) {
			try(Connection conn=Connect.getCon()){
				String sql="select * from usertbl where maNV like'%"+id+"%'";
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				UserDTO user=null;
				while(rs.next()) {
					user=new UserDTO(rs.getInt("maNV"),rs.getString("tenDangNhap"),rs.getString("password"),rs.getString("quyen"));
					findid.add(user);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Sai giá trị");
		}
		return findid;
	}
	public static ArrayList<UserDTO> findUsrname(String usrname){
		usrname=txtlook.getText();
		ArrayList<UserDTO> finduser=new ArrayList<>();
		if(usrname!="") {
			try(Connection con=Connect.getCon()){
				String sql="select * from usertbl where tenDangNhap like'%"+usrname+"%'";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				UserDTO user=null;
				while(rs.next()) {
					user=new UserDTO(rs.getInt("maNV"),rs.getString("tenDangNhap"),rs.getString("password"),rs.getString("quyen"));
					finduser.add(user);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Sai giá trị");
		}
		return finduser;
	}
	public static boolean isNum(String num) {
		return num!=null && num.matches("[-+]?\\d*\\.?\\d+");
	}
	public static void exportUser() {
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
	public static void importUser() {
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
						XSSFCell exusrname=exrow.getCell(1);
						XSSFCell expass=exrow.getCell(2);
						XSSFCell exquyen=exrow.getCell(3);
						
						 model.addRow(new Object[] {exid,exusrname,expass,exquyen});
						 
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
