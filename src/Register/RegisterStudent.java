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
			String answer,String risacode,String risaposition, String squestion, int squestionID, String phone, 
			String interest, String college, String degree, String concentration, String year, String month, String major) throws SQLException{
		
		PreparedStatement userpasswordCountStmt = null;
		PreparedStatement risapositionCountStmt = null;
		PreparedStatement studentcollegeCountStmt = null;
		PreparedStatement concentrationCountStmt = null;
		PreparedStatement collegeCountStmt = null;
		PreparedStatement majorCountStmt = null;
		PreparedStatement studentIdStmt = null;
		PreparedStatement concentrationStmt = null;
		PreparedStatement studentcollegeStmt = null;
		PreparedStatement collegeStmt = null;
		PreparedStatement majorStmt = null;
		PreparedStatement collegeStmt2 = null;
		PreparedStatement concentrationStmt2 = null;
		PreparedStatement studentcollegeStmt2 = null;
		PreparedStatement userpasswordStmt = null;
		PreparedStatement risapositionStmt = null;
		PreparedStatement userpasswordStmt2 = null;
		PreparedStatement risapositionStmt2 = null;
		PreparedStatement studentStmt = null;
		Connection conn = null;

		
		try {
			String userpasswordIdQuery = "select count(ID) as CountTotal from risa_hr.userpassword";
			String risapositionIdQuery = "select count(ID) as CountTotal from risa_hr.risaposition";
			String studentcollegeIdQuery = "select count(ID) as CountTotal from risa_hr.studentcollege";
			String collegeIdQuery = "select count(ID) as CountTotal from risa_hr.college";
			String majorIdQuery = "select count(ID) as CountTotal from risa_hr.major";
			String concentrationQuery = "select count(ID) as CountTotal from risa_hr.concentration";
			String studentIdQuery = "select ID from risa_hr.student where RISACode = ?";
			

			String insertUserpassword = "insert into risa_hr.userpassword values(?,?,?,?);";
			String insertRisaposition = "insert into risa_hr.risaposition values(?,?);";
			String insertConcentration = "insert into risa_hr.concentration values(?,?,?);";
			String insertStudentCollege = "insert into risa_hr.studentcollege values(?,?,?,?,?,?,?,?);";
			String insertCollege = "insert into risa_hr.college values(?,?);";
			String insertMajor = "insert into risa_hr.major values(?,?,?);";
			
			String updateStudent = "update risa_hr.student set Email = ?, Username = ?, Phone = ?, FieldofInterest = ?, UserPassword_ID =?, RISAPosition_ID = ?"
					+ " where RISACode = ?";
			String updateUserpassword = "update risa_hr.userpassword set UserPassword = ?, SecurityAnswer = ?, SecurityQuestion_ID =?"
					+ " where userpassword.ID = ?;";
			String updateRisaposition = "update risa_hr.risaposition set Position = ? where ID = ?;";
			String updateStudentCollege = "update risa_hr.studentcollege set College_ID = ?, Major_ID = ?, DegreeLevel_ID = ?, Concentration_ID = ?, "
					+ "GradMonth = ?, GradYear = ? where ID = ?;";
			String updateConcentration = "update risa_hr.concentration set Concentration = ?, Major_ID = ? where ID = ?;";
			String updateCollege = "update risa_hr.college set CollegeName = ? where ID = ?;";
			 
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
			
			studentcollegeCountStmt = conn.prepareStatement(studentcollegeIdQuery);
			ResultSet countSet3 = studentcollegeCountStmt.executeQuery();
			countSet3.next();
			int studentcollegeId = countSet3.getInt("CountTotal") + 1;
			
			collegeCountStmt = conn.prepareStatement(collegeIdQuery);
			ResultSet countSet4 = collegeCountStmt.executeQuery();
			countSet4.next();
			int collegeId = countSet4.getInt("CountTotal") + 1;
			
			concentrationCountStmt = conn.prepareStatement(concentrationQuery);
			ResultSet countSet5 = concentrationCountStmt.executeQuery();
			countSet5.next();
			int concentrationId = countSet5.getInt("CountTotal") + 1;
			
			majorCountStmt = conn.prepareStatement(majorIdQuery);
			ResultSet countSet6 = majorCountStmt.executeQuery();
			countSet6.next();
			int majorId = countSet6.getInt("CountTotal") + 1;
			
			studentIdStmt = conn.prepareStatement(studentIdQuery);
			studentIdStmt.setString(1, risacode);
			ResultSet rs = studentIdStmt.executeQuery();
			rs.next();
			int studentId = rs.getInt("ID");
			
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
			
			concentrationStmt = conn.prepareStatement(insertConcentration);
			concentrationStmt.setInt(1, concentrationId);
			concentrationStmt.setString(2, "N/A");
			concentrationStmt.setInt(3, 0);
			concentrationStmt.executeUpdate();
			
			studentcollegeStmt = conn.prepareStatement(insertStudentCollege);
			studentcollegeStmt.setInt(1, studentcollegeId);
			studentcollegeStmt.setInt(2, studentId);
			studentcollegeStmt.setInt(3, 0);
			studentcollegeStmt.setInt(4, 0);
			studentcollegeStmt.setInt(5, 0);
			studentcollegeStmt.setInt(6, 0);
			studentcollegeStmt.setInt(7, 0);
			studentcollegeStmt.setInt(8, 0);
			studentcollegeStmt.executeUpdate();
			
			collegeStmt = conn.prepareStatement(insertCollege);
			collegeStmt.setInt(1, collegeId);
			collegeStmt.setString(2,"N/A");
			collegeStmt.executeUpdate();
			
			majorStmt = conn.prepareStatement(insertMajor);
			majorStmt.setInt(1, majorId);
			majorStmt.setString(2,major);
			majorStmt.setInt(3,collegeId);
			majorStmt.executeUpdate();

			//updates
			
			studentStmt = conn.prepareStatement(updateStudent);
			studentStmt.setString(1, email);
			studentStmt.setString(2, username);		
			studentStmt.setString(3, phone);
			studentStmt.setString(4, interest);
			studentStmt.setInt(5, userpasswordId);
			studentStmt.setInt(6, risapositionId);
			studentStmt.setString(7, risacode);
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
			
			concentrationStmt2 = conn.prepareStatement(updateConcentration);
			concentrationStmt2.setString(1, concentration);
			concentrationStmt2.setInt(2, majorId);
			concentrationStmt2.setInt(3, concentrationId);
			concentrationStmt2.executeUpdate();
			
			studentcollegeStmt2 = conn.prepareStatement(updateStudentCollege);
			studentcollegeStmt2.setInt(1, collegeId);
			studentcollegeStmt2.setInt(2, majorId);
			studentcollegeStmt2.setString(3, degree);
			studentcollegeStmt2.setInt(4, concentrationId);
			studentcollegeStmt2.setString(5, month);
			studentcollegeStmt2.setString(6, year);
			studentcollegeStmt2.setInt(7, studentcollegeId);
			studentcollegeStmt2.executeUpdate();
			
			collegeStmt2 = conn.prepareStatement(updateCollege);
			collegeStmt2.setString(1, college);
			collegeStmt2.setInt(2,collegeId);
			collegeStmt2.executeUpdate();
		
			//close
			majorStmt.close();
			majorCountStmt.close();
			collegeStmt.close();
			collegeStmt2.close();
			collegeCountStmt.close();
			studentcollegeStmt2.close();
			concentrationStmt2.close();
			concentrationStmt.close();
			concentrationCountStmt.close();
			studentIdStmt.close();
			studentcollegeCountStmt.close();
			studentcollegeStmt.close();
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