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
    		<li><a href="HR_View.jsp">View</a></li>
    		<li><a href="HR_Update.jsp">Update</a></li>
    		<li><a href="HR_Add.jsp">Add Student</a></li>
    		<li><a href="HR_Add_Administrative.jsp"> Create Account</a></li>
    	</ul>
    </nav>
	</div>		
</header>
<h2>Select a category to sort the student file</h2>
<form>
<div class="optionsDiv">
        <select id="selectField" name ="selectField">
        	<option>select a category</option>
            <option value="RISACode" >RISA Code</option>
            <option value="FieldOfInterest" >Field Of Interest</option>
            <option value="Degree" >Degree</option>
            <option value="GraduateYear" >Grduate Year</option>
        </select>   
        <button type="submit" class="w3-button w3-red">Sort</button> 
</div>
</form>

<jsp:include page="CEO_View"/>

</body>
</html>