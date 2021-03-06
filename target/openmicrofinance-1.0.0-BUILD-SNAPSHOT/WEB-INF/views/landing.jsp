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
	List<String> clientStat = (List) request.getAttribute("clientStat");
	List<String> amountStat = (List) request.getAttribute("amountStat");	
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
<link type="text/css" href="/static/css/chosen.min.css" rel="stylesheet" />
<!-- Custom dashboard styles -->
<link type="text/css" href="/static/css/dashboard.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="/static/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="/static/css/generic.css" />
<link type="text/css" rel="stylesheet" href="/static/css/openmf.app.css" />
<link type="text/css" rel="stylesheet" href="/static/css/highcharts.css" />
<script src="/static/js/jquery.min.js" type="text/javascript"></script>
<script src="/static/js/chosen.jquery.min.js" type="text/javascript"></script>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/tablefilter.js" type="text/javascript"></script>
<script src="/static/js/openmf.basic.js" type="text/javascript"></script>
<script src="/static/js/highcharts.js" type="text/javascript"></script>
<script src="/static/js/jquery.highchartTable.js" type="text/javascript"></script>
<script type="text/javascript">
	/*
		jQuery document ready
		The HighchartsTable plugin takes data
		and attributes from a table
		and parses them to simply create an Hight-charts chart
	 */
	$(document).ready(function() {
		/*
			simply call highchartTable on a selector
			that selects the prepared table. Like this : 
		 */
		$('table.highchart').highchartTable();
	});
</script>
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
	<jsp:include page="/WEB-INF/views/leftnav.jsp" />

	<div class="container whitebg fullscreen">
		<div>
			<div class="row whitebg">
				<div class="col-md-12 pull-right whitebg">
					<%-- <div class="whitebg">
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
									<td>Done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>14</td>
									<td>Developing</td>
									<td>Part Complete</td>
								</tr>
								<tr>
									<td>35 (21 to 25 July)</td>
									<td>15</td>
									<td>Done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>16</td>
									<td>Done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>17</td>
									<td>Done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>36 (28 July to 1 Aug)</td>
									<td>18</td>
									<td>Done</td>
									<td style="color: green;">Complete</td>
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
									<td>Done</td>
									<td style="color: green;">Complete</td>
								</tr>
								<tr>
									<td>37 (4 Aug to 8 Aug)</td>
									<td>22</td>
									<td>Testing and issue fixes in progress</td>
									<td>In Progress</td>
								</tr>
								<tr>
									<td>37 (4 Aug to 8 Aug)</td>
									<td>23</td>
									<td>Developing</td>
									<td>In Progress</td>
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
					</div> --%>
					<div class="whitebg">
						<!-- chart generated and placed into below divs. which defined into tables data-graph-container attributes -->
						<div id="parent">
							<div class="highchart-container hc_container"></div>
							<div class="highchart-container_first hc_container"></div>
							<!-- <div class="highchart-container_second hc_container"></div> 
							<div style="clear: both;"></div>-->
						</div>

						<!-- There are two ways to define where the graph must be rendered. If you want a graph before the table you can use "data-graph-container-before" otherwise use "data-graph-container" and use a CSS selector to choose where to display the graph. -->
						<!-- data-graph-type :  The data-graph-type attribute is required available options are : column line area spline pie -->
						<!-- table structure for first example -->
						<table class="highchart"
							data-graph-container=".highchart-container"
							data-graph-type="area">
							<thead>
								<tr>
									<th>Week</th>
									<th>Savings Deposit</th>
									<th>Loan Payment</th>									
									<th>Savings Withdrawal</th>
									<th>Loan Disbursal</th>									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Prev Week</td>
									<td><%=amountStat.get(1) %></td>
									<td><%=amountStat.get(3) %></td>
									<td><%=amountStat.get(5) %></td>
									<td><%=amountStat.get(7) %></td>
								</tr>
								<tr>
									<td>This Week</td>
									<td><%=amountStat.get(0) %></td>
									<td><%=amountStat.get(2) %></td>
									<td><%=amountStat.get(4) %></td>
									<td><%=amountStat.get(6) %></td>
								</tr>
							</tbody>
						</table>

						<!--
		table structure for second example
	-->
						<table data-graph-type="spline"
							data-graph-container=".highchart-container_first"
							class="highchart">
							<thead>
								<tr>
									<th>Weeks</th>
									<th>Client Count</th>
									<th>Sch. Savings</th>
									<th>Sch. Deposit</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Prev Week</td>
									<td><%= clientStat.get(1) %></td>
									<td><%= clientStat.get(3) %></td>
									<td><%= clientStat.get(5) %></td>
								</tr>
								<tr>
									<td>This Week</td>
									<td><%= clientStat.get(0) %></td>
									<td><%= clientStat.get(2) %></td>
									<td><%= clientStat.get(4) %></td>
								</tr>
							</tbody>
						</table>

<!-- 						table structure for third example
						<table class="highchart"
							data-graph-container=".highchart-container_second"
							data-graph-type="pie">
							<thead>
								<tr>
									<th>Month</th>
									<th>Sales</th>
									<th data-graph-type="area">Benefits</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>January</td>
									<td>8000</td>
									<td>2000</td>
								</tr>
								<tr>
									<td>February</td>
									<td>12000</td>
									<td>3000</td>
								</tr>
								<tr>
									<td>March</td>
									<td>18000</td>
									<td>4000</td>
								</tr>
								<tr>
									<td>April</td>
									<td>2000</td>
									<td>-1000</td>
								</tr>
								<tr>
									<td>May</td>
									<td>500</td>
									<td>-2500</td>
								</tr>
							</tbody>
						</table>
 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
