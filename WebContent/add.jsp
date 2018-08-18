<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@page import="edu.jhu.sample.results.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/includes/header.html" />

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body, h1 {
	font-family: "Raleway", sans-serif
}

body, html {
	height: 100%
}

.bgimg {
	background-image: url('images/add.jpg');
	min-height: 100%;
	background-position: center;
	background-size: cover;
}
</style>

<body>
	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<div class="btn-group">

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Advertising <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Consultation Fee</a></li>
					<li><a href="#">Service Fee</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Car & Truck Expenses <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Gas</a></li>
					<li><a href="#">Mileage</a></li>
					<li><a href="#">Repairs</a></li>
					<li><a href="#">Vehicle Insurance</a></li>
					<li><a href="#">Vehicle Licensing</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Contractors <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Contractors</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Education and Training <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Books</a></li>
					<li><a href="#">Certification</a></li>
					<li><a href="#">Subscription</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Employee Benefits <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Accident Insurance</a></li>
					<li><a href="#">Health Insurance</a></li>
					<li><a href="#">Life Insurance</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Meals & Entertainment <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Movies</a></li>
					<li><a href="#">CD</a></li>
					<li><a href="#">Dine Out</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Office Expenses & Postage <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Hardware</a></li>
					<li><a href="#">Office Supplies</a></li>
					<li><a href="#">Packaging</a></li>
					<li><a href="#">Postage</a></li>
					<li><a href="#">Printing</a></li>
					<li><a href="#">Shipping & Couriers</a></li>
					<li><a href="#">Software</a></li>
				</ul>
			</div>
		</div>

		<div class="btn-group pull-right">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Other Expenses <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Bank Fees</a></li>
					<li><a href="#">Business Insurance</a></li>
					<li><a href="#">Commissions</a></li>
					<li><a href="#">Deprecation</a></li>
					<li><a href="#">Interest - Mortgage</a></li>
					<li><a href="#">Interest - Other</a></li>
					<li><a href="#">Online Services</a></li>
					<li><a href="#">Reference Materials</a></li>
					<li><a href="#">Repairs & Maintenance</a></li>
					<li><a href="#">Subscriptions/Dues/Memberships</a></li>
					<li><a href="#">Taxes & Licenses</a></li>
					<li><a href="#">Wages</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Personal <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Personal</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Professional Services <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Professional Services</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Rent or Lease <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Accounting</a></li>
					<li><a href="#">Legal Fees</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Supplies <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Equipment</a></li>
					<li><a href="#">Machinery</a></li>
					<li><a href="#">Office Space</a></li>
					<li><a href="#">Vehicles</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Travel <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Airfare</a></li>
					<li><a href="#">Hotel/Lodging/Accommodation</a></li>
				</ul>
			</div>

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Utilities <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Gas & Electrical</a></li>
					<li><a href="#">Phone</a></li>
				</ul>
			</div>

		</div>
	</div>
</body>

</html>