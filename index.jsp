<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<form class="login-form" onsubmit="return validate()" method="post" action="mainpage.jsp">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter username" name="uname" id="text1" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter password" name="psw" id="text2" required>
    
    <script type="text/javascript">
		function validate()
		{
		    if(   document.getElementById("text1").value == "a"
		       && document.getElementById("text2").value == "a" )
		    {
		        return true;
		    }
		    else
		    {
		        alert( "Please check your username and password are correct." );
		        return false;
		    }
		}
	</script>
    <button type="submit" >Login</button> 
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>
  <div class="container">
  	<center><span class="message">Not Registered? <a href="#">Register</a></span><br/><br/>
  	<span class="forgetID">Forgot <a href="#">ID or Password?</a></span></center>
  </div>
  </form>
  
  
  <form class="register-form" method="post">
  <div class="imgcontainer">
    <img src="images/Black.png" alt="pic" class="pic">
  </div>
  <div class="container">
  	<label for="uname"><b>First Name</b></label>
    <input type="text" placeholder="Enter first name" name="uname" id="text1" required>
    
    <label for="uname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter last name" name="uname" id="text1" required>
    
    <label for="uname"><b>Email</b></label>
    <input type="text" placeholder="Enter email address" name="uname" id="text1" required>
    
 	<label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter username" name="uname" id="text1" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter password" name="psw" id="text2" required>
  	
  	<label for="psw"><b>Re-enter Password</b></label>
    <input type="password" placeholder="Re-enter password" name="psw" id="text2" required>
  	
  	<button type="submit" >Register</button>
  	
  	<div class="container">
  	</br></br><center><span class="message">Already Registered? <a href="#">Login</a></span></center>
  	</div>
  
  </div>
  </form>
 
  </div>
  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js">
  </script>
  <script>
  $('.message a').click(function(){	
	  $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
  });
  
  </script>

</body>


</body>
</html>