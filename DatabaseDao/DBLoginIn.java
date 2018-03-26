package DatabaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBLoginIn {
	
	private int mId;
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

	public DBLoginIn(){
		mId = -1;
		mUserName = "";
		mPassword = "";
		mAccountType = "";
		mFound = false;
	};
	
	// Retrieves the information of the is required to login-in.
	public void getInfomation(String userName, String password) throws SQLException{
		
		PreparedStatement preparedStmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getconnectionToDatabase();
			
			String selectQuery = "SELECT  student.ID,  userpassword.UserPassword, student.Username, accountstatus.Status from userpassword " + 
					"Join student on userpassword.ID=student.ID " + 
					"join accountstatus on accountstatus.ID = student.ID;";
			
			preparedStmt = connection.prepareStatement(selectQuery);			
			ResultSet set = preparedStmt.executeQuery();

			while(set.next()) {
				mPassword = set.getString("UserPassword");
				mUserName = set.getString("Username");
				
				if(mUserName.equals(userName) && mPassword.equals(password)) {
					mId = set.getInt("ID");
					mAccountType = set.getString("Status");
					mFound = true;
					System.out.println(mAccountType);
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

			if (connection != null) {
				connection.close();
			}
		}
	}
}