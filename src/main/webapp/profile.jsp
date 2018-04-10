<%@ page import="domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Your page</h1>
<p>Hello <%= ((User)session.getAttribute("user")).getUsername() %>!</p>

<% if(((User) session.getAttribute("user")).isAdmin()) {%>
  <%= "<p>You are admin. Go to <a href='/admin.jsp'>admin.jsp</a></p>"%>
<%}%>

<a href="premium.jsp">For premium only</a>
<br>
<br>
<a href="/logout">Logout</a>

</body>
</html>
