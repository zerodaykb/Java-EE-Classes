<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="person" class="domain.Person" scope="session"/>
<jsp:setProperty name="person" property="*"/>

Osoba: ${person.firstname} ${person.surname} <br>
Pesel: ${person.pesel}


<form action="success.jsp" method="post">
    <label>Ulica: <input id="street" name="street"/></label><br>
    <label>Nr budynku: <input id="houseNumber" name="houseNumber"/></label><br>
    <label>Nr mieszkania: <input id="localNumber" name="localNumber"/></label><br>
    <label>Miasto: <input id="city" name="city"/></label><br>
    <label>Kod pocztowy: <input id="zipCode" name="zipCode"/></label><br>
    <label>Nr telefonu: <input id="phoneNumber" name="phoneNumber"/></label><br>
    <input type="submit" value="nastepny krok">
</form>


</body>
</html>
