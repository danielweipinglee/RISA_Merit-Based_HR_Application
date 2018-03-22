package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DataSet;
import DatabaseDao.DBConnection;
import DatabaseDao.DatabaseDao;
import Regex.ValidationRegex;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ValidationRegex validationRegex = new ValidationRegex();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(userName  + " " + password);
		DatabaseDao dao = new DatabaseDao();
		dao.getInfomation();
		for( DataSet data : dao.getData()) {
			if(userName.equals(data.getUserName()) && password.equals(data.getPassword())) {
				response.sendRedirect("mainpage.jsp");
				System.out.println("done");
			}
			else {
			System.out.println(data.getId() + " " + data.getPassword() +" " + data.getUserName() );
			System.out.println(validationRegex.isValidPassword(data.getPassword()));
			response.getWriter().append("Served at: ").append(request.getContextPath());
			}
		}		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id, password, db_id, db_password;
		try {

			Connection conn = DBConnection.getconnectionToDatabase();
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("update risa_hr.userPassword" + 
					"Set ?" + 
					"where ID = 1");
			
			id = request.getParameter("username");
			password = request.getParameter("password");
			
			while(myRs.next()) {
				db_id = myRs.getString("SecurityAnswer");
				db_password = myRs.getString("UserPassword");
				
				if(id.equals(db_id) && password.equals(db_password)){
					response.sendRedirect("mainpage.jsp");
					return;
				}
			}
			response.sendRedirect("index.jsp");
		}catch (Exception e){
			e.printStackTrace();
		}		
		
		
	}

	
}
