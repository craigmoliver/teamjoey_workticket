<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Work Ticket System</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${loggedIn == true}">
					<c:if test="${isManager == true}">
						<li><a href="/ticket?command=user_list">Users</a></li>
					</c:if>
					<li><a href="/ticket?command=user_changepassword">Change My Password</a></li>
					<li><a href="/login?command=logout">Logout</a></li>
				</c:if>
				<c:if test="${loggedIn == false}">
					<li><a href="/ticket">Submit A Ticket</a></li>
					<li><a href="/login">Login</a></li>
				</c:if>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</div>