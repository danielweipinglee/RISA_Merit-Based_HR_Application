package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseDao.CreateDefaults;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String firstName = "fisrtName";
		String lastName = "lastName";
		int uniqueCode = 11111111;
		
		CreateDefaults createAccount = new CreateDefaults();
		
		try {
			createAccount.getInfomation(firstName, lastName, uniqueCode);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("fisrtName");
		String lastName = request.getParameter("lastName");
		int uniqueCode = Integer.parseInt(request.getParameter("RisaCode"));
		
		CreateDefaults createAccount = new CreateDefaults();
		
		try {
			createAccount.getInfomation(firstName, lastName, uniqueCode);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
