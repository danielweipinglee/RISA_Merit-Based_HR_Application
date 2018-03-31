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
    		<li><a href="CEO_View.jsp">View</a></li>
    		<li><a href="CEO_Update.jsp">Update</a></li>
    		<li><a href="CEO_Add.jsp">Add Student</a></li>
    		<li><a href="CEO_Add_Employer.jsp">Add Employer</a></li>
    	</ul>
    </nav>
	</div>		
	
</header>

<div class="w3-container">
  <h2>Add HR</h2>
  
  <div class="w3-card-4">
    <div class="w3-container w3-red">
      <h3>HR Information</h3>
    </div>

    <form class="w3-container" method="post" action="CEOAdd">
      <p>
      <input class="w3-input" type="text"name="legalFirstName" id="HRlegalFirstName" required>
      <label>HR First Name</label></p>
      <p>     
      <input class="w3-input" type="text" name="legalLastName" id="HRlegalLastName" required>
      <label>HR Last Name</label></p>
      <p>     
      <input class="w3-input" type="text" name="RISACode" id="HRRISACode" required>
      <label>HR RIS Code</label></p>
      
      <button type="submit" class="w3-button w3-red">Add HR</button>
    </form>
  </div>
</div>


</body>
</html>