package GUI_CuaHangSach;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sun.jdi.connect.spi.Connection;

public class ConectionDB {
	public static void main(String[] args) {
		java.sql.Connection conn;
		try {
			String connectionURL="jdbc:sqlserver://localhost;databaseName=testdb;user=sa;password=sa2210";
			conn=DriverManager.getConnection(connectionURL);
			if(conn!=null) {
				JOptionPane.showMessageDialog(null, "ket noi thanh cong");
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, ""+ex);
		}
	}
}
