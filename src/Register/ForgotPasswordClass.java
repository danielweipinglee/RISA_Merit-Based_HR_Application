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
		
		public String isAdminorStudent(String risacode) {//checks if is an admin or student from risacode
			String AdminorStudent = null;
			Connection conn = null;
			try {
				String sql = "SELECT student.RISACode FROM risa_hr.student WHERE student.RISACode= ?;";
				conn = DBConnection.getconnectionToDatabase();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, risacode);
				
			    ResultSet resultset = statement.executeQuery();
			    while ( resultset.next() )
			    {
			    	AdminorStudent =  resultset.getString("risacode");
			    }
			    resultset.close();
			    statement.close();
			    
			    if(AdminorStudent != null) {//student
			    	return "student";
			    }else {
					sql = "SELECT admin.RISACode FROM risa_hr.admin WHERE admin.RISACode = ?;";
					conn = DBConnection.getconnectionToDatabase();
					PreparedStatement statement2 = conn.prepareStatement(sql);
					statement2.setString(1, risacode);
					
				    ResultSet resultset2 = statement2.executeQuery();
				    while ( resultset2.next() )
				    {
				    	AdminorStudent =  resultset2.getString("risacode");
				    }
				    resultset2.close();
				    statement2.close();
			    }
			    if(AdminorStudent != null) {//admin
			    	return "admin";
			    }
			}catch (SQLException e){
				
					e.printStackTrace();
			} 
			return "";
		}
		
		public String getSecurityQuestion(String risacode) {//gets security question from risa code
			String question = null;
			Connection conn = null;
		    try{//for the security question for student or admin 
		    	String sql = "Select SecurityQuestion from risa_hr.securityquestion \r\n" + 
		    			"		        		join risa_hr.userpassword on securityquestion.ID = userpassword.SecurityQuestion_ID \r\n" + 
		    			"		        		join risa_hr.student on student.UserPassword_ID = userpassword.ID and student.RISACode = ?\r\n" + 
		    			"                        union\r\n" + 
		    			"                        Select SecurityQuestion from risa_hr.securityquestion \r\n" + 
		    			"		        		join risa_hr.userpassword on securityquestion.ID = userpassword.SecurityQuestion_ID \r\n" + 
		    			"		        		join risa_hr.admin on admin.UserPassword_ID = userpassword.ID and admin.RISACode = ?;";
		    	conn = DBConnection.getconnectionToDatabase();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, risacode);
				statement.setString(2, risacode);
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
		
		public boolean doesAnswerMatch(String risacode, String ans) {//see if ans matches one in db
			Connection conn = null;
			boolean match = false;
			String found = null;
			try {
				String sql = "SELECT SecurityAnswer FROM risa_hr.userpassword,risa_hr.student "+
						"where student.UserPassword_ID = UserPassword.ID " + 
						"and SecurityAnswer = ? and RISACode = ? " + 
						"union all " + 
						"SELECT SecurityAnswer FROM risa_hr.userpassword,risa_hr.admin where admin.UserPassword_ID = UserPassword.ID " + 
						"and SecurityAnswer = ? and RISACode = ?;";
				conn = DBConnection.getconnectionToDatabase();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, ans);
				statement.setString(2, risacode);
				statement.setString(3, ans);
				statement.setString(4, risacode);
				
			    ResultSet resultset = statement.executeQuery();
			    while ( resultset.next() )
			    {
			      found =  resultset.getString("SecurityAnswer");
			    }
			    resultset.close();
			    statement.close();

			}catch (SQLException e){
				
					e.printStackTrace();
			}
			
			if(found != null) {
				match = true;
			}
			return match;
		}
		
		public void newPassword(String risacode,String psw, String AdminorStudent) throws SQLException{//updates password
			Connection conn = null;
			PreparedStatement Stmt = null;
			String query = null;
			try {
				if(AdminorStudent.equals("")) {//figure out which table to update
					return;//error 
				}
				else if(AdminorStudent.equals("student")) {
					query = "update risa_hr.userpassword,risa_hr.student set UserPassword = ? "
							+ "where userpassword.ID = student.UserPassword_ID and student.RISACode = ?;";
				}
				else if(AdminorStudent.equals("admin")) {
					query = "update risa_hr.userpassword,risa_hr.admin set UserPassword = ? " + 
							"where userpassword.ID = admin.UserPassword_ID and admin.RISACode = ?;";
				}
				conn = DBConnection.getconnectionToDatabase();
				
				Stmt = conn.prepareStatement(query);
				Stmt.setString(1, psw);
				Stmt.setString(2, risacode);
				Stmt.executeUpdate();
				
				Stmt.close();
				success = true;
				
			}catch (SQLException e) {
				
			}
		}
		
	}