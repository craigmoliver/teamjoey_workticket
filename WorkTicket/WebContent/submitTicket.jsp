<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Submit a Ticket</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<jsp:include page="includes/navigation.jsp" />
    <div class="container spacer">
	    <div class="row">
            <form action="" method="POST" id="ticket" role="form" data-toggle="validator">
			    <fieldset>
				    <legend>Submit a Ticket</legend>
				    <div class="form-group">
       					<label for="title" class="col-lg-2 control-label">Title</label>
						<div class="col-lg-10">
							<input type="text" name="title" id="input-title" class="form-control" placeholder="Title" maxlength="100" required />
							<p class="help-block with-errors"></p>
						</div>
					</div>
				    <div class="form-group">
				    	<label for="title" class="col-lg-2 control-label">Details</label>
						<div class="col-lg-10">
							<textarea name="description" id="input-description" class="form-control" placeholder="Details" rows="3" required></textarea>
							<p class="help-block with-errors"></p>
						</div>
					</div>
					
				    <div class="form-group">
				    	<div class="col-lg-12">
				    		<button type="submit" class="btn btn-primary">Submit</button>
				    	</div>
			    	</div>
			    	<input type="hidden" name="command" value="ticket_submit" />
			    </fieldset>
		    </form>
	    </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>