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

//TODO Delete all printIn method calls

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

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String AccountType[] = {"Active","Active CEO","Deleted","Admin"};
		DatabaseDao dao = new DatabaseDao();
		dao.getInfomation();
		boolean found = false;
		
		for( DataSet data : dao.getData()) {
			if(userName.equals(data.getUserName()) && password.equals(data.getPassword()) ) {
				if(AccountType[0].equals(data.getStatus())) {
					System.out.println("done" + found);
					found = true;
					response.sendRedirect("mainpage.jsp");	
				}
				else if(AccountType[1].equals(data.getStatus())) {
					//TODO Go to Active CEO page.
					
				}
				else if(AccountType[2].equals(data.getStatus()))
					response.sendRedirect("index_invalid.jsp");
			
				} else {
					//TODO Go to Admin Page.
				}
			//Delete
			System.out.println(data.getId() + " " + data.getPassword() +" " + data.getUserName() + " " + data.getStatus() );
		}
		
		if(!found) {
			response.sendRedirect("index_invalid.jsp");
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
			ResultSet myRs = myStmt.executeQuery("update risa_hr.userPassword" + var + 
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
