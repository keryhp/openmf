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
	OpenMFLoanProduct lpdetails = (OpenMFLoanProduct) request
			.getAttribute("lpdetails");
	pageContext.setAttribute("lpdetails", lpdetails);
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Loan Product Details</title>
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
					<div class="whitebg ng-scope">
						<div class="col-md-12">
							<ul class="breadcrumb">
								<li><a href="/loanproducts">Loan Products</a></li>
								<li class="active">Overview</li>
							</ul>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">
										Details</strong>
								</span>
							</div>
							<table class="table table-condensed">
								<tr class="graybg">
									<th>Product Name</th>
									<th>Loan Id/Code</th>
									<th>Description</th>
									<th>Fund</th>
									<th>Loan Amount</th>
									<th>Start Date</th>
									<th>Close Date</th>
								</tr>
								<tr>
									<td><c:out value="${lpdetails.productname}"
											escapeXml="true" /></td>
									<td><c:out value="${lpdetails.loancode}" escapeXml="true" /></td>
									<td><c:out value="${lpdetails.description}"
											escapeXml="true" /></td>
									<td>OpenMF Institution</td>
									<td><c:out value="${lpdetails.loanamount}"
											escapeXml="true" /></td>
									<td><c:out value="${lpdetails.startdate}" escapeXml="true" /></td>
									<td><c:out value="${lpdetails.closedate}" escapeXml="true" /></td>
								</tr>
							</table>

							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">
										Settings</strong>
								</span>
							</div>
							<table class="table table-condensed">
								<tr class="graybg">
									<th>Amortization</th>
									<th>Interest Method</th>
									<th>Repayment Period in Months</th>
									<th>Repayment Frequency</th>
									<th>Interest Calculation Period</th>
									<th>%Rate of Interest</th>
								</tr>
								<tr>
									<td>Equal Installments</td>
									<td>Flat</td>
									<td><c:out value="${lpdetails.repaymentperiod}"
											escapeXml="true" /></td>
									<td><c:out value="${lpdetails.repaymentfrequency}"
											escapeXml="true" /></td>
									<td>Same as Repayment Period</td>
									<td><c:out value="${lpdetails.rateofinterest}"
											escapeXml="true" /></td>
								</tr>
							</table>
							<div class="span gray-head">
								<span class="boldlabel"> <strong class="">
										Charges</strong>
								</span>
							</div>
							<table class="table table-condensed">
								<tr class="graybg">
									<th>Charges applicable</th>
									<th>Is Active</th>
								</tr>
								<tr>
									<td><p>Late Payment charges @ 1% per installment</p></td>
									<c:choose>
										<c:when test="${lpdetails.active == true}">
											<td>Active</td>
										</c:when>
										<c:otherwise>
											<td>Closed or Stopped</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
