package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseDao.CreateDefaults;
import Regex.ValidationRegex;
import studentpackage.StudentUpdate;

/**
 * Servlet implementation class StudentUpdateServlet
 */
@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdateServlet() {
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

		String phoneNumber = request.getParameter("phone");
		ValidationRegex validationRegex = new ValidationRegex();
			
		try {
			HttpSession session = request.getSession(false);
			String AccountID = (String) session.getAttribute("AccountID");
			
			if(validationRegex.isValidphoneNumber(phoneNumber)) { 
				StudentUpdate studentUpdate = new StudentUpdate();
				studentUpdate.updateStudent(phoneNumber, AccountID);

				request.setAttribute("success", "Successfully Updated.");
				RequestDispatcher rd = request.getRequestDispatcher("/Student_Main.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("errorUpdate", "Input was a invalid phone number.");
				request.getRequestDispatcher("/Student_Update.jsp").forward(request, response);
			}
			
			
			
		} catch (Exception e) {
			
		} finally {
			
		}
	}

}
