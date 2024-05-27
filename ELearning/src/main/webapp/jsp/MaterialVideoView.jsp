<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.bd {
	margin-top: 40px;
}

.bd2 {
	margin-top: 80px;
}
</style>
</head>
<body style="background-color: orange;">
<jsp:useBean id="bean" class="com.elearning.bean.TopicBean" scope="request"></jsp:useBean>
<h1 style="font-family: monospace; font-weight: bolder;">Happy
		Learning !!</h1>
	<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b><%=bean.getTopicName() %></b></li>
			
			
		</ol>
		</nav>
	</div>
<div class="container">
		<div class="row">
			<div class="col-form-label-lg">
			
			<embed src="/ELearning/ImageServlet?id=<%=bean.getId()%>" alt="Avatar" width="500px" height="500"/ >


					<embed src="/ELearning/VideoServlet?id=<%=bean.getId()%>" alt="Avatar" width="500px" height="500"/ >
			</div>	

</div></div></body>
</html>