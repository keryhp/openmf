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
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	ArrayList<OpenMFLoanAccount> loanAccounts = (ArrayList<OpenMFLoanAccount>) request
	.getAttribute("loanAccounts");
	ArrayList<OpenMFSavingsAccount> savingsAccounts = (ArrayList<OpenMFSavingsAccount>) request
	.getAttribute("savingsAccounts");
	ArrayList<OpenMFTransaction> transactions = (ArrayList<OpenMFTransaction>) request
	.getAttribute("transactions");
	ArrayList<OpenMFLoanRepayment> repaymentschedules = (ArrayList<OpenMFLoanRepayment>) request
	.getAttribute("repaymentschedules");
	ArrayList<OpenMFSavingsScheduledDeposit> depositschedules = (ArrayList<OpenMFSavingsScheduledDeposit>) request
	.getAttribute("depositschedules");
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Client Report</title>
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
							<li><a href="/reports/clients">Reports</a></li>
							<li class="active">Clients Report</li>
						</ul>
						<div>
							<div class="pull-right">
								<button onclick="exportFromHTML(<%=client.getId() %>);" class="btn btn-primary">
									<i class="fa fa-plus fa fa-white"></i>Export
								</button>
							</div>
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
								</tr>
								<%
									for (OpenMFLoanAccount loanAccount : loanAccounts) {
										long laId = loanAccount.getId();
										if (loanAccount.isActive()) {
								%>
								<tr>
									<td><c:out value="<%=loanAccount.getLoanaccountnumber()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=loanAccount.getLoancode()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=loanAccount.getApprovedamount()%>"
											escapeXml="true" /></td>
									<td><c:out
											value="<%=loanAccount.getBalanceoutstandingamount()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=loanAccount.getLoanstartdate()%>"
											escapeXml="true" /></td>
								</tr>
								<%
									}
									}
								%>

							</table>
						</div>
						<div>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">Savings
										Account Overview</strong>
								</span>
							</div>
							<table class="table table-condensed">
								<tr class="graybg">
									<th>Savings Account#</th>
									<th>Savings Code</th>
									<th>Available Balance</th>
									<th>Total# Deposits</th>
									<th>Matures On</th>
								</tr>
								<%
									for (OpenMFSavingsAccount savingsAccount : savingsAccounts) {
										long saId = savingsAccount.getId();
										if (savingsAccount.isActive()) {
								%>
								<tr>
									<td><c:out
											value="<%=savingsAccount.getSavingsaccountnumber()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=savingsAccount.getSavingscode()%>"
											escapeXml="true" /></td>
									<td><c:out
											value="<%=savingsAccount.getAvailablebalance()%>"
											escapeXml="true" /></td>
									<td><c:out
											value="<%=savingsAccount.getTotalnumdeposits()%>"
											escapeXml="true" /></td>
									<td><c:out value="<%=savingsAccount.getMatureson()%>"
											escapeXml="true" /></td>
								</tr>
								<%
									}
									}
								%>

							</table>
						</div>
						<div>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">Loan
										Repayment Schedules</strong>
								</span>
							</div>
							<table class="table table-striped">
								<colgroup span="4"></colgroup>
								<thead>
									<tr>
										<th colspan="5" scope="colgroup"></th>
										<th colspan="2" scope="colgroup">Loan Amount Balance</th>
										<th colspan="3" scope="colgroup">Total Cost of Loan</th>
										<th colspan="6" scope="colgroup">Installment Totals</th>
									</tr>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Scheduled Date</th>
										<th scope="col">Paid Date</th>
										<th scope="col"></th>
										<th scope="col">Principal due</th>
										<th scope="col">Interest</th>
										<th scope="col">Fees</th>
										<th scope="col">Penalties</th>
										<th scope="col">Due</th>
										<th scope="col">Paid</th>
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
												value="<%=schedule.getSerialnumber()%>" escapeXml="true"></c:out></td>
										<td><c:out value="<%=schedule.getScheduledate()%>"
												escapeXml="true"></c:out></td>
										<td><c:out value="<%=schedule.getPaiddate()%>"
												escapeXml="true"></c:out></td>
										<td>
											<!-- paid --> <%
 	if (schedule.isPaid()) {
 %>
											<div>
												<i class="fa fa-check fa-green"></i>
											</div> <%
 	} else {
 %>
											<div>
												<i class="fa fa-times fa-red"></i>
											</div> <%
 	}
 %>

										</td>
										<td><c:out value="<%=schedule.getPrincipaldue()%>"
												escapeXml="true"></c:out></td>
										<td><c:out value="<%=schedule.getInterestamount()%>"
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
							</table>
						</div>
						<div>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">Savings
										Deposit Schedules</strong>
								</span>
							</div>
							<table class="table table-striped">
								<colgroup span="4"></colgroup>
								<thead>
									<tr>
										<th colspan="1" scope="colgroup"></th>
										<th colspan="2" scope="colgroup">Schedule</th>
										<th colspan="3" scope="colgroup">Savings</th>
										<th colspan="6" scope="colgroup">Totals</th>
									</tr>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Scheduled Date</th>
										<th scope="col">Paid Date</th>
										<th scope="col"></th>
										<th scope="col">Interest</th>
										<th scope="col">Deposit</th>
										<th scope="col">Balance</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (OpenMFSavingsScheduledDeposit schedule : depositschedules) {
											if (schedule.isActive()) {
									%>
									<tr>
										<td scope="row"><c:out
												value="<%=schedule.getSerialnumber()%>" escapeXml="true"></c:out></td>
										<td><c:out value="<%=schedule.getScheduledate()%>"
												escapeXml="true"></c:out></td>
										<td><c:out value="<%=schedule.getPaiddate()%>"
												escapeXml="true"></c:out></td>
										<td>
											<!-- paid --> <%
 	if (schedule.isPaid()) {
 %>
											<div>
												<i class="fa fa-check fa-green"></i>
											</div> <%
 	} else {
 %>
											<div>
												<i class="fa fa-times fa-red"></i>
											</div> <%
 	}
 %>

										</td>
										<td><c:out value="<%=schedule.getInterestamount()%>"
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
							</table>
						</div>
						<div>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">Transaction</strong>
								</span>
							</div>
							<table class="order-table table">
								<thead>
									<tr class="graybg">
										<th>Id</th>
										<th>From Acc#</th>
										<th>To Acc#</th>
										<th>Transaction Date</th>
										<th>Type</th>
										<th>Product Id</th>
										<th>Status</th>
										<th>Posted By</th>
										<th>Approved By</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (OpenMFTransaction transaction : transactions) {
											long saId = transaction.getId();
									%>
									<tr>
										<td><c:out value="<%=transaction.getId()%>" /></td>
										<td><c:out value="<%=transaction.getFromaccountid()%>" /></td>
										<td><c:out value="<%=transaction.getToaccountid()%>" /></td>
										<td><c:out
												value="<%=transaction.getDateoftransaction()%>" /></td>
										<td><c:out value="<%=transaction.getTransactiontype()%>" /></td>
										<td><c:out value="<%=transaction.getProductid()%>" /></td>
										<td><c:out value="<%=transaction.isStatus()%>" /></td>
										<td><c:out value="<%=transaction.getPostedby()%>" /></td>
										<td><c:out value="<%=transaction.getApprovedby()%>" /></td>
									</tr>
									<%
										}
									%>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
