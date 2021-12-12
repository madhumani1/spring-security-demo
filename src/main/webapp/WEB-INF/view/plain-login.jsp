<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Form</title>
<style>
	.failed {
		color: red;
	}
	
	function reportError()	{
		var user = document.getElementsByName("username")[0].tagName;
		console.log("user: "+user);
		alert("The form was submitted");
	}
</style>
</head>
<body>
	<h3>My Custom Login Page</h3>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser"
			   method="POST" onsubmit="reportError();">
	
		<!-- Check for login error -->
		<c:if test="${param.error != null}">
			<i class="failed">Sorry! You entered invalid username/password.</i>
		</c:if>
		
		<c:if test="${param.logout != null}">
			<i class="failed">You have been logged out!</i>
		</c:if>
			
		<p>
			User name: <input type="text" name="username" />
		</p>

		<p>
			Password: <input type="password" name="password" />
		</p>
		
		<input type="submit" value="Login"/>
		
	</form:form>
</body>
</html>