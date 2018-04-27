<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DatabaseDao.DBConnection" import="java.sql.*" import="Register.ForgotPasswordClass"%>
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

<form class="login-form" method="post" action="updatePassword">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
  	
    <label for="uname"><b>Risa Code: ${rcode}</b></label><br><br>
	<div style="color: #FF0000;">${errorPsw}</div>
	
	<label for="uname" ><b>Enter New Password: </b></label><br>
    <input type="password" name = "psw" id="psw" placeholder="Enter new password"  required>
    
     <label for="uname" ><b>Re-enter password: </b></label><br>
    <input type ="password" name ="psw2" id="psw2" placeholder="Re-enter new password"  required>
	
	
    <button type="submit" >Submit</button>
  </div>
  <div class="container">
  	<center><span class="message">Already Registered? <a href="index.jsp">Login</a></span></center>
  </div>
  </form> 
  
 
  </div>
  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js">
  </script>

</body>