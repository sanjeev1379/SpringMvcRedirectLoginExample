<%@page isELIgnored="false" %>
<html>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setDateHeader("Expires", 0);
response.setHeader("Pragma","no-cache"); //http 1.0 backward compatability
if(session!=null &&
session.getAttribute("userId")!=null &&
session.getAttribute("userRole")!=null ){
if(session.getAttribute("userRole").equals("admin")){
out.println("session is null");
response.setHeader("Location","adminHome");
response.setStatus(301);
	}
	else if(session.getAttribute("userRole").equals("customer")){
		out.println("session is null");
		response.setHeader("Location","customerHome");
		response.setStatus(301);
			}
}
%>
<body>
<h2 align="center">LoginForm</h2>
<br/><hr/>
<div style="color:red;">
${status}
</div>
<div align="center">
<form action="login" method="post">
UserName :<input type="text" name="userName" required/><br/>
Password :<input type="password" name="password" required/><br/>
<input type="submit" value="Login"/>
</form></div>
</body>
</html>
