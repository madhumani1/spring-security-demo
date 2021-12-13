<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company home page</title>
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
			<h2>Welcome to Company Home page</h2>
		</div>
		<br />
		<security:authorize access="hasRole('MANAGER')">
			<!-- Add a link to point to /leaders ... this is for the managers -->
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
				(Only for Leadership groups)
			</p>
		</security:authorize>
		<br />
		<security:authorize access="hasRole('ADMIN')">
			<!-- Add a link to point to /leaders ... this is for the managers -->
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT SYSTEMS Meeting</a>
				(Only for Admin groups)
			</p>
		</security:authorize>
		<br />
		<p>Hola! I'm Dora, and this is my best friend boots.</p>
	</div>
	<hr>
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
</body>
</html>