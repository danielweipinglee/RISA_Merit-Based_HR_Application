package Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import DatabaseDao.DBConnection;

public class ForgotPasswordClass {
		private boolean success;

		public ForgotPasswordClass(){
			success = false;
		};
		
		public boolean isSuccess() {
			return success;
		}
		
		public String doesRisaCodeExist(String risacode) {//see if risacode exists in student or admin tables
			String code = null;
			Connection conn = null;
			try {
				String sql = "SELECT student.RISACode FROM risa_hr.student WHERE student.RISACode= ? UNION ALL " + 
						"SELECT admin.RISACode FROM risa_hr.admin WHERE admin.RISACode = ?;";
				conn = DBConnection.getconnectionToDatabase();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, risacode);
				statement.setString(2, risacode);
				
			    ResultSet resultset = statement.executeQuery();
			    while ( resultset.next() )
			    {
			      code =  resultset.getString("risacode");
			    }
			    resultset.close();
			    statement.close();

			}catch (SQLException e){
				
					e.printStackTrace();
			} 
			return code;
		}
		
		public String getSecurityQuestion(String risacode) {//gets security question from risa code
			String question = null;
			Connection conn = null;
		    try{//for the security question
		    	String sql = "Select SecurityQuestion from risa_hr.securityquestion " +
		        		"join risa_hr.userpassword on securityquestion.ID = userpassword.SecurityQuestion_ID " +
		        		"join risa_hr.student on student.UserPassword_ID = userpassword.ID and student.RISACode = ?; ";
		    	conn = DBConnection.getconnectionToDatabase();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, risacode);
				ResultSet resultset = statement.executeQuery();
			    while ( resultset.next() )
			    {
			      question =  resultset.getString("SecurityQuestion");;
			    }
		    resultset.close();
		    statement.close();
			}catch (Exception e){
				
					e.printStackTrace();
			} 
			return question;
		}
		
	}