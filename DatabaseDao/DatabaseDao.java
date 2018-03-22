package DatabaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Data.DataSet;

public class DatabaseDao {
	
	private List<DataSet> datasets ;
	
	public DatabaseDao(){
		datasets = new ArrayList<>();
	};
	
	public List<DataSet> getData() {
		return datasets;
	}
	
	public void getInfomation(){
		
		DataSet dataset = null;
		
		try {
			Connection connection = DBConnection.getconnectionToDatabase();
			
			String selectString = "SELECT R.ID, StudentPassword, Username FROM risa_hr.studentpassword R ,risa_hr.student S " +
					"where R.ID = S.ID";
			
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(selectString);
			
			while(set.next()) {
				dataset = new DataSet();
				dataset.setId(set.getInt("ID"));
				dataset.setPassword(set.getString("StudentPassword"));
				dataset.setUserName(set.getString("Username"));
				datasets.add(dataset);
			} 
		}	
		catch (SQLException e) {
		
	}

}
}
