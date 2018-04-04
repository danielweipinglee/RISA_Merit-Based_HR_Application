package DatabaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
	
	public static Connection getconnectionToDatabase() {
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/risa_hr";
		
		try {
			// load driver class
			Class.forName("com.mysql.jdbc.Driver");
			
			// get a hold of the driver class
			connect = DriverManager.getConnection(url, "root", "qhgus9857");
		 } 
		catch (ClassNotFoundException e) {
			 
		 } 
		catch (SQLException e) {
			 
		 } 
		if (connect != null) {
			 System.out.println("Connection was successful");
		 }	 
		 return connect;
	}

}
