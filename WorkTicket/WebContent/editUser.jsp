<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<%@ include file="includes/navigation.html" %>

    <div class="container">
	    <div class="row">
	    	<h2>${title}</h2>
            <form action="" method="POST" id="user" role="form" data-toggle="validator">
			    <fieldset>
				    <legend>${formheader}</legend>
				    <div class="form-group">
       					<label for="name" class="col-sm-2 control-label">Full Name</label>
						<div class="col-sm-10">
							<input type="text" name="name" id="input-name" class="form-control" placeholder="Full Name" value="${userHelper.user.name}" maxlength="100" required />
							<p class="help-block with-errors"></p>
						</div>
					</div>
					<div class="form-group">
       					<label for="email" class="col-sm-2 control-label">Email Address</label>
						<div class="col-sm-10">
							<input type="email" name="email" id="input-email" class="form-control" placeholder="Email Address" data-error="Email address invalid" value="${userHelper.user.email}" maxlength="100" required />
							<p class="help-block with-errors"></p>
						</div>
					</div>
					<div class="form-group">
       					<label for="username" class="col-sm-2 control-label">Username</label>
						<div class="col-sm-10">
							<input type="text" name="username" id="input-username" class="form-control" placeholder="Username" required value="${userHelper.user.username}" maxlength="20" />
							<p class="help-block with-errors"></p>
						</div>
					</div>
					
					<div class="form-group">
       					<label for="password" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<c:if test="${userHelper.user.newUser}">
								<%-- Required if is new user --%>
								<input type="password" name="password" id="input-password" class="form-control" placeholder="Password" data-toggle="validator" maxlength="20" required />
							</c:if>
							<c:if test="${!userHelper.user.newUser}">
								<%-- Not required if not new user --%>
								<input type="password" name="password" id="input-password" class="form-control" placeholder="Password" data-toggle="validator" maxlength="20" />
							</c:if>
							<p class="help-block with-errors"></p>
						</div>
					</div>
					<div class="form-group">
       					<label for="passwordconfirm" class="col-sm-2 control-label">Confirm Password</label>
						<div class="col-sm-10">
							
							<c:if test="${userHelper.user.newUser}">
								<%-- Required if is new user --%>
								<input type="password" name="passwordconfirm" id="input-passwordconfirm" class="form-control" data-match="#input-password" data-match-error="Passwords do not match" placeholder="Confirm Password" required />
							</c:if>
							<c:if test="${!userHelper.user.newUser}">
								<%-- Not required if not new user --%>
								<input type="password" name="passwordconfirm" id="input-passwordconfirm" class="form-control" data-match="#input-password" data-match-error="Passwords do not match" placeholder="Confirm Password" />
							</c:if>
							<p class="help-block with-errors"></p>
						</div>
					</div>
					<div class="form-group">
       					<label for="username" class="col-sm-2 control-label">Role</label>
						<div class="col-sm-10">
							<c:forEach items="${userHelper.allRoles}" var="role" varStatus="status">
								<%-- Emit a row containing TODO --%>
								<div class="radio">
								  <label>
										<c:if test="${role.equals(userHelper.user.role)}">
											<%-- TODO --%>
											<input type="radio" name="role" id="option-role-${status.index}" value="${role}" checked />
										</c:if>
										<c:if test="${!role.equals(userHelper.user.role)}">
											<%-- TODO --%>
											<input type="radio" name="role" id="option-role-${status.index}" value="${role}" />
										</c:if>    
								    	${role}
								  </label>
								</div>
				     		</c:forEach>
							<p class="help-block with-errors"></p>
						</div>
					</div>
					
				    <div class="form-group">
				    	<div class="col-sm-offset-2 col-sm-10">
				    		<button type="submit" class="btn btn-primary">Save</button>
				    		<a type="button" class="btn btn-default" href="/WorkTicket?command=user_list">Cancel</a>
				    	</div>
			    	</div>
			    	
			    	<input type="hidden" name="command" value="user_save" />
			    	
			    </fieldset>
		    </form>
	    </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>