package DatabaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class CreateDefaults {

	public CreateDefaults() {
		// TODO Auto-generated constructor stub
	};
	// Executes the select query and then stores it into mDatasets.
	public void getInfomation(String firstName, String lastName, int code) throws SQLException{
		
		PreparedStatement preparedStmt = null;
		Connection connection = null;

		try {
			connection = DBConnection.getconnectionToDatabase();
			
			String insertQuery = "insert into risa_hr.student " +
					"values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStmt = connection.prepareStatement(insertQuery);

			preparedStmt.setInt(1, code);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(2, "N/A");
			preparedStmt.setString(3, "N/A");
			preparedStmt.setString(2, "N/A");
			preparedStmt.setString(3, "N/A");
			preparedStmt.setInt(1, 11);
			preparedStmt.setString(2, "N/A");
			preparedStmt.setInt(1, 11);

			preparedStmt.executeUpdate();
	
		}	
		catch (SQLException e) {
		
		}
		finally {

			if (preparedStmt != null) {
				preparedStmt.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
}
		
		