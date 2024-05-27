<%@page import="com.elearning.model.EnrollModel"%>
<%@page import="com.elearning.bean.EnrollBean"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.ctl.EnrollCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.elearning.bean.CourseBean"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="com.elearning.ctl.CourseSearchCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.bd{
margin-top: 40px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CourseSearch</title>
</head>
<body style="background-color: orange;">
<h1 style="background-color: orange; font-family: monospace; font-weight: bolder;">Happy Learning !!</h1>
<div class="bd" >
<nav aria-label="breadcrumb"  >

  <ol class="breadcrumb" >
  
    <li class="breadcrumb-item active" aria-current="page"><b>Course</b></li>
  </ol>
</nav>
</div>
<div class="container">
 <form action="<%=ELearnView.COURSE_SERACH_CTL%>" method="post"> 
<div class="input-group mb-3">
  <input type="text" class="form-control" name="cname" placeholder="Course Search" aria-label="" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <span class="input-group-text" id="basic-addon2" ><i class="fa fa-search" aria-hidden="true"></i></span>
  
						&nbsp;	<input type="submit" name="operation"
								class="btn btn-primary pull-right " value="<%=CourseSearchCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=CourseSearchCtl.OP_RESET%>">
							
  </div>
</div>
<jsp:useBean id="enrolBean" class="com.elearning.bean.EnrollBean" scope="request"></jsp:useBean>
<center>
		<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
		<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
</center>
<table class="table table-bordered table-light">
		<thead class="thead-dark">
			
		</thead>
		<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					CourseBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<CourseBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
				<input type="hidden" name="cid" value="<%=bean.getId()%>"> <!-- Active -->
         					   
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			<tr>
				
				<td><%=bean.getId() %></td>
				<td><%=bean.getCourseName() %></td>
				<td><%=bean.getCourseDescription() %></td>
				<%-- <form action="<%=ELearnView.ENROLL_CTL%>" method="post">
 --%>				
              <%
          
              UserBean uBean=(UserBean)session.getAttribute("user");
           		EnrollModel model = new EnrollModel();
           		 EnrollBean enrollBean=model.findByPK(uBean.getId());
             	
              if(enrolBean.getStatus()!=1 ) {%>
				<%-- <td ><button class="btn btn-dark" type="submit" name="operation" value="<%=CourseSearchCtl.OP_SIGN_UP%>">Enroll</button></td>
				 --%>
				 <td ><a href="../student/coursesearch/enrollcourse?id=<%=bean.getId()%>&cid?=<%=bean.getId()%>" class="btn btn-dark" type="submit" name="operation" >Enroll</button></td>
				<%}else if(enrolBean.getStatus()==1){ %>
				<td ><button class="btn btn-dark" disabled="disabled" type="submit" name="operation" value="">Enrolled</button></td>
				<%} %>
				

				</tr><%} %></tbody></table>
</form>
</div>
<!-- </form> -->
<div style="margin-top: 500px">
<%@include file="footer.jsp" %></div>
</body>
</html>