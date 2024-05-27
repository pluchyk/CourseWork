<%@page import="com.elearning.ctl.CourseCtl"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit course</title>
</head>
<body>

<!-- Upload  -->

<div class="container">
		<div class="card mt-5 mx-auto text-center" style="width: 30rem;">
			
			<div class="card-body">
				<h5 class="card-title">Upload Files</h5>
<form action="<%=ELearnView.Course_VIEW_CTL%>" method="post" enctype="multipart/form-data" >
<jsp:useBean id="bean" class="com.elearning.bean.CourseBean"
								scope="request"></jsp:useBean>

							<input type="hidden" name="id" value="<%=bean.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=bean.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

							<br style="clear: both">
							<h3 style="margin-bottom: 15px; text-align: left:;">Upload Documents</h3>
							<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font></b>
  <div class="form-group">
   
    <input type="file" name="material" class="form-control-file" id="exampleFormControlFile1" required>
  </div>
  <div class="form-group">
    <h3>Upload Video</h3>
    <input type="file" name="video" class="form-control-file" id="exampleFormControlFile1" required>
  </div>
  
   
    <input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>" >
  
</form></div></div>
</div>
<div style="margin-top: 500px">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>