<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="register" method="post">
    <label>Username</label>
    <input id="username" name="username">
    <br>

    <label>Password</label>
    <input type="password" id="password" name="password">
    <br>

    <label>Confirm password</label>
    <input type="password" id="confirm_password" name="confirm_password">
    <br>

    <label>email</label>
    <input type="email" id="email" name="email">
    <br>

    <input type="submit" value="Register">
</form>
<h2>Have account? <a href="index.jsp">Login</a></h2>

</body>
</html>
