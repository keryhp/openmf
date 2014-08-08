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
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create Loan Product</title>
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
								<li class="active">Create Loan Product</li>
							</ul>
							<form:form id="createloanproduct" method="post"
								modelAttribute="loanProductForm" role="form"
								class="form-horizontal well">
								<fieldset>
									<h3>Details</h3>
									<hr></hr>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="productname">Product Name<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="text" path="productname"
												class="form-control" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="loancode">Loan Id/code<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="text" path="loancode" class="form-control"
												maxlength="8" required="required" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="description">Description</form:label>
										<div class="col-sm-2">
											<form:textarea rows="2" class="form-control"
												path="description"></form:textarea>
										</div>
										<form:label class="control-label col-sm-2" path="fund">Fund</form:label>
										<div class="col-sm-3">
											<form:select data-placeholder="Choose a fund"
												class="form-control chosen-select" path="fund" tabindex="2">
												<form:option value="openmfInstitution" selected="selected">OpenMF Institution</form:option>
												<!-- <option value="branch1">Fund two</option>
												<option value="branch2">Fund three</option> -->
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="loanamount">Loan Amount in GBP</form:label>
										<div class="col-sm-2">
											<form:input rows="2" type="number" step="any" class="form-control" path="loanamount" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="loantype">Type</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a Type"
												class="form-control chosen-select" path="loantype"
												tabindex="2">
												<form:option value="SHORTTERM" selected="selected">Short term</form:option>
												<form:option value="REPETITIVE">Repetitive</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="startdate">
											<i class="fa fa-calendar fa-1x"></i>Start Date</form:label>
										<div class="col-sm-2">
											<form:input path="startdate" class="form-control date-picker" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="closedate">
											<i class="fa fa-calendar fa-1x"></i>Close
											Date</form:label>
										<div class="col-sm-2">
											<form:input path="closedate" class="form-control date-picker" required="required" />
										</div>
									</div>
									<h3>Settings</h3>
									<hr></hr>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="amortization">Amortization<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<p>Equal Installments</p>
										</div>
										<form:label class="control-label col-sm-2 col-sm-offset-1"
											path="interestmethod">Interest
											Method<span class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<p>Flat</p>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2"
											path="repaymentperiod">Repayment Period in months<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="number" step="any" path="repaymentperiod"
												class="form-control" required="required" />
										</div>
										<form:label class="control-label col-sm-2"
											path="repaymentfrequency">Repayment Frequency<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a frequency"
												class="form-control chosen-select" path="repaymentfrequency"
												tabindex="2">
												<form:option value="1" selected="selected">Daily</form:option>
												<form:option value="7">Weekly</form:option>
												<form:option value="15">Fortnight</form:option>
												<form:option value="30">Monthly</form:option>
												<form:option value="90">Three Months</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2"
											path="interestcalcperiod">Interest
											Calculation Period<span class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<p>Same as repayment period</p>
										</div>
										<form:label class="control-label col-sm-2"
											path="rateofinterest" type="number" step="any">% Rate of Interest<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="text" path="rateofinterest"
												class="form-control" required="required" />
										</div>
									</div>
									<h3>Charges</h3>
									<hr></hr>
									<div class="form-group">
										<form:label class="control-label col-sm-2"
											path="chargesapplicable">Charges applicable<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-4">
											<form:select data-placeholder="Choose a charge"
												class="form-control chosen-select" path="chargesapplicable"
												tabindex="2">
												<form:option value="1" selected="selected">Late
													Payment charges @ 1% per installment</form:option>
											</form:select>
										</div>
										<form:label class="control-label col-sm-2" path="active">Active ?</form:label>
										<div class="checkbox col-sm-2">
											<label> <form:checkbox path="active" />
											</label>
										</div>
									</div>
									<div class="col-md-offset-5 paddedtop">
										<button id="cancel" type="reset" class="btn btn-warning">Cancel</button>
										<button id="save" type="submit" class="btn btn-primary">Save</button>
									</div>
								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
