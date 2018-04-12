<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DatabaseDao.DBConnection" import="java.sql.*" import="Register.ForgotPasswordClass"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To RISA</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<body background="images/background.png">
<div class="container-main">

<form class="login-form">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
    <label for="uname"><b>Risa Code: <%= request.getParameter("risacode") %></b></label><br>
    <label><b>Security Question: </b></label>
    <br>
	<%
	String code = null;
	String risacode = request.getParameter("risacode");
	ForgotPasswordClass forgotPassword = new ForgotPasswordClass();
	code = forgotPassword.getSecurityQuestion(risacode);
	%>
		<br><label name="securityquestion"><%= code %></label><br>

	<br>
    <input type="password" placeholder="Enter Answer Here"  required>
	
    <button type="submit" >Submit</button>
  </div>
  <div class="container">
  	<center><span class="message">Already Registered? <a href="index.jsp">Login</a></span></center>
  </div>
  </form> 
  
  <form class="register-form">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">

  	<center><label for="uname"><b>Please Contact Alvin Kim</b></label></center>
  	<center><label for="uname"><b>alvin.kim@ttu.edu</b></label></center>
  	
  	<div class="container">
  	<center><span class="message">Already Registered? <a href="index.jsp">Login</a></span></center>
  	</div>
  
  </div>
  </form>
 
  </div>
  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js">
  </script>
  <script>
  $(function() { 
	    $('form').on('submit', function(e) { 
	        e.preventDefault();  
	        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	    });
	});
  
  </script>

</body>