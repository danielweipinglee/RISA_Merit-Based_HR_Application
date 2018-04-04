package DatabaseDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateDefaultsManagement {

	private boolean successful = false;
	public CreateDefaultsManagement() {
			// TODO Auto-generated constructor stub
		};
		
	
	public boolean isSuccessful() {
		return successful;
	}
	// Executes the select query and then stores it into mDatasets.
	public void InputManagement(String firstName, String lastName, String code, int statusID) throws SQLException {

		PreparedStatement adminStmt = null;
		PreparedStatement countStmt = null;
		PreparedStatement countStmt2 = null;
		PreparedStatement organizationStmt = null;

		Connection connection = null;
		

		try {
			connection = DBConnection.getconnectionToDatabase();
			String countAdmin = "select count(ID) as CountTotal from risa_hr.admin";
			String countOrganization = "select count(ID) as CountTotal from risa_hr.organization";
			String adminQuery = "insert into admin values(?, ?, ?, ?, ?, ?, ?, ?)"; 
			String prganizationQuery = "insert into risa_hr.organization values(?,?)";

			
			countStmt = connection.prepareStatement(countAdmin);
			ResultSet countSet = countStmt.executeQuery();
			countSet.next();
			int adminID = countSet.getInt("CountTotal") + 1;

			countStmt2 = connection.prepareStatement(countOrganization);
			ResultSet countSet2 = countStmt2.executeQuery();
			countSet2.next();
			int organizationID = countSet2.getInt("CountTotal") + 1;

			adminStmt = connection.prepareStatement(adminQuery);
			adminStmt.setInt(1, adminID);
			adminStmt.setString(2, code);
			adminStmt.setString(3, firstName);
			adminStmt.setString(4, lastName);
			adminStmt.setInt(5, 0);
			adminStmt.setInt(6, 0);
			adminStmt.setString(7, "N/A");
			adminStmt.setInt(8, statusID);
			adminStmt.executeUpdate();

			organizationStmt = connection.prepareStatement(prganizationQuery);
			organizationStmt.setInt(1, organizationID);
			organizationStmt.setString(2, "N/A");
			organizationStmt.executeUpdate();
			
			successful = true;


		} catch (SQLException e) {

		} finally {
			
			if (adminStmt != null) {
				adminStmt.close();
			}
			if (countStmt != null) {
				countStmt.close();
			}
			if (countStmt2 != null) {
				countStmt2.close();
			}
			if (organizationStmt != null) {
				organizationStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
			
