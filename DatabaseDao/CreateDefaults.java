package DatabaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateDefaults {

	public CreateDefaults() {
		// TODO Auto-generated constructor stub
	};
	// Executes the select query and then stores it into mDatasets.
	public void getInfomation(String firstName, String lastName, int code) throws SQLException{
		
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmt2 = null;
		Connection connection = null;

		try {
			connection = DBConnection.getconnectionToDatabase();
			String CountQuery = "select count(ID) as CountTotal from risa_hr.student";
			String insertQuery = "insert into risa_hr.student " +
					"values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			//String insert risa_hr.userpassword values(8, 'asdfasfas', 'asdfafas', 3)
			preparedStmt2 = connection.prepareStatement(CountQuery);
			ResultSet set = preparedStmt2.executeQuery();
			set.next();
			
			
			int count = set.getInt("CountTotal") + 1;
			System.out.println(count);
			preparedStmt = connection.prepareStatement(insertQuery);

			preparedStmt.setInt(1, count);
			preparedStmt.setInt(2, code);
			preparedStmt.setString(3, firstName);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, "N/A");
			preparedStmt.setString(6, "N/A");
			preparedStmt.setString(7, "N/A");
			preparedStmt.setString(8, "N/A");
			preparedStmt.setInt(9, count);
			preparedStmt.setString(10, "N/A");
			preparedStmt.setInt(11, 2);
			System.out.println("insert");
			preparedStmt.executeUpdate();
	
		}	
		catch (SQLException e) {
		
		}
		finally {

			if (preparedStmt != null) {
				preparedStmt.close();
			}
			if (preparedStmt2 != null) {
				preparedStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
		
		