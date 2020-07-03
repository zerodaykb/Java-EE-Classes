package web;


import dao.repositories.Repository;
import domain.User;
import dao.repositories.UsersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private UsersRepository repo;

  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    repo = (UsersRepository) getServletContext().getAttribute("users");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    User user = retrieveUserFromUsername(request);
    request.getSession().setAttribute("user", user);
    response.sendRedirect("profile.jsp");
  }

  public User retrieveUserFromUsername(HttpServletRequest request) {
    String username = request.getParameter("username");
    return repo.getUserByUsername(username);
  }


}
