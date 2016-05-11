<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="srr.app">

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Westpac DDA</title>

<link rel="stylesheet" href="resources/css/index.css" />
<link rel="stylesheet" href="resources/css/srr-app.css" />
<link rel="stylesheet"
	href="resources/css/html5-boilerplate/normalize.css" />
<link rel="stylesheet" href="resources/css/html5-boilerplate/main.css" />
<link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet"
	href="resources/css/angular-grid/angular-grid.css" />
<link rel="stylesheet" href="resources/css/angular-grid/theme-fresh.css" />
<link rel="stylesheet" href="resources/css/angular-ui-select/select.css" />
<link rel="stylesheet" href="resources/css/select2/select2.css" />
<link rel="stylesheet"
	href="resources/css/angular-toggle-switch/angular-toggle-switch.css" />
<link rel="stylesheet" href="resources/css/ngToggle/ng-toggle.css" />

<script src="resources/scripts/angular.min.js"></script>
<script src="resources/scripts/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="resources/scripts/index.js"></script>
<script src="resources/scripts/angularDatePicker.js"></script>
<script src="resources/scripts/dda-test-workflow.js"></script>

<script src="resources/scripts/angular-sanitize/angular-sanitize.js"></script>
<script src="resources/scripts/angular-toggle-switch/angular-toggle-switch.js"></script>


<script src="resources/scripts/angular-ui-select/select.js"></script>
</head>
<body>
	<div class="navbar navbar-default navbar-collapse navbar-fixed-top"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#/"> <img
					src="resources/img/DelegatedDealingAuthorities1.png" class="logo" />
				</a>

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li ng-class="{active : path == '#/messages'}"><a
						href="#/dda-messages">Messages</a></li>
					<li ng-class="{active : path == '#/messages'}"><a
						href="#/dda-search">Search DDA</a></li>
					<li ng-class="{active : path == '#/srr-limits'}"><a
						href="#/dda-sydney">DDA Sydney</a></li>
					<li ng-class="{active : path == '#/messages'}"><a
						href="#/dda-london">DDA London</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container" id="rootcontainer">
		<div class="innercontainer" ng-view="ng-view" ng-controller="${jsController}">
			<div class="slide-animate" ng-include="${templateUrl}"></div>
		</div>
	</div>
	
	
</body>
</html>