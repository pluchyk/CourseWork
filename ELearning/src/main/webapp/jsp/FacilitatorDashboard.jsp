<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.elearning.util.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.elearning.ctl.CourseRegCtl"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#form1 {
	display: none;
}
#form2{
display: none;
}
#form3{
display: none;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<div class="container mt-5">
		
	
		<button type="button" id="formButton2" class="btn btn-warning">
			<i class="fa fa-eye fa-3x" aria-hidden="true">View Course</i>
		</button><br><br>
		<button type="button" id="formButton" class="btn btn-danger">
			<i class="fa fa-plus fa-3x" aria-hidden="true">Add New Course</i>
		</button>
		<a class="btn btn-danger" href="<%=ELearnView.TOPIC_REG_CTL%>"><i class="fa fa-plus fa-3x" aria-hidden="true">Add Topic</i></a>
	</div>
	<div class="container">
		<div class=" mt-5 mx-auto text-center" style="width: 35rem;">

			<div class="">
				<form id="form1" method="post" action="<%=ELearnView.Cousre_Reg_CTL%>">

					<jsp:useBean id="bean" class="com.elearning.bean.CourseBean"
						scope="request"></jsp:useBean>

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
					</font></b><b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>

					<div class="form-group">
						<input type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="cname"
							value="<%=DataUtility.getStringData(bean.getCourseName())%>"
							placeholder="Course Name"> <font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<div class="form-group">
						
						<textarea name="cdesc" class="form-control"
							id="exampleFormControlTextarea1" rows="3" placeholder="Course Description"></textarea>
					</div>



					<button type="submit" name="operation" class="btn btn-danger"
						value="<%=CourseRegCtl.OP_SAVE%>">Add</button>
				</form>
			</div>
		</div>
	</div>
<!-- TOPIC ADD START -->

<!-- TOPIC END  -->
<div class="container">
<table class="table" id="form2">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Course Name</th>
      <th scope="col">Course Description</th>
      <!-- <th scope="col">Upload</th> -->
    </tr>
  </thead>
  <tbody>
  <%
  int index=1;
  Connection conn = JDBCDataSource.getConnection();
	String sql = "SELECT * FROM COURSE";
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
  
  %>
    <tr>
      <th scope="row"><%=index++ %></th>
      <td><%=rs.getString(3) %></td>
      <td><%=rs.getString(4) %></td>
     <%--  <td><a href="viewcourse/upload?id=<%=rs.getInt(1)%>">Upload</a></td> --%>
    </tr>
    
    <%} %>
  </tbody>
</table>



</div>
	<script type="text/javascript">
$("#formButton").click(function(){
    $("#form1").toggle();
});
/* JQUERY FOR TABLE */
  $("#formButton2").click(function(){
	    $("#form2").toggle();
 });
  /* JQUERY FOR TOPIC ADDITION */
  $("#formButton3").click(function(){
	    $("#form3").toggle();
 });
</script>
	<div style="margin-top: 500px">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>