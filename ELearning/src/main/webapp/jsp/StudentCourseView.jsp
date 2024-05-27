<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.elearning.util.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
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
	<h1 style="font-family: monospace; font-weight: bolder;">Happy
		Learning !!</h1>
	<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>Overview</b></li>
		</ol>
		</nav>
	</div>
	<jsp:useBean id="bean" class="com.elearning.bean.CourseBean"
		scope="request"></jsp:useBean>
	<div class="container">
		<div class="row">
			<div class="col-form-label-lg">
				<h1>
					Course Name :
					<%=bean.getCourseName()%></h1>
				<p><%=bean.getCourseDescription()%></p>
			</div>

		</div>

	</div>


	<div class="bd2">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>Syllabus</b></li>
		</ol>
		</nav>
	</div>
	<table class="table">
  <thead class="thead-light">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Topic Name</th>
      <th scope="col">Description</th>
      <th scope="col">#</th>
    </tr>
  </thead>
  <tbody>
    
    
  
	<%
	int index =1;
	Connection con = JDBCDataSource.getConnection();
	String sql = "SELECT * FROM TOPIC WHERE courseid = '"+bean.getId()+"'";
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
	
	
	%>
	
	<div class="container">
		<div class="row">
			<tr>
      <th scope="row"><%=index++ %></th>
      <td><%=rs.getString(4) %></td>
      <td><%=rs.getString(5) %></td>
      <td><a href="courseview/video&material?id=<%=rs.getLong(1) %>" class="btn btn-success">Start</a></td>
    </tr>
			
			
			

		</div>

	</div>
	<%} %></tbody>
</table>
<div style="margin-top: 500px">
	<%@include file="footer.jsp"%></div>
</body>
</html>