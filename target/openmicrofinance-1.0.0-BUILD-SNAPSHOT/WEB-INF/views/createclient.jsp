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
	ArrayList<OpenMFGroup> groups = (ArrayList<OpenMFGroup>) request
			.getAttribute("groups");
	pageContext.setAttribute("groups", groups);
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create New Client</title>
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
					<div class="whitebg">
						<div class="col-md-12">
							<ul class="breadcrumb">
								<li><a href="/clients">Clients</a></li>
								<li class="active">Create Client</li>
							</ul>
							<form:form id="createclient" class="form-horizontal well"
								modelAttribute="clientForm" method="post" role="form">
								<fieldset>
									<legend>Create Client</legend>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="office">Office<span
												class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:select data-placeholder="Choose a Office"
												class="form-control chosen-select" path="office"
												tabindex="2">
												<form:option value="headoffice" selected="selected">Head
													Office</form:option>
											</form:select>
										</div>
										<form:label class="control-label col-sm-2" path="supervisor">Supervisor</form:label>

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
										<form:label class="control-label col-sm-2" path="forename">First
											name<span class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="forename" class="form-control"
												required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="midname">Middle
											name</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="midname" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="surname">Last
											name<span class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="surname" class="form-control"
												required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="address">Address</form:label>

										<div class="col-sm-3">
											<form:textarea path="address" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="contact">Mobile
											number</form:label>

										<div class="col-sm-3">
											<form:input type="number" step="any" path="contact" class="form-control" required="required" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="dateofbirth">Date of Birth</form:label>

										<div class="col-sm-3">
											<form:input path="dateofbirth" class="form-control" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="gender">Gender</form:label>

										<div class="col-sm-3">
											<form:select class="form-control chosen-select" path="gender"
												tabindex="2">
												<form:option value="male" selected="selected">Male</form:option>
												<form:option value="female">Female</form:option>
												<form:option value="others">Others</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="clienttype">Client Type</form:label>

										<div class="col-sm-3">
											<form:select class="form-control chosen-select"
												path="clienttype" tabindex="2"
												onchange="showOrHideGroup(this);">
												<form:option value="individual" selected="selected">Individual
													Person</form:option>
												<form:option value="group">Member of a Group</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 hide" id="groupSelect">
											<form:label class="control-label col-sm-2" path="groupid">Group</form:label>
											<form:select data-placeholder="Choose a Group"
												class="form-control chosen-select" path="groupid"
												tabindex="2">
												<%
													for (OpenMFGroup group : groups) {
												%>
												<form:option value="<%=group.getId()%>">
													<c:out value="<%=group.getGroupname()%>" escapeXml="true" />
												</form:option>
												<%
													}
												%>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="externalId">External
											Id</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="externalId"
												class="form-control" />
										</div>
										<form:label class="control-label col-sm-2"
											path="clientclassification">Client
											Classification</form:label>

										<div class="col-sm-3">
											<form:select class="form-control chosen-select"
												path="clientclassification" tabindex="2">
												<form:option value="clientclass1" selected="selected">Illiterate, Business</form:option>
												<form:option value="clientclass2">Literate, Business</form:option>
												<form:option value="clientclass3">Others</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="active">Active</form:label>

										<div class="col-sm-3">
											<form:checkbox path="active" />
										</div>
										<div>
											<div>
												<form:label class="control-label col-sm-2"
													path="activationdate">Activation
													Date<span class="required">*</span>
												</form:label>

												<div class="col-sm-3">
													<form:input path="activationdate"
														class="form-control date-picker" required="required" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="eligible">Eligible for Loans?</form:label>

										<div class="col-sm-3">
											<form:checkbox path="eligible" />
										</div>

										<form:label class="control-label col-sm-2" path="submittedon">Submitted on</form:label>

										<div class="col-sm-3">
											<form:input path="submittedon"
												class="form-control date-picker" required="required" />
										</div>
									</div>
									<div class="col-md-offset-5">
										<a id="cancel" href="/clients">
											<button type="button" class="btn btn-default"
												onclick="goback();">Cancel</button>
										</a>
										<button id="save" type="submit" class="btn btn-primary">Submit</button>
									</div>
								</fieldset>
							</form:form>
						</div>

					</div>
				</div>
				<!-- Footer -->
			</div>
			<!-- /row-fluid -->
		</div>
		<!-- /blockui-->
	</div>
	<!-- /container -->
</body>
</html>
