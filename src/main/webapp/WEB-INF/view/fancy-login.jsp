<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fancy Login Form</title>
<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h3>My Custom Login Page</h3>
	<div>
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			<div class="panel panel-info">	
				
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>
				
				<div style="padding-top: 30px" class="panel-body">
					<!-- Login Form -->
					<form action="${pageContext.request.contextPath}/authenticateTheUser" 
							   method="POST" class="form-horizontal">
						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>
									<!-- Check for login error -->
									<c:if test="${param.error != null}">
										<i class="failed">Sorry! You entered invalid username/password.</i>
									</c:if>
									
									<!--		            
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out.
									</div>
								    -->
								</div>
					        </div>
					    </div>
						
						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input type="text" name="username" placeholder="username" class="form-control">
						</div>
						<!-- <p>
							User name: <input type="text" name="username" />
						</p> -->
						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							
							<input type="password" name="password" placeholder="password" class="form-control" >
						</div>
						<!-- <p>
							Password: <input type="password" name="password" />
						</p> -->
						<!-- Login/Submit Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Login</button>
							</div>
						</div>
						<!-- <input type="submit" value="Login"/> -->
						
						<!-- Manually adding tokens -->
						<!-- 
							Comment the below line and you will get HTTP status 403 error.
							Because CSRF token was not found.
						 -->
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>		
	</div>
</body>
</html>