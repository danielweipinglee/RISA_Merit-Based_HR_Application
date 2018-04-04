package adminpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseDao.DBConnection;

public class Admin_Delete {
	private boolean success;

	public Admin_Delete(){
		success = false;
	};
	
	public boolean isSuccess() {
		return success;
	}
	
	public int getAccountStatusID(String status) {//to get the id from the status selected in dropdown
		int id = -1;
		try {
			String sql = "select ID from accountstatus where Status = ?;";
			Connection conn = DBConnection.getconnectionToDatabase();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, status);
			
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
	
	public String AccountExistsStudent(String fname, String lname, String risacode) {//check if account exists in db
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
	
	public String AccountExistsAdmin(String fname, String lname, String risacode) {//check if account exists in db
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

	
	public void DeleteStudent(String fname, String lname, String risacode) throws SQLException{
		PreparedStatement Stmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getconnectionToDatabase();
			Stmt = conn.prepareStatement("update risa_hr.student,risa_hr.accountstatus set AccountStatus_ID = accountstatus.ID "
					+ "where accountstatus.Status = 'Deleted' and RISACode = ? and LegalFirstName = ? " + 
					"and LegalLastName = ?;");
			Stmt.setString(1, risacode);
			Stmt.setString(2, fname);
			Stmt.setString(3, lname);
			int count = Stmt.executeUpdate();
			
		    Stmt.close();
		    conn.close();
		    
		    if(count > 0) {
		    	success = true;
		    }
		    
		    
		}catch (SQLException e){
			// TODO: handle exception
		}
	}
	
	public void DeleteAdmin(String fname, String lname, String risacode, int statusId) throws SQLException{
		PreparedStatement Stmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getconnectionToDatabase();
			Stmt = conn.prepareStatement("update risa_hr.admin,risa_hr.accountstatus set AccountStatus_ID = accountstatus.ID " + 
					"where accountstatus.Status = 'Deleted' and RISACode = ? and LegalFirstName = ? " + 
					"and LegalLastName = ? and AccountStatus_ID = ?;");
			Stmt.setString(1, risacode);
			Stmt.setString(2, fname);
			Stmt.setString(3, lname);
			Stmt.setInt(4, statusId);
			
			int count = Stmt.executeUpdate();
			
		    Stmt.close();
		    conn.close();
		    
		    if(count > 0) {
		    	success = true;
		    }
		    
		}catch (SQLException e){
			// TODO: handle exception
		}
	}
}
