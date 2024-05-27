<%@page import="com.elearning.ctl.FacultyLoginCtl"%>
<%@page import="com.elearning.ctl.StudentLoginCtl"%>
<%@page import="com.elearning.ctl.ForgotPasswordCtl"%>

<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.ctl.FacultyRegCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login View</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
</head>
<body>
	<div class="container">
		<div class="card mt-5 mx-auto text-center" style="width: 18rem;">
			
			<div class="card-body">
				<h5 class="card-title">Faculty Login As</h5>
				<form action="<%=ELearnView.FACULTY_LOGIN_CTL%>" method="post">
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
					
					
					
					<button type="submit" name="operation" class="btn btn-primary" value="<%=FacultyLoginCtl.OP_SIGN_IN%>">Submit</button>
					<a href="<%=ELearnView.FORGOT_PASSWORD_CTL %>" class="btn btn-primary"  >Forgot Password</a>
				</form>

			</div>
		</div>
	</div>


	<div style="margin-top: 500px">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>