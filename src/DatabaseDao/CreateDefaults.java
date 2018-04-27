package DatabaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateDefaults {

	private boolean successful = false;
	
	public CreateDefaults() {
		// TODO Auto-generated constructor stub
	};
	
	public boolean isSuccessful() {
		return successful;
	}
	// Executes the select query and then stores it into mDatasets.
	public void getInfomation(String firstName, String lastName, String code) throws SQLException{
		
		PreparedStatement studentStmt = null;
		PreparedStatement countStmt = null;
		PreparedStatement countStmt2 = null;
		PreparedStatement countStmt3 = null;
		PreparedStatement collegeStmt = null;
		PreparedStatement certificationStmt = null;
		PreparedStatement validStmt = null;
		
		Connection connection = null;
		

		try {
			connection = DBConnection.getconnectionToDatabase();
			String vaildMemeber = "Select RISACode from risa_hr.student " +
									"where RISACode = ?";
			validStmt = connection.prepareStatement(vaildMemeber);
			validStmt.setString(1, code);

			ResultSet validSet = validStmt.executeQuery();
			if(!validSet.first()) {
			
			String countStudent = "select count(ID) as CountTotal from risa_hr.student";
			String countCollege = "select count(ID) as CountTotal from risa_hr.studentcollege";
			String countCertification = "select count(ID) as CountTotal from risa_hr.studentcertification";
			String studentQuery = "insert into risa_hr.student " +
									"values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String collegeQuery = "insert risa_hr.studentcollege values(?, ?, ?, ?, ?, ?, ?, ?)";
			String certificationQuery = "insert risa_hr.studentcertification values(?, ?, ?, ?, ?)"; 
			
			countStmt = connection.prepareStatement(countStudent);
			ResultSet countSet = countStmt.executeQuery();
			countSet.first();
			int studentID = countSet.getInt("CountTotal") + 1;
			
			countStmt2 = connection.prepareStatement(countCollege);
			ResultSet countSet2 = countStmt2.executeQuery();
			countSet2.first();
			int collegeID = countSet2.getInt("CountTotal") + 1;
			
			countStmt3 = connection.prepareStatement(countCertification);
			ResultSet countSet3 = countStmt3.executeQuery();
			countSet3.first();
			int certificationID = countSet3.getInt("CountTotal") + 1;
									
			studentStmt = connection.prepareStatement(studentQuery);
			studentStmt.setInt(1, studentID);
			studentStmt.setString(2, code);
			studentStmt.setString(3, firstName);
			studentStmt.setString(4, lastName);
			studentStmt.setString(5, "N/A");
			studentStmt.setString(6, "N/A");
			studentStmt.setString(7, "N/A");
			studentStmt.setString(8, "N/A");
			studentStmt.setInt(9, 0);
			studentStmt.setString(10, "N/A");
			studentStmt.setInt(11, 1);
			studentStmt.setInt(12, 0);
			studentStmt.executeUpdate();

			collegeStmt = connection.prepareStatement(collegeQuery);
			collegeStmt.setInt(1, collegeID);
			collegeStmt.setInt(2, studentID);
			collegeStmt.setInt(3, 0);
			collegeStmt.setInt(4, 0);
			collegeStmt.setInt(5, 0);
			collegeStmt.setInt(6, 0);
			collegeStmt.setString(7, "N/A");
			collegeStmt.setString(8, "0000");
			collegeStmt.executeUpdate();
			
			certificationStmt = connection.prepareStatement(certificationQuery);
			certificationStmt.setInt(1, certificationID);
			certificationStmt.setInt(2, 0);
			certificationStmt.setString(3, "0000");
			certificationStmt.setInt(4,studentID);
			certificationStmt.setInt(5, 0);
			certificationStmt.executeUpdate();
			
			successful = true;
			}
			else {
				successful = false;
			}
		}
		
		catch (SQLException e) {
		
		}
		finally {

			if (studentStmt != null) {
				studentStmt.close();
			}
			if (countStmt != null) {
				countStmt.close();
			}
			if (countStmt2 != null) {
				countStmt2.close();
			}
			if (countStmt3 != null) {
				countStmt3.close();
			}
			if (collegeStmt != null) {
				collegeStmt.close();
			}
			if (certificationStmt != null) {
				certificationStmt.close();
			}
			if (validStmt != null) {
				validStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
		
		