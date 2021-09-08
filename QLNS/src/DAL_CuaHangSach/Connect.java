package DAL_CuaHangSach;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private static Connection conn;
	public static Connection getCon() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QLNS;"
					+ "username=sa;password=sa2210");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
