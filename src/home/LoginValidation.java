package home;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static final String URL="jdbc:mysql://localhost:3306/risa_hr";
    private static Connection conn=null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id, password, db_id, db_password;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from StudentPassword");
			
			id = request.getParameter("username");
			password = request.getParameter("password");
			
			while(myRs.next()) {
				db_id = myRs.getString("SecurityAnswer");
				db_password = myRs.getString("StudentPassword");
				
				if(id.equals(db_id) && password.equals(db_password)){
					response.sendRedirect("mainpage.jsp");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}		
		
		
	}

	
}
