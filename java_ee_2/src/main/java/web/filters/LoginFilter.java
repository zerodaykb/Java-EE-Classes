package web.filters;

import dao.repositories.UsersRepository;
import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter({"/login"})
public class LoginFilter implements Filter {
  FilterConfig config;

  public void setFilterConfig(FilterConfig config) {
    this.config = config;
  }

  public FilterConfig getFilterConfig() {
    return config;
  }

  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
    setFilterConfig(filterConfig);
  }

  @Override
  public void doFilter (
    ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    if(!correctCredentials(httpRequest)) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendRedirect("/");

      return;
    }

    chain.doFilter(request, response);

  }

  @Override
  public void destroy () {

  }

  boolean correctCredentials(HttpServletRequest request) {
    ServletContext context = getFilterConfig().getServletContext();
    UsersRepository repo = (UsersRepository) context.getAttribute("users");

    User user = repo.getUserByUsername(request.getParameter("username"));
    String password = request.getParameter("password");

    return user != null && user.getPassword().equals(password);
  }
}
