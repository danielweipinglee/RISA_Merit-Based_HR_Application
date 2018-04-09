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
        	ResultSet rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.Concentration_ID;");
        	out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        	out.println("<table class=\"w3-table-all\">");
            out.println("<thead><tr class=\"w3-red\">"
            		+ "<th>RISA Code</th>"            		
            		+ "<th>Phone</th>"
            		+ "<th>Field Of Interest</th>"
            		+ "<th>Department</th>"
            		+ "<th>Degree Level</th>"
            		+ "<th>Concentration</th>"
            		+ "<th>Graduate Month</th>"
            		+ "<th>Graduate Year</th>"
            		+ "</tr></thead>");
            
            HttpSession session = request.getSession(false);
            String userid = (String) session.getAttribute("UserName");
            String RISACode = "";
            while (rs.next()) {
            	if(rs.getString(8).equals(userid)) {
            		RISACode = rs.getString(1);
            	}
            }
            
            rs = stmt.executeQuery("select * from student s join studentcollege c on s.ID = c.ID join college cl on cl.ID = c.College_ID join degreelevel d on d.ID = c.DegreeLevel_ID join concentration con on con.ID = c.College_ID;");
            while (rs.next()) {
            	if(RISACode.equals(rs.getString(1))) {
            		out.println("<tr><td>" + rs.getInt(2) + "</td>"
            				+ "<td>" + rs.getString(7) + "</td>"
                    		+ "<td>" + rs.getString(10) + "</td>"
                    		+ "<td>" + rs.getString(22) + "</td>"
                    		+ "<td>" + rs.getString(24) + "</td>"
                    		+ "<td>" + rs.getString(26) + "</td>"
                    		+ "<td>" + rs.getString(19) + "</td>"
                            + "<td>" + rs.getString(20).substring(0,4) + "</td>"
                    		+ "</tr>"); 
            	}
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
