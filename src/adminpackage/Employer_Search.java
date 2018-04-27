package adminpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseDao.DBConnection;

/**
 * Servlet implementation class Employer_Search
 */
@WebServlet("/Employer_Search")
public class Employer_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employer_Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = "";
		String query = "";
		PrintWriter out = response.getWriter();
		

		response.setContentType("text/html");
        try {
        	search = request.getParameter("search");
        	
        if(search.equals("Default")) {
        	response.sendRedirect("Employer_View.jsp");
        	}
        else{
        	query = "SELECT student.LegalFirstName, student.LegalLastName, student.Username, student.Phone, student.FieldofInterest, major.Major \r\n" + 
        			"FROM student inner join studentcollege on student.ID = studentcollege.Student_ID\r\n" + 
        			"inner join major on major.ID = studentcollege.Major_ID and major = '" + search + "'; ";
        }
        	connection = DBConnection.getconnectionToDatabase();
        	Statement stmt = connection.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
        	out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
        			"<html>\r\n" + 
        			"<head>\r\n" + 
        			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n" + 
        			"<title>RISA</title>\r\n" + 
        			"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/active_CEO.css\">\r\n" + 
        			"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n" + 
        			"</head>\r\n" + 
        			"<body>\r\n" + 
        			"<header>\r\n" + 
        			"	<div class=\"container\">\r\n" + 
        			"    <img src=\"images/White.png\" alt=\"pic\" class=\"pic\" >\r\n" + 
        			"    <nav>\r\n" + 
        			"    	<ul>\r\n" + 
        			"    		<li><a href=\"Employer_View.jsp\">View</a></li>\r\n" + 
        			"    	</ul>\r\n" + 
        			"    </nav>\r\n" + 
        			"	</div>		\r\n" + 
        			"	\r\n" + 
        			"</header>\r\n" + 
        			"<div style=\"float:right; float:top; padding-right:10px; padding-top:15px\" class=\"optionsDiv\">\r\n" + 
        			"<form action=\"Employer_Search\" method=\"get\">");
        			try {
        				Connection conn = DBConnection.getconnectionToDatabase();
        				Statement statement = conn.createStatement() ;
        		        ResultSet resultset =statement.executeQuery("select DISTINCT Major from major;");
        				out.println("    <select name=\"search\" id=\"search\">\r\n");
        				while(resultset.next()){ 
	        			out.println("<option> ");
	        			String major = resultset.getString("Major");
	        			out.println(major);
	        			out.println("</option>\r\n");
        				}
	        			out.println("</select>\r\n" + 
	        			"	<br>\r\n"); 
        			}catch(Exception e) {
        				out.println(e);
        			}
        			out.println("<button type=\"submit\" class=\"w3-button w3-red\">Search</button> </form></div>\r\n" + 
        					"<div style=\"float:right; float:top; padding-right:10px; padding-top:15px\" class=\"optionsDiv\">\r\n" + 
        					"<form action=\"Employer_Sort\" method=\"get\">");
        			try {
        				Connection conn = DBConnection.getconnectionToDatabase();
        				Statement statement = conn.createStatement() ;
        		        ResultSet resultset =statement.executeQuery("select DISTINCT Major from major;");
        				out.println("    <select name=\"sort\" id=\"sort\">\r\n");
        				while(resultset.next()){ 
	        			out.println("<option> ");
	        			String major = resultset.getString("Major");
	        			out.println(major);
	        			out.println("</option>\r\n");
        				}
	        			out.println("</select>\r\n" + 
	        			"	<br>\r\n"); 
        			}catch(Exception e) {
        				out.println(e);
        			}
        	out.println("<button type=\"submit\" class=\"w3-button w3-red\">Sort</button> \r\n" + 
        			"</form>\r\n" + 
        			"</div>\r\n" + 
        			"<h2>Select a category to search the student file</h2>"
        			+ "<h3>Searched: " + search + "</h3>");
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\"><th>First Name</th><th>Last Name</th><th>Username</th><th>Phone</th><th>Field of Interest</th>"
            		+ "<th>Major</th></tr></thead>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("LegalFirstName") + "</td><td>" + 
            rs.getString("LegalLastName") + "</td><td>" + rs.getString("Username") + "</td><td>" + rs.getString("Phone") + "</td><td>" +
                		rs.getString("FieldofInterest") + "</td><td>" + rs.getString("Major") + "</td></tr>"); 
            }
            out.println("</table><br>");
        	connection.close();
           }
            catch (Exception e) {
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
