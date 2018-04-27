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

import Regex.ValidationRegex;
import Register.RegisterEmployer;

/**
 * Servlet implementation class RegisterEmployerServlet
 */
@WebServlet("/RegisterEmployerServlet")
public class RegisterEmployerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEmployerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exists = null;
		
		try {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String answer = request.getParameter("answer");
		String risacode = request.getParameter("risacode");
		String risaposition = request.getParameter("risaposition");
		String squestion = request.getParameter("securityquestion");
		String organization = request.getParameter("organization");
		
		ValidationRegex check = new ValidationRegex();
		RegisterEmployer registerEmployer = new RegisterEmployer();
		exists = registerEmployer.AccountExists(fname,lname,risacode);
		int squestionID = registerEmployer.getQuestionID(squestion);
	    
	    if(exists == null) {
			request.setAttribute("EmployerError", "Risa code with first and last name not found.");
			request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);	
	    }
		
	    else if(check.isValidEmployerEmail(email) == false) {
			request.setAttribute("EmployerError", "Please enter a valid email address.");
			request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);
        }
		else if(check.isValidPassword(password) == false) {
			request.setAttribute("EmployerError", "Please enter a valid password. Password should have at lease one upper and lowercase character. Password should also contain a number and be at least 10 characters long.");
			request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);
		}
		else if(!password.equals(password2)) {
			request.setAttribute("EmployerError", "Passwords entered are different from one another.");
			request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);
		}
		else if(squestion.equals("Default")) {
			request.setAttribute("EmployerError", "No security question selected.");
			request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);					
		}

		else {
			int accountstatusid = registerEmployer.getAccountStatusID(role,risacode);

			registerEmployer.insertThenUpdate(fname,lname,email,password,answer,
					risacode,risaposition,squestion,squestionID,accountstatusid,role,organization);
			
			if(registerEmployer.isSuccess()) {
				request.setAttribute("success", "Successfully Registered. Username is email.");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		        rd.forward(request, response);
			}
			else {
				request.setAttribute("EmployerError", "Error Occured. Please try again later.");
				request.getRequestDispatcher("/RegisterEmployer.jsp").forward(request, response);
			}
		}

		}catch (SQLException e){
		} 		
}	

}
