<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.elearning.bean.RaiseQueryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.bd {
	margin-top: 40px;
}

.bd2 {
	margin-top: 80px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discussion</title>
</head>
<body style="background-color: orange;">
	<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>Discussion Forum</b></li>
		</ol>
		</nav>
	</div>
	<div class="container">
	<a href="<%=ELearnView.RAISE_VIEW_CTL %>" type="button" class="btn btn-success btn-lg">Raise Query</a>
	</div>
	
	<div class="container mt-4">
	<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Status</th>
      <th scope="col">course Name</th>
      <th scope="col">User</th>
      <th scope="col">Last Update</th>
      <th scope="col">Answer</th>
    </tr>
  </thead>
  <tbody>
  
  <%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = ((pageNo - 1) * pageSize) + 1;
	RaiseQueryBean bean = null;
	
	List list = ServletUtility.getList(request);
	
	Iterator<RaiseQueryBean> it = list.iterator();
	
	while (it.hasNext()) {
		bean = it.next();
%>
  
  <%UserBean u = (UserBean)session.getAttribute("user"); %>
  
    <tr>
    <%if(bean.getStatus()==1){ %>
    <td><p class="btn btn-success">Resolved</p></td><%}else{ %>
    <td><p class="btn btn-danger">Un-Resolved</p></td>
    <%} %>
    <td><%=bean.getCourseName() %></td>
    <td>
    <%=u.getFirstName()%></td>
    <td><%=DataUtility.getDateString(bean.getModifiedDatetime()) %></td>
      <td><%=DataUtility.getString(bean.getAnswer()) %></td>
    </tr>
   <%} %>
  </tbody>
</table>
	</div>
	<div style="margin-top: 500px">
	<%@include file="footer.jsp"%></div>
</body>
</html>