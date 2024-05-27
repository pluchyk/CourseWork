<%@page import="com.elearning.ctl.ELearnView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Home Page</title>
<style>
body {
	background-image: url(images/elern.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

</head>
<body >
<%@include file="header.jsp" %>
<!-- SLiders starts -->
	<div class="mt-5">
	<center><a href="<%=ELearnView.SIGNIN_CTL%>" class="btn btn-outline-secondary">Login</a> &nbsp;&nbsp;&nbsp; <a href="<%=ELearnView.SIGNUP_CTL%>" class="btn btn-outline-secondary">SignUp</a></center>
	<center></center>
	</div>
	<!-- Slides End -->
<div style="margin-top: 500px">
		<%@ include file="footer.jsp"%>
		</div>
</body>
</html>