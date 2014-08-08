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
	UserService userService = UserServiceFactory.getUserService();
	AppContext appContext = AppContext.getAppContext();
	ConfigManager configManager = appContext.getConfigManager();
	OpenMFUser currentUser = appContext.getCurrentUser();
	String clientId = (String) request.getAttribute("clientId");
	ArrayList<OpenMFSavingsProduct> savingsProducts = (ArrayList<OpenMFSavingsProduct>) request
			.getAttribute("savingsProducts");
	ArrayList<OpenMFUser> omfusers = (ArrayList<OpenMFUser>) request
			.getAttribute("omfusers");
	pageContext.setAttribute("omfusers", omfusers);
	pageContext.setAttribute("clientId", clientId);
	pageContext.setAttribute("savingsProducts", savingsProducts);
	ArrayList<String> tmpLA = new ArrayList<String>();
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create Savings Account</title>
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
								<li><a href="/clients">Clients</a></li>
								<li class="active">Apply Savings</li>
							</ul>
							<form:form id="createsavingsaccount" method="post"
								modelAttribute="savingsAccountForm" role="form"
								class="form-horizontal well">
								<fieldset>
									<h3>Create Savings Account</h3>
									<hr></hr>
									<div class="form-group">
										<label class="control-label col-sm-2" id="clientId">Client
											Id </label>
										<div class="col-sm-2">
											<p class="control-label col-sm-2"><%=clientId%></p>
										</div>
										<form:label class="control-label col-sm-2" path="savingsofficer">Savings Officer
										</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a savings officer"
												class="form-control chosen-select" path="savingsofficer"
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
										<form:label class="control-label col-sm-2" path="savingscode">Savings Id/code
										</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose a Type"
												class="form-control chosen-select" path="savingscode"
												tabindex="2" onchange="updateSavingsAmount(this);">
												<%
													for (OpenMFSavingsProduct savingsprod : savingsProducts) {
																tmpLA.add(savingsprod.getSavingsamount());
												%>
												<form:option value="<%=savingsprod.getSavingscode()%>">
													<c:out value="<%=savingsprod.getSavingscode()%>" escapeXml="true" />
												</form:option>
												<%
													}
												%>
											</form:select>
											<select id="lavalues" class="hide">
												<%
													for (String la : tmpLA) {
												%>
												<option value="<%=la%>">
													<c:out value="<%=la%>" escapeXml="true" />
												</option>
												<%
													}
												%>
											</select>
										</div>

										<form:label class="control-label col-sm-2" path="savingspurpose">Purpose</form:label>
										<div class="col-sm-2">
											<form:textarea rows="2" class="form-control"
												path="savingspurpose"></form:textarea>
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2" path="approvedby">Approved By<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:select data-placeholder="Choose an approver"
												class="form-control chosen-select" path="approvedby"
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

										<form:label class="control-label col-sm-2"
											path="savingsstartdate">
											<i class="fa fa-calendar fa-1x"></i>Start Date<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input path="savingsstartdate"
												class="form-control date-picker" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="approvedon">
											<i class="fa fa-calendar fa-1x"></i>Approval Date
										</form:label>
										<div class="col-sm-2">
											<form:input path="approvedon"
												class="form-control date-picker" required="required" />
										</div>
									</div>


									<div class="form-group">
										<form:label class="control-label col-sm-2" path="submittedon">
											<i class="fa fa-calendar fa-1x"></i>Submission Date<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input path="submittedon"
												class="form-control date-picker" required="required" />
										</div>
										<form:label class="control-label col-sm-2" path="matureson">
											<i class="fa fa-calendar fa-1x"></i>Maturity Date<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input path="matureson"
												class="form-control date-picker" required="required" />
										</div>
									</div>
									<div class="form-group">
										<form:label class="control-label col-sm-2"
											path="totalprincipaldeposit">Deposit amount (if any)<span
												class="required">*</span>
										</form:label>
										<div class="col-sm-2">
											<form:input type="number" step="any" path="totalprincipaldeposit"
												class="form-control" required="required" />
										</div>
									</div>
									<div class="form-group">
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
