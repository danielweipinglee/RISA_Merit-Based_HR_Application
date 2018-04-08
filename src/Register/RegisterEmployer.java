package Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseDao.DBConnection;

public class RegisterEmployer {
	private boolean success;
	
	public RegisterEmployer(){
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
	
	public int getAccountStatusID(String role, String risacode) {//to get the security question id from the selected value
		int id = 0;
		try {
			String sql = "select ID from accountstatus where Status = ?;";
			Connection conn = DBConnection.getconnectionToDatabase();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, role);
			
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
			Stmt = conn.prepareStatement("SELECT RISACode FROM risa_hr.admin where RISACode = ? "
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
	
	public void insertThenUpdate(String fname, String lname,String email,String password,
			String answer,String risacode,String risaposition, String squestion, int squestionID, int accountstatusid, String role, String organization) throws SQLException{
		
		PreparedStatement userpasswordCountStmt = null;
		PreparedStatement userpasswordStmt = null;
		PreparedStatement userpasswordStmt2 = null;
		PreparedStatement studentStmt = null;
		PreparedStatement organizationCountStmt = null;
		PreparedStatement organizationStmt = null;
		PreparedStatement organizationStmt2 = null;
		Connection conn = null;

		
		try {
			String userpasswordIdQuery = "select count(ID) as CountTotal from risa_hr.userpassword";
			String organizationIdQuery = "select count(ID) as CountTotal from risa_hr.organization";

			String insertUserpassword = "insert into risa_hr.userpassword values(?,?,?,?);";
			String insertOrganization = "insert into risa_hr.organization values(?,?);";
			
			String updateAdmin = "update risa_hr.admin set Organization_ID = ?, UserPassword_ID =?, Username = ?, AccountStatus_ID = ?"
					+ " where RISACode = ?";
			
			String updateUserpassword = "update risa_hr.userpassword set UserPassword = ?, SecurityAnswer = ?, SecurityQuestion_ID =?"
					+ " where userpassword.ID = ?;";
			String updateOrganization = "update risa_hr.organization set Name =? where ID = ?;";
			 
			conn = DBConnection.getconnectionToDatabase();
			//get ids for tables
			userpasswordCountStmt = conn.prepareStatement(userpasswordIdQuery);
			ResultSet countSet = userpasswordCountStmt.executeQuery();
			countSet.next();
			int userpasswordId = countSet.getInt("CountTotal") + 1;
			
			organizationCountStmt = conn.prepareStatement(organizationIdQuery);
			ResultSet countSet2 = organizationCountStmt.executeQuery();
			countSet2.next();
			int organizationId = countSet2.getInt("CountTotal") + 1;

			//inserts
			userpasswordStmt = conn.prepareStatement(insertUserpassword);
			userpasswordStmt.setInt(1, userpasswordId);
			userpasswordStmt.setString(2, "N/A");
			userpasswordStmt.setString(3, "N/A");
			userpasswordStmt.setInt(4, 0);
			userpasswordStmt.executeUpdate();
			
			organizationStmt = conn.prepareStatement(insertOrganization);
			organizationStmt.setInt(1, organizationId);
			organizationStmt.setString(2, "N/A");
			organizationStmt.executeUpdate();	

			//updates
			studentStmt = conn.prepareStatement(updateAdmin);
			studentStmt.setInt(1, organizationId);
			studentStmt.setInt(2, userpasswordId);
			studentStmt.setString(3, email);
			studentStmt.setInt(4, accountstatusid);
			studentStmt.setString(5, risacode);
			studentStmt.executeUpdate();
			
			userpasswordStmt2 = conn.prepareStatement(updateUserpassword);
			userpasswordStmt2.setString(1, password);
			userpasswordStmt2.setString(2, answer);
			userpasswordStmt2.setInt(3, squestionID);
			userpasswordStmt2.setInt(4, userpasswordId);
			userpasswordStmt2.executeUpdate();
			
			organizationStmt2 = conn.prepareStatement(updateOrganization);
			organizationStmt2.setString(1, organization);
			organizationStmt2.setInt(2, organizationId);
			organizationStmt2.executeUpdate();

			
			userpasswordCountStmt.close();
			userpasswordStmt.close();
			userpasswordStmt2.close();
			studentStmt.close();
			organizationStmt.close();
			organizationStmt2.close();
			organizationCountStmt.close();
			conn.close();
			
			success = true;

		}catch (SQLException e){
		}
	}
}