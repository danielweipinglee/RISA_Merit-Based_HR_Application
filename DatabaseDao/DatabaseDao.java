package DatabaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Data.DataSet;

public class DatabaseDao {
	
	// List of all the row that were retrieved from the database.
	private List<DataSet> mDatasets ;
	
	public DatabaseDao(){
		mDatasets = new ArrayList<>();
	};
	
	public List<DataSet> getData() {
		return mDatasets;
	}
	
	// Executes the select query and then stores it into mDatasets.
	public void getInfomation(){
		
		DataSet dataset = null;
		
		try {
			Connection connection = DBConnection.getconnectionToDatabase();
			
			String selectString = "SELECT  student.ID,  userpassword.UserPassword, student.Username, accountstatus.Status from userpassword \n" + 
					"Join student on userpassword.ID=student.ID\n" + 
					"join accountstatus on accountstatus.ID = student.ID;";
			
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(selectString);
			
			while(set.next()) {
				dataset = new DataSet();
				dataset.setId(set.getInt("ID"));
				dataset.setPassword(set.getString("UserPassword"));
				dataset.setUserName(set.getString("Username"));
				dataset.setStatus(set.getString("Status"));
				mDatasets.add(dataset);
			} 
		}	
		catch (SQLException e) {
		
	}

}
}
