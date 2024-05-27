<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.ctl.TopicRegCtl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.elearning.util.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="com.elearning.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Topic Reg</title>
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

			<li class="breadcrumb-item active" aria-current="page"><b>Add Topic</b></li>
		</ol>
		</nav>
</div>		
<jsp:useBean id="bean" class="com.elearning.bean.TopicBean" scope="request"></jsp:useBean>
<div class="container">
<form action="<%=ELearnView.TOPIC_REG_CTL%>" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<%=bean.getId()%>">
         					   <input type="hidden" name="roleid" value="<%=2%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b><b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></b><br/>
<label for="exampleInputPassword1">Course Name</label>
<div class="form-group"><select class="custom-select" name="cname">
<%
Connection con = JDBCDataSource.getConnection();
String sql = "SELECT * FROM COURSE";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
while(rs.next()){

%>

 
  <option value="<%=rs.getLong(1)%>"><%=rs.getString(3) %></option>
  

<%} %></select>
<div class="form-group">
    <label for="exampleInputEmail1">Topic Name</label>
    <input type="text" class="form-control" name="tname" id="exampleInputEmail1" value="<%=DataUtility.getStringData(bean.getTopicName())%>" aria-describedby="emailHelp" placeholder="Enter Topic">
    <font  color="red"><%=ServletUtility.getErrorMessage("tname", request)%></font>
  </div>
 <div class="form-group">
    <label for="exampleFormControlTextarea1">Topic Description</label>
    <textarea class="form-control" name="tdesc" id="exampleFormControlTextarea1" rows="3"><%=DataUtility.getStringData(bean.getTopicDescription())%></textarea>
  <font  color="red"><%=ServletUtility.getErrorMessage("tdesc", request)%></font>
  </div> 
</div>
 <div class="form-group">
    <label for="exampleFormControlFile1">Upload Material</label>
    <input type="file" name="uploadmaterial"  class="form-control-file" id="exampleFormControlFile1" required>
    <font  color="red"><%=ServletUtility.getErrorMessage("uploadmaterial", request)%></font>
  </div>
   <div class="form-group">
    <label for="exampleFormControlFile1">Upload Video</label>
    <input type="file" name="uploadvideo" class="form-control-file" id="exampleFormControlFile1" required>
    <font  color="red"><%=ServletUtility.getErrorMessage("uploadvideo", request)%></font>
  </div>
 <button type="submit" name="operation" class="btn btn-primary" value="<%=TopicRegCtl.OP_SIGN_UP%>">Add Topic</button>
</form>
<br/>
</div>
<%@include file="footer.jsp" %>
</body>
</html>