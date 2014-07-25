<!DOCTYPE html>
<%@ page language="java"
	contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description"
	content="This is the 'Open Microfinance' Signin page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Welcome!</title>
<link href="/favicon.ico" rel="shortcut icon" type="image/ico" />
<!-- Bootstrap core CSS -->
<link type="text/css" href="/static/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css" href="/static/css/bootstrap-ext.css"
	rel="stylesheet" />
<!-- Custom dashboard styles -->
<link type="text/css" href="/static/css/dashboard.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="/static/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="/static/css/generic.css" />
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.js"></script>
<script src="/static/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<div id="content" class="container">

		<h2>
			Welcome
			<sec:authentication property="principal.nickname" />
			. You need to be a registered and approved user to access this application.
		</h2>
		<h2 class="form-signin-heading">Please Signin.</h2>

		<div style="width: 40%;">
			<form:form id="register" method="post"
				modelAttribute="registrationForm" class="form-signin" role="form">
				<fieldset>
					<form:label path="username">
  		Username:
 		</form:label>
					<form:errors path="username" cssClass="fieldError" />
					<br />

					<form:input path="username" type="text" class="form-control"
						placeholder="User name here" required="required"
						autofocus="autofocus" />
					<form:label path="password">
  		Password:
        </form:label>
					<form:errors path="password" cssClass="fieldError" />
					<br />

					<form:input path="password" type="password" class="form-control"
						placeholder="Password" required="required" />
				</fieldset>
				<p></p>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</form:form>
		</div>
	</div>
</body>
</html>
