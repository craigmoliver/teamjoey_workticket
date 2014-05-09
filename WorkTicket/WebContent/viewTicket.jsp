<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<%@ include file="includes/head.html" %>
</head>
<body>
	<jsp:include page="includes/navigation.jsp" />
	<div class="container">
		<div class="row">
	    	<h1>${title}</h1>
	    </div>
	</div>
    <div class="container spacer">
    	<div class="row">
	    	<h2>Details</h2>
	    </div>
	    <div class="row">
	    	<div class="col-lg-2"><strong>Date Posted</strong></div>
	    	<div class="col-lg-10"><fmt:formatDate type="both" value="${ticketHelper.ticket.datePostedAsDate}" /></div>
  		</div>
	    <div class="row">
	    	<div class="col-lg-2"><strong>Title</strong></div>
	    	<div class="col-lg-10">${ticketHelper.ticket.title}</div>
  		</div>
  		<div class="row">
  			<div class="col-lg-2 col-lg-2"><strong>Description</strong></div>
	    	<div class="col-lg-10 col-lg-10">${ticketHelper.ticket.description}</div>
  		</div>
  		<c:if test="${isManager}">
  			<%-- Required if is new user --%>
  			<div class="row spacer">
	  			<form action="/ticket" method="POST" id="assign" role="form" data-toggle="validator">
	  				<label for="password" class="col-lg-2 control-label">Assigned To</label>
					<div class="col-lg-2">
						<select name="assignTo" class="form-control">
							<c:forEach items="${ticketHelper.users}" var="user" varStatus="status">
								<%-- Emits TODO --%>
								<c:if test="${ticketHelper.ticket.assignedTo.equals(user)}">
									<%-- Select if assigned  --%>
									<option value="${user}" selected>${user}</option>
								</c:if>
								<c:if test="${!ticketHelper.ticket.assignedTo.equals(user)}">
									<%-- Unselected if not assigned --%>
									<option value="${user}">${user}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-8">
						<button type="submit" class="btn btn-small">Update</button>
					</div>
				    <input type="hidden" name="ticketId" value="${ticketHelper.ticket.ticketId}" />
				    <input type="hidden" name="command" value="ticket_assign" />
				</form>  		
  			</div>
  		</c:if>
  		
  		
  	</div>
    <div class="container spacer">
    	<div class="row">
	    	<h2>Annotations</h1>
	    </div>
	    <div class="row">
	    	<table class="table table-bordered">
	    		<thead>
	    			<tr>
	    				<th>Posted</th>
	    				<th>Text</th>
	    				<c:if test="${isManager}">
	    					<%--  --%>
	    					<th>Saved By</th>
	    				</c:if>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${ticketHelper.annotations}" var="annotation" varStatus="status">
	    				<%-- Emits TODO --%>
	    				<tr>
	    					<td><fmt:formatDate type="both" value="${annotation.datePostedAsDate}" dateStyle="short" timeStyle="short" /></td>
	    					<td>${annotation.text}</td>
	    					<c:if test="${isManager}">
	    						<%-- --%>
	    						<td>${annotation.authorUsername}</td>
	    					</c:if>
	    				</tr>
				    </c:forEach>
	    		</tbody>
	    	
	    	</table>
	    </div>
  	</div>
    <div class="container spacer">
	    <div class="row">
            <form action="" method="POST" id="annotation" role="form" data-toggle="validator">
			    <fieldset>
				    <legend>Add Annotation</legend>
				    <div class="form-group">
						<div class="col-sm-12">
							<textarea class="form-control" rows="3" required></textarea>
							<p class="help-block with-errors"></p>
						</div>
					</div>
					
				    <div class="form-group">
				    	<div class="col-sm-12">
				    		<button type="submit" class="btn btn-primary">Save</button>
				    	</div>
			    	</div>
			    	
			    	<input type="hidden" name="ticketId" value="${ticketHelper.ticket.ticketId}" />
			    	<input type="hidden" name="command" value="annotation_save" />
			    	
			    </fieldset>
		    </form>
	    </div>
	</div>
	<%@ include file="includes/bottom.html" %>
</body>
</html>