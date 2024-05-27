<%@page import="com.elearning.ctl.QueryCtl"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answer Query</title>
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
		<form action="<%=ELearnView.QUERY_CTL%>" method="post" >
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
			

				<div class="form-group">
					<label for="exampleFormControlTextarea1">Questions</label>
					<input type="text" class="form-control" name="question" value="<%=DataUtility.getString(bean.getCourseDescription()) %>" readonly="readonly">
					
					<font color="red"><%=ServletUtility.getErrorMessage("tdesc", request)%></font>
				</div>
				<div class="form-group">
					<label for="exampleFormControlTextarea1">Answer</label>
					<textarea class="form-control" name="answer"
						id="exampleFormControlTextarea1" rows="3"><%=DataUtility.getStringData(bean.getAnswer())%></textarea>
					<font color="red"><%=ServletUtility.getErrorMessage("answer", request)%></font>
				</div>
			
	
	<div align="center"><button type="submit" name="operation" class="btn btn-primary"
		value="<%=QueryCtl.OP_SAVE%>">Submit</button></div>
	</form></div>
	<br />
 <%@include file="footer.jsp" %>
</body>
</html>