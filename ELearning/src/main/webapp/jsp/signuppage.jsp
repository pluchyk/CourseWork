<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container-fluid">
  <div class="row">
  <div class="col-sm-9 col-md-6 col-lg-8">
     <p><img alt="" src="../images/e1.jpg"></p>
      
    </div>
    <div class="col-sm-3 col-md-6 col-lg-4 mt-5 text-center" align="right">
    <p><H1>SIGN UP AS</H1></p>
     <p><a href="<%=ELearnView.FACILITATOR_REG_CTL %>" class="btn btn-primary btn-lg">Facilitator</a></p>
      <p><a href="<%=ELearnView.STUDENT_REG_CTL %>" class="btn btn-primary btn-lg">Student</a></p>
      <p><a href="<%=ELearnView.FACULTY_REG_CTL %>" class="btn btn-primary btn-lg">Faculty</a></p>
    </div>
    
  </div>
</div>
<div class="mt-5">
<%@include file="footer.jsp" %>
</div>
</body>
</html>