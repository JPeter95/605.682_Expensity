<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import="edu.jhu.sample.results.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	background-image: url('images/index.jpg');
	min-height: 100%;
	background-position: center;
	background-size: cover;
}
</style>

<body>
	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<div class="w3-display-middle">
			<h1 class="w3-jumbo w3-animate-top">EXPENSITY</h1>
			<hr class="w3-border-grey" style="margin: auto; width: 40%">
			<p class="w3-large w3-center">Manage your expenses, before they
				manage you</p>
		</div>
		
		<div class="w3-display-bottomleft w3-padding-large">
			Powered by <a href="https://www.w3schools.com/w3css/default.asp"
				target="_blank">w3.css</a>
		</div>

		<div class="w3-display-topright w3-padding-large">
			<form method="GET" action="SslController">
				<input class="w3-button w3-white" type="submit" id="signup" name="action" value="Signup"> 
				<input class="w3-button w3-white" type="submit" id="login" name="action" value="Login">
			</form>
		</div>
	</div>

</body>

</html>