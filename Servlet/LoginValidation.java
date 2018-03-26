package Servlet;

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

import DatabaseDao.DBConnection;
import DatabaseDao.DBLoginIn;
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

		String userName = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String AccountType[] = {"Active","Active_CEO","Deleted","Admin","Active_Hr", "Active_Employee"};
		DBLoginIn loginIn = new DBLoginIn();
		
		try {
			loginIn.getInfomation(userName, password);
			if(loginIn.getFound()) {
				if(AccountType[0].equals(loginIn.getmAccountType())) {
					response.sendRedirect("mainpage.jsp");	
				}
				else if(AccountType[1].equals(loginIn.getmAccountType()) || AccountType[4].equals(loginIn.getmAccountType()) ) {
					//TODO Go to Active CEO page.
					System.out.println("Active_CEO or hr");
				}

				else if(AccountType[5].equals(loginIn.getmAccountType())) {
					response.sendRedirect("Employee");
				} else {
					//TODO Go to Admin Page.
					System.out.println("Admin");
				}
			}
			else {
			response.sendRedirect("index_invalid.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	protected int getQuestionID(String squestion) {//to get the security question id from the selected value
		int id = 0;
		try {
			String sql = "select ID from securityquestion where SecurityQuestion = ?;";
			Connection conn = DBConnection.getconnectionToDatabase();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, squestion);
			
		    ResultSet resultset = statement.executeQuery();
		    while ( resultset.next() )
		    {
		      id =  resultset.getInt("ID") ;
		    }
		    resultset.close();
		    statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return id;
	}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
				try {
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String email = request.getParameter("email");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String password2 = request.getParameter("password2");
				String risacode = request.getParameter("risacode");
				String answer = request.getParameter("answer");
				String squestion = request.getParameter("securityquestion");
				int squestionID = getQuestionID(squestion); //need id from selected question

				ValidationRegex check = new ValidationRegex();
				if (check.isValidEmail(email) && check.isValidPassword(password) && password.equals(password2)) {

					 String sql = "update risa_hr.student, risa_hr.userpassword " + 
							"set student.Email = ?, " +
							"student.Username = ?, " + 
							"userpassword.UserPassword = ?, " +
							"userpassword.SecurityAnswer = ?, "  +
							"userpassword.SecurityQuestion_ID = ? " + 
							"where student.RISACode = ? " + 
							"and student.LegalFirstName = ? " + 
							"and student.LegalLastName = ? " +
							"and userpassword.ID = student.ID;";

					Connection conn = DBConnection.getconnectionToDatabase();
					PreparedStatement statement = conn.prepareStatement(sql);
					
					statement.setString(1, email);
					statement.setString(2, username);
					statement.setString(3, password);
					statement.setString(4, answer);
					statement.setInt(5, squestionID);
					statement.setString(6, risacode);
					statement.setString(7, fname);
					statement.setString(8, lname);
					
					
					statement.executeUpdate();
					statement.close();
					
					 
					response.sendRedirect("index.jsp");	
				}
				else {
					response.sendRedirect("register_invalid.jsp");	
				}
				
				}catch (Exception e){
					e.printStackTrace();
				} 
				
		}	
	}