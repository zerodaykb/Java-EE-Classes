<%@ page import="domain.LoanApplication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <jsp:useBean id="loanService" class="service.LoanService" scope="application"/>
    <jsp:useBean id="loan" class="domain.LoanApplication" scope="session"/>
    <jsp:useBean id="parameters" class="domain.LoanParameters" scope="session"/>
    <jsp:useBean id="person" class="domain.Person" scope="session"/>
    <jsp:useBean id="address" class="domain.Address" scope="session"/>

    <jsp:setProperty name="address" property="*"/>

<%

    loan.setParameters(parameters);
    loan.setPerson(person);
    loan.setAddress(address);
    loanService.add(loan);
    request.getSession().invalidate();

%>
<ul>
<%
  for (LoanApplication loanApplication: loanService.getAll()) {
%>
<li>Wniosek nr
    <%=
    loanApplication.getNumber() + "<br/>" +
    " Osoba: " +
            loanApplication.getPerson().getFirstname() +
            loanApplication.getPerson().getSurname() + "<br/>" +
    " Kwota: " + loanApplication.getParameters().getAmount() + "<br/>" +
    " Liczba rat: " + loanApplication.getParameters().getInstallmentCount() + "<br/>" +
    " Address: " +
            loanApplication.getAddress().getStreet() + " " +
            loanApplication.getAddress().getHouseNumber() + "/" +
            loanApplication.getAddress().getLocalNumber() + " " +
            loanApplication.getAddress().getZipCode() + " " +
            loanApplication.getAddress().getCity() + "<br/>" +
    " Telefon: " + loanApplication.getAddress().getPhoneNumber()

    %>
</li>
<%
   }
%>
</ul>
<a href="index.jsp">Złoż nowy wniosek</a>
</body>
</html>
