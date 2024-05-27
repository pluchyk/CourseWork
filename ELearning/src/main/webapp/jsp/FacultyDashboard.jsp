<%@page import="com.elearning.ctl.QueryCtl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.elearning.util.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.elearning.util.ServletUtility"%>
<%@page import="com.elearning.util.DataUtility"%>
<%@page import="com.elearning.ctl.RaiseQueryCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
#form1 {
	display: none;
}

#form2 {
	display: none;
}

#form3 {
	display: none;
}

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

			<li class="breadcrumb-item active" aria-current="page"><b>Discussion
					Forum</b></li>
		</ol>
		</nav>
	</div>
	<center>
		<button type="button" id="formButton2" class="btn btn-warning">
			<i class="fa fa-eye fa-3x" aria-hidden="true">View Queries</i>
		</button>
	</center>
	<div class="container">
		<div class=" mt-5 mx-auto text-center" style="width: 35rem;">

			<div class="">
				<form id="form2" method="post" action="<%=ELearnView.QUERY_CTL%>">

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

					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Course Name</th>
								<th scope="col">Course Description</th>

								<th scope="col">Date Posted</th>
								<th scope="col">Answer</th>
								<!-- <th scope="col">Upload</th> -->
							</tr>
						</thead>
						<tbody>
							<%
								int index = 1;
								Connection conn = JDBCDataSource.getConnection();
								String sql = "SELECT * FROM RAISEQUERY";
								PreparedStatement ps = conn.prepareStatement(sql);
								ResultSet rs = ps.executeQuery();
								while (rs.next()) {
							%>
							<tr>
								<th scope="row"><%=index++%></th>
								<td><%=rs.getString(3)%></td>
								<td><%=rs.getString(4)%></td>


								<td><%=DataUtility.getDateString(rs.getDate(9))%></td>
								<%if(rs.getInt(5)!=1){ %>
								<td><a
									href="../faculty/dashboard/queryresolved?id=<%=rs.getInt(1)%>"
									class="btn btn-warning">Answer</a></td>
								<%}else{ %>
								<td><a href="" disabled="disabled" class="btn btn-warning">Answered</a></td>
								<%} %>
							</tr>

							<%
								}
							%>
						</tbody>
					</table>


					<%-- <button type="submit" name="operation" class="btn btn-danger"
						value="<%=QueryCtl.OP_SAVE%>">Add</button> --%>
				</form>
			</div>
		</div>
	</div>
	<div style="margin-top: 500px">
		<%@include file="footer.jsp"%>
	</div>


	<script type="text/javascript">
		$("#formButton").click(function() {
			$("#form1").toggle();
		});
		/* JQUERY FOR TABLE */
		$("#formButton2").click(function() {
			$("#form2").toggle();
		});
		/* JQUERY FOR TOPIC ADDITION */
		$("#formButton3").click(function() {
			$("#form3").toggle();
		});
	</script>
</body>
</html>