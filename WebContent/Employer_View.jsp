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
    		<li><a href="Employer_View.jsp">View</a></li>
    	</ul>
    </nav>
	</div>		
	
</header>
<div style="float:right; float:top; padding-right:10px; padding-top:15px" class="optionsDiv">
<form action="Employer_Search" method="get">
  <%try{//for the dropdown menu
    	Connection conn = DBConnection.getconnectionToDatabase();
        Statement statement = conn.createStatement() ;
        ResultSet resultset =statement.executeQuery("select DISTINCT Major from major;");     
	%>
	<br>
    <select name="search" id="search">
    <%  while(resultset.next()){ %>
            <option><%= resultset.getString("Major")%></option>
    <% } %>
    </select>
	<%
       }catch(Exception e){   
          out.println(e);
       }
	%>	
   <button type="submit" class="w3-button w3-red">Search</button> </form></div>
<div style="float:right; float:top; padding-right:10px; padding-top:15px" class="optionsDiv">
<form action="Employer_Sort" method="get">
    <%try{//for the dropdown menu
    	Connection conn = DBConnection.getconnectionToDatabase();
        Statement statement = conn.createStatement() ;
        ResultSet resultset =statement.executeQuery("select DISTINCT Major from major;");     
	%>
	<br>
    <select name="sort" id="sort">
    <%  while(resultset.next()){ %>
            <option><%= resultset.getString("Major")%></option>
    <% } %>
    </select>
	<%
       }catch(Exception e){   
          out.println(e);
       }
	%>	
<button type="submit" class="w3-button w3-red">Sort</button> 
</form>
</div>
<h2>Select a category to search the student file</h2>
<jsp:include page="Employer_View"/>
</body>
</html>