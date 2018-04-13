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
		ArrayList<String> majorsList = new ArrayList<String>();

		response.setContentType("text/html");
        try {
        	search = request.getParameter("search");
        	
        if(search.equals("Default")) {
        		query = "SELECT student.LegalFirstName, student.LegalLastName, student.Username, student.Phone, student.FieldofInterest, major.Major \r\n" + 
        				"FROM student inner join studentcollege on student.ID = studentcollege.Student_ID\r\n" + 
        				"inner join major on major.ID = studentcollege.Major_ID;";
        	}
        else{
        	query = "SELECT student.LegalFirstName, student.LegalLastName, student.Username, student.Phone, student.FieldofInterest, major.Major \r\n" + 
        			"FROM student inner join studentcollege on student.ID = studentcollege.Student_ID\r\n" + 
        			"inner join major on major.ID = studentcollege.Major_ID and major = '" + search + "'; ";
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
        	out.println("<script language=\"javascript\">\n" + 
        			"            function addRow(tableID) {\n" + 
        			"                var table = document.getElementById(tableID);\n" + 
        			"                var rowCount = table.rows.length;\n" + 
        			"                var row = table.insertRow(rowCount); \n" + 
        			"                var cell0 = row.insertCell(0);\n" + 
        			"                var element1 = document.createElement(\"input\");\n" + 
        			"                element1.type = \"text\";\n" + 
        			"                element1.name = \"line\"+(rowCount+1);\n" + 
        			"                element1.value=\"\"+(rowCount+1);\n" + 
        			"                cell0.appendChild(element1);\n" + 
        			"                document.getElementById(\"countofrows\").value=table.rows.length;\n" + 
        			"            }\n" + 
        			"        </script>");
        	
        	out.println("<header>\r\n" + 
        			"	<div class=\"container\">\r\n" + 
        			"    <img src=\"images/White.png\" alt=\"pic\" class=\"pic\" >\r\n" + 
        			"    <nav>\r\n" + 
        			"    	<ul>\r\n" + 
        			"    		<li><a href=\"Employer_View.jsp\">View</a></li>\r\n" + 
        			"    		<li><a href=\"Employer_Sort.jsp\">Sort</a></li>\r\n" + 
        			"    		<li><a href=\"Employer_Search.jsp\">Search</a></li>\r\n" + 
        			"    	</ul>\r\n" + 
        			"    </nav>\r\n" + 
        			"	</div>\r\n" + 
        			"	<form method=\"get\" action=\"Employer_Search\">	\r\n");
        			try {
        				Connection conn = DBConnection.getconnectionToDatabase();
        				Statement statement = conn.createStatement() ;
        		        ResultSet resultset =statement.executeQuery("select Major from major;");
        				out.println("    <select name=\"search\" onchange=\"this.form.submit()\">\r\n");
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
        			out.println("	</form>\r\n" + 
        			"</header>");
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
