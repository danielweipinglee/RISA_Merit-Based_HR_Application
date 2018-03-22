package Regex;
import java.util.regex.*;  

public class ValidationRegex {
	
	private Pattern password;
	private Pattern email;
	
	public ValidationRegex() {
		this.password = Pattern.compile("^(?=.{10})(?=.*\\p{Upper})(?=.*\\p{Lower})(?=.*\\d)\\w+");
		this.email = Pattern.compile("^\\w+@(ttu)\\.(edu)");
	}
	
	// password
	public boolean isValidPassword(String UserPassword) {
		return password.matcher(UserPassword).matches(); 
	}
	// email
	public boolean isValidEmail(String Useremail) {
		return email.matcher(Useremail).matches();
	}
}
