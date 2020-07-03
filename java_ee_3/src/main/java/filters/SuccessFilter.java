package filters;

import domain.Person;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.Validator.areValidParams;

@WebFilter({"success.jsp"})
public class SuccessFilter implements Filter {
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    Person person = (Person) req.getSession().getAttribute("person");

    if(person == null) {
      res.sendRedirect("index.jsp");
      return;
    }

    if(!areValidParams(req, new String[] {
        "street",
        "houseNumber",
        "localNumber",
        "city",
        "zipCode",
        "phoneNumber"
      }))
    {
      res.sendRedirect("address.jsp");
      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy () {

  }
}
