<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DatabaseDao.DBConnection" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To RISA</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<body background="images/background.png">
<div class="container-main"> 
  <form class="login-form" method="post" action="LoginValidation">
  
  <div class="container-main">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
	<label><b>The information you input is invalid. Email should be ttu email. Password needs to be at least 8 characters with one
	lower and uppercase letter with one number. </b></label><br>
	
  	<label for="uname"><b>First Name</b></label>
    <input type="text" placeholder="Enter first name" name="fname" id="fname" required>
    
    <label for="uname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter last name" name="lname" id="lname" required>
    
    <label for="uname"><b>Email</b></label>
    <input type="text" placeholder="Enter email address" name="email" id="email" required>
    
 	<label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter username" name="username" id="username" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter password" name="password" id="password" required>
  	
  	<label for="psw"><b>Re-enter Password</b></label>
    <input type="password" placeholder="Re-enter password" name="password2" id="password2" required>
    
    <label for="psw"><b>Risa Unique Code</b></label>
    <input type="text" placeholder="Unique code" name="risacode" id="risacode" required>
    
    <label for="psw"><b>Security question</b></label>
    
    </b><br>
	<%
    try{//for the dropdown menu
    	Connection conn = DBConnection.getconnectionToDatabase();
        Statement statement = conn.createStatement() ;
        ResultSet resultset =statement.executeQuery("select SecurityQuestion from securityquestion;") ;
	%>
	<br>
    <select name="securityquestion">
    <%  while(resultset.next()){ %>
            <option><%= resultset.getString("securityquestion")%></option>
    <% } %>
    </select>
	<br>
	<%
       }catch(Exception e){
          out.println(e);
       }
	%>
	<br>
	<label for="psw"><b>Security question answer</b></label>
  	<input type="text" placeholder="Security question answer" name="answer" id="answer" required>
  	
  	<button type="submit" >Register</button>
  	
  	<div class="container">
  	</br></br><center><span class="message">Already Registered? <a href="#">Login</a></span></center>
  	</div>
  
  </div>
  </form>
 
  </div>
  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js">
  </script>
  <script>
  $('.message a').click(function(){	
	  $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
  });
  
  </script>

</body>