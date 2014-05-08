<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<h2>${formheader}</h2>
			<div class="table-responsive">
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>Ticket #</th>
							<th>Title</th>
							<th>Latest Activity Date</th>
							<th>Latest Activity</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tickets}" var="ticketHelper" varStatus="status">
							<%-- Emit a row containing TODO --%>
							<tr>
								<td><a href="/ticket?command=ticket_view&ticketId=${ticketHelper.ticket.ticketId}" class="btn btn-primary btn-sm">${ticket.ticketId}</a></td>
								<td>${ticketHelper.ticket.title}</td>
								<c:if test="${ticketHelper.hasAnnotations}">
									<td><fmt:formatDate type="both" value="${ticketHelper.latestAnnotation.datePostedAsDate}" /></td>
									<td>${ticketHelper.latestAnnotation.text}</td>
								</c:if>
								<c:if test="${!ticketHelper.hasAnnotations}">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</c:if>
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