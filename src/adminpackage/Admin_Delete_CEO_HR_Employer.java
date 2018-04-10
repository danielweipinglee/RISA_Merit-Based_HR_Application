package adminpackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin_Delete_CEO_HR_Employer
 */
@WebServlet("/Admin_Delete_CEO_HR_Employer")
public class Admin_Delete_CEO_HR_Employer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Delete_CEO_HR_Employer() {
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
		int statusId = -1;
		String firstName = request.getParameter("legalFirstName");
		String lastName = request.getParameter("legalLastName");
		String uniqueCode = request.getParameter("risaCode");
		String status = request.getParameter("status");
		Admin_Delete del = new Admin_Delete();
			
		try {
			exists = del.AccountExistsAdmin(firstName, lastName, uniqueCode);
			if(exists == null) {
				request.setAttribute("successDeleteAdmin", "");
				request.setAttribute("errorDeleteAdmin", "Error. Could not find. Try again.");
				request.getRequestDispatcher("/admin_Delete_CEOandHRandEmployer.jsp").forward(request, response);
				
			}else{
				
				statusId = del.getAccountStatusID(status);
				if(statusId >= 0) {
					del.DeleteAdmin(firstName, lastName, uniqueCode, statusId);
					
					if(del.isSuccess()) {
						request.setAttribute("errorDeleteAdmin", "");
						request.setAttribute("successDeleteAdmin", "Successfully Deleted.");
						RequestDispatcher rd = request.getRequestDispatcher("/admin_Delete_CEOandHRandEmployer.jsp");
				        rd.forward(request, response);
					}else{
						request.setAttribute("successDeleteAdmin", "");
						request.setAttribute("errorDeleteAdmin", "Error Occured. Please try again.");
						request.getRequestDispatcher("/admin_Delete_CEOandHRandEmployer.jsp").forward(request, response);
					}
				}
				else {
					request.setAttribute("successDeleteAdmin", "");
					request.setAttribute("errorDeleteAdmin", "Error Occured. Please try again.");
					request.getRequestDispatcher("/admin_Delete_CEOandHRandEmployer.jsp").forward(request, response);
				}
			}
		}catch (SQLException e){

		}

	}

}
