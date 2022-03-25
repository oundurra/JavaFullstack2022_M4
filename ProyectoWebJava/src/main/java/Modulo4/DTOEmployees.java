package Modulo4;

import java.sql.*;


public class DTOEmployees {
	public static boolean insert(Connection conn, Employees employees) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("INSERT INTO employees VALUES (?,?,?,?,?,?)" );
			pst.setInt(1, employees.getEmp_no());
			pst.setDate(2, new java.sql.Date(employees.getBirth_date().getTime()));
			pst.setString(3, employees.getFirst_name());
			pst.setString(4, employees.getLast_name());
			pst.setString(5, employees.getGender());
			pst.setDate(6, new java.sql.Date(employees.getHire_date().getTime()));			
			pst.execute();
			vret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}	
	
	public static Employees select(Connection conn, int emp_no) {
		PreparedStatement pst;
		Employees employees = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM employees WHERE emp_no=?" );
			pst.setInt(1, emp_no);
			ResultSet rs = pst.executeQuery();
			rs.next();
			employees = new Employees(
							rs.getInt("emp_no")
							, rs.getDate("birth_date")
							, rs.getString("first_name")
							, rs.getString("last_name")		
							, rs.getString("gender")							
							, rs.getDate("hire_date")
							);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return employees;
	}
	
	public static boolean update(Connection conn, Employees employees) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("UPDATE employees SET birth_date=?, first_name=?, last_name=?, gender=?, hire_date=?  WHERE emp_no=?" );
			pst.setDate(1, new java.sql.Date(employees.getBirth_date().getTime()));
			pst.setString(2, employees.getFirst_name());
			pst.setString(3, employees.getLast_name());
			pst.setString(4, employees.getGender());
			pst.setDate(5, new java.sql.Date(employees.getHire_date().getTime()));
			pst.setInt(6, employees.getEmp_no());
			if (pst.executeUpdate() > 0) {
				vret = true; 
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}
	
	public static boolean delete(Connection conn, int emp_no) {
		PreparedStatement pst;
		boolean vret = false;
		try {
			pst = conn.prepareStatement("DELETE FROM employees WHERE emp_no=?" );
			pst.setInt(1, emp_no);
			pst.execute();
			vret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return vret;
	}	
}
