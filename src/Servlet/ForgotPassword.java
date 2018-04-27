package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				HttpSession session=request.getSession();
				session.setAttribute("rcode",risacode);  
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
		String risacode = null;
		String ans = null;
		boolean match = false;

		try {
			ans=request.getParameter("answer");
			HttpSession session=request.getSession(); 
			risacode=(String)session.getAttribute("rcode");

		ForgotPasswordClass forgotPassword = new ForgotPasswordClass();
		match = forgotPassword.doesAnswerMatch(risacode,ans);
		if(match == true) {//matches
			
	    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/updatepassword.jsp");
	    	rd.forward(request, response);
	    }
	    else {//wrong 
	    	 
	    	request.setAttribute("AnswerError", "Incorrect. Please try again.");	    	
	    	RequestDispatcher rd = request.getRequestDispatcher("/forgotpassword2.jsp");
	    	rd.forward(request,response);

	    }		
		}catch(Exception e) {
		}
	}

}
