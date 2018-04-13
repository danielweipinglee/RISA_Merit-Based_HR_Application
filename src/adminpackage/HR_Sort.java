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
 * Servlet implementation class HR_Sort
 */
@WebServlet("/HR_Sort")
public class HR_Sort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HR_Sort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = null;
		String query = null;
		PrintWriter out = response.getWriter();
		
		query = "select * from student;";
		response.setContentType("text/html");
        try {
        	search = request.getParameter("search");
        	
        if(search.equals("Active_CEO")) {
        		query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 0 order by LegalFirstName,LegalLastName ASC;";
        	}
        else if(search.equals("Active")){
        	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 1 order by LegalFirstName,LegalLastName ASC;;";
        }
        else if(search.equals("Deleted")){
        	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 2 \n" + 
        			"union\n" + 
        			"SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student \n" + 
        			"where AccountStatus_ID = 2 order by LegalFirstName,LegalLastName ASC;";
        }
        else if(search.equals("admin")){
        	query ="SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 3 order by LegalFirstName,LegalLastName ASC;";
        }
        else if(search.equals("Active_HR")) {
        	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 4 order by LegalFirstName,LegalLastName ASC;";
        }
        else if(search.equals("Active_Employer")){
        	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 5 order by LegalFirstName,LegalLastName ASC;";
        }
        else if(search.equals("None")){
        	query = "select LegalFirstName,LegalLastName,Username from student order by LegalFirstName,LegalLastName ASC;";
        }
        //System.out.print(query);
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
        			"    		<li><a href=\"HR_View.jsp\">View</a></li>\r\n" + 
        			"    		<li><a href=\"HR_Update.jsp\">Update</a></li>\r\n" + 
        			"    		<li><a href=\"HR_Add.jsp\">Add</a></li>\r\n" + 
        			"    		<li><a href=\"HR_Sort.jsp\">Sort</a></li>\r\n" + 
        			"    		<li><a href=\"HR_Search.jsp\">Search</a></li>\r\n" + 
        			"    		\r\n" + 
        			"    	</ul>\r\n" + 
        			"    </nav>\r\n" + 
        			"	</div></header>\r\n" + 
        			"		<form method=\"get\" action=\"HR_Sort\">\r\n" + 
        			"<div class=\"SortOptionsDiv\" >\r\n" + 
        			"        Search\r\n" + 
        			"        <select id=\"search\" name=\"search\" onchange=\"this.form.submit()\">\r\n" + 
        			"            <option value=\"none\" selected>None</option>\r\n" + 
        			"            <option value=\"Active_CEO\">Active CEO</option>\r\n" + 
        			"            <option value=\"Active\">Active</option>\r\n" + 
        			"            <option value=\"Deleted\">Deleted</option>\r\n" + 
        			"            <option value=\"Admin\">Admin</option>\r\n" + 
        			"            <option value=\"Active_HR\">Active HR</option>\r\n" + 
        			"            <option value=\"Active_Employer\">Active Employer</option>\r\n" + 
        			"        </select>   \r\n" + 
        			"</div>\r\n" + 
        			"</form>");	
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\"><th>First Name</th><th>Last Name</th><th>Email</th></tr></thead>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("LegalFirstName") + "</td><td>" + rs.getString("LegalLastName") + "</td><td>" + rs.getString("Username") + "</td></tr>"); 
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