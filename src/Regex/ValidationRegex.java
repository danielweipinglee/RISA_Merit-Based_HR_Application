package Regex;
import java.util.regex.*;  

public class ValidationRegex {
	
	private Pattern password;
	private Pattern email;
	private Pattern employerEmail;
	private Pattern year;
	
	public ValidationRegex() {
		/* (?=.{10}) Implies that the password has to be at least 10 char.
		 * (?=.*\\p{Upper}) Implies that the password has to at least have 1 Uppercase char.
		 * (?=.*\\p{Lower}) Implies that the password has to at least have 1 Lowercase char.
		 * (?=.*\\d) Implies that the password has to at least have 1 number.
		 * \\w+ Implies that the string can have any characters that are in [a-zA-Z_0-9].
		 * @(ttu)\\.(edu) Implies that the end of the string has to be @ttu.edu
		 */
		this.password = Pattern.compile("^(?=.{10})(?=.*\\p{Upper})(?=.*\\p{Lower})(?=.*\\d)\\w+");
		this.email = Pattern.compile("^\\w+\\.?\\w+@(ttu)\\.(edu)");
		this.employerEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		this.year = Pattern.compile("^[12][0-9]{3}$");//checks years 1000 to 2999
	}
	
	// password
	public boolean isValidPassword(String UserPassword) {
		return password.matcher(UserPassword).matches(); 
	}
	// email
	public boolean isValidEmail(String Useremail) {
		return email.matcher(Useremail).matches();
	}
	// non ttu email
	public boolean isValidEmployerEmail(String Useremail) {
		return employerEmail.matcher(Useremail).matches();
	}
	public boolean isYear(String yearEntered) {
		return year.matcher(yearEntered).matches();
	}
}
