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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = "";
		String sort = "";
		String query = null;
		PrintWriter out = response.getWriter();
		
		query = "select * from student;";
		response.setContentType("text/html");
        try {
        	search = request.getParameter("search");
        	sort = request.getParameter("sort");
        	//System.out.print(search);
        if(search.equals("Active_CEO")) {
        		query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 0;";
        	}
        else if(search.equals("Active")){
        	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 1;";
        }
        else if(search.equals("Deleted")){
        	query = "SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student where AccountStatus_ID = 2 \n" + 
        			"union\n" + 
        			"SELECT LegalFirstName,LegalLastName,Email as Username FROM risa_hr.student \n" + 
        			"where AccountStatus_ID = 2;";
        }
        else if(search.equals("admin")){
        	query ="SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 3;";
        }
        else if(search.equals("Active_HR")) {
        	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 4;";
        }
        else if(search.equals("Active_Employer")){
        	query = "SELECT LegalFirstName,LegalLastName,Username FROM risa_hr.admin where AccountStatus_ID = 5;";
        }
        else if(search.equals("None")){
        	query = "select LegalFirstName,LegalLastName,Username from student;";
        }
        
        //sort
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
	    	query = "select LegalFirstName,LegalLastName,Username from student order by LegalFirstName,LegalLastName ASC;";
	    }

        	connection = DBConnection.getconnectionToDatabase();
        	Statement stmt = connection.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
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

	}
	

}