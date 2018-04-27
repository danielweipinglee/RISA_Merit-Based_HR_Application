package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentpackage.StudentUpdate;
import studentpackage.StudentUpdateHRCEO;

/**
 * Servlet implementation class StudentUpdateHRCEOServlet
 */
@WebServlet("/StudentUpdateHRCEOServlet")
public class StudentUpdateHRCEOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdateHRCEOServlet() {
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
		String phoneNumber = request.getParameter("phone");
		String studentName = request.getParameter("name");
			
		try {
			HttpSession session = request.getSession(false);
			String AccountID = (String) session.getAttribute("AccountID");
			String AccountStatus = (String) session.getAttribute("AccountStatus");
			
			StudentUpdateHRCEO studentUpdate = new StudentUpdateHRCEO();
			studentUpdate.updateStudent(studentName, phoneNumber);


			
			if(AccountStatus.equals("Active_CEO")) {
				
				request.setAttribute("success", "Successfully Updated.");
				RequestDispatcher rd = request.getRequestDispatcher("/CEO_Main.jsp");
		        rd.forward(request, response);
			}
			
			else if(AccountStatus.equals("Active_HR")) {
				
				request.setAttribute("success", "Successfully Updated.");
				RequestDispatcher rd = request.getRequestDispatcher("/HR_Main.jsp");
		        rd.forward(request, response);
			}
			
			else if(AccountStatus.equals("admin")) {
				
				request.setAttribute("success", "Successfully Updated.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin_main.jsp");
		        rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			
		} finally {
			
		}
	}

}