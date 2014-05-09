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
	    	<div class="col-sm-offset-3 col-lg-6">
	    		<div class="bg-success">
            		<h2>Thank you for your submission.</h2>
            		<p>Your Ticket ID # is ${ticketId}. Someone from our semi-courteous staff will get back to you shortly.</p>
            	</div>
            </div>
	    </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>