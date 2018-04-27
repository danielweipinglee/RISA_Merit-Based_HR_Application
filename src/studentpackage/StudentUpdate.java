package studentpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DatabaseDao.DBConnection;

// only do the students phone number
public class StudentUpdate {
	
	private String mphoneNumber = "";
	
	public String getphoneNumber() {
		return mphoneNumber;
	}

	public StudentUpdate(){}
	
	public void updateStudent(String mphoneNumber, String ID) throws SQLException{
		
		PreparedStatement updatestmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getconnectionToDatabase();
			String updateQuery = "update risa_hr.student " + 
					"set Phone = ? where ID = ?";			
	
			updatestmt = connection.prepareStatement(updateQuery);
			updatestmt.setString(1, mphoneNumber);
			updatestmt.setString(2, ID);
			updatestmt.executeUpdate();

		}
		catch (SQLException e){
		
		}
		finally {
			
			if (updatestmt != null) {
				updatestmt.close();;
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
}
