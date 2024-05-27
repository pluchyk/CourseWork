
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
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
<body>
<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>My Profile</b></li>
			
			
		</ol>
		</nav>
	</div>
<div class="container">
		<div class="card mt-5 mx-auto text-center" style="width: 18rem;">
			
			<div class="card-body">
				<h5 class="card-title">My-Profile</h5>
				<form action="<%=ELearnView.FACULTY_REG_CTL%>" method="post">
				<jsp:useBean id="bean" class="com.elearning.bean.UserBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
         					   <input type="hidden" name="roleid" value="<%=2%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b><b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
				<div  class="form-group"> <input
							type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="fname" value="<%=DataUtility.getStringData(bean.getFirstName())%>" placeholder="First Name">
					<font  color="red"><%=ServletUtility.getErrorMessage("fname", request)%></font>
					</div>
					<div  class="form-group"> <input
							type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="lname" value="<%=DataUtility.getStringData(bean.getLastName())%>" placeholder="Last Name">
					<font  color="red"><%=ServletUtility.getErrorMessage("lname", request)%></font>
					</div>
					<div  class="form-group"> <input
							type="email" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="login"  value="<%=DataUtility.getStringData(bean.getLogin())%>" placeholder="Email">
					<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<div class="form-group">
						 <input
							type="password" name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>" class="form-control" id="exampleInputPassword1"
							placeholder="Password">
							<font  color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
					</div>
					<div class="form-group">
						 <input
							type="password" name="cpassword" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>" class="form-control" id="exampleInputPassword1"
							placeholder="Retype Password">
							<font  color="red"><%=ServletUtility.getErrorMessage("cpassword", request)%></font>
					</div>
					<%if(userBean.getRoleid()==3){ %>
					<div class="form-group">
						 <input
							type="text" name="coursename" value="<%=DataUtility.getStringData(bean.getCourseName())%>" class="form-control" id="exampleInputPassword1"
							placeholder="Course Name">
							<font  color="red"><%=ServletUtility.getErrorMessage("coursename", request)%></font>
					</div>
					<%} %>
					
				</form>

			</div>
		</div>
	</div>
	<div style="margin-top: 500px">
<%@include file="footer.jsp" %></div>
</body>
</html>