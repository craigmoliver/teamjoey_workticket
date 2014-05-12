<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Change My Password</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<jsp:include page="includes/navigation.jsp" />
    <div class="container">
	    <div class="row">
	    	<h2>Change My Password</h2>
            <form action="/ticket" method="POST" id="user" role="form" data-toggle="validator">
			    <fieldset>
				    <div class="form-group">
       					<label for="password" class="col-lg-2 control-label">Password</label>
						<div class="col-lg-10">
							<input type="password" name="password" id="input-password" class="form-control" placeholder="Password" data-toggle="validator" maxlength="20" required />
							<p class="help-block with-errors"></p>
						</div>
					</div>
					<div class="form-group">
       					<label for="passwordconfirm" class="col-lg-2 control-label">Confirm Password</label>
						<div class="col-lg-10">
							<input type="password" name="passwordconfirm" id="input-passwordconfirm" class="form-control" data-match="#input-password" data-match-error="Passwords do not match" placeholder="Confirm Password" required />
							<p class="help-block with-errors"></p>
						</div>
					</div>
					
				    <div class="form-group">
				    	<div class="col-lg-offset-2 col-lg-10">
				    		<button type="submit" class="btn btn-primary">Update</button>
				    		<a type="button" class="btn btn-default" href="/ticket">Cancel</a>
				    	</div>
			    	</div>
			    	
			    	<input type="hidden" name="command" value="user_changepassword" />
			    	
			    </fieldset>
		    </form>
	    </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>