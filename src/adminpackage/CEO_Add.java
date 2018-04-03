package adminpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseDao.CreateDefaults;

/**
 * Servlet implementation class CEO_Add
 */
@WebServlet("/CEO_Add")
public class CEO_Add extends HttpServlet {

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CEO_Add() {
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
		// TODO Auto-generated method stub
		String firstName = request.getParameter("legalFirstName");
		String lastName = request.getParameter("legalLastName");
		String uniqueCode = request.getParameter("risaCode");
		CreateDefaults createAccount = new CreateDefaults();
		
		try {
			createAccount.getInfomation(firstName, lastName, uniqueCode);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
