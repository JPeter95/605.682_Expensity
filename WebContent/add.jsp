<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@page import="edu.jhu.sample.results.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/includes/favicon.html" />

<!-- Template from: https://www.w3schools.com/w3css/w3css_templates.asp -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">

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

form {
	margin: 20px auto;
	width: 420px;
}

.form-row {
	padding: 5px 10px;
}

label {
	display: block;
	margin: 3px 0;
}

.form-row input {
	padding: 3px 1px;
	width: 100%;
}

input.currency {
	text-align: right;
	padding-right: 15px;
}
</style>

<body>
	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<form action="ServletController">
			<div class="form-group">
				<select class="selectpicker" name="note" required>
					<optgroup label="Advertising">
						<option>Consultation Fee</option>
						<option>Service Fee</option>
					</optgroup>
					<optgroup label="Car & Truck Expenses">
						<option>Gas</option>
						<option>Mileage</option>
						<option>Repairs</option>
						<option>Vehicle Insurance</option>
						<option>Vehicle Licensing</option>
					</optgroup>
					<optgroup label="Contractors">
						<option>Contractors</option>
					</optgroup>
					<optgroup label="Education and Training">
						<option>Books</option>
						<option>Certification</option>
						<option>Subscription</option>
					</optgroup>
					<optgroup label="Employee Benefits">
						<option>Accident Insurance</option>
						<option>Health Insurance</option>
						<option>Life Insurance</option>
					</optgroup>
					<optgroup label="Meals & Entertainment">
						<option>Movies</option>
						<option>CD</option>
						<option>Dine Out</option>
					</optgroup>
					<optgroup label="Office Expenses & Postage">
						<option>Hardware</option>
						<option>Office Supplies</option>
						<option>Packaging</option>
						<option>Postage</option>
						<option>Printing</option>
						<option>Shipping & Couriers</option>
						<option>Software</option>
					</optgroup>
					<optgroup label="Other Expenses">
						<option>Bank Fees</option>
						<option>Business Insurance</option>
						<option>Commissions</option>
						<option>Deprecation</option>
						<option>Interest - Mortgage</option>
						<option>Interest - Other</option>
						<option>Online Services</option>
						<option>Reference Materials</option>
						<option>Repairs & Maintenance</option>
						<option>Subscriptions/Dues/Memberships</option>
						<option>Taxes & Licenses</option>
						<option>Wages</option>
					</optgroup>
					<optgroup label="Personal">
						<option>Personal</option>
					</optgroup>
					<optgroup label="Professional Services">
						<option>Accounting</option>
						<option>Legal Fees</option>
						<option>Equipment</option>
						<option>Machinery</option>
						<option>Office Space</option>
						<option>Vehicles</option>
					</optgroup>
					<optgroup label="Supplies">
						<option>Supplies</option>
					</optgroup>
					<optgroup label="Travel">
						<option>Airfare</option>
						<option>Hotel/Lodging/Accommodation</option>
					</optgroup>
					<optgroup label="Utilities">
						<option>Gas & Electrical</option>
						<option>Phone</option>
				</select>

				<div class="form-row">
					<label for="amount"><b>Amount</b></label> <input type="amount"
						placeholder="Expense Amount" name="amount"
						value="${expense.amount}" min="0" step="0.01"
						data-number-to-fixed="2" data-number-stepfactor="100"
						class="currency" id="c1" required>
				</div>

				<div class="form-row hide-inputbtns">
					<label for="date"><b>Date</b></label> <input class="date"
						type="date" placeholder="YYYY-MM-DD" data-date-split-input="true"
						name="date" value="${expense.date}" required>
				</div>

				<div class="form-row">
					<label for="description"><b>Description:</b></label> <input
						type="description" placeholder="Expense Description:"
						name="description" value="${expense.description}" required>
				</div>
				<input type="submit" name="action" value="AddExpense!">
			</div>
		</form>
	</div>
</html>