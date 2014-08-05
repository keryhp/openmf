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
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description" content="This is the Clients Info Page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Users</title>
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
							<ul class="breadcrumb">
								<li class="active">Users</li>
							</ul>
						</div>
						<div class="col-md-12">
							<div class="pull-right">
								<a href="/admin/createuser" class="btn btn-primary"><i
									class="fa fa-plus fa fa-white"></i>Create User</a>
							</div>
							<input type="search"
								class="light-table-filter marginbottom0px form-control"
								data-table="order-table"
								placeholder="Filter by Name/Account#/Staff/Office" />

							<table class="order-table table">
								<thead>
									<tr class="graybg">
										<th>Name</th>
										<th>Email</th>
										<th>Office</th>
										<th>Contact#</th>
										<th>Role</th>
									</tr>
								</thead>
								<tbody>
									<%
										int count = 0;
										for (OpenMFUser user : omfusers) {
											long omfuId = user.getId();
									%>
									<tr class="pointer-main"
										onclick="viewUserDetailsFn(<%=omfuId%>);">
										<td class="pointer" onclick="viewUserDetailsFn(<%=omfuId%>);"><c:out
												value="<%=user.getUsername()%>" escapeXml="true" /></td>
										<td class="pointer" onclick="viewUserDetailsFn(<%=omfuId%>);"><c:out
												value="<%=user.getEmail()%>" escapeXml="true" /></td>
										<td class="pointer" onclick="viewUserDetailsFn(<%=omfuId%>);"><c:out
												value="<%=user.getMain_office()%>" escapeXml="true" /></td>
										<td class="pointer" onclick="viewUserDetailsFn(<%=omfuId%>);"><c:out
												value="<%=user.getContact()%>" escapeXml="true" /></td>
										<td class="pointer" onclick="viewUserDetailsFn(<%=omfuId%>);"><c:out
												value="<%=user.getRole()%>" escapeXml="true" /></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
							<ul class="pager">
								<li class="previous"><a id="prev" href=""><i
										class="fa fa-long-arrow-left"></i> Previous</a></li>
								<li class="next"><a id="next" href="">Next <i
										class="fa fa-long-arrow-right"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
