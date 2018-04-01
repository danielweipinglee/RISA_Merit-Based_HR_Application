package adminpackage;

import java.io.IOException;

import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseDao.DBConnection;
/**
 * Servlet implementation class RetrieveData
 */
@WebServlet("/CEO_View")
public class CEO_View extends HttpServlet {
	Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CEO_View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		
        res.setContentType("text/html");
        try {
        	connection = DBConnection.getconnectionToDatabase();
        	Statement stmt = connection.createStatement();
        	ResultSet rs = stmt.executeQuery("select * from student");
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\"><th>RISA Code</th><th>First Name</th><th>Last Name</th><th>Field Of Interest</th></tr></thead>");
            while (rs.next()) {
            	
                out.println("<tr><td>" + rs.getInt(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(10) + "</td></tr>"); 
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
	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		
	}

}