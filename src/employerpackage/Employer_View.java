package employerpackage;

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
 * Servlet implementation class Employer_View
 */
@WebServlet("/Employer_View")
public class Employer_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employer_View() {
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
        	ResultSet rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID;");
        	
        	String selectedField = request.getParameter("selectField");
        	
        	if(selectedField.equals("RISACode")) {
        		System.out.println("RISA Code");
        		rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID ORDER BY RISACode;");
            }else if(selectedField.equals("FieldOfInterest")) {
            	System.out.println("Interest");
            	rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID ORDER BY FieldofInterest;");
            }else if(selectedField.equals("Degree")) {
            	System.out.println("Degree");
            	rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID ORDER BY DegreeLevel;");
            }else if(selectedField.equals("GraduateYear")) {
            	System.out.println("Year");
            	rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID ORDER BY GradYear;");
            }
        	
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\">"
            		+ "<th>RISA Code</th>"            		
            		+ "<th>Field Of Interest</th>"
            		+ "<th>Department</th>"
            		+ "<th>Degree Level</th>"
            		+ "<th>Concentration</th>"
            		+ "<th>Graduate Month</th>"
            		+ "<th>Graduate Year</th>"
            		+ "</tr></thead>");
            
            while (rs.next()) {
            	
            	 out.println("<tr><td>" + rs.getInt(2) + "</td>"
                 		+ "<td>" + rs.getString(10) + "</td>"
                 		+ "<td>" + rs.getString(22) + "</td>"
                 		+ "<td>" + rs.getString(24) + "</td>"
                 		+ "<td>" + rs.getString(26) + "</td>"
                 		+ "<td>" + rs.getString(19) + "</td>"
                         + "<td>" + rs.getString(20).substring(0,4) + "</td>"
                 		+ "</tr>"); 
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
