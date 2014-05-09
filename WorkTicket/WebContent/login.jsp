<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<jsp:include page="includes/navigation.jsp" />
	<div class="container spacer">
		<div class="row">
			<form method="POST" class="form-horizontal" role="form">
				<fieldset>
					<legend>Enter Your Credentials</legend>
				  	<div class="form-group">
				    	<label for="inputUsername" class="col-sm-2 control-label">Username</label>
				    	<div class="col-sm-10">
				      		<input type="text" class="form-control" name="username" id="inputUsername" required />
				    	</div>
					</div>
				  	<div class="form-group">
						<label for="inputPassword" class="col-sm-2 control-label">Password</label>
				    	<div class="col-sm-10">
				      		<input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password" required />
				    	</div>
				  	</div>
				  	<div class="form-group">
				    	<div class="col-sm-offset-2 col-sm-10">
				      		<button type="submit" class="btn btn-default">Login</button>
				    	</div>
					</div>
					
					<input type="hidden" name="command" value="user_login" />
				</fieldset>
			</form>
		</div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>