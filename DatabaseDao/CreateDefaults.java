package DatabaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Data.DataSet;

public class CreateDefaults {

	
	// List of all the row that were retrieved from the database.
	public CreateDefaults() {
		// TODO Auto-generated constructor stub
	};
	
	// Executes the select query and then stores it into mDatasets.
	public void getInfomation(){
		

		try {
			Connection connection = DBConnection.getconnectionToDatabase();
			
			String updateQuery = "update userpassword Set SecurityAnswer= ? " + 
					"where ID = ? ";
			String updateQuery2 = "update student Set LegalFirstName = ?, LegalLastName=?" + 
					"where ID = ?";
			
		    PreparedStatement preparedStmt = connection.prepareStatement(updateQuery);
		    preparedStmt.setString(1, "random");
		    preparedStmt.setInt(2, 2);

		    preparedStmt.executeUpdate();
			//TODO another PreparedStatment
		    
		    connection.close();
	
		}	
		catch (SQLException e) {
		
	}