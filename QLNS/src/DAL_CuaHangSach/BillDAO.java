package DAL_CuaHangSach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import GUI_CuaHangSach.Bills;

public class BillDAO extends Bills  {
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static Statement st=null;
	public BillDAO() {
		try(Connection con=Connect.getCon()){
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateBill() {
		try {
			
			int slmoi=soluong-Integer.valueOf(t6.getText());
			int ids=Integer.valueOf(model.getValueAt(table.getSelectedRow()	, 0).toString());
			model.setValueAt(slmoi, table.getSelectedRow(), 6);
			try(Connection con=Connect.getCon()){
				String sql="update booktbl set soLuong='"+slmoi+"' where IDSach='"+ids+"'";
				st=con.createStatement();
				st.executeUpdate(sql);
				BookDAO.bookList();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void returnBill() {
		int slmoi=soluong;
		int ids=Integer.valueOf(model.getValueAt(table.getSelectedRow()	, 0).toString());
		model.setValueAt(slmoi, table.getSelectedRow(), 6);
		try(Connection con=Connect.getCon()){
			String sql="update booktbl set soLuong='"+slmoi+"' where IDSach='"+ids+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
			BookDAO.bookList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*public static void getNV() {
		String nv=t2.getText();
		try(Connection )
	}*/
}
