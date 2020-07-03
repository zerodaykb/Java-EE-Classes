package filters;

import domain.LoanParameters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.Validator.areValidParams;

@WebFilter({"address.jsp"})
public class AddressFilter implements Filter {
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    LoanParameters loanParameters = (LoanParameters) req.getSession().getAttribute("parameters");

    if(loanParameters == null) {
      res.sendRedirect("index.jsp");
      return;
    }

    if(!areValidParams(req, new String[] {"firstname", "surname", "pesel"})) {
      res.sendRedirect("person.jsp");
      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy () {

  }
}
