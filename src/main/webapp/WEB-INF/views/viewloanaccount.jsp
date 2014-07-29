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
	OpenMFLoanAccount lnaccdetails = (OpenMFLoanAccount) request
			.getAttribute("lnaccdetails");
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	OpenMFLoanProduct loanproduct = (OpenMFLoanProduct) request
			.getAttribute("loanproduct");
	ArrayList<OpenMFLoanRepayment> repaymentschedules = (ArrayList<OpenMFLoanRepayment>) request
			.getAttribute("repaymentschedules");
	pageContext.setAttribute("repaymentschedules", repaymentschedules);
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>View Loan Account</title>
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
					<li><a href="/finance/accounting.htm"><i class="fa fa-money"></i>
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
					<div class="whitebg">
						<div class="col-md-12">
							<div>
								<ul class="breadcrumb">
									<li><a href="/clients.htm">Clients</a></li>
									<li class="active">View Loan Account</li>
								</ul>
							</div>
							<div>

								<div class="span gray-head" style="margin-left: 0%;">
									<span style="margin-left: 10px; font-size: 20px"> <strong>
											<i class="fa fa-stop cstatuscode"></i>Loan Acc# <c:out
												value="${lnaccdetails.loanaccountnumber}" escapeXml="true"></c:out>
											| <c:out value="${lnaccdetails.loancode}" escapeXml="true"></c:out>
											| Client Acc# <c:out value="${client.accountNumber}"
												escapeXml="true"></c:out>
									</strong>
									</span>
								</div>

								<div class="col-sm-12 col-md-12 primarydiv">
									<div class="pull-right">
										<span> <a
											onclick="assignfieldofficer(<%=lnaccdetails.getId()%>);"
											class="btn btn-primary"><i class="fa fa-user fa-white"></i>Assign
												Field Officer</a>
										</span> <span> <a
											onclick="loanDisburse(<%=lnaccdetails.getId()%>);"
											class="btn btn-primary"> <i class="fa fa-check fa-white"></i>Disburse
										</a>
										</span> <span><a
											onclick="loanRepayment(<%=lnaccdetails.getId()%>);"
											class="btn btn-primary"><i class="fa fa-times fa-white"></i>Repayment</a>
										</span>
									</div>
								</div>

								<div class="row span"
									style="margin-left: -1px; margin-top: -20px">
									<div class="col-sm-6 col-md-6">
										<table class="table table-striped table-bordered">
											<tbody>
												<tr>
													<th class="table-bold-loan">Disbursement Date</th>
													<td><span class="padded-td"><c:out
																value="${lnaccdetails.disbursedon}" escapeXml="true"></c:out><span
															class="hide">Not Available</span></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Currency</th>
													<td><span class="padded-td">GBP</span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Loan Supervisor</th>
													<td><span class="padded-td"><span class="hide">Unassigned</span>
															<c:out value="${lnaccdetails.loanofficer}"
																escapeXml="true"></c:out><span> <a
																onclick="unassignloansupervisor(<%=lnaccdetails.getId()%>)"><i
																	class="fa fa-times"></i></a>
														</span> </span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Field Officer</th>
													<td><span class="padded-td"><c:out
																value="${client.supervisor}" escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">ExternalId</th>
													<td><span class="padded-td"><c:out
																value="${client.externalId}" escapeXml="true"></c:out></span></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="col-sm-6 col-md-6">
										<table class="table table-striped table-bordered">
											<tbody>
												<tr>
													<th class="table-bold-loan">Loan Purpose</th>
													<td><span class="padded-td"><c:out
																value="${lnaccdetails.loanpurpose}" escapeXml="true"></c:out><span
															class="hide">Not provided</span></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Approved Amount</th>
													<td><span class="padded-td"><c:out
																value="${lnaccdetails.approvedamount}" escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Disburse Amount</th>
													<td><span class="padded-td"><c:out
																value="${lnaccdetails.disbursedamount}" escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Arrears By</th>
													<td><span class="padded-td"><c:out
																value="${lnaccdetails.arrearsby}" escapeXml="true"></c:out><span
															class="hide">Not Provided</span></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Frequency</th>
													<td><span class="padded-td"><c:out
																value="${loanproduct.repaymentfrequency}"
																escapeXml="true"></c:out></span></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<div class="row paddedleft">
									<hr class="marginbottom" />
									<!-- tabset -->
									<div>
										<!-- tab container -->
										<div>
											<ul id="myTab" class="nav nav-tabs">
												<li class="active"><a href="#accountdetails"
													data-toggle="tab">Account Details</a></li>
												<li class=""><a href="#repmntschedule"
													data-toggle="tab">Repayment Schedule</a></li>
												<li class=""><a href="#transactions" data-toggle="tab">Transactions</a></li>
												<li class=""><a href="#loandocs" data-toggle="tab">Loan
														Documents</a></li>
												<li class=""><a href="#guarantor" data-toggle="tab">Guarantor
														details</a></li>
												<li class=""><a href="#notes" data-toggle="tab">Notes</a></li>
											</ul>

											<div class="tab-content">
												<div class="tab-pane active" id="accountdetails">
													<table class="table table-striped">
														<thead>
															<tr>
																<th class="empty"></th>
																<th class="empty"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Loan Type</td>
																<td><span class="padded-td"><c:out
																			value="${loanproduct.loantype}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Repayment Strategy</td>
																<td><span class="padded-td">Manual
																		collection and submission by field officer</span></td>
															</tr>
															<tr>
																<td>Total Number of Repayments</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.totalnumrepayments}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Number of Repayments made</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.numrepaymentsmade}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Number of Repayments missed</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.numpaymentsmissed}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Amortization</td>
																<td><span class="padded-td">Equal
																		Installments</span></td>
															</tr>
															<tr>
																<td>% Rate of Interest</td>
																<td><span class="padded-td"><c:out
																			value="${loanproduct.rateofinterest}"
																			escapeXml="true"></c:out> % per annum | Flat</span></td>
															</tr>
															<tr>
																<td>Fund source</td>
																<td><span class="padded-td">OpenMF
																		Institution</span></td>
															</tr>
															<tr>
																<td>Interest Calculation Period</td>
																<td><span class="padded-td">Same as
																		repayment period</span></td>
															</tr>
															<tr>
																<td>Submitted on</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.submittedon}" escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Approved on</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.approvedon}" escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Disbursed on</td>
																<td><span class="padded-td"><c:out
																			value="${lnaccdetails.disbursedon}" escapeXml="true"></c:out></span></td>
															</tr>
														</tbody>
													</table>
												</div>

												<div class="tab-pane" id="repmntschedule">
													<table class="table table-striped">
														<colgroup span="4"></colgroup>
														<thead>
															<tr>
																<th colspan="5" scope="colgroup"></th>
																<th colspan="2" scope="colgroup">Loan Amount
																	Balance</th>
																<th colspan="3" scope="colgroup">Total Cost of Loan</th>
																<th colspan="6" scope="colgroup">Installment Totals</th>
															</tr>
															<tr>
																<th scope="col">#</th>
																<!-- <th scope="col">Days</th> -->
																<th scope="col">Scheduled Date</th>
																<th scope="col">Paid Date</th>
																<th scope="col"></th>
																<th scope="col">Principal due</th>
																<!-- <th scope="col">Balance of Loan</th> -->
																<th scope="col">Interest</th>
																<th scope="col">Fees</th>
																<th scope="col">Penalties</th>
																<th scope="col">Due</th>
																<th scope="col">Paid</th>
																<!-- <th scope="col">In Advance</th>
																<th scope="col">Late</th>
																<th scope="col">Waived</th> -->
																<th scope="col">Outstanding</th>
															</tr>
														</thead>
														<tbody>
															<%
																for (OpenMFLoanRepayment schedule : repaymentschedules) {
																	if (schedule.isActive()) {
															%>
															<tr>
																<td scope="row"><c:out
																		value="<%=schedule.getSerialnumber()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getScheduledate()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getPaiddate()%>"
																		escapeXml="true"></c:out></td>
																<td>
																	<!-- paid -->
																	<%
																	if(schedule.isPaid()){
																	%>
																	<div>
																		<i class="fa fa-check fa-green"></i>
																	</div>
																	<%
																	}else{
																	%>
																	<div>
																		<i class="fa fa-times fa-red"></i>
																	</div>
																	<%
																	}
																	%>
																	
																</td>
																<td><c:out value="<%=schedule.getPrincipaldue()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out
																		value="<%=schedule.getInterestamount()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getFees()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getPenalties()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getDueamount()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out value="<%=schedule.getPaidamount()%>"
																		escapeXml="true"></c:out></td>
																<td><c:out
																		value="<%=schedule.getBalanceoutstandingamount()%>"
																		escapeXml="true"></c:out></td>
															</tr>
															<%
																}
																}
															%>
														</tbody>
														<tfoot class="ui-widget-header">
															<tr>
																<th></th>
																<th colspan="3">Total</th>
																<th>1000.00</th>

																<th>1000.00</th>
																<th>0.00</th>
																<th>1000.00</th>
																<th>0.00</th>
																<th>0.00</th>
																<th>1000.00</th>
															</tr>
														</tfoot>
													</table>
												</div>

												<div class="tab-pane" id="transactions">
													<button class="btn btn-primary pull-right"
														onclick="export()">Export</button>
													<div class="pull-right">
														<input type="checkbox" /> <label class="control-label">Hide
															Accrual Transactions</label>
													</div>
													<br />
													<table class="table table-striped">
														<colgroup span="4"></colgroup>
														<thead>
															<tr class="graybg">
																<th class="pointer"
																	onclick="changeTransactionSort('id')">Id</th>
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
												<div class="tab-pane" id="loandocs">
													<div class="pull-right">
														<a href="#/addloandocument/loandetailsid"
															class="btn btn-primary"><i
															class="fa fa-plus fa-white"></i>Upload</a>
													</div>
													<br />
													<table class="table table-striped">
														<thead>
															<tr>
																<th>Name</th>
																<th>Description</th>
																<th>Filename</th>
																<th>Actions</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>doc name</td>
																<td>doc description</td>
																<td>doc fileName</td>
																<td><a target="_blank"
																	href="hostUrl-document-docUrl"><i
																		class="fa fa-cloud-download"></i></a> <a
																	onclick="deleteDocument(documentid);"><i
																		class="fa fa-times"></i></a></td>
															</tr>
															<tr>
																<td>doc name</td>
																<td>doc description</td>
																<td>doc fileName</td>
																<td><a target="_blank"
																	href="hostUrl-document-docUrl"><i
																		class="fa fa-cloud-download"></i></a> <a
																	onclick="deleteDocument(documentid);"><i
																		class="fa fa-times"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="tab-pane" id="guarantor">
													<p>under construction</p>
												</div>
												<div class="tab-pane" id="notes">
													<p>under construction</p>
												</div>


											</div>
											<!-- end of tab content -->

										</div>
									</div>
									<!-- tabset ends -->
								</div>
								<!-- for col md 12 before bread crumb -->
							</div>
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
