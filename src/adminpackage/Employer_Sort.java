package adminpackage;

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

import DatabaseDao.DBConnection;

/**
 * Servlet implementation class Employer_Sort
 */
@WebServlet("/Employer_Sort")
public class Employer_Sort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employer_Sort() {
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
        		query = "SELECT student.LegalFirstName, student.LegalLastName, student.Username, student.Phone, student.FieldofInterest, major.Major \r\n" + 
        				"FROM student inner join studentcollege on student.ID = studentcollege.Student_ID\r\n" + 
        				"inner join major on major.ID = studentcollege.Major_ID order by LegalFirstName, LegalLastName ASC;";
        	}
        else{
        	query = "SELECT student.LegalFirstName, student.LegalLastName, student.Username, student.Phone, student.FieldofInterest, major.Major \r\n" + 
        			"FROM student inner join studentcollege on student.ID = studentcollege.Student_ID\r\n" + 
        			"inner join major on major.ID = studentcollege.Major_ID and major = '" + search + "' order by LegalFirstName, LegalLastName ASC; ";
        }
        	connection = DBConnection.getconnectionToDatabase();
        	Statement stmt = connection.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
        	out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
        			"<html>\n" + 
        			"<head>\n" + 
        			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" + 
        			"<title>RISA</title>");
        	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/active_CEO.css\">");
        	
        	out.println("<header>\r\n" + 
        			"	<div class=\"container\">\r\n" + 
        			"    <img src=\"images/White.png\" alt=\"pic\" class=\"pic\" >\r\n" + 
        			"    <nav>\r\n" + 
        			"    	<ul>\r\n" + 
        			"    		<li><a href=\"Employer_View.jsp\">View</a></li>\r\n" + 
        			"    		<li><a href=\"Employer_Sort.jsp\">Sort</a></li>\r\n" + 
        			"    		<li><a href=\"Employer_Search.jsp\">Search</a></li>\r\n" + 
        			"    	</ul>\r\n" + 
        			"    	\r\n" + 
        			"    </nav>\r\n" + 
        			"	</div>		\r\n" + 
        			"	\r\n" + 
        			"</header>\r\n" + 
        			"	<form method=\"get\" action=\"Employer_Search\">	\r\n" + 
        			"		<%\r\n" + 
        			"    try{//for the dropdown menu\r\n" + 
        			"    	Connection conn = DBConnection.getconnectionToDatabase();\r\n" + 
        			"        Statement statement = conn.createStatement() ;\r\n" + 
        			"        ResultSet resultset =statement.executeQuery(\"select Major from major;\") ;\r\n" + 
        			"	%>\r\n" + 
        			"	<br>\r\n" + 
        			"    Alphabetical: <select name=\"search\" onchange=\"this.form.submit()\">\r\n" + 
        			"    <%  while(resultset.next()){ %>\r\n" + 
        			"            <option><%= resultset.getString(\"Major\")%></option>\r\n" + 
        			"    <% } %>\r\n" + 
        			"    </select>\r\n" + 
        			"	<br>\r\n" + 
        			"	<%\r\n" + 
        			"       }catch(Exception e){\r\n" + 
        			"    	   \r\n" + 
        			"          out.println(e);\r\n" + 
        			"       }\r\n" + 
        			"	%>	\r\n" + 
        			"	</form>");
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\"><th>First Name</th><th>Last Name</th><th>Username</th><th>Phone</th><th>Field of Interest</th>"
            		+ "<th>Major</th></tr></thead>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("LegalFirstName") + "</td><td>" + 
            rs.getString("LegalLastName") + "</td><td>" + rs.getString("Username") + "</td><td>" + rs.getString("Phone") + "</td><td>" +
                		rs.getString("FieldofInterest") + "</td><td>" + rs.getString("Major") + "</td></tr>"); 
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
