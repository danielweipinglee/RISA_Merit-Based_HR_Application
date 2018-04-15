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
  <form class="login-form" method="post" action="RegisterEmployerServlet">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
	<div style="color: #FF0000;">${EmployerError}</div><br>
  	<label for="psw"><b>Risa Unique Code</b></label>
    <input type="text" placeholder="Unique code" name="risacode" id="risacode" value="<%=request.getParameter("risacode")!=null?request.getParameter("risacode"):""%>" required>
    
	<label for="psw"><b>Select Role</b></label><br/>
	<select name="role" id="role" name="role">
	<option value="Active_Employer">Active_Employer</option>
	</select><br/><br/>
	
    <label for="uname"><b>First Name</b></label>
    <input type="text" placeholder="Enter first name" name="fname" id="fname" value="<%=request.getParameter("fname")!=null?request.getParameter("fname"):""%>" required> 
    
    <label for="uname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter last name" name="lname" id="lname" value="<%=request.getParameter("lname")!=null?request.getParameter("lname"):""%>" required>
    
    <label for="uname"><b>Email</b></label>
    <input type="text" placeholder="Enter email address" name="email" id="email" value="<%=request.getParameter("email")!=null?request.getParameter("email"):""%>" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter password" name="password" id="password" required>
  	
  	<label for="psw"><b>Re-enter Password</b></label>
    <input type="password" placeholder="Re-enter password" name="password2" id="password2" required>
    
    <label for="psw"><b>Security question</b></label>
    
    </b><br>
	<%
    try{//for the dropdown menu
    	Connection conn = DBConnection.getconnectionToDatabase();
        Statement statement = conn.createStatement() ;
        ResultSet resultset =statement.executeQuery("select SecurityQuestion from securityquestion;") ;
	%>
	<br>
    <select name="securityquestion">
    <%  while(resultset.next()){ %>
            <option><%= resultset.getString("securityquestion")%></option>
    <% } %>
    </select>
	<br>
	<%
       }catch(Exception e){
          out.println(e);
       }
	%>
	<br>
	<label for="psw"><b>Security question answer</b></label>
  	<input type="text" placeholder="Security question answer" name="answer" id="answer" required>
  	
  	<label for="psw"><b>Enter organization</b></label>
  	<input type="text" placeholder="Enter organization" name="organization" id="organization" value="<%=request.getParameter("organization")!=null?request.getParameter("organization"):""%>" required>
  	
  	<button type="submit" >Register</button>
  	
  	<div class="container">
  	</br></br><center><span class="message">Already Registered? <a href="index.jsp">Login</a></span></center>
  	</div>
  
  </div>
  </form>
 
  </div>
 

</body>