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
	String loanaccountid = (String) request
			.getAttribute("loanaccountid");
	pageContext.setAttribute("loanaccountid", loanaccountid);
	pageContext.setAttribute("omfusers", omfusers);
	OpenMFClient client = (OpenMFClient) request.getAttribute("client");
	OpenMFLoanAccount lnaccdetails = (OpenMFLoanAccount) request
			.getAttribute("lnaccdetails");
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Loan Disburse</title>
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
	<jsp:include page="/WEB-INF/views/header.jsp">
		<jsp:param name="username" value="<%=currentUser.getUsername()%>" />
		<jsp:param name="userid" value="<%=currentUser.getId()%>" />
	</jsp:include>
	<jsp:include page="/WEB-INF/views/leftnav.jsp"/>

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">

					<div class="col-md-12">
						<ul class="breadcrumb">
							<li><a href="/clients">Clients</a></li>
							<li class="active">Make Loan Disburse</li>
						</ul>
						<form:form id="loandisburse" class="form-horizontal"
							method="post" modelAttribute="loanDisburseForm">
							<fieldset>
								<legend>Make a Disbursal</legend>
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
									<form:input path="loanaccountid" hidden="true" value="<%=loanaccountid %>"/>
									<label class="control-label col-sm-2">Loan Account</label>
									<div class="col-sm-3">
										<p>
											<c:out value="${lnaccdetails.loanaccountnumber}"
												escapeXml="true"></c:out>
											|
											<c:out value="${lnaccdetails.loancode}" escapeXml="true"></c:out>
										</p>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="disbursedamount">Amount Disbursed<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="number" step="any" path="disbursedamount"
											class="form-control" required="required" />
									</div>
									<form:label class="control-label col-sm-2" path="disbursedon">
										<i class="fa fa-calendar fa-1x"></i>Disbursed on<span
											class="required">*</span>
									</form:label>
									<div class="col-sm-2">
										<form:input path="disbursedon"
											class="form-control date-picker" required="required" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2"
										path="transactionreference">Transaction Reference<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" path="transactionreference"
											class="form-control" required="required" />
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
