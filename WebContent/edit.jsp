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
	background-color: #4CAF50;
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

/* Float cancel and edit buttons and add an equal width */
.cancelbtn, .editbtn {
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

/* Change styles for cancel button and edit button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .editbtn {
		width: 100%;
	}
}
</style>

<body>
	<%
		ResultExpenseBean bean = new ResultExpenseBean();
		if (session.getAttribute("expense") != null) {
			bean = (ResultExpenseBean) session.getAttribute("expense");
		}
		
		String note = bean.getNote();
	%>

	<div class="bgimg w3-display-container">

		<form method="POST" action="SqlGateway">
			<div class="container">
				<p style="color: red">${errorText}</p>

				<h1>Edit Expense</h1>
				<hr>
				
				<div class="inline">
				<label for="note"><b>Note</b></label>
				<select name="note" required>
					<optgroup label="Advertising">
						<option value="Consultation Fee" <%if(note.equals("Consultation Fee")){%>selected<%}%>>Consultation Fee</option>
						<option value="Service Fee" <%if(note.equals("Service Fee")){%>selected<%}%>>Service Fee</option>
					</optgroup>
					<optgroup label="Car & Truck Expenses">
						<option value="Gas" <%if(note.equals("Gas")){%>selected<%}%>>Gas</option>
						<option value="Repairs" <%if(note.equals("Repairs")){%>selected<%}%>>Repairs</option>
						<option value="Vehicle Insurance" <%if(note.equals("Vehicle Insurance")){%>selected<%}%>>Vehicle Insurance</option>
						<option value="Vehicle Licensing" <%if(note.equals("Vehicle Licensing")){%>selected<%}%>>Vehicle Licensing</option>
					</optgroup>
					<optgroup label="Contractors">
						<option value="Contractors" <%if(note.equals("Contractors")){%>selected<%}%>>Contractors</option>
					</optgroup>
					<optgroup label="Education and Training">
						<option value="Books" <%if(note.equals("Books")){%>selected<%}%>>Books</option>
						<option value="Certification" <%if(note.equals("Certification")){%>selected<%}%>>Certification</option>
						<option value="Subscription" <%if(note.equals("Subscription")){%>selected<%}%>>Subscription</option>
					</optgroup>
					<optgroup label="Employee Benefits">
						<option value="Accident Insurance" <%if(note.equals("Accident Insurance")){%>selected<%}%>>Accident Insurance</option>
						<option value="Health Insurance" <%if(note.equals("Health Insurance")){%>selected<%}%>>Health Insurance</option>
						<option value="Life Insurance" <%if(note.equals("Life Insurance")){%>selected<%}%>>Life Insurance</option>
					</optgroup>
					<optgroup label="Meals & Entertainment">
						<option value="Movies" <%if(note.equals("Movies")){%>selected<%}%>>Movies</option>
						<option value="CD" <%if(note.equals("CD")){%>selected<%}%>>CD</option>
						<option value="Dine Out" <%if(note.equals("Dine Out")){%>selected<%}%>>Dine Out</option>
					</optgroup>
					<optgroup label="Office Expenses & Postage">
						<option value="Hardware" <%if(note.equals("Hardware")){%>selected<%}%>>Hardware</option>
						<option value="Office Supplies" <%if(note.equals("Office Supplies")){%>selected<%}%>>Office Supplies</option>
						<option value="Packaging" <%if(note.equals("Packaging")){%>selected<%}%>>Packaging</option>
						<option value="Postage" <%if(note.equals("Postage")){%>selected<%}%>>Postage</option>
						<option value="Printing" <%if(note.equals("Printing")){%>selected<%}%>>Printing</option>
						<option value="Shipping & Couriers" <%if(note.equals("Shipping & Couriers")){%>selected<%}%>>Shipping & Couriers</option>
						<option value="Software" <%if(note.equals("Gas")){%>selected<%}%>>Software</option>
					</optgroup>
					<optgroup label="Other Expenses">
						<option value="Bank Fees" <%if(note.equals("Bank Fees")){%>selected<%}%>>Bank Fees</option>
						<option value="Business Insurance" <%if(note.equals("Business Insurance")){%>selected<%}%>>Business Insurance</option>
						<option value="Commissions" <%if(note.equals("Commissions")){%>selected<%}%>>Commissions</option>
						<option value="Deprecation" <%if(note.equals("Deprecation")){%>selected<%}%>>Deprecation</option>
						<option value="Interest - Mortgage" <%if(note.equals("Interest - Mortgage")){%>selected<%}%>>Interest - Mortgage</option>
						<option value="Interest - Other" <%if(note.equals("Interest - Other")){%>selected<%}%>>Interest - Other</option>
						<option value="Online Services" <%if(note.equals("Online Services")){%>selected<%}%>>Online Services</option>
						<option value="Reference Materials" <%if(note.equals("Reference Materials")){%>selected<%}%>>Reference Materials</option>
						<option value="Repairs & Maintenance" <%if(note.equals("Repairs & Maintenance")){%>selected<%}%>>Repairs & Maintenance</option>
						<option value="Subscriptions/Dues/Memberships" <%if(note.equals("Subscriptions/Dues/Memberships")){%>selected<%}%>>Subscriptions/Dues/Memberships</option>
						<option value="Taxes & Licenses" <%if(note.equals("Taxes & Licenses")){%>selected<%}%>>Taxes & Licenses</option>
						<option value="Wages" <%if(note.equals("Wages")){%>selected<%}%>>Wages</option>
					</optgroup>
					<optgroup label="Professional Services">
						<option value="Accounting" <%if(note.equals("Accounting")){%>selected<%}%>>Accounting</option>
						<option value="Legal Fees" <%if(note.equals("Legal Fees")){%>selected<%}%>>Legal Fees</option>
						<option value="Equipment" <%if(note.equals("Equipment")){%>selected<%}%>>Equipment</option>
						<option value="Machinery" <%if(note.equals("Machinery")){%>selected<%}%>>Machinery</option>
						<option value="Office Space" <%if(note.equals("Office Space")){%>selected<%}%>>Office Space</option>
						<option value="Vehicles" <%if(note.equals("Vehicles")){%>selected<%}%>>Vehicles</option>
					</optgroup>
					<optgroup label="Supplies">
						<option value="Supplies" <%if(note.equals("Supplies")){%>selected<%}%>>Supplies</option>
					</optgroup>
					<optgroup label="Travel">
						<option value="Airfare" <%if(note.equals("Airfare")){%>selected<%}%>>Airfare</option>
						<option value="Hotel/Lodging/Accommodation" <%if(note.equals("Hotel/Lodging/Accommodation")){%>selected<%}%>>Hotel/Lodging/Accommodation</option>
					</optgroup>
					<optgroup label="Utilities">
						<option value="Gas & Electrical" <%if(note.equals("Gas & Electrical")){%>selected<%}%>>Gas & Electrical</option>
						<option value="Phone" <%if(note.equals("Phone")){%>selected<%}%>>Phone</option>
					</optgroup>
					<optgroup label="Personal">
						<option value="Personal" <%if(note.equals("Personal")){%>selected<%}%>>Personal</option>
						<option value="Other" <%if(note.equals("Other")){%>selected<%}%>>Other</option>
					</optgroup>
				</select>
				
				<label for="description"><b>Description</b></label> 
				<input type="text" placeholder="Expense Description" name="description" size=99
					value="${expense.description}" required>
				</div>
				
				<div class="inline">
				<label for="date"><b>Date</b></label> 
				<input class="date" type="date" placeholder="MM-DD-YYYY" data-date-split-input="true"
					name="date" value="${expense.date}" required>

				<label for="amount"><b>Amount</b></label> 
				<input type="number" placeholder="Expense Amount" name="amount"
					value="${expense.amount}" min="0" step="0.01"
					data-number-to-fixed="2" data-number-stepfactor="100" required>
				</div>

				<br> 
				
				<div class="clearfix">
					<button type="reset" class="cancelbtn">Reset</button>
					<button type="submit" class="editbtn" name="action"
						value="EditExpense">Edit Expense</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>