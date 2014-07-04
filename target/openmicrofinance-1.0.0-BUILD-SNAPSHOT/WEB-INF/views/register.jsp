<!DOCTYPE html>
<%@ page language="java"
	contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description"
	content="This is the 'Open Microfinance' landing page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Registration</title>
<link href="/favicon.ico" rel="shortcut icon" type="image/ico" />
<link type="text/css" rel="stylesheet" href="/static/css/generic.css" />
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.js"></script>
<![endif]-->
</head>

<body>
<div id="content">
<p>
Welcome to the Open Microfinance prototype application, <sec:authentication property="principal.nickname" />.
Please enter your registration details in order to use the application.
</p>
<p>
The data you enter here will be registered in the application's GAE data store, keyed under your unique
Google Accounts identifier. It doesn't have to be accurate. When you log in again, the information will be automatically
retrieved.
</p>

<form:form id="register" method="post" modelAttribute="registrationForm">
  	<fieldset>
  		<form:label path="forename">
  		Forename:
 		</form:label> <form:errors path="forename" cssClass="fieldError" /><br />
  		<form:input path="forename" /> <br />

  		<form:label path="surname">
  		Surname:
 		</form:label><form:errors path="surname" cssClass="fieldError" /> <br />
  		<form:input path="surname" /><br />
	</fieldset>
	<input type="submit" value="Register"/>
</form:form>
</div>
</body>
</html>
