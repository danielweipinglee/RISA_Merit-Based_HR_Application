<%@page import="adminpackage.CEO_View"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RISA</title>
<link rel="stylesheet" type="text/css" href="css/active_CEO.css">

<script language="javascript">
            function addRow(tableID) {
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                var row = table.insertRow(rowCount); 
                var cell0 = row.insertCell(0);
                var element1 = document.createElement("input");
                element1.type = "text";
                element1.name = "line"+(rowCount+1);
                element1.value=""+(rowCount+1);
                cell0.appendChild(element1);
                document.getElementById("countofrows").value=table.rows.length;
            }
        </script>



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
    	</ul>
    </nav>
	</div>		
</header>

<form method="get" action="CEO_View">
<div class="optionsDiv" >
        Sort 
        <select id="selectField" onchange="this.form.submit()" >
        	<option value="None" selected>None</option>
            <option value="Alphabetical">Alphabetical</option>
        </select>   
</div>

<div class="SearchOptionsDiv" >
        Search
        <select id="search" name="search" onchange="this.form.submit()">
            <option value="none" selected>None</option>
            <option value="Active_CEO">Active CEO</option>
            <option value="Active">Active</option>
            <option value="Deleted">Deleted</option>
            <option value="Admin">Admin</option>
            <option value="Active_HR">Active HR</option>
            <option value="Active_Employer">Active Employer</option>
        </select>   
</div>
</form>

<jsp:include page="CEO_View"/>

</body>
</html>
