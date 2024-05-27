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

.mt-100 {
	margin-top: 50px
}

body {
    background-color: #E8F5E9
}

.padding {
    padding: 10rem !important
}

.progress {
    width: 150px;
    height: 150px;
    line-height: 150px;
    background: none;
    margin: 0 auto;
    box-shadow: none;
    position: relative
}

.progress:after {
    content: "";
    width: 100%;
    height: 100%;
    border-radius: 50%;
    border: 12px solid #fff;
    position: absolute;
    top: 0;
    left: 0
}

.progress>span {
    width: 50%;
    height: 100%;
    overflow: hidden;
    position: absolute;
    top: 0;
    z-index: 1
}

.progress .progress-left {
    left: 0
}

.progress .progress-bar {
    width: 100%;
    height: 100%;
    background: none;
    border-width: 12px;
    border-style: solid;
    position: absolute;
    top: 0
}

.progress .progress-left .progress-bar {
    left: 100%;
    border-top-right-radius: 80px;
    border-bottom-right-radius: 80px;
    border-left: 0;
    -webkit-transform-origin: center left;
    transform-origin: center left
}

.progress .progress-right {
    right: 0
}

.progress .progress-right .progress-bar {
    left: -100%;
    border-top-left-radius: 80px;
    border-bottom-left-radius: 80px;
    border-right: 0;
    -webkit-transform-origin: center right;
    transform-origin: center right;
    animation: loading-1 1.8s linear forwards
}

.progress .progress-value {
    width: 90%;
    height: 90%;
    border-radius: 50%;
    background: #44484b;
    font-size: 24px;
    color: #fff;
    line-height: 135px;
    text-align: center;
    position: absolute;
    top: 5%;
    left: 5%
}

.progress.blue .progress-bar {
    border-color: #049dff
}

.progress.blue .progress-left .progress-bar {
    animation: loading-2 1.5s linear forwards 1.8s
}

.progress-left .progress-bar {
    animation: loading-3 1s linear forwards 1.8s
}

.progress-left .progress-bar {
    animation: loading-4 0.4s linear forwards 1.8s
}

.progress-left .progress-bar {
    animation: loading-5 1.2s linear forwards 1.8s
}

@keyframes loading-1 {
    0% {
        -webkit-transform: rotate(0deg);
        transform: rotate(0deg)
    }

    100% {
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg)
    }
}

@keyframes loading-2 {
    0% {
        -webkit-transform: rotate(0deg);
        transform: rotate(0deg)
    }

    100% {
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg)
    }
}

@keyframes loading-3 {
    0% {
        -webkit-transform: rotate(0deg);
        transform: rotate(0deg)
    }

    100% {
        -webkit-transform: rotate(90deg);
        transform: rotate(90deg)
    }
}

@keyframes loading-4 {
    0% {
        -webkit-transform: rotate(0deg);
        transform: rotate(0deg)
    }

    100% {
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg)
    }
}

@media only screen and (max-width: 990px) {
    .progress {
        margin-bottom: 20px
    }
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body style="background-color: orange;">
	<h1 style="font-family: monospace; font-weight: bolder;">Happy
		Learning !!</h1>
	<div class="bd">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>My
					Performance</b></li>
		</ol>
		</nav></div>
		<!-- START PROGRESS BAR  -->
		<div class="container">
		<div class="row">
			<%
				UserBean userBean3 = (UserBean) session.getAttribute("user");
				long Uid = userBean3.getId();
			
				Connection connection1 = JDBCDataSource.getConnection();
				String sql1 = "select enroll.id,courseid,enroll.userid,status,cname,cdescription,uploadmaterial,uploadvideo from enroll inner join course on enroll.courseid=course.id where enroll.userid='"
						+ Uid + "'";
				PreparedStatement ps1 = connection1.prepareStatement(sql1);
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
			%>
			<!-- PROGRESS BAR  -->
			
		<div class="col-sm-4">
				<div class="card" style="width: 18rem;">
				<p class="card-text" align="center" style="font-weight: bold;"><%=rs1.getString(5)%></p>
					<!-- <img class="card-img-top" src="images/teacher.png" alt="Card image cap"> -->
					<div class="card-body">
					<div class="progress blue"> <span class="progress-left"> <span class="progress-bar"></span> </span> <span class="progress-right"> <span class="progress-bar"></span> </span>
                <div class="progress-value">100%</div>
                
		</div></div></div></div>			
			<%} %>
</div>
	</div>
	<div class="bd2">
		<nav aria-label="breadcrumb">

		<ol class="breadcrumb">

			<li class="breadcrumb-item active" aria-current="page"><b>My
					Learning Path</b></li>
		</ol>
		</nav>
	</div>


	<div class="container">
		<div class="row">
			 <%
				UserBean userBean2 = (UserBean) session.getAttribute("user");
				long id = userBean2.getId();
				
				Connection connection = JDBCDataSource.getConnection();
				String sql = "select enroll.id,courseid,enroll.userid,status,cname,cdescription,uploadmaterial,uploadvideo from enroll inner join course on enroll.courseid=course.id where enroll.userid='"
						+ id + "'";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
			%> 
			<div class="col-sm-4">
				<div class="card" style="width: 18rem;">
					<!-- <img class="card-img-top" src="images/teacher.png" alt="Card image cap"> -->
					<div class="card-body">
						<h5 class="card-title">Course</h5>
						<p class="card-text"><%=rs.getString(5)%></p>
						<a href="dashboard/coursesearch/courseview?id=<%=rs.getLong(2) %>"
							class="btn btn-primary">Go to <%=rs.getString(5)%></a>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<div style="margin-top: 500px">
		<%@include file="footer.jsp"%></div>
</body>
</html>