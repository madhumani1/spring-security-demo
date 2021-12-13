<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My SYSTEMS Home Page</title>
<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Welcome All you System/Admin users!!!</h2>
		</div>
		<br />
		<p>
			We have our annual holiday Caribbean cruise coming up. Register now!
			<br>
			Keep this trip a secret, don't tell the regular employees :-)
		</p>
	</div>
	<hr>
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	
	<br>
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
	<br>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
</body>
</html>