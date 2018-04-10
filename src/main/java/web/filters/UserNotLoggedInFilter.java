package web.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({"profile.jsp", "admin.jsp"})
public class UserNotLoggedInFilter implements Filter {
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpSession session = httpRequest.getSession();

    if(session.getAttribute("user") == null) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendRedirect("/");

      return;
    }

    chain.doFilter(request, response);

  }

  @Override
  public void destroy () {

  }
}
