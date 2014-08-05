<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="uk.ac.openmf.model.*"%>
<%@page import="uk.ac.openmf.web.*"%>
<%@ page language="java"
	contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ page import="com.google.appengine.api.users.*"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreNeedIndexException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	OpenMFUser currentUser = (OpenMFUser) request
			.getAttribute("currentUser");
	pageContext.setAttribute("currentUser", currentUser);
	ArrayList<OpenMFUser> omfusers = (ArrayList<OpenMFUser>) request
			.getAttribute("omfusers");
	pageContext.setAttribute("omfusers", omfusers);
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create User</title>
<link href="/favicon.ico" rel="shortcut icon" type="image/ico" />
<!-- Bootstrap core CSS -->
<link type="text/css" href="/static/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css" href="/static/css/bootstrap-ext.css"
	rel="stylesheet" />
<link type="text/css" href="/static/css/chosen.min.css" rel="stylesheet" />
<!-- Custom dashboard styles -->
<link type="text/css" href="/static/css/dashboard.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="/static/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="/static/css/generic.css" />
<link type="text/css" rel="stylesheet" href="/static/css/openmf.app.css" />
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/chosen.jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/tablefilter.js"></script>
<script src="/static/js/openmf.basic.js"></script>
<script src="/static/js/jquery-ui.js"></script>
<script src="/static/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript">
$(document).ready(function (){
	var dateToday = new Date();
	$(".date-picker").datepicker({format: "dd/mm/yyyy", minDate: dateToday});
	$("#dateofbirth").datepicker({format: "dd/mm/yyyy"}); //TODO do not allow future date for dob
});
</script>
<link type="text/css" rel="stylesheet" href="/static/css/jquery-ui.css" />
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.js"></script>
<script src="/static/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<jsp:include page="/WEB-INF/views/header.jsp">
		<jsp:param name="username" value="<%=currentUser.getUsername()%>" />
		<jsp:param name="userid" value="<%=currentUser.getId()%>" />
	</jsp:include>
	<jsp:include page="/WEB-INF/views/leftnav.jsp"/>
	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">

					<div class="col-md-12">
						<ul class="breadcrumb">
							<li><a href="/admin/users">Users</a></li>
							<li class="active">Create User</li>
						</ul>
						<form:form id="createuser" class="form-horizontal" method="post"
							modelAttribute="userForm">
							<fieldset>
								<legend>Create User</legend>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="username">User
										Name<span class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" path="username" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="forename">First
										Name<span class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" path="forename" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="surname">Last
										Name<span class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" path="surname" class="form-control" />
									</div>
								</div>
								<div>
									<div>
										<div class="form-group">
											<form:label class="control-label col-sm-2" path="email">Email<span
													class="required">*</span>
											</form:label>

											<div class="col-sm-3">
												<form:input type="email" path="email" class="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="contact">Contact<span
												class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:input type="number" path="contact" class="form-control" />
										</div>
									</div>
								</div>
								<div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="address">Address<span
												class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:input path="address" class="form-control" />
										</div>
									</div>
								</div>
								<div>
									<div>
										<div class="form-group">
											<form:label class="control-label col-sm-2" path="password">Password<span
													class="required">*</span>
											</form:label>

											<div class="col-sm-3">
												<form:input type="password" path="password"
													class="form-control" />
											</div>
										</div>
										<!-- 										<div class="form-group">
											<label class="control-label col-sm-2" for="repeatPassword">Repeat
												Password<span class="required">*</span>
											</label>

											<div class="col-sm-3">
												<input type="password" id="repeatPassword"
													name="repeatPassword" class="form-control" />
											</div>
										</div> -->
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="mainoffice">Input office<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a Office"
											class="form-control chosen-select" path="mainoffice"
											tabindex="2">
											<form:option value="headoffice" selected="selected">Head
												Office</form:option>
											<!-- <form:option value="branch1">Branch one</form:option>
											<form:option value="branch2">Branch two</form:option>
											<form:option value="branch3">Branch three</form:option> -->
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="supervisor">Input Staff</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a Supervisor"
											class="form-control chosen-select" path="supervisor"
											tabindex="2">
											<%
												for (OpenMFUser omfuser : omfusers) {
											%>
											<form:option value="<%=omfuser.getUsername()%>">
												<c:out value="<%=omfuser.getUsername()%>" escapeXml="true" />
											</form:option>
											<%
												}
											%>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="role">Select Roles<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a Role"
											class="form-control chosen-select" path="role" tabindex="2">
											<form:option value="FIELD_OFFICER" selected="selected">Field Officer</form:option>
											<form:option value="SUPERVISOR">Supervisor</form:option>
											<form:option value="ACCOUNTANT">Accountant</form:option>
											<form:option value="CLIENT">Client</form:option>
											<form:option value="ADMIN">Admin</form:option>
											<form:option value="BRANCH_MANAGER">Branch Manager</form:option>
											<form:option value="GENERAL_MANAGER">General Manager</form:option>
											<form:option value="HIGH_LEVEL">High Level</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="enabled">Enabled?</form:label>
									<div class="checkbox col-sm-2">
										<label> <form:checkbox path="enabled" />
										</label>
									</div>
								</div>
								<div class="col-md-offset-3">
									<a id="cancel" href="/admin/users" class="btn btn-default">Cancel</a>
									<button id="save" type="submit" class="btn btn-primary">Save</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
