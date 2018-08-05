<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import="edu.jhu.sample.results.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

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
			<h1 class="w3-jumbo w3-animate-top">Add Tables here</h1>
			<hr class="w3-border-grey" style="margin: auto; width: 40%">
			<p class="w3-large w3-center">This is the main page, after the user logs in</p>
		</div>
	</div>
</body>

</html>