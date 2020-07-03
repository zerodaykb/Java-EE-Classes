package web.filters;

import dao.repositories.UsersRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/register")
public class RegisterFilter implements Filter {
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

    if(!passwordMatch(httpRequest) || fieldsAlreadyUsedInDatabase(httpRequest)) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendRedirect("register.jsp");

      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy () {

  }

  private boolean fieldsAlreadyUsedInDatabase(HttpServletRequest request) {
    ServletContext context = getFilterConfig().getServletContext();
    UsersRepository repo = (UsersRepository) context.getAttribute("users");

    String username = request.getParameter("username");
    String email = request.getParameter("email");

    return repo.areValuesUsed(username, email);
  }

  private boolean passwordMatch(HttpServletRequest request) {
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");

    return password.equals(confirmPassword);
  }

}
