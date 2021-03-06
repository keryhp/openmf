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
	OpenMFGroup group = (OpenMFGroup) request.getAttribute("group");
	ArrayList<OpenMFClient> clients = (ArrayList<OpenMFClient>) request
			.getAttribute("clients");
	ArrayList<OpenMFLoanAccount> loanAccounts = (ArrayList<OpenMFLoanAccount>) request
			.getAttribute("loanAccounts");
	int numofActiveLoans = 0;
	double balLoan = 0.0;
	for (OpenMFLoanAccount loanAccount : loanAccounts) {
		if (loanAccount.isActive()) {
			numofActiveLoans++;
			if(loanAccount.getBalanceoutstandingamount() != null){
				balLoan += new Double(loanAccount.getBalanceoutstandingamount()).doubleValue();
			}
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Groups Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>View Group</title>
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
					<c:choose>
						<c:when test="${group != null}">
							<div class="whitebg">
								<div class="col-md-12">
									<div>
										<ul class="breadcrumb">
											<li><a href="/groups">Groups</a></li>
											<li class="active">View Group</li>
										</ul>
									</div>
									<div class="row">
										<div class="col-md-8 col-sm-8">
											<h3 class="client-title">
												<i class="fa fa-user fa-white"></i> <strong><c:out
														value="${group.groupname }" escapeXml="true" /></strong> <small>
													<c:out value="<%=group.getAccountnumber()%>"></c:out> | <c:out
														value="<%=group.getExternalId()%>"></c:out> | <c:out
														value="<%=group.getSupervisor()%>"></c:out>
												</small>
											</h3>
										</div>
									</div>
									<div class="overflowhidden marginbottom0 ">
										<ul id="myTab" class="nav nav-tabs">
											<li class="active"><a href="#general" data-toggle="tab">General</a></li>
											<li class=""><a href="#notes" data-toggle="tab">Notes</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="general">
												<div class="col-md-12 col-sm-12">
													<div class="row primarydiv">
														<div class="pull-right">
															<span> <a
																onclick="createGroupLoanAccountsFn(<%=group.getId()%>)"
																class="btn btn-primary"> <i
																	class="fa fa-arrow-right fa-white"></i>Apply New Group
																	Loan
															</a>
															</span>
														</div>
													</div>

													<div class="row client">
														<div class="col-sm-9 col-md-9 paddingleft0px">
															<div>
																<div class="pull-right">
																	<!-- <span class="">
																		<button type="button" class="btn-primary btn btn-sm"
																			onclick="showClosedLoans();">View Closed
																			Loans</button>
																	</span> -->
																</div>
																<div>
																	<div class="span gray-head">
																		<span class="boldlabel"> <strong class="">Group
																				Members</strong>
																		</span>
																	</div>
																	<table class="order-table table">
																		<thead>
																			<tr class="graybg">
																				<th>Name</th>
																				<th>Client#</th>
																				<th>Status</th>
																				<th>Office</th>
																				<th>Staff</th>
																			</tr>
																		</thead>
																		<tbody>
																			<%
																				int count = 0;
																						for (OpenMFClient client : clients) {
																							long clientId = client.getId().longValue();
																			%>
																			<tr class="pointer-main"
																				onclick="viewClientFn(<%=clientId%>);">
																				<td class="pointer"
																					onclick="viewClientFn(<%=clientId%>);"><c:out
																						value="<%=client.getForename()%>" escapeXml="true" />
																					<c:out value="<%=client.getSurname()%>"
																						escapeXml="true" /></td>
																				<td class="pointer"
																					onclick="viewClientFn(<%=clientId%>);"><c:out
																						value="<%=client.getAccountNumber()%>"></c:out></td>
																				<c:choose>
																					<c:when test="${client.active == false }">
																						<td class="pointer"
																							onclick="viewClientFn(<%=clientId%>);"><i
																							class="fa fa-stop cstatusprogress"></i>Closed</td>
																					</c:when>
																					<c:otherwise>
																						<td class="pointer"
																							onclick="viewClientFn(<%=clientId%>);"><i
																							class="fa fa-stop cstatusactive"></i>Active</td>
																					</c:otherwise>
																				</c:choose>
																				<td class="pointer"
																					onclick="viewClientFn(<%=clientId%>);"><c:out
																						value="<%=client.getOffice()%>"></c:out></td>
																				<td class="pointer"
																					onclick="viewClientFn(<%=clientId%>);"><c:out
																						value="<%=client.getSupervisor()%>"></c:out></td>
																			</tr>
																			<%
																				}
																			%>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														<div class="col-sm-3 col-md-3">
															<div class="thumbnail row">
																<h4>
																	<strong><c:out value="${group.groupname }"
																			escapeXml="true" /></strong>
																</h4>
																<img src="/static/images/demo_user.jpg" alt="Avatar" />
																<table class="table-minified">
																	<tr>
																		<th class="table-bold">Activation Date</th>
																		<td><span class="padded-td"><c:out
																					value="<%=group.getActivationdate()%>"></c:out><span
																				style="display: none">not activated</span></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Mobile number</th>
																		<td><span class="padded-td"><c:out
																					value="<%=group.getContact()%>"></c:out></span></td>
																	</tr>
																	<tr>
																		<th class="table-bold">Office</th>
																		<td><span class="padded-td"><c:out
																					value="<%=group.getOffice()%>"></c:out></span></td>
																	</tr>
																	<c:choose>
																		<c:when test="${group.active == false }">
																			<tr>
																				<th class="table-bold">Close Date</th>
																				<td><span class="padded-td"><c:out
																							value="NA"></c:out></span></td>
																			</tr>
																		</c:when>
																	</c:choose>
																	<tr>
																		<th class="whitebg" colspan="2">Performance
																			History</th>
																	</tr>
																	<tr>
																		<th># of Active Loans</th>
																		<td><span class="padded-td"><%=numofActiveLoans %></span></td>
																	</tr>
																	<tr>
																		<th>Total Loan Balance</th>
																		<td><span class="padded-td"><%=balLoan %></span></td>
																	</tr>
																	<tr>
																		<th>Eligibile</th>
																		<td><span class="padded-td"><%=group.isEligible() %></span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- end of general tab -->
											<div class="tab-pane" id="notes">
												<br></br>
												<p>Notes here</p>
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
