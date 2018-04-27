package studentpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseDao.DBConnection;

public class StudentUpdateHRCEO {
	private String mphoneNumber = "";
	
	public String getphoneNumber() {
		return mphoneNumber;
	}

	public StudentUpdateHRCEO(){}
	
	public void updateStudent(String studentName, String mphoneNumber) throws SQLException{
		
		PreparedStatement updatestmt = null;
		PreparedStatement selectstmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getconnectionToDatabase();
			String selectQuery = "select student.ID from student " +
									"where Username = ?";
			
			String updateQuery = "update risa_hr.student " + 
									"set Phone = ? where ID = ?";
			
			selectstmt = connection.prepareStatement(selectQuery);
			selectstmt.setString(1, studentName);
			ResultSet set = selectstmt.executeQuery();
			set.first();
			String studentID = set.getString("ID");
	
			updatestmt = connection.prepareStatement(updateQuery);
			updatestmt.setString(1, mphoneNumber);
			updatestmt.setString(2, studentID);
			updatestmt.executeUpdate();

		}
		catch (SQLException e){
		
		}
		finally {
			
			if (updatestmt != null) {
				updatestmt.close();;
			}
			if (selectstmt != null) {
				selectstmt.close();;
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
}
