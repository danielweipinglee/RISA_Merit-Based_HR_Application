<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DatabaseDao.DBConnection" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RISA</title>
<link rel="stylesheet" type="text/css" href="css/active_CEO.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="index.jsp" style="color: white; text-decoration: none; float: right; 
padding-top: 15px; font-weight: bold; padding-right: 20px;">Log Out</a>
<header>
	<div class="container">
    <img src="images/White.png" alt="pic" class="pic" >
    <nav>
    	<ul>
    		<li><a href="HR_View.jsp">View</a></li>
    		<li><a href="HR_Update.jsp">Update</a></li>
    		<li><a href="HR_Add_Administrative.jsp">Create Account</a></li>
    	</ul>
    </nav>
	</div>		
</header>

<div class="w3-container">
  <h2>HR Account</h2>
  
  <div class="w3-card-4">
    <div class="w3-container w3-red">
      <h3>Create Student Account</h3>
    </div>

    <form class="w3-container" method="post" action="CEO_Add">
        	<div style="color: #FF0000;">${errorCreate}</div><br>
      <p>
      <input class="w3-input" type="text"name="legalFirstName" id="legalFirstName" required>
      <label>First Name</label></p>
      <p>     
      <input class="w3-input" type="text" name="legalLastName" id="legalLastName" required>
      <label>Last Name</label></p>
      <p>     
      <input class="w3-input" type="text" name="risaCode" id="RISACode" required>
      <label>RISA Code</label></p>
      
      <button type="submit" class="w3-button w3-red">Add Student</button>
    </form>
  </div>
</div>

</body>
</html>