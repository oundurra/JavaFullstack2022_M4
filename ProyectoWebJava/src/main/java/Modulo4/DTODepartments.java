package Modulo4;

import java.sql.*;


public class DTODepartments {
	
	public static boolean insert(Connection conn, Departments departments) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("INSERT INTO departments VALUES (?,?)" );
			pst.setString(1, departments.getDept_no());
			pst.setString(2, departments.getDept_name());
			pst.execute();
			vret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}	
	
	public static Departments select(Connection conn, String dept_no) {
		PreparedStatement pst;
		Departments departments = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM departments WHERE dept_no=?" );
			pst.setString(1, dept_no);
			ResultSet rs = pst.executeQuery();
			rs.next();
			departments = new Departments(rs.getString("dept_no"), rs.getString("dept_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return departments;
	}
	
	public static boolean update(Connection conn, Departments departments) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("UPDATE departments SET dept_name=? WHERE dept_no=?" );
			pst.setString(1, departments.getDept_name());
			pst.setString(2, departments.getDept_no());
			if (pst.executeUpdate() > 0) {
				vret = true;
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}
	
	public static boolean delete(Connection conn, String dept_no) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("DELETE FROM departments WHERE dept_no=?" );
			pst.setString(1, dept_no);
			pst.execute();
			vret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}	
}
