<%@page import="javax.lang.model.element.Element"%>
<%@page import="com.elearning.ctl.FacultyLoginCtl"%>
<%@page import="com.elearning.ctl.StudentLoginCtl"%>
<%@page import="com.elearning.ctl.FacilitatorLoginCtl"%>

<%@page import="com.elearning.bean.UserBean"%>
<%@page import="com.elearning.ctl.ELearnView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
	<%
 UserBean userBean=(UserBean)session.getAttribute("user");
boolean userLoggedIn = userBean != null;

String welcomeMsg = "Hi, ";
if (userLoggedIn) {
    String role = (String) session.getAttribute("role");
    welcomeMsg += userBean.getFirstName();
    if(userBean.getRoleid()==1){
    	welcomeMsg +="";
    }else  if(userBean.getRoleid()==2){
    	welcomeMsg +="";
    }
    else if(userBean.getRoleid()==3){
    	welcomeMsg +="";
    }
} else {
    welcomeMsg += "Guest";
}

%>
	<!-- NavBar Starts  -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="#">E-Learning</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Col- Auto Start  -->
		<div class="col-auto">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<%if(!userLoggedIn) {%>
					<li class="nav-item active"><a class="nav-link"
						href="<%=ELearnView.HOME_CTl%>">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/ELearning/jsp/AboutUsView.jsp">About Us</a></li>
					<%} %>
					<%if(userLoggedIn) {%>
					
					<%if(userBean.getRoleid()==1){ %>
					 <li class="nav-item dropdown">
 	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/ELearning<%=ELearnView.MY_PROFILE_CTL%>">My Profile</a>
        <a class="dropdown-item" href="<%=ELearnView.FACILITATOR_LOGIN_CTL%>?operation=<%=FacilitatorLoginCtl.OP_LOG_OUT%>">Logout</a> 
        </div>
      </li>
      
					<%} %>
					<%if(userBean.getRoleid()==2){ %>
					<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          View
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ELearnView.Student_Dashboard_CTL%>">Dashboard</a>
          <a class="dropdown-item" href="<%=ELearnView.COURSE_SERACH_CTL%>">Course Search</a>
          <a class="dropdown-item" href="<%=ELearnView.DISCUSSION_CTL%>">Discussion Forum</a>
         </div>
      </li>
 		<li class="nav-item dropdown">
 	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/ELearning<%=ELearnView.MY_PROFILE_CTL%>">My Profile</a>
        <a class="dropdown-item" href="<%=ELearnView.STUDENT_LOGIN_CTL%>?operation=<%=StudentLoginCtl.OP_LOG_OUT%>">Logout</a> 
        </div>
      </li>
					<%} %>
					<%if(userBean.getRoleid()==3){ %>
 <li class="nav-item dropdown">
 	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         <a class="dropdown-item" href="/ELearning<%=ELearnView.MY_PROFILE_CTL%>">My Profile</a>
        <a class="dropdown-item" href="<%=ELearnView.FACULTY_LOGIN_CTL%>?operation=<%=FacultyLoginCtl.OP_LOG_OUT%>">Logout</a> 
        </div>
      </li>
					<%} %>
					<%} %>
					

				</ul>

			</div>
		</div>
		<!-- Col- Auto ENd  -->
	</div>
		<ul class="nav justify-content-end">
  	
 	<%if(userLoggedIn) {%>
 	
 	<%--  <li class="nav-item dropdown">
 	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="">My Profile</a>
          <a class="dropdown-item" href="<%=ELearnView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li> --%>
  	<%} %>
  	
	</ul>
	</nav>
	<!-- NavBar Ends  -->