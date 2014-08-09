<header class="top" role="header">
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
						<li><a href="/clients">Clients</a></li>
						<li><a href="/groups">Groups</a></li>

					</ul></li>
				<li><a href="/finance/accountingcoa"><i class="fa fa-money"></i>
						Accounting</a></li>
				<li class="dropdown" id="reports-menu"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"><i
						class="fa fa-bar-chart-o"></i> Reports<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/reports/clients">Client Accounts</a></li>
						<!-- <li><a href="/reports/savingsrep">Savings Accounts</a></li> -->
						<li><a href="/reports/tasksrep">Today's Tasks</a></li>
					</ul></li>
				<li class="dropdown" id="preview-menu"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"><i
						class="fa fa-wrench"></i> Admin<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/users">Users</a></li>
						<li><a href="/admin/organization">Organization</a></li>
						<li><a href="/admin/products">Products</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="main-menu-right">
				<li class="dropdown" id="user-menu"><a id="user-dropdown"
					class="dropdown-toggle" data-toggle="dropdown" href="#">${param.username}<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a id="help" href="/help"><i
								class="fa fa-question-circle"></i> Help</a></li>
						<li><a href="/viewuser?omfuId=${param.userid}"><i
								class="fa fa-user"></i> Profile</a></li>
						<li><a href="/logout"><i class="fa fa-off"></i>Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>
</header>