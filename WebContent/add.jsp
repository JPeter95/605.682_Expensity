<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@page import="edu.jhu.sample.results.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/includes/header.html" />

<!-- Template from: https://www.w3schools.com/w3css/w3css_templates.asp -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box
}

body, html {
	height: 100%
}

/* Full-width input fields */
input[type=text], input[type=number], input[type=date], select {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=number], input[type=date] {
	text-align: right;
}

input[type=text]:focus, input[type=number]:focus, input[type=date]:focus, select:focus {
	background-color: #ddd;
	outline: none;
}

hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
	background-color: #33D5FF;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

.bgimg {
	background-image: url('images/add.jpg');
	min-height: 100%;
	background-position: center;
	background-size: cover;
}

h1, label {
	color: white;
}

/* Extra styles for the cancel button */
.cancelbtn {
	padding: 14px 20px;
	background-color: #f44336;
}

/* Float cancel and add buttons and add an equal width */
.cancelbtn, .addbtn {
	float: left;
	width: 50%;
}

/* Add padding to container elements */
.container {
	margin: auto;
	padding: 16px;
	width: 60%; 
}

/* Add padding to form elements */
.inline {
	margin: auto;
	padding: 24px;
	width: 50%; 
	float: left;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for cancel button and add button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .addbtn {
		width: 100%;
	}
}
</style>

<body>
	<div class="bgimg w3-display-container">

		<form method="POST" action="SqlGateway">
			<div class="container">
				<p style="color: red">${errorText}</p>

				<h1>Add Expense</h1>
				<hr>
				
				<div class="inline">
				<label for="note"><b>Note</b></label>
				<select name="note" required>
					<optgroup label="Advertising">
						<option value="Consultation Fee">Consultation Fee</option>
						<option value="Service Fee">Service Fee</option>
					</optgroup>
					<optgroup label="Car & Truck Expenses">
						<option value="Gas">Gas</option>
						<option value="Repairs">Repairs</option>
						<option value="Vehicle Insurance">Vehicle Insurance</option>
						<option value="Vehicle Licensing">Vehicle Licensing</option>
					</optgroup>
					<optgroup label="Contractors">
						<option value="Contractors">Contractors</option>
					</optgroup>
					<optgroup label="Education and Training">
						<option value="Books">Books</option>
						<option value="Certification">Certification</option>
						<option value="Subscription">Subscription</option>
					</optgroup>
					<optgroup label="Employee Benefits">
						<option value="Accident Insurance">Accident Insurance</option>
						<option value="Health Insurance">Health Insurance</option>
						<option value="Life Insurance">Life Insurance</option>
					</optgroup>
					<optgroup label="Meals & Entertainment">
						<option value="Movies">Movies</option>
						<option value="CD">CD</option>
						<option value="Dine Out">Dine Out</option>
					</optgroup>
					<optgroup label="Office Expenses & Postage">
						<option value="Hardware">Hardware</option>
						<option value="Office Supplies">Office Supplies</option>
						<option value="Packaging">Packaging</option>
						<option value="Postage">Postage</option>
						<option value="Printing">Printing</option>
						<option value="Shipping & Couriers">Shipping & Couriers</option>
						<option value="Software">Software</option>
					</optgroup>
					<optgroup label="Other Expenses">
						<option value="Bank Fees">Bank Fees</option>
						<option value="Business Insurance">Business Insurance</option>
						<option value="Commissions">Commissions</option>
						<option value="Deprecation">Deprecation</option>
						<option value="Interest - Mortgage">Interest - Mortgage</option>
						<option value="Interest - Other">Interest - Other</option>
						<option value="Online Services">Online Services</option>
						<option value="Reference Materials">Reference Materials</option>
						<option value="Repairs & Maintenance">Repairs & Maintenance</option>
						<option value="Subscriptions/Dues/Memberships">Subscriptions/Dues/Memberships</option>
						<option value="Taxes & Licenses">Taxes & Licenses</option>
						<option value="Wages">Wages</option>
					</optgroup>
					<optgroup label="Professional Services">
						<option value="Accounting">Accounting</option>
						<option value="Legal Fees">Legal Fees</option>
						<option value="Equipment">Equipment</option>
						<option value="Machinery">Machinery</option>
						<option value="Office Space">Office Space</option>
						<option value="Vehicles">Vehicles</option>
					</optgroup>
					<optgroup label="Supplies">
						<option value="Supplies">Supplies</option>
					</optgroup>
					<optgroup label="Travel">
						<option value="Airfare">Airfare</option>
						<option value="Hotel/Lodging/Accommodation">Hotel/Lodging/Accommodation</option>
					</optgroup>
					<optgroup label="Utilities">
						<option value="Gas & Electrical">Gas & Electrical</option>
						<option value="Phone">Phone</option>
					</optgroup>
					<optgroup label="Personal">
						<option value="Personal">Personal</option>
						<option value="Other">Other</option>
					</optgroup>
				</select>
				
				<label for="description"><b>Description</b></label> 
				<input type="text" placeholder="Expense Description" name="description" size=99 required>
				</div>
				
				<div class="inline">
				<label for="date"><b>Date</b></label> 
				<input class="date" type="date" placeholder="MM-DD-YYYY" data-date-split-input="true"
					name="date" required>

				<label for="amount"><b>Amount</b></label> 
				<input type="number" placeholder="Expense Amount" name="amount" min="0" step="0.01"
					data-number-to-fixed="2" data-number-stepfactor="100" required>
				</div>

				<br> 
				
				<div class="clearfix">
					<button type="reset" class="cancelbtn">Reset</button>
					<button type="submit" class="addbtn" name="action"
						value="AddExpense">Add Expense</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>