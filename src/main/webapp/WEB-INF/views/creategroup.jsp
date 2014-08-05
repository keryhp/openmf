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
<meta name="description"
	content="This is the 'Open Microfinance' landing page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create Group</title>
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
});
</script>
<link type="text/css" rel="stylesheet" href="/static/css/jquery-ui.css" />
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.js"></script>
<script src="/static/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<a class="navbar-brand" href="/">OpenMF</a>
				<ul class="nav navbar-nav" id="main-menu-left">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><i class="fa fa-group"></i>
							Clients<b class="caret"></b></a>
						<ul class="dropdown-menu" id="swatch-menu">
							<li><a href="/clients">Clients</a></li>
							<li><a href="/groups">Groups</a></li>

						</ul></li>
					<li><a href="/finance/accountingcoa"><i
							class="fa fa-money"></i> Accounting</a></li>
					<li class="dropdown" id="reports-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-bar-chart-o"></i> Reports<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/reports/allrep">All</a></li>
							<li><a href="/reports/clientsrep">Clients</a></li>
							<li><a href="/reports/loansrep">Loans</a></li>
							<li><a href="/reports/savingsrep">Savings</a></li>
							<li><a href="/reports/fundsrep">Funds</a></li>
							<li><a href="/reports/accountingrep">Accounting</a></li>
						</ul></li>
					<li class="dropdown" id="preview-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-wrench"></i> Admin<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/admin/users">Users</a></li>
							<li><a href="/admin/organization">Organization</a></li>
							<li><a href="/admin/system">System</a></li>
							<li><a href="/admin/products">Products</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="main-menu-right">
					<li class="dropdown" id="user-menu"><a id="user-dropdown"
						class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out
								value="<%=currentUser.getUsername()%>"></c:out><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a id="help" href="/help"><i
									class="fa fa-question-circle"></i> Help</a></li>
							<li><a href="/viewuser?omfuId=<%=currentUser.getId()%>"><i
									class="fa fa-user"></i> Profile</a></li>
							<li><a href="/usersetting"><i class="fa fa-cog"></i>
									Settings</a></li>
							<li><a href="/logout"><i class="fa fa-off"></i>Logout</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input id="search" type="text" placeholder="Search"
						class="form-control search-query col-md-4" />
				</form>
			</div>
		</div>
	</nav>

	<div class="left-nav">
		<ul class="nav nav-pills nav-stacked margin-nav">
			<li><a class="black" href="/"><i class="fa fa-desktop fa-fw"></i>Dashboard</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/clients"><i
					class="fa fa-group fa-fw"></i> Clients</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/groups"><i
					class="fa fa-group fa-fw"></i> Groups</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/admin/users"><i
					class="fa fa-user fa-fw"></i>Users</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/finance/accountingcoa"><i
					class="fa fa-tasks fa-fw"></i>Chart of Accounts</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/admin/products"><i
					class="fa fa-plus fa-fw"></i>Products</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/admin/organization"><i
					class="fa fa-sitemap fa-fw"></i>Organization</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/help"><i
					class="fa fa-question-circle fa-fw"></i>Help</a></li>
		</ul>
	</div>
	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">
					<div class="whitebg">
						<div class="col-md-12">
							<ul class="breadcrumb">
								<li><a href="/groups">Groups</a></li>
								<li class="active">Create a Group</li>
							</ul>
							<form:form id="creategroup" class="form-horizontal well"
								modelAttribute="groupForm" method="post" role="form">
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
										<form:label class="control-label col-sm-2" path="groupname">Group
											name<span class="required">*</span>
										</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="groupname" class="form-control"
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
											<form:input type="number" path="contact" class="form-control" />
										</div>
										<form:label class="control-label col-sm-2" path="externalId">External
											Id</form:label>

										<div class="col-sm-3">
											<form:input type="text" path="externalId"
												class="form-control" />
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
														class="form-control date-picker" />
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
												class="form-control date-picker" />
										</div>
									</div>
									<div class="col-md-offset-5">
										<a id="cancel" href="/groups">
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
			</div>
		</div>
	</div>
</body>
</html>
