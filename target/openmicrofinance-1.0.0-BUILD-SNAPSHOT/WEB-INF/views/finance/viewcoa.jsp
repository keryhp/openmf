<!DOCTYPE html>
<%@page import="uk.ac.openmf.utils.*"%>
<%@page import="java.util.*"%>
<%@page import="uk.ac.openmf.model.*"%>
<%@page import="uk.ac.openmf.web.*"%>
<%@page import="uk.ac.openmf.services.*"%>
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
	OpenMFChartOfAccounts coa = (OpenMFChartOfAccounts) request
			.getAttribute("coa");
	ArrayList<OpenMFGeneralLedger> todaysledger = (ArrayList<OpenMFGeneralLedger>) request
			.getAttribute("todaysledger");
	ArrayList<OpenMFGeneralJournal> todaysjournal = (ArrayList<OpenMFGeneralJournal>) request
			.getAttribute("todaysjournal");
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
	<jsp:include page="/WEB-INF/views/header.jsp">
		<jsp:param name="username" value="<%=currentUser.getUsername()%>" />
		<jsp:param name="userid" value="<%=currentUser.getId()%>" />
	</jsp:include>
	<jsp:include page="/WEB-INF/views/leftnav.jsp"/>

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">
					<div class="paddedbottom10">
						<ul class="breadcrumb">
							<li><a href="/finance/accountingcoa">CoAs</a></li>
							<li class="active">CoA Details</li>
						</ul>
					</div>

					<div class="row">
						<div class="col-md-8 col-sm-8">
							<h3 class="client-title">
								<i class="fa fa-user fa-white"></i> <strong><c:out
										value="${coa.coaname }" escapeXml="true" /> | <c:out
										value="${coa.coaid}" escapeXml="true" /> | <c:out
										value="${coa.mfiaccounttype }" escapeXml="true" /> | <c:out
										value="${coa.office }" escapeXml="true" />  | <c:out
										value="${coa.funds }" escapeXml="true" /></strong>
							</h3>
						</div>
					</div>
					<div class="overflowhidden marginbottom0 ">
						<ul id="myTab" class="nav nav-tabs">
							<li class="active"><a href="#todaysledger" data-toggle="tab">Overview</a></li>
							<li class=""><a href="#ledgersearch" data-toggle="tab">Ledger Search</a></li>
							<li class=""><a href="#jorunalsearch" data-toggle="tab">Journal Search</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="todaysledger">
								<div class="col-md-12 col-sm-12">
									<div class="row client">
										<div class="col-sm-9 col-md-9 paddingleft0px">
											<div>
												<div>
													<div class="span gray-head">
														<span class="boldlabel"> <strong class="">General
																Legder Details</strong>
														</span>
													</div>
													<table class="table table-condensed">
														<tr class="graybg">
															<th>Id</th>
															<th>CoA Id</th>
															<th>Status</th>
															<th>Bal. Available</th>
															<th>Bal. Pending</th>
														</tr>
														<tr>
															<%
																for (OpenMFGeneralLedger generalledger : todaysledger) {
																	long ledgerId = generalledger.getId();
															%>
															<td><c:out
																	value="<%=generalledger.getGeneralledgerid()%>"
																	escapeXml="true" /></td>
															<td><c:out value="<%=generalledger.getCoaid()%>"
																	escapeXml="true" /></td>
															<td><c:out value="<%=generalledger.getStatus()%>"
																	escapeXml="true" /></td>
															<td><c:out
																	value="<%=generalledger.getBalanceavailable()%>"
																	escapeXml="true" /></td>
															<td><c:out
																	value="<%=generalledger.getBalancePending()%>"
																	escapeXml="true" /></td>
															<%
																}
															%>
														</tr>
													</table>
												</div>
												<div>
													<div class="span gray-head">
														<span class="boldlabel"> <strong>General
																Journal Overview</strong>
														</span>
													</div>
													<table class="table table-condensed">
														<tr class="graybg">
															<th>Transaction Type</th>
															<th>Client Acc#</th>
															<th>Posted By</th>
															<th>Approved By</th>
															<th>Transaction Amount</th>
															<th>Status</th>
														</tr>
														<%
															for (OpenMFGeneralJournal generaljournal : todaysjournal) {
																long journalId = generaljournal.getId();
														%>
														<tr>
															<td><c:out
																	value="<%=generaljournal.getTransactiontype()%>"
																	escapeXml="true" /></td>
															<td><c:out
																	value="<%=generaljournal.getClientaccountid()%>"
																	escapeXml="true" /></td>
															<td><c:out value="<%=generaljournal.getPostedby()%>"
																	escapeXml="true" /></td>
															<td><c:out
																	value="<%=generaljournal.getApprovedby()%>"
																	escapeXml="true" /></td>
															<td><c:out
																	value="<%=generaljournal.getTransactionamount()%>"
																	escapeXml="true" /></td>
															<td><c:out value="<%=generaljournal.getStatus()%>"
																	escapeXml="true" /></td>
														</tr>
														<%
															}
														%>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
							<!-- end of general tab -->
							<div class="tab-pane" id="ledgersearch">
								<br></br>
								<p>Ledger search</p>
							</div>
							<div class="tab-pane" id="jorunalsearch">
								<br />
								<p>Journal search</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
