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
			
			String selectString = "SELECT R.ID, UserPassword, Username FROM risa_hr.userPassword R ,risa_hr.student S " + 
					"where R.ID = S.ID";
			
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(selectString);
			
			while(set.next()) {
				dataset = new DataSet();
				dataset.setId(set.getInt("ID"));
				dataset.setPassword(set.getString("UserPassword"));
				dataset.setUserName(set.getString("Username"));
				mDatasets.add(dataset);
			} 
		}	
		catch (SQLException e) {
		
	}

}
}
