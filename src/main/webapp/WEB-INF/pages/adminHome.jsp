<%@page isELIgnored="false" %>
<html>
<body>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setDateHeader("Expires", 0);
response.setHeader("Pragma","no-cache"); //http 1.0 backward compatability
 if(session==null ||
session.getAttribute("userId")==null){
out.println("session is null");
response.setHeader("Location","login");
response.setStatus(301); 
}
else{
out.println("session is not null");	
}  
%>
<h2>Admin Home</h2>
 ${status} 
 <%
 out.println(session.getAttribute("userId"));
 %>
 <a href="logout.do">Logout</a>
</body>
</html>