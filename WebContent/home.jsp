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
</style>

<body>
	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<div class="w3-display-middle">
			<!-- <h1 class="w3-jumbo w3-animate-top">Add Tables here</h1>
			<hr class="w3-border-grey" style="margin: auto; width: 40%">
			<p class="w3-large w3-center">This is the main page, after the
				user logs in</p> -->
			<form action="SqlGatewayServlet" method="POST">
				<input type="hidden" name="action" value="showData"> <input
					type="submit" class="button button_Submit" value="Show Data">
			</form>
			
			<form action="add.jsp">
				<input class="w3-button w3-white" type="submit" id="add"
					name="action" value="AddExpense">
			</form>

			${regSQLResult}

		</div>
	</div>
</body>

</html>