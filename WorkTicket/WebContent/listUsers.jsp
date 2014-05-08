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
			<p><a href="/WorkTicket?command=user_new" class="btn btn-primary btn-sm">New</a></p>
			<div class="table-responsive">
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th>Username</th>
							<th>Name</th>
							<th>Email</th>
							<th>Role</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user" varStatus="status">
							<%-- Emit a row containing TODO --%>
							<tr>
								<td>
			     					<a href="/WorkTicket?command=user_edit&username=${user.username}" class="btn btn-primary btn-sm">Edit</a>
			     				</td>
								<td>${user.username}</td>
								<td>${user.name}</td>
								<td>${user.email}</td>
								<td>${user.role}</td>
			   				</tr>
			     		</c:forEach>
		     		</tbody>
		     	</table>
	     	</div>
	     </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>