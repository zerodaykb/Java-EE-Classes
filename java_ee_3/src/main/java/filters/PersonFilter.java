package filters;

import domain.LoanApplication;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.Validator.areValidParams;

@WebFilter({"person.jsp"})
public class PersonFilter implements Filter {

  @Override
  public void init (FilterConfig filterConfig) throws ServletException { }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    LoanApplication loanApplication = (LoanApplication) req.getSession().getAttribute("loan");

    if(loanApplication == null) {
      res.sendRedirect("index.jsp");
      return;
    }

    if(!areValidParams(req, new String[] {"amount", "installmentCount"})) {
      res.sendRedirect("loanParameters.jsp");
      return;
    }

    if(Integer.parseInt(req.getParameter("amount")) <= 0 ||
       Integer.parseInt(req.getParameter("installmentCount")) <= 0)
    {
      res.sendRedirect("loanParameters.jsp");
      return;
    }


    chain.doFilter(request, response);
  }

  @Override
  public void destroy () {

  }
}
