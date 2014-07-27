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
<meta name="description" content="This is the centers Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create New center</title>
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
						class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out value="<%=currentUser.getUsername() %>"></c:out><b
							class="caret"></b></a>
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
			<li><a class="black" href="/"><i
					class="fa fa-desktop fa-fw"></i>Dashboard</a></li>
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
					<div class="whitebg">
						<div class="col-md-12">
							<ul class="breadcrumb">
								<li><a href="/centers.htm">Centers</a></li>
								<li class="active">Create Center</li>
							</ul>
							<form name="createcenterform" novalidate="novalidate"
								class="form-horizontal well" onsubmit="submit()">
								<fieldset>
									<legend>Create center</legend>
									<div class="form-group">
										<label class="control-label col-sm-2">Office<span
											class="required">*</span></label>

										<div class="col-sm-3">
											<select data-placeholder="Choose a Office"
												class="form-control chosen-select" id="officeId"
												tabindex="2">
												<option value="headoffice" selected="selected">Head
													Office</option>
												<option value="branch1">Branch one</option>
												<option value="branch2">Branch two</option>
												<option value="branch3">Branch three</option>
											</select>
										</div>
										<label class="control-label col-sm-2">Staff</label>

										<div class="col-sm-3">
											<select class="form-control chosen-select" id="staffId"
												tabindex="2">
												<option value="staff1" selected="selected">Staff
													one</option>
												<option value="staff2">Staff two</option>
												<option value="staff3">Staff three</option>
												<option value="staff4">Staff four</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="firstname">First
											name<span class="required">*</span>
										</label>

										<div class="col-sm-3">
											<input type="text" id="firstname" name="firstname"
												class="form-control" required="required" />
										</div>
										<div class="col-sm-2">
											<form-validate valattributeform="createcenterform"
												valattribute="firstname" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="middlename">Middle
											name</label>

										<div class="col-sm-3">
											<input type="text" id="middlename" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="lastname">Last
											name<span class="required">*</span>
										</label>

										<div class="col-sm-3">
											<input type="text" id="lastname" name="lastname"
												class="form-control" required="required" />
										</div>
										<div class="col-sm-2">
											<form-validate valattributeform="createcenterform"
												valattribute="lastname" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="mobileNo">Mobile
											number</label>

										<div class="col-sm-3">
											<input type="text" id="mobileNo" name="mobileNo"
												pattern="/^[0-9]+$/" class="form-control" />
										</div>
										<div class="col-sm-2">
											<span> <small class="required"> Must be number
											</small>
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Date of Birth</label>

										<div class="col-sm-3">
											<input id="dateofbirth" type="text" name="dateofbirth"
												class="form-control datepicker"
												data-date-format="mm/dd/yyyy" max="restrictDate" />
										</div>
										<label class="control-label col-sm-2">Gender</label>

										<div class="col-sm-3">
											<select class="form-control chosen-select" id="genderId"
												tabindex="2">
												<option value="male" selected="selected">Male</option>
												<option value="female">Female</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">center Type</label>

										<div class="col-sm-3">
											<select class="form-control chosen-select" id="centertypeId"
												tabindex="2">
												<option value="individual" selected="selected">Individual
													Person</option>
												<option value="verysmall">Very Small Enterprise</option>
												<option value="smallmedium">Small/Medium Enterprise</option>
											</select>
										</div>

										<label class="control-label col-sm-2">center
											Classification</label>

										<div class="col-sm-3">
											<select class="form-control chosen-select"
												id="centerClassificationId" tabindex="2">
												<option value="centerclass1" selected="selected">Classification
													one</option>
												<option value="centerclass2">Classification two</option>
												<option value="centerclass3">Classification three</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="externalId">External
											Id</label>

										<div class="col-sm-3">
											<input type="text" id="externalId" name="externalId"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Active</label>

										<div class="col-sm-3">
											<input id="activeCheckbox" type="checkbox" />
										</div>
										<div>
											<div>
												<label class="control-label col-sm-2">Activation
													Date<span class="required">*</span>
												</label>

												<div class="col-sm-3">
													<input id="activationDate" type="text"
														name="activationdate" class="form-control datepicker"
														data-date-format="mm/dd/yyyy" min="'2000-01-01'"
														max="restrictDate" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Submitted on</label>

										<div class="col-sm-3">
											<input id="submittedon" type="text" name="submittedon"
												class="form-control datepicker"
												data-date-format="mm/dd/yyyy" min="'2000-01-01'"
												max="restrictDate" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Open Savings
											Account</label>

										<div class="col-sm-3">
											<input id="opensavingsproduct" type="checkbox" />
										</div>
										<div>
											<label class="control-label col-sm-2">Select Savings
												Account</label>

											<div class="col-sm-3">
												<select class="form-control chosen-select"
													id="savingsproductId" tabindex="2">
													<option value="savingsproduct1" selected="selected">Savings
														Produce one</option>
													<option value="savingsproduct2">Savings Produce
														two</option>
													<option value="savingsproduct3">Savings Produce
														three</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-offset-5">
										<a id="cancel" href="/centers.htm">
											<button type="button" class="btn btn-default" onclick="goback();">Cancel</button>
										</a>
										<button id="save" type="submit" class="btn btn-primary">Submit</button>
									</div>
								</fieldset>
							</form>
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
