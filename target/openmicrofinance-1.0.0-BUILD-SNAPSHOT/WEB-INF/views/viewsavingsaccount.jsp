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
	OpenMFSavingsAccount sgaccdetails = (OpenMFSavingsAccount) request
			.getAttribute("sgaccdetails");
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	OpenMFSavingsProduct savingsproduct = (OpenMFSavingsProduct) request
			.getAttribute("savingsproduct");
	ArrayList<OpenMFSavingsScheduledDeposit> depositschedules = (ArrayList<OpenMFSavingsScheduledDeposit>) request
			.getAttribute("depositschedules");
	pageContext.setAttribute("depositschedules", depositschedules);

%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>View Savings Account</title>
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
					<div class="whitebg">
						<div class="col-md-12">
							<div>
								<ul class="breadcrumb">
									<li><a href="/clients">Clients</a></li>
									<li class="active">View Loan Account</li>
								</ul>
							</div>
							<div>

								<div class="span gray-head" style="margin-left: 0%;">
									<span style="margin-left: 10px; font-size: 20px"> 
									<% if(sgaccdetails.isActive()) {%>
									<strong>
											<i class="fa fa-stop cstatusactive"></i>Savings Acc# <c:out
												value="${sgaccdetails.savingsaccountnumber}"
												escapeXml="true"></c:out> | <c:out
												value="${sgaccdetails.savingscode}" escapeXml="true"></c:out>
											| Client Acc# <c:out value="${client.accountNumber}"
												escapeXml="true"></c:out>
									</strong>
									<% } else { %>
									<strong>
											<i class="fa fa-stop cstatusprogress"></i>Savings Acc# <c:out
												value="${sgaccdetails.savingsaccountnumber}"
												escapeXml="true"></c:out> | <c:out
												value="${sgaccdetails.savingscode}" escapeXml="true"></c:out>
											| Client Acc# <c:out value="${client.accountNumber}"
												escapeXml="true"></c:out>
									</strong>
									<%} %>
									</span>
								</div>

								<div class="col-sm-12 col-md-12 primarydiv">
									<div class="pull-right">
										<span> <a
											onclick="savingsDeposit(<%=sgaccdetails.getId()%>);"
											class="btn btn-primary"> <i class="fa fa-plus-square fa-white"></i>Deposit
										</a>
										</span> <span><a
											onclick="savingsWithdrawal(<%=sgaccdetails.getId()%>);"
											class="btn btn-primary"><i class="fa fa-minus-circle fa-white"></i>Withdrawal</a>
										</span>
									</div>
								</div>

								<div class="row span"
									style="margin-left: -1px; margin-top: -20px">
									<div class="col-sm-6 col-md-6">
										<table class="table table-striped table-bordered">
											<tbody>
												<tr>
													<th class="table-bold-loan">Maturity Date</th>
													<td><span class="padded-td"><c:out
																value="${sgaccdetails.matureson}" escapeXml="true"></c:out><span
															class="hide">Not Available</span></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Savings Supervisor</th>
													<td><span class="padded-td"><span class="hide">Unassigned</span>
															<c:out value="${sgaccdetails.savingsofficer}"
																escapeXml="true"></c:out><span> <a
																onclick="unassignsavingssupervisor(<%=sgaccdetails.getId()%>)"><i
																	class="fa fa-times"></i></a>
														</span> </span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">ExternalId</th>
													<td><span class="padded-td"><c:out
																value="${client.externalId}" escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Frequency (days)</th>
													<td><span class="padded-td"><c:out
																value="${savingsproduct.depositfrequency}"
																escapeXml="true"></c:out></span></td>
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
																value="${sgaccdetails.savingspurpose}" escapeXml="true"></c:out><span
															class="hide">Not provided</span></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Total Deposit Amount</th>
													<td><span class="padded-td"><c:out
																value="${sgaccdetails.totalprincipaldeposit}"
																escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Withdrawn Amount</th>
													<td><span class="padded-td"><c:out
																value="TBD"
																escapeXml="true"></c:out></span></td>
												</tr>
												<tr>
													<th class="table-bold-loan">Balance Available</th>
													<td><span class="padded-td"><c:out
																value="${sgaccdetails.availablebalance}"
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
													data-toggle="tab">Deposit Schedule</a></li>
												<li class=""><a href="#savingsdocs" data-toggle="tab">Savings
														Documents</a></li>
												<li class=""><a href="#guarantor" data-toggle="tab">Beneficiary
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
																			value="${savingsproduct.savingstype}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Deposit and Withdrawal Strategy</td>
																<td><span class="padded-td">Manual by field
																		officer</span></td>
															</tr>
															<tr>
																<td>Total Number of Deposits</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.totalnumdeposits}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<%-- <tr>
																<td>Number of Repayments made</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.numrepaymentsmade}"
																			escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Number of Repayments missed</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.numpaymentsmissed}"
																			escapeXml="true"></c:out></span></td>
															</tr> --%>
															<tr>
																<td>% Rate of Interest</td>
																<td><span class="padded-td"><c:out
																			value="${savingsproduct.rateofinterest}"
																			escapeXml="true"></c:out> % per annum | Flat</span></td>
															</tr>
															<tr>
																<td>Interest Calculation Period</td>
																<td><span class="padded-td">Same as tenure
																		period</span></td>
															</tr>
															<tr>
																<td>Submitted on</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.submittedon}" escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Approved on</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.approvedon}" escapeXml="true"></c:out></span></td>
															</tr>
															<tr>
																<td>Starts from</td>
																<td><span class="padded-td"><c:out
																			value="${sgaccdetails.savingsstartdate}"
																			escapeXml="true"></c:out></span></td>
															</tr>
														</tbody>
													</table>
												</div>

												<div class="tab-pane" id="repmntschedule">
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
																<td><c:out
																		value="<%=schedule.getInterestamount()%>"
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
														<!-- <tfoot class="ui-widget-header">
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
														</tfoot> -->
													</table>
												</div>											
												<div class="tab-pane" id="savingsdocs">
													<div class="pull-right">
														<a href="#/addsavingsdocument/savingsdetailsid"
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
