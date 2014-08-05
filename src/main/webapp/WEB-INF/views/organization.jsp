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
	UserService userService = UserServiceFactory.getUserService();
	AppContext appContext = AppContext.getAppContext();
	ConfigManager configManager = appContext.getConfigManager();
	OpenMFUser currentUser = appContext.getCurrentUser();
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Organization</title>
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
	<jsp:include page="/WEB-INF/views/leftnav.jsp"/>

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">

					<div class="row paddedleft paddedtop">
						<div class="col-sm-6 col-md-6">
							<div class="list-group">
								<a class="list-group-item" href="/offices">
									<h4 class="list-group-item-heading">
										<i class="fa fa-building fa-1x"></i> Manage Offices
									</h4>

									<p class="list-group-item-text">Add new office or modify or
										deactivate office or modify office hierarchy</p>
								</a> <a class="list-group-item" href="/roi">
									<h4 class="list-group-item-heading">
										<i class="fa fa-calendar fa-1x"></i> Configure Interest
										Rates
									</h4>

									<p class="list-group-item-text">Set or modify interest
										rates</p>
								</a> <a class="list-group-item" href="/assignroles">
									<h4 class="list-group-item-heading">
										<i class="fa fa-user fa-1x"></i> Assign Roles to users
									</h4>

									<p class="list-group-item-text">Assign roles to users</p>
								</a>
							</div>
						</div>
						<div class="col-sm-6 col-md-6">
							<div class="list-group">
								<a class="list-group-item" href="/policyprocedure">
									<h4 class="list-group-item-heading">
										<i class="fa fa-1x fa-cogs"></i> Manage Policies and
										Procedures
									</h4>

									<p class="list-group-item-text">Manage Policies and
										Procedures for Portfolio Tracking System</p>
								</a> <a class="list-group-item" href="/managefunds">
									<h4 class="list-group-item-heading">
										<i class="fa fa-1x fa-money"></i> Manage Funds
									</h4>

									<p class="list-group-item-text">Manage funds across the
										organization</p>
								</a> <a class="list-group-item" href="/approvebulk">
									<h4 class="list-group-item-heading">
										<i class="fa fa-angle-double-right fa-1x"></i> Bulk Loan
										assignment or approval
									</h4>

									<p class="list-group-item-text">Manage the bulk loan
										approval and assignments</p>
								</a>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
