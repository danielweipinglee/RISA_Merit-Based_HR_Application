package adminpackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseDao.CreateDefaults;
import DatabaseDao.CreateDefaultsManagement;

/**
 * Servlet implementation class CEO_Add_Management
 */
@WebServlet("/CEO_Add_Management")
public class CEO_Add_Management extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CEO_Add_Management() {
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

		String Status [] = {"Active_CEO", "Active_HR", "admin", "Active_Employer"}; 
		String firstName = request.getParameter("legalFirstName");
		String lastName = request.getParameter("legalLastName");
		String uniqueCode = request.getParameter("risaCode");
		String status = request.getParameter("Status");
		int statusID = -1;

	
		CreateDefaultsManagement createAccount = new CreateDefaultsManagement();
		
		HttpSession session = request.getSession(false);
		String AccountStatus = (String) session.getAttribute("AccountStatus");
		
		try {
			
			if(status.equals("Active_CEO")) {
				statusID = 0;
			}
			else if(status.equals("admin")) {
				statusID = 3;
			}
			else if(status.equals("Active_HR")) {
				statusID = 4;
			}
			else if(status.equals("Active_Employer")) {
				statusID = 5;
			}
			createAccount.InputManagement(firstName, lastName, uniqueCode, statusID);

			if(AccountStatus.equals("Active_CEO")) {
				if(createAccount.isSuccessful()) {
						request.setAttribute("success", "Successfully Created Account.");
						RequestDispatcher rd = request.getRequestDispatcher("/CEO_Main.jsp");
				        rd.forward(request, response);
				}
				else {
						request.setAttribute("errorCreate", "Error Occured. Please try again later.");
						request.getRequestDispatcher("/CEO_Add_Employer.jsp").forward(request, response);
				}
			}
			
			if(AccountStatus.equals("admin")) {
				if(createAccount.isSuccessful()) {
						request.setAttribute("success", "Successfully Created Account.");
						RequestDispatcher rd = request.getRequestDispatcher("/admin_main.jsp");
				        rd.forward(request, response);
				}
				else {
						request.setAttribute("errorCreate", "Error Occured. Please try again later.");
						request.getRequestDispatcher("/admin_Add_HR.jsp").forward(request, response);
				}
			}
			
			if(AccountStatus.equals("Active_HR")) {
				if(createAccount.isSuccessful()) {
						request.setAttribute("success", "Successfully Created Account.");
						RequestDispatcher rd = request.getRequestDispatcher("/HR_Main.jsp");
				        rd.forward(request, response);
				}
				else {
						request.setAttribute("errorCreate", "Error Occured. Please try again later.");
						request.getRequestDispatcher("/HR_Add_Administrative.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

}
}
