<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>title</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<div class="container">
		<div class="row">
			<form method="POST" class="form-horizontal" role="form">
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
			</form>
		</div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>