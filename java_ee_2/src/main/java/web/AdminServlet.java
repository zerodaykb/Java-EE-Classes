package web;


import dao.repositories.UsersRepository;
import domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/premium")
public class AdminServlet extends HttpServlet {
  private UsersRepository repo;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    repo = (UsersRepository) getServletContext().getAttribute("users");
  }


  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    User user = retrieveUserFromUsername(request);

    if(user != null) {
      setUserPremiumOption(request, user);
      updateUser(user);
    }

    response.sendRedirect("admin.jsp");
  }

  public User retrieveUserFromUsername(HttpServletRequest request) {
    String username = request.getParameter("username");
    return repo.getUserByUsername(username);
  }

  private void setUserPremiumOption(HttpServletRequest request, User user) {
    String account_type = request.getParameter("account_type");
    user.setPremium(account_type.equals("premium"));
  }

  private void updateUser(User user) {
    repo.update(user);
  }

}
