<%@page import="adminpackage.CEO_View"%>
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
<a href="index.jsp" style="color: white; text-decoration: none; float: right; 
padding-top: 15px; font-weight: bold; padding-right: 20px;">Log Out</a>
<header>
	<div class="container">
    <img src="images/White.png" alt="pic" class="pic" >
    <nav>
    	<ul>
    		<li><a href="CEO_View.jsp">View </a></li>
    		<li><a href="CEO_Update.jsp">Update </a></li>
    		<li><a href="CEO_Add.jsp">Add Student </a></li>
    		<li><a href="CEO_Add_Employer.jsp">Add Employer</a></li>
    	</ul>
    </nav>
	</div>		
	
</header>


<div style="float:right; float:top; padding-right:100px; padding-top:15px" class="optionsDiv">
<form action="CEO_Search" method="get">
         <select id="search" name ="search">
        	<option value="None">select a category</option>
            <option value="Active_CEO" >Active CEO</option>
            <option value="Active" >Active</option>
            <option value="Deleted" >Deleted</option>
            <option value="admin" >admin</option>
            <option value="Active_HR" >Active HR</option>
            <option value="Active_Employer" >Active Employer</option>
        </select>   
        <button type="submit" class="w3-button w3-red">Search</button> 
</form></div>
<div style="float:right; float:top; padding-right:100px; padding-top:15px" class="optionsDiv">
<form action="CEO_Sort" method="get">
        <select id="sort" name ="sort">
        	<option value="None">select a category</option>
            <option value="Active_CEO" >Active CEO</option>
            <option value="Active" >Active</option>
            <option value="Deleted" >Deleted</option>
            <option value="admin" >admin</option>
            <option value="Active_HR" >Active HR</option>
            <option value="Active_Employer" >Active Employer</option>
        </select>   
        <button type="submit" class="w3-button w3-red">Sort</button> 
</form>
</div>
<h2>Select a category to search the student file</h2>


<jsp:include page="CEO_View"/>


</body>
</html>