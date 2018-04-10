package Servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseDao.DBConnection;
import Register.RegisterStudent;
import Register.ForgotPasswordClass;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {//check if risa code exists
			String code = null;
			String risacode = request.getParameter("risacode");
			ForgotPasswordClass forgotPassword = new ForgotPasswordClass();
			code = forgotPassword.doesRisaCodeExist(risacode);
			
		    if(code == null) {//not found
		    	 request.setAttribute("errorMessage", "Code not found. Please try again.");
		    	 request.getRequestDispatcher("/forgotpassword1.jsp").forward(request, response);
		    }
		    else {//found
		    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/forgotpassword2.jsp?p = " + code);
		    	rd.forward(request, response);
		    }
		}catch (Exception e){
			
				e.printStackTrace();
		} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("forgotpassword2.jsp");
	}

}
