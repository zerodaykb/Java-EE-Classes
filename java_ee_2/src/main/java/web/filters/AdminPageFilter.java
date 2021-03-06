package web.filters;


import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({"admin.jsp"})
public class AdminPageFilter implements Filter {

  @Override
  public void init (FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    User user = retrieveUserFromSession(request);

    if(user == null || !user.isAdmin()) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendRedirect("/");

      return;
    }

    chain.doFilter(request, response);

  }

  @Override
  public void destroy () {

  }


  private User retrieveUserFromSession(ServletRequest request) {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpSession session = httpRequest.getSession();

    return (User) session.getAttribute("user");
  }
}
