package com.ash.user.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/addServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
   public void init() {
	   try {
		   System.out.println("init()");
		    Class.forName("com.mysql.jdbc.Driver");
		   //Class.forName("com.mysql.cj.jdbc.Driver");

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Lulu@341");
	   } catch (SQLException e) {
		
		e.printStackTrace();
	   } 
	catch (ClassNotFoundException e) {

	e.printStackTrace();
}
	   
   }
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String firstName = request.getParameter("firstName");
		    String lastName = request.getParameter("lastName");
		    String email = request.getParameter("email");
		    String password = request.getParameter("password");
		    
		    
		    try {
				Statement statement = connection.createStatement();
				int result = statement.executeUpdate("insert into user values('"+firstName+"','"+lastName+"','"+email+"','"+password+"')");
				
				PrintWriter out = response.getWriter();
				if(result>0) {
				out.print("<H1> USER CREATED </H1>");
				}
				else {
					out.print("<H1> ERROR CREATING USER </H1>");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    
	}
	
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
