<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RISA</title>
<link rel="stylesheet" type="text/css" href="css/active_CEO.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<header>
	<div class="container">
    <img src="images/White.png" alt="pic" class="pic" >
    <nav>
    	<ul>
    		<li><a href="Employer_View.jsp">View</a></li>
    	</ul>
    </nav>
	</div>		
	
</header>
<div class="w3-container">
  <h2>Welcome to RISA Merit-Based HR Application</h2>
  
  <div class="w3-card-4">
    <div class="w3-container w3-red">
    <h1>You are Employer now, How are you doing?</h1>  
    Today's date: <%= (new java.util.Date()).toLocaleString()%> 
    
  </div>
</div>
</body>
</html>