package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseDao.DBLoginIn;
import Regex.ValidationRegex;
import Register.RegisterStudent;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String AccountType[] = { "Active", "Active_CEO", "Deleted", "admin", "Active_HR", "Active_Employer" };
		DBLoginIn loginIn = new DBLoginIn();

		HttpSession session = request.getSession();
		session.setAttribute("UserName", userName);

		try {
			loginIn.getInfomation(userName, password);
			if (loginIn.getFound()) {
				session.setAttribute("AccountStatus", loginIn.getmAccountType());
				session.setAttribute("AccountID", loginIn.getId());

				if (AccountType[0].equals(loginIn.getmAccountType())) {
					response.sendRedirect("Student_Main.jsp");
				} else if (AccountType[1].equals(loginIn.getmAccountType())) {
					response.sendRedirect("CEO_Main.jsp");
				} else if (AccountType[2].equals(loginIn.getmAccountType())) {
					response.sendRedirect("index_invalid.jsp");
				} else if (AccountType[3].equals(loginIn.getmAccountType())) {
					response.sendRedirect("admin_main.jsp");
				} else if (AccountType[4].equals(loginIn.getmAccountType())) {
					response.sendRedirect("HR_Main.jsp");
				} else if (AccountType[5].equals(loginIn.getmAccountType())) {
					response.sendRedirect("Employer_Main.jsp");
				}
			} else {
				response.sendRedirect("index_invalid.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exists = null;
		
			try {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String answer = request.getParameter("answer");
			String risacode = request.getParameter("risacode");
			String risaposition = request.getParameter("risaposition");
			String squestion = request.getParameter("securityquestion");
			String phone = request.getParameter("phonenumber");
			String interest = request.getParameter("interest");
			String degree = request.getParameter("degree");
			String college = request.getParameter("college");
			String concentration = request.getParameter("concentration");
			String month = request.getParameter("gradmonth");
			String year = request.getParameter("gradyear");
			String major = request.getParameter("major");
			
			if(major.equals("other")) {
				major = request.getParameter("majorother");
				if(major == null || major == "") {
					request.setAttribute("errorMsg", "Please enter major.");
					request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);	
			    }
			}
			
			
			ValidationRegex check = new ValidationRegex();
			RegisterStudent registerStudent = new RegisterStudent();
			exists = registerStudent.AccountExists(fname,lname,risacode);
			int squestionID = registerStudent.getQuestionID(squestion);
		    
		    if(exists == null) {
				request.setAttribute("errorMsg", "Risa code with first and last name not found.");
				request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);	
		    }
			
		    else if(check.isValidEmail(email) == false) {
				request.setAttribute("errorMsg", "Please enter a valid email address. TTU email should be used.");
				request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);
            }
			else if(check.isValidPassword(password) == false) {
				request.setAttribute("errorMsg", "Please enter a valid password. Password should have at lease one upper and lowercase character. Password should also contain a number and be at least 10 characters long.");
				request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);
			}
			else if(!password.equals(password2)) {
				request.setAttribute("errorMsg", "Passwords entered are different from one another.");
				request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);
			}
			else if(squestion.equals("Default")) {
				request.setAttribute("errorMsg", "No security question selected.");
				request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);					
			}

			else {

				registerStudent.insertThenUpdate(fname,lname,email,username,password,answer,
						risacode,risaposition,squestion,squestionID,phone,interest,college,degree,concentration,year,month,major);
				
				if(registerStudent.isSuccess()) {
					request.setAttribute("success", "Successfully Registered.");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			        rd.forward(request, response);
				}
				else {
					request.setAttribute("errorMsg", "Error Occured. Please try again later.");
					request.getRequestDispatcher("/register_invalid.jsp").forward(request, response);
				}
			}
	
			}catch (SQLException e){
			} 		
	}	
}

