<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DatabaseDao.DBConnection" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RISA</title>
<link rel="stylesheet" type="text/css" href="css/active_CEO.css">
</head>
<body>
<header>
	<div class="container">
    <img src="images/White.png" alt="pic" class="pic" >
    <nav>
    	<ul>
    		<li><a href="Employer_View.jsp">View</a></li>
    		<li><a href="Employer_Sort.jsp">Sort</a></li>
    		<li><a href="Employer_Search.jsp">Search</a></li>
    	</ul>
    	
    </nav>
	</div>		
	
</header>
	<form method="get" action="Employer_Search">	
		<%
    try{//for the dropdown menu
    	Connection conn = DBConnection.getconnectionToDatabase();
        Statement statement = conn.createStatement() ;
        ResultSet resultset =statement.executeQuery("select Major from major;") ;
	%>
	<br>
    Alphabetical: <select name="search" onchange="this.form.submit()">
    <%  while(resultset.next()){ %>
            <option><%= resultset.getString("Major")%></option>
    <% } %>
    </select>
	<br>
	<%
       }catch(Exception e){
    	   
          out.println(e);
       }
	%>	
	</form>

</body>
</html>