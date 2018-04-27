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
 * Servlet implementation class CEO_Sort
 */
@WebServlet("/CEO_Sort")
public class CEO_Sort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CEO_Sort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = "";
		String query = null;
		PrintWriter out = response.getWriter();
		
		query = "select * from student;";
		response.setContentType("text/html");
        try {
        	sort = request.getParameter("sort");

        if(sort.equals("Active_CEO")) {
    		query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 0 order by LegalFirstName,LegalLastName ASC;";
    	}
        else if(sort.equals("Active")){
    	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 1 order by LegalFirstName,LegalLastName ASC;";
        }
        else if(sort.equals("Deleted")){
    	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 2 \n" + 
    			"union\n" + 
    			"SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student \n" + 
    			"where AccountStatus_ID = 2 order by LegalFirstName,LegalLastName ASC;";
        }
	    else if(sort.equals("admin")){
	    	query ="SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 3 order by LegalFirstName,LegalLastName ASC;";
	    }
	    else if(sort.equals("Active_HR")) {
	    	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 4 order by LegalFirstName,LegalLastName ASC;";
	    }
	    else if(sort.equals("Active_Employer")){
	    	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 5 order by LegalFirstName,LegalLastName ASC;";
	    }
	    else if(sort.equals("None")){
	    	response.sendRedirect("CEO_View.jsp");
	    }
	        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
	        		"<html>\r\n" + 
	        		"<head>\r\n" + 
	        		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n" + 
	        		"<title>RISA</title>\r\n" + 
	        		"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/active_CEO.css\">\r\n" + 
	        		"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n" + 
	        		"\r\n" + 
	        		"</head>\r\n" + 
	        		"<body>\r\n" + 
	        		"\r\n" + 
	        		"<header>\r\n" + 
	        		"	<div class=\"container\">\r\n" + 
	        		"    <img src=\"images/White.png\" alt=\"pic\" class=\"pic\" >\r\n" + 
	        		"    <nav>\r\n" + 
	        		"    	<ul>\r\n" + 
	        		"    		<li><a href=\"CEO_View.jsp\">View </a></li>\r\n" + 
	        		"    		<li><a href=\"CEO_Update.jsp\">Update </a></li>\r\n" + 
	        		"    		<li><a href=\"CEO_Add.jsp\">Add Student </a></li>\r\n" + 
	        		"    		<li><a href=\"CEO_Add_Employer.jsp\">Add Employer</a></li>\r\n" + 
	        		"    	</ul>\r\n" + 
	        		"    </nav>\r\n" + 
	        		"	</div>		\r\n" + 
	        		"	\r\n" + 
	        		"</header>\r\n" + 
	        		"\r\n" + 
	        		"\r\n" + 
	        		"<div style=\"float:right; float:top; padding-right:100px; padding-top:15px\" class=\"optionsDiv\">\r\n" + 
	        		"<form action=\"CEO_Search\" method=\"get\">\r\n" + 
	        		"<!-- <div style=\"position:absolute; margin-left:750px; top:200px;\" class=\"optionsDiv\"> --> \r\n" + 
	        		"         <select id=\"search\" name =\"search\">\r\n" + 
	        		"        	<option value=\"None\">select a category</option>\r\n" + 
	        		"            <option value=\"Active_CEO\" >Active CEO</option>\r\n" + 
	        		"            <option value=\"Active\" >Active</option>\r\n" + 
	        		"            <option value=\"Deleted\" >Deleted</option>\r\n" + 
	        		"            <option value=\"admin\" >admin</option>\r\n" + 
	        		"            <option value=\"Active_HR\" >Active HR</option>\r\n" + 
	        		"            <option value=\"Active_Employer\" >Active Employer</option>\r\n" + 
	        		"        </select>   \r\n" + 
	        		"        <button type=\"submit\" class=\"w3-button w3-red\">Search</button> \r\n" + 
	        		"</form></div>\r\n" + 
	        		"<div style=\"float:right; float:top; padding-right:100px; padding-top:15px\" class=\"optionsDiv\">\r\n" + 
	        		"<form action=\"CEO_Sort\" method=\"get\">\r\n" + 
	        		"        <select id=\"sort\" name =\"sort\">\r\n" + 
	        		"        	<option value=\"None\">select a category</option>\r\n" + 
	        		"            <option value=\"Active_CEO\" >Active CEO</option>\r\n" + 
	        		"            <option value=\"Active\" >Active</option>\r\n" + 
	        		"            <option value=\"Deleted\" >Deleted</option>\r\n" + 
	        		"            <option value=\"admin\" >admin</option>\r\n" + 
	        		"            <option value=\"Active_HR\" >Active HR</option>\r\n" + 
	        		"            <option value=\"Active_Employer\" >Active Employer</option>\r\n" + 
	        		"        </select>   \r\n" + 
	        		"        <button type=\"submit\" class=\"w3-button w3-red\">Sort</button> \r\n" + 
	        		"</form>\r\n" + 
	        		"</div>\r\n" + 
	        		"<h2>Select a category to search the student file</h2>"
	        		+ "<h3>Sorted: " + sort+ "</h3>");

	        	connection = DBConnection.getconnectionToDatabase();
	        	Statement stmt = connection.createStatement();
	        	ResultSet rs = stmt.executeQuery(query);
	        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
	        	out.println("<table class=\"w3-table-all\">");
	            out.println("<thead><tr class=\"w3-red\"><th>First Name</th><th>Last Name</th><th>Email</th></tr></thead>");
	            while (rs.next()) {
	                out.println("<tr><td>" + rs.getString("LegalFirstName") + "</td><td>" + rs.getString("LegalLastName") + "</td><td>" + rs.getString("Username") + "</td></tr>"); 
	            }
	            out.println("</table><br></body>\r\n" + 
	            		"</html>");
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
