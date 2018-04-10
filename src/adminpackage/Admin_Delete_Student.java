package adminpackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminpackage.Admin_Delete;

/**
 * Servlet implementation class Admin_Delete_Student
 */
@WebServlet("/Admin_Delete_Student")
public class Admin_Delete_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Delete_Student() {
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
		String firstName = request.getParameter("legalFirstName");
		String lastName = request.getParameter("legalLastName");
		String uniqueCode = request.getParameter("risaCode");
		Admin_Delete del = new Admin_Delete();
			
		try {
			exists = del.AccountExistsStudent(firstName, lastName, uniqueCode);
			if(exists == null) {
				request.setAttribute("successDeleteStudent", "");
				request.setAttribute("errorDeleteStudent", "Error. Could not find student. Try again.");
				request.getRequestDispatcher("/admin_Delete_Student.jsp").forward(request, response);
				
			}else{
			
				del.DeleteStudent(firstName, lastName, uniqueCode);
				
				if(del.isSuccess()) {
					request.setAttribute("errorDeleteStudent", "");
					request.setAttribute("successDeleteStudent", "Successfully Deleted.");
					RequestDispatcher rd = request.getRequestDispatcher("/admin_Delete_Student.jsp");
			        rd.forward(request, response);
				}
				else {
					request.setAttribute("successDeleteStudent", "");
					request.setAttribute("errorDeleteStudent", "Error Occured. Please try again later.");
					request.getRequestDispatcher("/admin_Delete_Student.jsp").forward(request, response);
				}
			}
		}catch (SQLException e){
			// TODO: handle exception
		}

	}

}
