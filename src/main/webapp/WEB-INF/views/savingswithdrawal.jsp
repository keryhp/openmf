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
	String clientId = (String) request
			.getAttribute("clientId");
	pageContext.setAttribute("clientId", clientId);
	String savingsaccountid = (String) request
			.getAttribute("savingsaccountid");
	pageContext.setAttribute("savingsaccountid", savingsaccountid);
	pageContext.setAttribute("omfusers", omfusers);
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	OpenMFSavingsAccount sgaccdetails = (OpenMFSavingsAccount) request
			.getAttribute("sgaccdetails");
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Savings Withdrawal</title>
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

					<div class="col-md-12">
						<ul class="breadcrumb">
							<li><a href="/clients">Clients</a></li>
							<li class="active">Make Savings Withdrawal</li>
						</ul>
						<form:form id="savingswithdrawal" class="form-horizontal"
							method="post" modelAttribute="savingsWithdrawalForm">
							<fieldset>
								<legend>Make a Withdrawal</legend>
								<div class="form-group">
									<label class="control-label col-sm-2">Client </label>
									<div class="col-sm-3">
										<p>
											<c:out value="${client.forename }" escapeXml="true" />
											<c:out value="${client.surname }" escapeXml="true" /> |
											<c:out value="<%=client.getAccountNumber()%>"></c:out>
										</p>
									</div>
									<form:input path="clientId" hidden="true" value="<%=clientId %>"/>
									<form:input path="savingsaccountid" hidden="true" value="<%=savingsaccountid %>"/>
									<label class="control-label col-sm-2">Savings Account</label>
									<div class="col-sm-3">
										<p>
											<c:out value="${sgaccdetails.savingsaccountnumber}"
												escapeXml="true"></c:out>
											|
											<c:out value="${sgaccdetails.savingscode}" escapeXml="true"></c:out>
										</p>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="withdrawalamount">Amount Withdrawn<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="number" path="withdrawalamount"
											class="form-control" />
									</div>
									<form:label class="control-label col-sm-2" path="dateofwithdrawal">
										<i class="fa fa-calendar fa-1x"></i>Withdrawal Date<span
											class="required">*</span>
									</form:label>
									<div class="col-sm-2">
										<form:input path="dateofwithdrawal"
											class="form-control date-picker" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2"
										path="transactionreference">Transaction Reference<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" path="transactionreference"
											class="form-control" />
									</div>
									<form:label class="control-label col-sm-2"
										path="transactionnote">Transaction Note
									</form:label>

									<div class="col-sm-3">
										<form:input path="transactionnote" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="postedby">Posted By</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a User"
											class="form-control chosen-select" path="postedby"
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
									<form:label class="control-label col-sm-2" path="status">Status (check for successfully collected)</form:label>
									<div class="checkbox col-sm-3">
										<label> <form:checkbox path="status" />
										</label>
									</div>
								</div>
								<div class="col-md-offset-3">
									<a id="cancel" href="/clients" class="btn btn-default">Cancel</a>
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
