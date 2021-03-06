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
	OpenMFUser omfuser = (OpenMFUser) request.getAttribute("omfuser");
	ArrayList<OpenMFTask> tasks = (ArrayList<OpenMFTask>) request
			.getAttribute("tasks");
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Tasks Report</title>
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
	<jsp:include page="/WEB-INF/views/leftnav.jsp" />
	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">

					<div id="reportdata" class="col-md-12">
						<ul class="breadcrumb">
							<li><a href="/reports/users">Reports</a></li>
							<li class="active">Clients Report</li>
						</ul>
						<div>
							<div class="pull-right">
								<button onclick="exportTasksFromHTML(<%=omfuser.getId()%>);"
									class="btn btn-primary">
									<i class="fa fa-plus fa fa-white"></i>Export
								</button>
							</div>
							<div class="span gray-head">
								<span class="boldlabel"> <strong>Pending Tasks</strong>
								</span>
							</div>
							<table class="table table-condensed">
								<tr class="graybg">
									<th>Task#</th>
									<!-- <th>Description</th>
																			<th>Assigned To</th> -->
									<th>Date Assigned</th>
									<th>Amount</th>
									<th>Acc#</th>
									<th>Type</th>
								</tr>
								<%
																			for (OpenMFTask task : tasks) {
																						long taskId = task.getId();
																		%>
								<tr>
									<td><c:out value="<%=taskId%>" escapeXml="true" /></td>
									<%--<td><c:out value="<%=task.getDescription()%>"
																					escapeXml="true" /></td>
																					<td><c:out value="<%=task.getAssignedto()%>"
																					escapeXml="true" /></td> --%>
									<td><c:out value="<%=task.getDateassigned()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=task.getAmount()%>" escapeXml="true" /></td>
									<td><c:out value="<%=task.getAccountnumber()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=task.getCollectiontype()%>"
											escapeXml="true" /></td>
								</tr>
								<%
																					}
																		%>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
