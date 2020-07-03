<%@ page import="java.util.Random" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Random generator = new Random();
    String number = "" + Math.abs(generator.nextInt());
%>

<jsp:useBean id="loan" class="domain.LoanApplication" scope="session"/>
<jsp:setProperty name="loan" property="number" value="<%= number %>"/>
<jsp:setProperty name="loan" property="date" value="<%= new Date() %>"/>

Wygenerowany numer wniosku: <%= loan.getNumber() %>
<br>
Data wygenerowania: <%= loan.getDate()%>

<form action="person.jsp" method="post">
    <label>Wnioskowana kwota:<input type="number" id="amount" name="amount"/></label><br>
    <label>Ilosc rat: <input type="number" id="installmentCount" name="installmentCount"/></label>
    <input type="submit" value="nastepny krok"/>
</form>



</body>
</html>
