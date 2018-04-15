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

<form class="login-form" method="get" action="ForgotPassword">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
<div style="color: #FF0000;">${errorMessage}</div>

    <label for="risacode"><b>Risa Code</b></label>
    <input type="text" placeholder="Enter Risa Code" name="risacode" id="risacode" required>
	<button type="submit">Search</button>
</div>
  	<div class="container">
  	<center><span class="message">Already Registered? <a href="index.jsp">Login</a></span></center>
  	</div>
  </form>
   
  </div>

</body>