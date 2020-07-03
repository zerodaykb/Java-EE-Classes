package web;


import domain.User;
import dao.repositories.UsersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private UsersRepository repo;

  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    repo = (UsersRepository) getServletContext().getAttribute("users");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    User user = retrieveUserFromRequest(request);
    repo.add(user);

    request.getSession().setAttribute("user", user);
    response.sendRedirect("profile.jsp");
  }

  private User retrieveUserFromRequest(HttpServletRequest request) {
    User user = new User();
    user.setUsername(request.getParameter("username"));
    user.setPassword(request.getParameter("password"));
    user.setEmail(request.getParameter("email"));

    return user;
  }
}
