package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Regex.ValidationRegex;
import Register.ForgotPasswordClass;

/**
 * Servlet implementation class updatePassword
 */
@WebServlet("/updatePassword")
public class updatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePassword() {
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
		String psw = null;
		String psw2 = null;
		String risacode = null;
		String AdminorStudent = null;
		try {
			psw = request.getParameter("psw");
			psw2 = request.getParameter("psw2");
			HttpSession session=request.getSession(); 
			risacode=(String)session.getAttribute("rcode");
			ValidationRegex check = new ValidationRegex();
			ForgotPasswordClass updatePassword = new ForgotPasswordClass();
			if(psw.equals(psw2) == false) {
				request.setAttribute("errorPsw", "Passwords do not match.");
				request.getRequestDispatcher("/updatepassword.jsp").forward(request, response);	
			}
			else if(check.isValidPassword(psw) == false) {
				request.setAttribute("errorPsw", "Please enter a valid password. Password should have at lease one upper and lowercase character. Password should also contain a number and be at least 10 characters long.");
				request.getRequestDispatcher("/updatepassword.jsp").forward(request, response);	
			}
			else {
				AdminorStudent = updatePassword.isAdminorStudent(risacode);
				updatePassword.newPassword(risacode,psw,AdminorStudent);
				
				if(updatePassword.isSuccess()) {
					request.setAttribute("successPassword", "Successfully set new password.");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			        rd.forward(request, response);
				}
				else {
					request.setAttribute("errorPsw", "Error Occured. Please try again.");
					request.getRequestDispatcher("/updatepassword.jsp").forward(request, response);
				}

			}

		}catch(Exception e) {
		}
	}
}
