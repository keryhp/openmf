<!DOCTYPE html>
<%@page import="uk.ac.openmf.utils.OMFDateUtils"%>
<%@page import="uk.ac.openmf.utils.OMFUtils"%>
<%@page import="uk.ac.openmf.services.*"%>
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
	OpenMFUser omfuser = (OpenMFUser) request.getAttribute("omfuser");
	PhotoServiceManager serviceManager = AppContext.getAppContext()
			.getPhotoServiceManager();
	OpenMFPhoto photo = (OpenMFPhoto) OMFUtils.getPhotoByTypeId(omfuser
			.getId());
	ArrayList<OpenMFClient> clients = (ArrayList<OpenMFClient>) request
			.getAttribute("clients");
	ArrayList<OpenMFGroup> groups = (ArrayList<OpenMFGroup>) request
			.getAttribute("groups");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>User Info</title>
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

						</ul></li>
					<li><a href="/finance/accountingcoa.htm"><i
							class="fa fa-money"></i> Accounting</a></li>
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
							<li><a href="/admin/users.htm">Users</a></li>
							<li><a href="/admin/organization.htm">Organization</a></li>
							<li><a href="/admin/system.htm">System</a></li>
							<li><a href="/admin/products.htm">Products</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="main-menu-right">
					<li class="dropdown" id="user-menu"><a id="user-dropdown"
						class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out
								value="<%=currentUser.getUsername()%>"></c:out><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a id="help" href="/help.htm"><i
									class="fa fa-question-circle"></i> Help</a></li>
							<li><a href="/viewuser.htm?omfuId=<%=currentUser.getId()%>"><i
									class="fa fa-user"></i> Profile</a></li>
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
			<li><a class="black" href="/finance/freqposting.htm"><i
					class="fa fa-repeat fa-fw"></i>Frequent Postings</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/finance/journalentry.htm"><i
					class="fa fa-plus fa-fw"></i>+ Journal Entry</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/finance/accountsclosure.htm"><i
					class="fa fa-bell-o fa-fw"></i>Closing Entries</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/finance/accountingcoa.htm"><i
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
					<c:choose>
						<c:when test="${omfuser != null}">
							<div class="whitebg">
								<div class="col-md-12">
									<div>
										<ul class="breadcrumb">
											<li><a href="/admin/users.htm">Users</a></li>
											<li class="active">View user</li>
										</ul>
									</div>
									<div class="row">
										<div class="col-md-8 col-sm-8">
											<h3 class="client-title">
												<i class="fa fa-user fa-white"></i> <strong><c:out
														value="${omfuser.forename }" escapeXml="true" /> <c:out
														value="${omfuser.surname }" escapeXml="true" /></strong> <small>
													| <c:out value="<%=omfuser.getMain_office()%>"></c:out> | <c:out
														value="<%=omfuser.getSupervisor()%>"></c:out>
												</small>
											</h3>
										</div>
									</div>
									<div class="overflowhidden marginbottom0 ">
										<ul id="myTab" class="nav nav-tabs">
											<li class="active"><a href="#general" data-toggle="tab">General</a></li>
											<li class=""><a href="#changepassword" data-toggle="tab">Change
													Password</a></li>
											<li class=""><a href="#addphoto" data-toggle="tab">Photos</a></li>
											<li class=""><a href="#address" data-toggle="tab">Address</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="general">
												<div class="col-md-12 col-sm-12">
													<div class="row primarydiv">
														<div class="pull-right">
															<span> <a
																onclick="deleteUser(<%=omfuser.getId()%>)"
																class="btn btn-primary"> <i
																	class="fa fa-arrow-right fa-white"></i>Delete User
															</a>
															</span>
														</div>
													</div>

													<div class="row client">
														<div class="col-sm-9 col-md-9 paddingleft0px">
															<div>
																<div>
																	<div class="span gray-head">
																		<span class="boldlabel"> <strong class="">Supervisor
																				for Clients</strong>
																		</span>
																	</div>
																	<table class="table table-condensed">
																		<tr class="graybg">
																			<th>Client Acc#</th>
																			<th>Name</th>
																		</tr>
																		<%
																			for (OpenMFClient client : clients) {
																						long clientId = client.getId();
																						if (client.isActive()) {
																		%>
																		<tr class="pointer"
																			onclick="viewClientFn(<%=clientId%>);">
																			<td><c:out
																					value="<%=client.getAccountNumber()%>"
																					escapeXml="true" /></td>
																			<td><c:out value="<%=client.getForename()%>"
																					escapeXml="true" /> <c:out
																					value="<%=client.getSurname()%>" escapeXml="true" /></td>
																		</tr>
																		<%
																			}
																					}
																		%>

																	</table>
																</div>
															</div>
															<div>
																<div>
																	<div class="span gray-head">
																		<span class="boldlabel"> <strong>Supervisor
																				for groups</strong>
																		</span>
																	</div>
																	<table class="table table-condensed">
																		<tr class="graybg">
																			<th>Group Acc#</th>
																			<th>Group Name</th>
																		</tr>
																		<%
																			for (OpenMFGroup group : groups) {
																						long groupId = group.getId();
																						if (group.isActive()) {
																		%>
																		<tr class="pointer"
																			onclick="viewGroupFn(<%=groupId%>);">
																			<td><c:out value="<%=group.getAccountnumber()%>"
																					escapeXml="true" /></td>
																			<td><c:out value="<%=group.getGroupname()%>"
																					escapeXml="true" /></td>
																		</tr>
																		<%
																			}
																					}
																		%>
																	</table>
																</div>
															</div>
														</div>

														<div class="col-sm-3 col-md-3">
															<div class="thumbnail row">
																<h4>
																	<strong><c:out value="${omfuser.forename }"
																			escapeXml="true" /> <c:out
																			value="${client.surname }" escapeXml="true" /> </strong>
																</h4>
																<%
																	if (photo == null) {
																%>
																<img src="/static/images/demo_user.jpg" alt="Avatar" />
																<%
																	} else {
																%>
																<img
																	src="<%=serviceManager.getImageDownloadUrl(photo)%>"
																	alt="Photo Image" />
																<%
																	}
																%>
																<table class="table-minified">
																	<tr>
																		<th class="table-bold">Username</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getUsername()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Mobile number</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getContact()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Email</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getEmail()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Office</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getMain_office()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Supervisor</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getSupervisor()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Role</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getRole()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Address</th>
																		<td><span class="padded-td"><c:out
																					value="<%=omfuser.getAddress()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Activation Date</th>
																		<td><span class="padded-td"><c:out
																					value="<%=OMFDateUtils.getDateFromTimestamp(omfuser
								.getTimestamp())%>"></c:out></span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- end of general tab -->
											<div class="tab-pane" id="address">
												<br></br>
												<p>
													<c:out value="<%=omfuser.getAddress()%>"></c:out>
												</p>
											</div>
											<div class="tab-pane" id="changepassword">
												<br />

												<div id="changepassword" class="row client">
													<div class="modal-header silver">
														<h3 class="bolder">Change Password</h3>
													</div>


													<div class="modal-body form-horizontal">
														<br></br>
														<div class="form-group">
															<label class="control-label col-sm-4" for="password">Password</label>

															<div class="col-sm-5">
																<input type="password" id="password"
																	class="form-control" />
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-4"
																for="repeatPassword">Repeat password</label>

															<div class="col-sm-5">
																<input type="password" id="repeatPassword"
																	class="form-control" />
															</div>
														</div>
													</div>

													<div class="modal-footer silver">
														<button class="btn btn-warning">Cancel</button>
														<button class="btn btn-primary">Save</button>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="addphoto">
												<div id="adduserphoto" class="row client">
													<div class="col-sm-6 col-md-6">
														<form action="<%=serviceManager.getUploadUrl()%>"
															method="post" enctype="multipart/form-data">
															<input id="input-file" class="inactive file btn"
																type="file" name="photo" onchange="onFileSelected()" />
															<input hidden="true" name="userId"
																value="<c:out value="${omfuser.id}"/>" /> <input
																hidden="true" name="type" value="<c:out value="user"/>" />
															<input id="btn-post" class="active btn" type="submit"
																value="submit" />
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
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
