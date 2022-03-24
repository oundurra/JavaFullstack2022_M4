package Modulo4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Departments departments;

		try {
			// Inicializa Conexion
			conn = DBConnection.initializeDatabase();
			
			// Utiliza el select del Crud
			switch(request.getParameter("operation")) {
				case "select":
					departments = DTODepartments.select(conn,request.getParameter("dept_no"));
					vret = departments.getDept_no() + ";" + departments.getDept_name();
					break;
				case "insert":
					if(DTODepartments.insert(conn,request.getParameter("dept_no"),request.getParameter("dept_name"))) {
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
