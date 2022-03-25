package Modulo4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EjemploServlet
 */
@WebServlet("/EjemploServlet")
public class EjemploServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EjemploServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        Connection conn;
        String vret = null;


		try {
			// Inicializa Conexion
			conn = DBConnection.initializeDatabase();
			
			// Implementa el Crud
			switch(request.getParameter("table")) {
			case "departments":
		        Departments departments;				
				switch(request.getParameter("operation")) {
					case "select":
						departments = DTODepartments.select(conn,request.getParameter("dept_no"));
						vret = departments.getDept_no() + ";" + departments.getDept_name();
						break;
					case "insert":
						if(DTODepartments.insert(conn,new Departments(request.getParameter("dept_no"),request.getParameter("dept_name")))) {
							vret = "Exito";
						};
						break;
					case "update":
						if(DTODepartments.update(conn,new Departments(request.getParameter("dept_no"),request.getParameter("dept_name")))) {
							vret = "Exito";
						};
						break;
					case "delete":
						if(DTODepartments.delete(conn,request.getParameter("dept_no"))) {
							vret = "Exito";
						};
						break;					
				};
				break;
			case "employees":
		        Employees employees;
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				switch(request.getParameter("operation")) {
				case "select":
					employees = DTOEmployees.select(conn,Integer.parseInt(request.getParameter("emp_no")));
					vret = employees.getEmp_no()
							+ ";" + dateFormat.format(employees.getBirth_date())
							+ ";" + employees.getFirst_name()
							+ ";" + employees.getLast_name()
							+ ";" + employees.getGender()
							+ ";" + dateFormat.format(employees.getHire_date());
					break;
				case "insert":
					try {
						if(DTOEmployees.insert(conn,new Employees(
														Integer.parseInt(request.getParameter("emp_no"))
														,dateFormat.parse(request.getParameter("birth_date"))
														,request.getParameter("first_name")
														,request.getParameter("last_name")
														,request.getParameter("gender")
														,dateFormat.parse(request.getParameter("hire_date"))))) {
							vret = "Exito";
						};
					} catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case "update":
					try {
						if(DTOEmployees.update(conn,new Employees(
								Integer.parseInt(request.getParameter("emp_no"))
								,dateFormat.parse(request.getParameter("birth_date"))
								,request.getParameter("first_name")
								,request.getParameter("last_name")
								,request.getParameter("gender")
								,dateFormat.parse(request.getParameter("hire_date"))))) {
							vret = "Exito";
						};
					} catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case "delete":
					if(DTOEmployees.delete(conn,Integer.parseInt(request.getParameter("emp_no")))) {
						vret = "Exito";
					};
					break;					
				};
			};
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		response.getWriter().append(vret);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
