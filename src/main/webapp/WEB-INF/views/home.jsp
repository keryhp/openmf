<!DOCTYPE html>
<%@ page language="java"
	contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-GB" xml:lang="en-GB">
<head>
<meta charset="utf-8" />
<meta name="description"
	content="This is the 'Open Microfinance' landing page." />
<meta name="keywords" content="Open Microfinance" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Home page</title>
<link href="/favicon.ico" rel="shortcut icon" type="image/ico" />
<link type="text/css" rel="stylesheet" href="/static/css/generic.css" />
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.js"></script>
<![endif]-->
</head>

  <body>
  <div id="content">
     <h3>The Home Page</h3>
     <p>Welcome back <sec:authentication property="principal.nickname"/>.</p>
     <p>
     You can get to this page if you have authenticated and are a registered user.
     You are registered as
     <sec:authentication property="principal.forename"/> <sec:authentication property="principal.surname"/>.
     </p>
     <p>
     <a href="/logout.htm">Logout</a>.
     </p>
  </div>
  </body>
</html>
