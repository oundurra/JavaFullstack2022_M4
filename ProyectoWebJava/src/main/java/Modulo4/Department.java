package Modulo4;

import java.sql.*;


public class Department {
	
	public static boolean insert(Connection conn, String dept_no, String dept_name) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("INSERT INTO departments VALUES (?,?)" );
			pst.setString(1, dept_no);
			pst.setString(2, dept_name);
			pst.execute();
			vret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}	
	
	public static String select(Connection conn, String dept_no) {
		PreparedStatement pst;
		String vret = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM departments WHERE dept_no=?" );
			pst.setString(1, dept_no);
			ResultSet rs = pst.executeQuery();
			rs.next();
			vret = rs.getString("dept_no") + ";" + rs.getString("dept_name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}
}
