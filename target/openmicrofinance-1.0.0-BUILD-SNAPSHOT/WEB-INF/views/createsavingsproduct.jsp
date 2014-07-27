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
	ArrayList<OpenMFSavingsProduct> savingsProduct = (ArrayList<OpenMFSavingsProduct>) request
			.getAttribute("savingsProduct");
	pageContext.setAttribute("savingsProduct", savingsProduct);
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Clients Info</title>
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
<script src="/static/js/openmf.datepicker.js"></script>
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
							<li><a href="/clients.htm">Clients</a></li>
							<li><a href="/groups.htm">Groups</a></li>
							<li><a href="/centers.htm">Centers</a></li>
						</ul></li>
					<li><a href="/accounting.htm"><i class="fa fa-money"></i>
							Accounting</a></li>
					<li class="dropdown" id="reports-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-bar-chart-o"></i> Reports<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/reports/allrep.htm">All</a></li>
							<li><a href="/reports/clientsrep.htm">Clients</a></li>
							<li><a href="/reports/loansrep.htm">Loans</a></li>
							<li><a href="/reports/savingsrep.htm">Savings</a></li>
							<li><a href="/reports/fundsrep.htm">Funds</a></li>
							<li><a href="/reports/accountingrep.htm">Accounting</a></li>
						</ul></li>
					<li class="dropdown" id="preview-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-wrench"></i> Admin<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/users.htm">Users</a></li>
							<li><a href="/organization.htm">Organization</a></li>
							<li><a href="/system.htm">System</a></li>
							<li><a href="/products.htm">Products</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="main-menu-right">
					<li class="dropdown" id="user-menu"><a id="user-dropdown"
						class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out
								value="<%=currentUser.getUsername()%>"></c:out><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a id="help" href="/help.htm"><i
									class="fa fa-question-circle"></i> Help</a></li>
							<li><a href="/viewuser.htm?omfuId=<%=currentUser.getId()%>"><i class="fa fa-user"></i>
									Profile</a></li>
							<li><a href="/usersetting.htm"><i class="fa fa-cog"></i>
									Settings</a></li>
							<li><a href="/logout.htm"><i class="fa fa-off"></i>Logout</a></li>
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
			<li><a class="black" href="/advsearch.htm"><i
					class="fa fa-search fa-fw"></i>Advanced Search</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/offices.htm"><i
					class="fa fa-compass fa-fw"></i>Offices</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/tasks.htm"><i
					class="fa fa-check fa-fw"></i>Tasks</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/entercollectionsheet.htm"><i
					class="fa fa-tasks fa-fw"></i>Collections</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/freqposting.htm"><i
					class="fa fa-repeat fa-fw"></i>Frequent Postings</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/journalentry.htm"><i
					class="fa fa-plus fa-fw"></i>+ Journal Entry</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/accountsclosure.htm"><i
					class="fa fa-bell-o fa-fw"></i>Closing Entries</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/accountingcoa.htm"><i
					class="fa fa-sitemap fa-fw"></i>Chart of Accounts</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/createclient.htm"><i
					class="fa fa-user fa-fw"></i>+ Client</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/creategroup.htm"><i
					class="fa fa-group fa-fw"></i>+ Group</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/createcenter.htm"><i
					class="fa fa-group fa-fw"></i> + Center</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/help.htm"><i
					class="fa fa-question-circle fa-fw"></i>Help</a></li>
		</ul>
	</div>

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">
					<div class="whitebg ng-scope">
						<div class="col-md-12">
							<ul class="breadcrumb">
								<li><a href="/savingsproducts.htm">Savings Products</a></li>
								<li class="active">Create Savings Product</li>
							</ul>
							<form:form id="createsavingsproduct" method="post"
								modelAttribute="savingsProductForm" role="form"
								class="form-horizontal well">
								<fieldset>
									<h3>Details</h3>
									<hr></hr>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="productname">Product Name<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="text" path="productname"
												class="form-control" />
										</div>
										<form:label class="control-label col-sm-2" path="savingscode">Savings Id/code<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="text" path="savingscode"
												class="form-control" maxlength="4" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="description">Description</form:label>
										<div class="col-sm-2">
											<form:textarea rows="2" class="form-control"
												path="description"></form:textarea>
										</div>
										<form:label class="control-label col-sm-2" path="savingstype">Type</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a Type"
												class="form-control chosen-select" path="savingstype"
												tabindex="2">
												<form:option value="VOLUNTARY" selected="selected">Voluntary</form:option>
												<form:option value="COMPULSORY">Compulsory</form:option>
											</form:select>
										</div>
									</div>
									<%-- <div class="form-group">
										<form:label class="control-label col-sm-2" path="savingsamount">Min Savings Amount in GBP</form:label>
										<div class="col-sm-2">
											<form:input rows="2" class="form-control" path="savingsamount" />
										</div>
									</div> --%>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="startdate">
											<i class="fa fa-calendar fa-1x"></i>Start Date</form:label>
										<div class="col-sm-2">
											<form:input path="startdate" class="form-control date-picker" />
										</div>
										<form:label class="control-label col-sm-2" path="closedate">
											<i class="fa fa-calendar fa-1x"></i>Close
											Date</form:label>
										<div class="col-sm-2">
											<form:input path="closedate" class="form-control date-picker" />
										</div>
									</div>
									<h3>Settings</h3>
									<hr></hr>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="tenure">Tenure in months for Maturity<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="number" path="tenure" class="form-control" />
										</div>
										<form:label class="control-label col-sm-2"
											path="depositfrequency">Deposit Frequency<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a frequency"
												class="form-control chosen-select" path="depositfrequency"
												tabindex="2">
												<form:option value="7" selected="selected">Weekly</form:option>
												<form:option value="15">Fortnight</form:option>
												<form:option value="30">Monthly</form:option>
												<form:option value="90">Three Months</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2"
											path="interestcalcperiod">Interest
											Calculation Period<span class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<p>Same as Tenure</p>
										</div>
										<form:label class="control-label col-sm-2"
											path="rateofinterest">% Rate of Interest<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="number" path="rateofinterest"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2 col-sm-offset-1"
											path="interestmethod">Interest
											Method<span class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<p>Flat</p>
										</div>
										<form:label class="control-label col-sm-2" path="active">Active ?</form:label>
										<div class="checkbox col-sm-2">
											<label> <form:checkbox path="active" />
											</label>
										</div>
									</div>
									<div class="col-md-offset-5 paddedtop">
										<button id="cancel" type="reset" class="btn btn-warning">Cancel</button>
										<button id="save" type="submit" class="btn btn-primary">Save</button>
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
