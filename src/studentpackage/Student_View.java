package studentpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseDao.DBConnection;

/**
 * Servlet implementation class Student_Edit
 */
@WebServlet("/Student_View")
public class Student_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
        try {
        	connection = DBConnection.getconnectionToDatabase();
        	Statement stmt = connection.createStatement();
        	ResultSet rs = stmt.executeQuery("select * from student");
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\"><th>RISA Code</th><th>First Name</th><th>Last Name</th><th>Field Of Interest</th></tr></thead>");
            HttpSession session = request.getSession(false);
            String userid = (String) session.getAttribute("UserName");
            
            while (rs.next()) {
            	if(userid.equals(rs.getString(3))) {
            		out.println("<tr><td>" + rs.getInt(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(10) + "</td></tr>");} 
            	}
            out.println("</table>");
        	connection.close();
           }
            catch (Exception e) {
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
