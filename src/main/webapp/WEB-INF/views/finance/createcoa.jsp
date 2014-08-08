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
<title>Create CoA</title>
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

					<div class="col-md-12">
						<ul class="breadcrumb">
							<li><a href="/finance/accountingcoa">CoA's</a></li>
							<li class="active">Create CoA</li>
						</ul>
						<form:form id="createcoa" modelAttribute="chartOfAccountsForm"
							class="form-horizontal" method="post" role="form">
							<fieldset>
								<legend>Create new CoA</legend>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="coaname">CoA
										Name<span class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="text" class="form-control" path="coaname" required="required" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="funds">Funds<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:input type="number" step="any" class="form-control" path="funds" required="required" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="office">Input office<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a Office"
											class="form-control chosen-select" path="office" tabindex="2">
											<form:option value="HEAD_OFFICE" selected="selected">Head
												Office</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label class="control-label col-sm-2" path="office">MFI Account Type<span
											class="required">*</span>
									</form:label>

									<div class="col-sm-3">
										<form:select data-placeholder="Choose a MFI Account Type"
											class="form-control chosen-select" path="mfiaccounttype"
											tabindex="2">
											<form:option value="ACCOUNT_PAYABLE" selected="selected">Account Payable</form:option>
											<form:option value="ACCOUNT_RECIEVABLE">Account Recievable</form:option>
										</form:select>
									</div>
								</div>
								<div class="col-md-offset-3">
									<a id="cancel" onclick="goback();" class="btn btn-default">Cancel</a>
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
