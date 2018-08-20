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
	background-image: url('images/home1.jpg');
	min-height: 100%;
	background-position: center;
	background-size: cover;
}

h1, label {
	color: white;
}

table {
	margin-left: 4em;
	border-collapse: collapse;
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
	float: left;
	width: 50%;
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

#cost {
	padding-left: 150px;
	text-align: right;
}
</style>

<body>
	<%
		ResultUserBean user = (ResultUserBean) request.getAttribute("user");
		String name = user.getName();
		String email = user.getEmail();

		List<ResultExpenseBean> expenses = new ArrayList<>();
		try {
			expenses = (List<ResultExpenseBean>) request.getAttribute("expenses");
		} catch (Exception ex) {
			System.out.println("Error retrieving expenses...");
		}
		
		int numOfExpenses = expenses.size();
		double total = 0.00;
		for (ResultExpenseBean expense : expenses) {
			total += Double.parseDouble(expense.getAmount());
		}
	%>

	<div class="bgimg w3-display-container w3-animate-opacity">
		<div class="w3-display-middle container">

			<p style="color: red">${errorText}</p>

			<p>
				Hello <b><%=name%></b>, Welcome to Expensity!
			</p>
			<p>
				You current have <b><%=numOfExpenses%></b> expenses...
			</p>

			<form action="add.jsp">
				<div class="clearfix">
					<button type="submit" class="addbtn" name="action"
						value="AddExpense">Add Expense</button>
				</div>
			</form>

			<table>
				<tr>
					<th>Expense</th>
					<th>Description</th>
					<th>Date</th>
					<th>Cost</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach items="<%=expenses%>" var="expense">
					<tr>
						<td><c:out value="${expense.note}" /></td>
						<td><c:out value="${expense.description}" /></td>
						<td><c:out value="${expense.date}" /></td>
						<td id="cost">$<c:out value="${expense.amount}" /></td>
						<td>
							<form method="POST" action="SqlGateway">
								<input type="hidden" name="action" value="RemoveExpense"> <input
									type="hidden" name="expense_to_remove" value="${expense.id}">
								<input type="submit" value="Remove">
							</form>
						</td>
						<td>
							<form method="POST" action="SqlGateway">
								<input type="hidden" name="action" value="EditPage"> <input
									type="hidden" name="expense_to_edit" value="${expense.id}">
								<input type="submit" value="Edit">
							</form>
						</td>
					</tr>
				</c:forEach>

				<tr>
					<th>Total</th>
					</th>
					</th>
					<th id="cost">$<%=total%>
					</th>
					</th>
				</tr>
			</table>

		</div>
	</div>
</body>

</html>