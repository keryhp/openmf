<!DOCTYPE html>
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
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	OpenMFGroup group = (OpenMFGroup) request.getAttribute("group");
	ArrayList<OpenMFLoanAccount> loanAccounts = (ArrayList<OpenMFLoanAccount>) request
			.getAttribute("loanAccounts");
	ArrayList<OpenMFSavingsAccount> savingsAccounts = (ArrayList<OpenMFSavingsAccount>) request
			.getAttribute("savingsAccounts");
	int numofActiveLoans = 0;
	int numofActiveSavings = 0;
	double balLoan = 0.0;
	double totSavings = 0.0;
	PhotoServiceManager serviceManager = AppContext.getAppContext()
			.getPhotoServiceManager();
	OpenMFPhotoManager photoManager = AppContext.getAppContext()
			.getPhotoManager();
	OpenMFPhoto photo = (OpenMFPhoto) OMFUtils.getPhotoByTypeId(client
			.getId());
	ArrayList<OpenMFPhoto> photos = (ArrayList<OpenMFPhoto>) OMFUtils
			.getAllPhotoListByTypeId(client.getId());
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>View Client</title>
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
					<li><a href="/finance/accounting.htm"><i
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
						<c:when test="${client != null}">
							<div class="whitebg">
								<div class="col-md-12">
									<div>
										<ul class="breadcrumb">
											<li><a href="/clients.htm">Clients</a></li>
											<li class="active">View client</li>
										</ul>
									</div>
									<div class="row">
										<div class="col-md-8 col-sm-8">
											<h3 class="client-title">
												<i class="fa fa-user fa-white"></i> <strong><c:out
														value="${client.forename }" escapeXml="true" /> <c:out
														value="${client.surname }" escapeXml="true" /></strong> <small>
													<c:out value="<%=client.getAccountNumber()%>"></c:out> | <c:out
														value="<%=client.getExternalId()%>"></c:out> | <c:out
														value="<%=client.getSupervisor()%>"></c:out>
												</small>
											</h3>
										</div>
									</div>
									<div class="overflowhidden marginbottom0 ">
										<ul id="myTab" class="nav nav-tabs">
											<li class="active"><a href="#general" data-toggle="tab">General</a></li>
											<li class=""><a href="#address" data-toggle="tab">Address</a></li>
											<li class=""><a href="#transactions" data-toggle="tab">Transactions</a></li>
											<li class=""><a href="#addphoto" data-toggle="tab">Photos</a></li>
											<!-- <li class=""><a href="#tab" data-toggle="tab">Tab
											5</a></li> -->
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="general">
												<div class="col-md-12 col-sm-12">
													<div class="row primarydiv">
														<div class="pull-right">
															<span> <a
																onclick="createLoanAccountFn(<%=client.getId()%>)"
																class="btn btn-primary"> <i
																	class="fa fa-arrow-right fa-white"></i>Apply New Loan
															</a>
															</span> <span><a
																onclick="createSavingsAccountFn(<%=client.getId()%>)"
																class="btn btn-primary"><i
																	class="fa fa-dollar fa-white"></i>Add New Savings</a> </span>
															<%
																if (group == null) {
															%>
															<span> <a
																onclick="assignGroupFn(<%=client.getId()%>)"
																class="btn btn-primary"><i
																	class="fa fa-group fa-white"></i>Assign Group</a>
															</span>
															<%
																}
															%>
															<!-- <button type="button" class="btn btn-primary ">
																<i class="fa fa-user fa-white"></i>Unassign Staff
															</button> -->
														</div>
													</div>

													<div class="row client">
														<div class="col-sm-9 col-md-9 paddingleft0px">
															<div style="display: none">
																<h3>Client Closed</h3>
															</div>
															<div>
																<div class="pull-right">
																	<span class="">
																		<button type="button" class="btn-primary btn btn-sm"
																			onclick="showClosedTable('closedLoans');">View
																			Closed Loans</button> <!-- <button type="button" class="btn-primary btn btn-sm">View
																	Active Loans</button> -->
																	</span>
																</div>
																<div>
																	<div class="span gray-head">
																		<span class="boldlabel"> <strong class="">Loan
																				Account Overview</strong>
																		</span>
																	</div>
																	<table class="table table-condensed">
																		<tr class="graybg">
																			<th>Loan Account#</th>
																			<th>Loan Code</th>
																			<th>Approved Amount</th>
																			<th>Outstd Amt.</th>
																			<th>Start Date</th>
																			<th>Actions</th>
																		</tr>
																		<%
																			for (OpenMFLoanAccount loanAccount : loanAccounts) {
																						long laId = loanAccount.getId();
																						if (loanAccount.isActive()) {
																							numofActiveLoans++;
																							if (loanAccount.getBalanceoutstandingamount() != null) {
																								balLoan += new Double(
																										loanAccount
																												.getBalanceoutstandingamount())
																										.doubleValue();
																							}
																		%>
																		<%-- <tr class="pointer-main"
																			onclick="viewLoanAccountFn(<%=laId%>);"> --%>
																		<tr>
																			<%
																				if (loanAccount.isDefaulted()) {
																			%>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><i
																				class="fa fa-stop cstatusprogress"></i> <c:out
																					value="<%=loanAccount.getLoanaccountnumber()%>"
																					escapeXml="true" /></td>
																			<%
																				} else {
																			%>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><i
																				class="fa fa-circle cstatusactive"></i> <c:out
																					value="<%=loanAccount.getLoanaccountnumber()%>"
																					escapeXml="true" /></td>
																			<%
																				}
																			%>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getLoancode()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getApprovedamount()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getBalanceoutstandingamount()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getLoanstartdate()%>"
																					escapeXml="true" /></td>
																			<td class="pointer center"><a
																				onclick="loanRepayment(<%=laId%>);"
																				class="btn btn-xs btn-primary " tooltip="Repayment">
																					<i class="fa fa-plus-square fa-white"></i>
																			</a> <a onclick="loanDisburse(<%=laId%>);"
																				class="btn btn-xs btn-primary " tooltip="Disburse">
																					<i class="fa fa-minus-circle fa-white"></i>
																			</a> <%-- <a onclick="loanPenalty(<%=laId%>);"
																				class="btn btn-xs btn-primary "
																				tooltip="Add Penalty"> <i
																					class="fa fa-flag fa-red"></i>
																			</a> --%></td>
																		</tr>
																		<%
																			}
																					}
																		%>

																	</table>
																	<table id="closedLoans"
																		class="table table-condensed hide">
																		<tr class="graybg">
																			<th>Loan Account#</th>
																			<th>Loan Code</th>
																			<th>Approved Amount</th>
																			<th>Close Date</th>
																		</tr>
																		<%
																			for (OpenMFLoanAccount loanAccount : loanAccounts) {
																						long laId = loanAccount.getId();
																						if (!loanAccount.isActive()) {
																		%>
																		<tr class="pointer-main">
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><i
																				class="fa fa-stop cstatusprogress"></i> <c:out
																					value="<%=loanAccount.getLoanaccountnumber()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getLoancode()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><c:out
																					value="<%=loanAccount.getApprovedamount()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewLoanAccountFn(<%=laId%>);"><span><c:out
																						value="<%=loanAccount.getLoanclosedate()%>"
																						escapeXml="true" /></span></td>
																		</tr>

																		<%
																			}
																					}
																		%>

																	</table>
																</div>
																<div>
																	<div class="pull-right">
																		<span>
																			<button type="button" class="btn-primary btn btn-sm"
																				onclick="showClosedTable('closedSavings');">
																				View Closed Savings</button> <!-- <button type="button" class="btn-primary btn btn-sm">
																		View Active Savings</button> -->
																		</span>
																	</div>
																	<div class="span gray-head">
																		<span class="boldlabel"> <strong>Savings
																				Overview</strong>
																		</span>
																	</div>
																	<table class="table table-condensed">
																		<tr class="graybg">
																			<th>Account#</th>
																			<th>Savings Code</th>
																			<th class="center">Balance</th>
																			<th>Actions</th>
																		</tr>
																		<%
																			for (OpenMFSavingsAccount savingsAccount : savingsAccounts) {
																						long saId = savingsAccount.getId();
																						if (savingsAccount.isActive()) {
																							numofActiveSavings++;
																							if (savingsAccount.getAvailablebalance() != null) {
																								totSavings += new Double(
																										savingsAccount.getAvailablebalance())
																										.doubleValue();
																							}
																		%>
																		<tr>
																			<%
																				if (savingsAccount.isScheduledepositmissed()) {
																			%>
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><i
																				class="fa fa-circle cstatusprogress"></i> <c:out
																					value="<%=savingsAccount
										.getSavingsaccountnumber()%>"
																					escapeXml="true" /></td>
																			<%
																				} else {
																			%>
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><i
																				class="fa fa-circle cstatusactive"></i> <c:out
																					value="<%=savingsAccount
										.getSavingsaccountnumber()%>"
																					escapeXml="true" /></td>
																			<%
																				}
																			%>
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><c:out
																					value="<%=savingsAccount.getSavingscode()%>"
																					escapeXml="true" /></td>
																			<td class="pointer center"><c:out
																					value="<%=savingsAccount.getAvailablebalance()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"><a
																				onclick="savingsDeposit(<%=saId%>);"
																				class="btn btn-xs btn-primary "> <i
																					class="fa fa-plus-square fa-white"></i>
																			</a> <a onclick="savingsWithdrawal(<%=saId%>);"
																				class="btn btn-xs btn-primary "> <i
																					class="fa fa-minus-circle fa-white"></i>
																			</a></td>
																		</tr>
																		<%
																			}
																					}
																		%>
																	</table>
																	<table id="closedSavings"
																		class="table table-condensed hide">
																		<tr class="graybg">
																			<th>Account#</th>
																			<th>Savings Code</th>
																			<th>Closed Date</th>
																		</tr>
																		<%
																			for (OpenMFSavingsAccount savingsAccount : savingsAccounts) {
																						long saId = savingsAccount.getId();
																						if (!savingsAccount.isActive()) {
																		%>
																		<tr class="pointer-main">
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><i
																				class="fa fa-stop cstatusprogress"></i> <c:out
																					value="<%=savingsAccount.getSavingsaccountnumber()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><c:out
																					value="<%=savingsAccount.getSavingscode()%>"
																					escapeXml="true" /></td>
																			<td class="pointer"
																				onclick="viewSavingsAccountFn(<%=saId%>);"><span><c:out
																						value="<%=savingsAccount.getSavingsclosedate()%>"
																						escapeXml="true" /></span></td>
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
																	<strong><c:out value="${client.forename }"
																			escapeXml="true" /> <c:out
																			value="${client.midname }" escapeXml="true" /> <c:out
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
																		<th class="table-bold">Activation Date</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getActivationdate()%>"></c:out><span
																				style="display: none">not activated</span></span></td>
																	</tr>
																	<%
																		if (group != null) {
																	%>
																	<tr>
																		<th class="table-bold">Member of</th>
																		<td><span class="padded-td"><c:out
																					value="<%=group.getGroupname()%>"></c:out></span></td>
																	</tr>
																	<%
																		}
																	%>
																	<tr>
																		<th class="table-bold">Mobile number</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getContact()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Gender</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getGender()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Client Type</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getClienttype()%>"></c:out> </span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Client Classification</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getClientclassification()%>"></c:out>
																		</span></td>
																	</tr>

																	<tr>
																		<th class="table-bold">Date of Birth</th>
																		<td><span class="padded-td"><c:out
																					value="<%=client.getDateofbirth()%>"></c:out></span></td>
																	</tr>
																	<c:choose>
																		<c:when test="${client.active == false }">
																			<tr>
																				<th class="table-bold">Close Date</th>
																				<td><span class="padded-td"><c:out
																							value="NA"></c:out></span></td>
																			</tr>
																		</c:when>
																	</c:choose>
																	<tr>
																		<td colspan="2"><img style="height: 55px"
																			src="/static/images/demo_signature.jpg" alt="Avatar" /></td>
																	</tr>
																	<tr>
																		<th class="whitebg" colspan="2">Performance
																			History</th>
																	</tr>
																	<!-- <tr>
																		<th># of Loan Cycle</th>
																		<td><span class="padded-td">0</span></td>
																	</tr>
																	<tr>
																		<th>Last Loan Amount</th>
																		<td><span class="padded-td">0</span></td>
																	</tr>
																	 -->
																	<tr>
																		<th>Num of Active loans</th>
																		<td><span class="padded-td"><%=numofActiveLoans%></span></td>
																	</tr>
																	<tr>
																		<th>Loan Balance</th>
																		<td><span class="padded-td"><%=balLoan%></span></td>
																	</tr>

																	<tr>
																		<th># of Active Savings</th>
																		<td><span class="padded-td"><%=numofActiveSavings%></span></td>
																	</tr>
																	<tr>
																		<th>Total Savings</th>
																		<td><span class="padded-td"><%=totSavings%></span></td>
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
													<c:out value="<%=client.getAddress()%>"></c:out>
												</p>
											</div>
											<div class="tab-pane" id="transactions">
												<!-- <button class="btn btn-primary pull-right"
														onclick="export()">Export</button> -->
												<br />
												<table class="table table-striped">
													<colgroup span="4"></colgroup>
													<thead>
														<tr class="graybg">
															<th class="pointer" onclick="changeTransactionSort('id')">Id</th>
															<th class="pointer"
																onclick="changeTransactionSort('officeName')">Office
																Name</th>
															<th class="pointer"
																onclick="changeTransactionSort('date')">Transaction
																Date</th>
															<th class="pointer"
																onclick="changeTransactionSort('type.value')">Type</th>
															<th class="pointer"
																onclick="changeTransactionSort('amount')">Amount</th>
															<th class="pointer" colspan="5" scope="colgroup">Breakdown</th>
														</tr>
														<tr>
															<th scope="col"></th>
															<th scope="col"></th>
															<th scope="col"></th>
															<th scope="col"></th>
															<th scope="col"></th>
															<th class="pointer" scope="col"
																onclick="changeTransactionSort('principalPortion')">Principal</th>
															<th class="pointer" scope="col"
																onclick="changeTransactionSort('interestPortion')">Interest</th>
															<th class="pointer" scope="col"
																onclick="changeTransactionSort('feeChargesPortion')">Fees</th>
															<th class="pointer" scope="col"
																onclick="changeTransactionSort('penaltyChargesPortion')">Penalties</th>
														</tr>
													</thead>
													<tbody>
														<tr class="pointer-main">
															<td class="pointer" onclick="viewTransactionFn(id);">
																12334</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																Office Name</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																dd/mm/yyyy</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																withdrawal</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																100.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																105.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																5.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																0.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																0.00</td>
														</tr>
														<tr class="pointer-main">
															<td class="pointer" onclick="viewTransactionFn(id);">
																12335</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																Office Name</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																dd/mm/yyyy</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																withdrawal</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																100.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																105.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																5.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																0.00</td>
															<td class="pointer" onclick="viewTransactionFn(id);">
																0.00</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div class="tab-pane" id="addphoto">
												<div id="addclientphoto" class="row client">
													<div class="col-sm-6 col-md-6">
														<form action="<%=serviceManager.getUploadUrl()%>"
															method="post" enctype="multipart/form-data">
															<input id="input-file" class="inactive file btn"
																type="file" name="photo" onchange="onFileSelected()" />
															<input hidden="true" name="clientId"
																value="<c:out value="${client.id}"/>" /> <input
																hidden="true" name="type"
																value="<c:out value="client"/>" /> <input id="btn-post"
																class="active btn" type="submit" value="submit" />
														</form>
													</div>
												</div>
												<!-- view photos here -->
												<%
													for (OpenMFPhoto tmpPhoto : photos) {
												%>
												<div class="col-sm-3 col-md-3">
													<div class="thumbnail row">
														<img src="<%=serviceManager.getImageDownloadUrl(tmpPhoto)%>"
															alt="Photo Image" />
													</div>
												</div>
												<%
													}
												%>
											</div>
											<!-- <div class="tab-pane" id="tab">
											<p>Sample tab data.</p>
										</div> -->
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
