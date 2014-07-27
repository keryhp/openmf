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
<meta name="description"
	content="This is the 'Open Microfinance' landing page." />
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
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<a class="navbar-brand" href="/">OpenMF</a>
				<ul class="nav navbar-nav" id="main-menu-left">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><i class="fa fa-group"></i>
							Clients<b class="caret"></b></a>
						<ul class="dropdown-menu" id="swatch-menu">
							<li><a href="/clients.htm">Clients</a></li>
							<li><a href="/groups.htm">Groups</a></li>
							<li><a href="/centers.htm">Centers</a></li>
						</ul></li>
					<li><a href="/accounting.htm"><i class="fa fa-money"></i>
							Accounting</a></li>
					<li class="dropdown" id="reports-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-bar-chart-o"></i> Reports<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/reports/allrep.htm">All</a></li>
							<li><a href="/reports/clientsrep.htm">Clients</a></li>
							<li><a href="/reports/loansrep.htm">Loans</a></li>
							<li><a href="/reports/savingsrep.htm">Savings</a></li>
							<li><a href="/reports/fundsrep.htm">Funds</a></li>
							<li><a href="/reports/accountingrep.htm">Accounting</a></li>
						</ul></li>
					<li class="dropdown" id="preview-menu"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"><i
							class="fa fa-wrench"></i> Admin<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/users.htm">Users</a></li>
							<li><a href="/organization.htm">Organization</a></li>
							<li><a href="/system.htm">System</a></li>
							<li><a href="/products.htm">Products</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="main-menu-right">
					<li class="dropdown" id="user-menu"><a id="user-dropdown"
						class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out
								value="<%=currentUser.getUsername()%>"></c:out><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a id="help" href="/help.htm"><i
									class="fa fa-question-circle"></i> Help</a></li>
							<li><a href="/viewuser.htm?omfuId=<%=currentUser.getId()%>"><i class="fa fa-user"></i>
									Profile</a></li>
							<li><a href="/usersetting.htm"><i class="fa fa-cog"></i>
									Settings</a></li>
							<li><a href="/logout.htm"><i class="fa fa-off"></i>Logout</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input id="search" type="text" placeholder="Search"
						class="form-control search-query col-md-4" />
				</form>
			</div>
		</div>
	</nav>

	<div class="left-nav">
		<ul class="nav nav-pills nav-stacked margin-nav">
			<li><a class="black" href="/"><i class="fa fa-desktop fa-fw"></i>Dashboard</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/advsearch.htm"><i
					class="fa fa-search fa-fw"></i>Advanced Search</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/offices.htm"><i
					class="fa fa-compass fa-fw"></i>Offices</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/tasks.htm"><i
					class="fa fa-check fa-fw"></i>Tasks</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/entercollectionsheet.htm"><i
					class="fa fa-tasks fa-fw"></i>Collections</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/freqposting.htm"><i
					class="fa fa-repeat fa-fw"></i>Frequent Postings</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/journalentry.htm"><i
					class="fa fa-plus fa-fw"></i>+ Journal Entry</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/accountsclosure.htm"><i
					class="fa fa-bell-o fa-fw"></i>Closing Entries</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/accountingcoa.htm"><i
					class="fa fa-sitemap fa-fw"></i>Chart of Accounts</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/createclient.htm"><i
					class="fa fa-user fa-fw"></i>+ Client</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/creategroup.htm"><i
					class="fa fa-group fa-fw"></i>+ Group</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/createcenter.htm"><i
					class="fa fa-group fa-fw"></i> + Center</a></li>
			<li class="divider"></li>
			<li><a class="black" href="/help.htm"><i
					class="fa fa-question-circle fa-fw"></i>Help</a></li>
		</ul>
	</div>

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">
					<div class="whitebg">
						<table class="table table-striped">
							<colgroup span="4"></colgroup>
							<thead>
								<tr>
									<th colspan="5" scope="colgroup"></th>
									<th colspan="2" scope="colgroup">Tasks Description</th>
								</tr>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Task</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td scope="row">1</td>
									<td>Improve the navigation functionalities, link each page
										with their basic page layout</td>
								</tr>
								<tr>
									<td scope="row">2</td>
									<td>Develop the Client Information management with UI with
										a simple client creation and user management</td>
								</tr>

								<tr>
									<td scope="row">3</td>
									<td>Identify and Write up the required Rest services for
										all the functionalities chosen</td>
								</tr>
								<tr>
									<td scope="row">4</td>
									<td>Identify the basic Data model</td>
								</tr>
								<tr>
									<td scope="row">5</td>
									<td>Try and learn developing an android application</td>
								</tr>
								<tr>
									<td scope="row">6</td>
									<td>Implement the persistence layer and services with GAE
										Datastore</td>
								</tr>
								<tr>
									<td scope="row">7</td>
									<td>user profile management</td>
								</tr>
								<tr>
									<td scope="row">8</td>
									<td>Implement the rest services for the Client information
										management</td>
								</tr>
								<tr>
									<td scope="row">9</td>
									<td>Create the products and services configuration pages
										and implement the configuration module</td>
								</tr>
								<tr>
									<td scope="row">10</td>
									<td>Create a sample loan and savings application (for
										individuals and groups) in UI and implement the back end rest
										services</td>
								</tr>
								<tr>
									<td scope="row">11</td>
									<td>continue learning and developing a sample loan
										application UI on android application</td>
								</tr>
								<tr>
									<td scope="row">12</td>
									<td>Identify the gaps between the Loan application UI and
										develop the required datamodel updates along with necessary
										changes to each of the layers</td>
								</tr>
								<tr>
									<td scope="row">13</td>
									<td>Implement Lending, Disbursal, Deposit, Withdrawal and
										Collection</td>
								</tr>
								<tr>
									<td scope="row">14</td>
									<td>Implement pagination, identify the required javascript
										functions and implement them</td>
								</tr>
								<tr>
									<td scope="row">15</td>
									<td>Learn to develop an offline data storage and
										synchronisation</td>
								</tr>
								<tr>
									<td scope="row">16</td>
									<td>Implement Accounting system - the Chart of Accounts,
										General Ledger, Journal entries</td>
								</tr>
								<tr>
									<td scope="row">17</td>
									<td>Transaction management</td>
								</tr>
								<tr>
									<td scope="row">18</td>
									<td>Interest application over individual and group loans</td>
								</tr>
								<tr>
									<td scope="row">19</td>
									<td>Develop an offline task list sample feature and with
										offline data storage and synchronisation</td>
								</tr>

								<tr>
									<td scope="row">20</td>
									<td>develop the Dash board UI</td>
								</tr>
								<tr>
									<td scope="row">21</td>
									<td>Implement the underlying required set of services
										along with the datamodel</td>
								</tr>
								<tr>
									<td scope="row">22</td>
									<td>Test for gaps and implement the missing features</td>
								</tr>

								<tr>
									<td scope="row">23</td>
									<td>Implement the tasks list for field officers</td>
								</tr>
								<tr>
									<td scope="row">24</td>
									<td>Implement the underlying required set of services
										along with the datamodel</td>
								</tr>
								<tr>
									<td scope="row">25</td>
									<td>Test for gaps and implement the missing features</td>
								</tr>
								<tr>
									<td scope="row">26</td>
									<td>Plan towards writing thesis</td>
								</tr>

								<tr>
									<td scope="row">27</td>
									<td>Work towards developing the rest of the UI for the
										android application along with necessary offline storage and
										sync up testing</td>
								</tr>
								<tr>
									<td scope="row">28</td>
									<td>Gather data about usability and scalability testing of
										the application</td>
								</tr>
								<tr>
									<td scope="row">29</td>
									<td>Write up the thesis</td>
								</tr>
								<tr>
									<td scope="row">30</td>
									<td>Write up poster</td>
								</tr>

								<tr>
									<td scope="row">31</td>
									<td>Write up the thesis</td>
								</tr>
								<tr>
									<td scope="row">32</td>
									<td>Prepare poster presentation</td>
								</tr>
								<tr>
									<td scope="row">33</td>
									<td>Get the thesis and poster reviewed</td>
								</tr>

								<tr>
									<td scope="row">34</td>
									<td>Write up the thesis</td>
								</tr>
								<tr>
									<td scope="row">35</td>
									<td>Prepare poster presentation</td>
								</tr>
								<tr>
									<td scope="row">36</td>
									<td>Write up thesis and submit it</td>
								</tr>
							</tbody>
						</table>

					</div>

					<div class="whitebg">
						<table class="table table-striped">
							<colgroup span="4"></colgroup>
							<thead>
								<tr>
									<th colspan="2" scope="colgroup"></th>
									<th colspan="4" scope="colgroup">Progress Page with work
										plan</th>
								</tr>
								<tr>
									<th scope="col">Academic Week#</th>
									<th scope="col">Task Number</th>
									<th scope="col">Comments</th>
									<th scope="col">Status</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>33 (7 to 11 July)</td>
									<td>1</td>
									<td>Basic navigation completed, with page under
										construction</td>
									<td style="color: green;">Completed</td>
								</tr>
								<tr>
									<td>33 (7 to 11 July)</td>
									<td>2</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>33 (7 to 11 July)</td>
									<td>3</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>33 (7 to 11 July)</td>
									<td>4</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>33 (7 to 11 July)</td>
									<td>5</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>6</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>7</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>8</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>9</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>10</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>34 (14 to 18 July)</td>
									<td>11</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>12</td>
									<td>done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>13</td>
									<td>Learning</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>14</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>15</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>16</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>17</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>18</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>19</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>20</td>
									<td>Developing</td>
									<td>In progress</td>
								</tr>
								<tr>
									<td>37 (4 Aug to 8 Aug)</td>
									<td>21</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>37 (4 Aug to 8 Aug)</td>
									<td>22</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>37 (4 Aug to 8 Aug)</td>
									<td>23</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>38 (11 Aug to 15 Aug)</td>
									<td>24, 25, 26, 27</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>39 (18 Aug to 22 Aug)</td>
									<td>28, 29, 30, 31</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>40 (25 Aug to 29 Aug)</td>
									<td>32, 33, 34</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>41 (1 Sep to 5 Sep)</td>
									<td>35, 36</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>42, 43, 44 (6 Sep to 17 Sep)</td>
									<td>36</td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/static/js/jquery.min.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>
