package Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseDao.DBConnection;


public class RegisterStudent {
	private boolean success;

	public RegisterStudent(){
		success = false;
	};
	
	public boolean isSuccess() {
		return success;
	}
	
	public int getQuestionID(String squestion) {//to get the security question id from the selected value
		int id = 0;
		try {
			String sql = "select ID from securityquestion where SecurityQuestion = ?;";
			Connection conn = DBConnection.getconnectionToDatabase();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, squestion);
			
		    ResultSet resultset = statement.executeQuery();
		    while ( resultset.next() )
		    {
		      id =  resultset.getInt("ID") ;
		    }
		    resultset.close();
		    statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return id;
	}

	public String AccountExists(String fname, String lname, String risacode) {//check if account exists in db
		PreparedStatement Stmt = null;
		String exists = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getconnectionToDatabase();
			Stmt = conn.prepareStatement("SELECT RISACode FROM risa_hr.student where RISACode = ? "
					+ "and LegalFirstName = ? and LegalLastName = ?;");
			Stmt.setString(1, risacode);
			Stmt.setString(2, fname);
			Stmt.setString(3, lname);
			ResultSet resultset = Stmt.executeQuery();
		    while ( resultset.next() ){
		      exists =  resultset.getString("risacode");
		    }
		    resultset.close();
		    Stmt.close();
		}catch (SQLException e){
		} 
		
	    return exists;
	}

	
	// inserts and updates tables needed for student registration
	public void insertThenUpdate(String fname, String lname,String email,String username,String password,
			String answer,String risacode,String risaposition, String squestion, int squestionID) throws SQLException{
		
		PreparedStatement userpasswordCountStmt = null;
		PreparedStatement risapositionCountStmt = null;
		PreparedStatement userpasswordStmt = null;
		PreparedStatement risapositionStmt = null;
		PreparedStatement userpasswordStmt2 = null;
		PreparedStatement risapositionStmt2 = null;
		PreparedStatement studentStmt = null;
		Connection conn = null;

		
		try {
			String userpasswordIdQuery = "select count(ID) as CountTotal from risa_hr.userpassword";
			String risapositionIdQuery = "select count(ID) as CountTotal from risa_hr.risaposition";

			String insertUserpassword = "insert into risa_hr.userpassword values(?,?,?,?);";
			String insertRisaposition = "insert into risa_hr.risaposition values(?,?);";
			
			String updateStudent = "update risa_hr.student set Email = ?, Username = ?, UserPassword_ID =?, RISAPosition_ID = ?"
					+ " where RISACode = ?";
			
			String updateUserpassword = "update risa_hr.userpassword set UserPassword = ?, SecurityAnswer = ?, SecurityQuestion_ID =?"
					+ " where userpassword.ID = ?;";
			
			String updateRisaposition = "update risa_hr.risaposition set Position = ? where ID = ?;";
			 
			conn = DBConnection.getconnectionToDatabase();
			//get ids for tables
			userpasswordCountStmt = conn.prepareStatement(userpasswordIdQuery);
			ResultSet countSet = userpasswordCountStmt.executeQuery();
			countSet.next();
			int userpasswordId = countSet.getInt("CountTotal") + 1;

			risapositionCountStmt = conn.prepareStatement(risapositionIdQuery);
			ResultSet countSet2 = risapositionCountStmt.executeQuery();
			countSet2.next();
			int risapositionId = countSet2.getInt("CountTotal") + 1;

			//inserts
			userpasswordStmt = conn.prepareStatement(insertUserpassword);
			userpasswordStmt.setInt(1, userpasswordId);
			userpasswordStmt.setString(2, "N/A");
			userpasswordStmt.setString(3, "N/A");
			userpasswordStmt.setInt(4, 0);
			userpasswordStmt.executeUpdate();
			
			risapositionStmt = conn.prepareStatement(insertRisaposition);
			risapositionStmt.setInt(1, risapositionId);
			risapositionStmt.setString(2, "N/A");
			risapositionStmt.executeUpdate();

			//updates
			
			studentStmt = conn.prepareStatement(updateStudent);
			studentStmt.setString(1, email);
			studentStmt.setString(2, username);
			studentStmt.setInt(3, userpasswordId);
			studentStmt.setInt(4, risapositionId);
			studentStmt.setString(5, risacode);
			studentStmt.executeUpdate();
			
			userpasswordStmt2 = conn.prepareStatement(updateUserpassword);
			userpasswordStmt2.setString(1, password);
			userpasswordStmt2.setString(2, answer);
			userpasswordStmt2.setInt(3, squestionID);
			userpasswordStmt2.setInt(4, userpasswordId);
			userpasswordStmt2.executeUpdate();

			risapositionStmt2 = conn.prepareStatement(updateRisaposition);
			risapositionStmt2.setString(1, risaposition);
			risapositionStmt2.setInt(2, risapositionId);
			risapositionStmt2.executeUpdate();
		
			userpasswordCountStmt.close();
			risapositionCountStmt.close();
			userpasswordStmt.close();
			risapositionStmt.close();
			userpasswordStmt2.close();
			risapositionStmt2.close();
			studentStmt.close();
			conn.close();
			
			success = true;

		}catch (SQLException e){
		}
	}
}