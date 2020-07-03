<%@ page import="domain.Weather" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<jsp:useBean id="client" class="domain.WeatherClient" scope="session"/>

<form method="post">

    <label>Wybierz miasto:</label>
    <label>
        <select name="name">
            <option value="Warszawa">Warszawa</option>
            <option value="Gdansk">Gdansk</option>
            <option value="Krakow">Krakow</option>
            <option value="Wroclaw">Wroclaw</option>
            <option value="Poznan">Poznan</option>
            <option value="Lodz">Lodz</option>
            <option value="Katowice">Katowice</option>
        </select>
    </label>

    <input type="submit" value="sprawdz pogode"/>
</form>


<%
  String name = request.getParameter("name");

  if(name != null) {
    Weather weather = client.getByName(name);

    if(weather != null) {
%>
    <p>
        <%=
        "Miasto: " + name + "<br>" +
        "Poziom zachmurzenia: " + weather.getClouds().getAll() + "%" + "<br>" +
        "Temperatura: " + weather.getMain().getTemp() + "&#x2103;" + "<br>" +
        "Ciśnienie: " + weather.getMain().getPressure() + " hPa" + "<br>" +
        "Predkosc wiatru: " + weather.getWind().getSpeed() + " m/s"
        %>
    </p>
<%
    }
  }
%>




</body>
</html>
