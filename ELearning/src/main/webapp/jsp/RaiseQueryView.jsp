<%@page import="com.elearning.ctl.RaiseQueryCtl"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.util.ServletUtility"%>
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
<style>

.bd {
	margin-top: 40px;
}

.bd2 {
	margin-top: 80px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: orange;">
<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>Discussion Forum</b></li>
		</ol>
		</nav>
</div>
	<jsp:useBean id="bean" class="com.elearning.bean.RaiseQueryBean"
		scope="request"></jsp:useBean>
	<div class="container">
		<form action="<%=ELearnView.RAISE_VIEW_CTL%>" method="post"
			>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="roleid" value="<%=2%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font></b><b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></b><br>
			<label for="exampleInputPassword1">Course Name</label>
			<div class="form-group">
				<select class="custom-select" name="cname">
					<%
Connection con = JDBCDataSource.getConnection();
String sql = "SELECT * FROM COURSE";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
while(rs.next()){

%>


					<option value="<%=rs.getString(3)%>"><%=rs.getString(3) %></option>


					<%} %>
				</select>

				<div class="form-group">
					<label for="exampleFormControlTextarea1">Description</label>
					<textarea class="form-control" name="tdesc"
						id="exampleFormControlTextarea1" rows="3"><%=DataUtility.getStringData(bean.getCourseDescription())%></textarea>
					<font color="red"><%=ServletUtility.getErrorMessage("tdesc", request)%></font>
				</div>
			</div>
	
	<div align="center"><button type="submit" name="operation" class="btn btn-primary"
		value="<%=RaiseQueryCtl.OP_SIGN_UP%>">Submit</button></div>
	</form>
	<br /></div>
	
<div style="margin-top: 500px">
	<%@include file="footer.jsp"%></div>
</body>
</html>