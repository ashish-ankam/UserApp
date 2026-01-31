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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/readServlet")
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
   public void init() {
	   try {
		   System.out.println("init()");
		   Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Lulu@341");
	   } catch (SQLException e) {
		
		e.printStackTrace();
	   } catch (ClassNotFoundException e) {
	
		e.printStackTrace();
	}
	   
   }
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 System.out.println("inside do get()");
		    
		    
		    try {
				Statement statement = connection.createStatement();
		
				ResultSet resultset = statement.executeQuery("select * from user");
				PrintWriter out = response.getWriter();
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>");
				out.println("First Name");
				out.println("</th>");
				out.println("<th>");
				out.println("Last Name");
				out.println("</th>");
				out.println("<th>");
				out.println("Email");
				out.println("</th>");
				out.println("</tr>");
				out.println("</table>");
				
				while(resultset.next()) {
					out.println("<html>");
					out.println("<body>");
					out.println("<tr>");
					
					out.println("<td>");
					out.println(resultset.getString(1));
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<td>");
					out.println(resultset.getString(2));
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<td>");
					out.println(resultset.getString(3));
					out.println("</td>");
					out.println("</tr>");
					out.println("</body>");
					out.println("</html>");
					
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
