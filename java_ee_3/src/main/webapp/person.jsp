<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="parameters" class="domain.LoanParameters" scope="session"/>
<jsp:useBean id="loan" class="domain.LoanApplication" scope="session"/>

<jsp:setProperty name="parameters" property="*"/>

Numer wniosku: ${loan.number} <br>
Wnioskowana kwota: ${parameters.amount} <br>
Liczba rat: ${parameters.installmentCount}

<form action="address.jsp" method="post">
    <label>Imie: <input id="firstname" name="firstname"/></label><br>
    <label>Nazwisko: <input id="surname" name="surname"/></label><br>
    <label>Pesel: <input id="pesel" name="pesel"/></label><br>

    <input type="submit" value="nastepny krok">

</form>


</body>
</html>
