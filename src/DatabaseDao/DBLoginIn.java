package DatabaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLoginIn {
	
	private String mId;
	private String mUserName;
	private String mPassword;
	private String mAccountType;
	private boolean mFound;

	public String getmAccountType() {
		return mAccountType;
	}

	public boolean getFound() {
		return mFound;
	}
	public String getId() {
		return mId;
	}

	public DBLoginIn(){
		mId = "";
		mUserName = "";
		mPassword = "";
		mAccountType = "";
		mFound = false;
	};
	
	// Retrieves the information of the is required to login-in.
	public void getInfomation(String userName, String password) throws SQLException{
		
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmt2 = null;
		Connection connection = null;

		
		try {
			connection = DBConnection.getconnectionToDatabase();
			
			String selectQuery = "SELECT student.ID, userpassword.UserPassword, student.Username, " +
					"accountstatus.Status from student " +
					"inner Join userpassword on userpassword.ID=student.UserPassword_ID " +
					"inner join accountstatus on accountstatus.ID = student.AccountStatus_ID";
			
			String selectQuery2 = "SELECT  admin.ID, admin.Username, " + 
					"accountstatus.Status, userpassword.UserPassword from admin " + 
					"inner join accountstatus on accountstatus.ID = admin.AccountStatus_ID " + 
					"inner join userpassword on userpassword.ID = admin.UserPassword_ID";
					
			preparedStmt = connection.prepareStatement(selectQuery);
			ResultSet set = preparedStmt.executeQuery();

			while(set.next()) {
				mPassword = set.getString("UserPassword");
				mUserName = set.getString("Username");
				if(!mUserName.equals("N/A") && !mPassword.equals("Default") && mUserName.equals(userName) && mPassword.equals(password)) {
					mId = set.getString("ID");
					mAccountType = set.getString("Status");
					mFound = true;
				}
			}

			preparedStmt2 = connection.prepareStatement(selectQuery2);
			ResultSet set2 = preparedStmt2.executeQuery();
			while(set2.next()) {
				mPassword = set2.getString("UserPassword");
				mUserName = set2.getString("Username");

				if(!mUserName.equals("N/A") && !mPassword.equals("Default") && mUserName.equals(userName) && mPassword.equals(password)) {
					mId = set2.getString("ID");
					mAccountType = set2.getString("Status");			
					mFound = true;
				}
			}
		}	
		
		catch (SQLException e) {
		//TODO write an Exception
		}
		
		finally {

			if (preparedStmt != null) {
				preparedStmt.close();
			}
			
			if( preparedStmt2 != null) {
				preparedStmt2.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
}