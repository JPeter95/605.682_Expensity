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
body, h1 {
	font-family: "Raleway", sans-serif
}

body, html {
	height: 100%
}

.bgimg {
	background-image: url('images/home1.jpg');
	min-height: 100%;
	background-position: center;
	background-size: cover;
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

h1, label {
	color: white;
}

table {
	border-collapse: collapse;
}

#margin {
	padding-right: 100px;
}

#cost {
	text-align: right;
}

#tablebtn {
	padding-left: 50px;
}

p {
	font-size: 20px;
	color: black;
}

tr {
	border: solid;
	border-width: 1px 0;
}

tr:first-child {
	border-top: none;
}

tr:last-child {
	border-bottom: none;
}

th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
}

/* Float add buttons and add an equal width */
.addbtn {
	width: 100%;
}

/* Extra styles for the cancel button */
.editbtn {
	padding: 14px 20px;
	background-color: #4CAF50;
}

/* Extra styles for the cancel button */
.removebtn {
	padding: 14px 20px;
	background-color: #f44336;
}

/* Add padding to container elements */
.container {
	margin: auto;
	padding: 16px;
	width: 60%;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for add button on extra small screens */
@media screen and (max-width: 300px) {
	.addbtn {
		width: 100%;
	}
}
</style>

<body>
	<%
		ResultUserBean user = (ResultUserBean) request.getAttribute("user");
		String name = user.getName();
		String email = user.getEmail();

		List<ResultExpenseBean> expenseList = new ArrayList<>();
		expenseList = (List<ResultExpenseBean>) request.getAttribute("expenses");

		int numOfExpenses = expenseList.size();
		double total = 0.00;
		for (ResultExpenseBean expense : expenseList) {
			total += Double.parseDouble(expense.getAmount());
		}
	%>

	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<div class="w3-display-middle">

			<p style="color: red">${errorText}</p>

			<p>
				Hello <b><%=name%></b>, Welcome to Expensity!
			</p>
			<p>
				You currently have <b><%=numOfExpenses%></b> expense(s) totaling <b>$<%=total%></b>
			</p>

			<table>
				<tr>
					<th>Expense</th>
					<th>Description</th>
					<th>Date</th>
					<th id="cost">Cost</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach items="${requestScope.expenses}" var="exp">
					<tr>
						<td id="margin"><c:out value="${exp.note}" /></td>
						<td id="margin"><c:out value="${exp.description}" /></td>
						<td id="margin"><c:out value="${exp.date}" /></td>
						<td id="cost">$<c:out value="${exp.amount}" /></td>
						<td id="tablebtn">
							<form method="POST" action="SqlGateway">
								<input type="hidden" name="action" value="EditPage"> <input
									type="hidden" name="expense_to_edit" value="${exp.id}">
								<button class="editbtn" type="submit" value="Edit">Edit</button>
							</form>
						</td>
						<td id="tablebtn">
							<form method="POST" action="SqlGateway">
								<input type="hidden" name="action" value="RemoveExpense">
								<input type="hidden" name="expense_to_remove" value="${exp.id}">
								<button class="removebtn" type="submit" value="Remove">Remove</button>
							</form>
						</td>
					</tr>
				</c:forEach>

				<tr>
					<th>Total</th>
					<th></th>
					<th></th>
					<th id="cost">$<%=total%></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			
			<form action="add.jsp">
				<div class="clearfix">
					<button type="submit" class="addbtn" name="action"
						value="AddExpense">Add Expense</button>
				</div>
			</form>
		</div>
	</div>
</body>

</html>