<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<form action="login" method="post">
    <label>Username</label>
    <input id="username" name="username">
    <br>

    <label>Password</label>
    <input type="password" id="password" name="password">
    <br>

    <input type="submit" value="Login">

</form>
<h2>Don't have account? <a href="register.jsp">Register</a></h2>

</body>
</html>