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
import java.util.List;

@WebServlet("admin.jsp")
public class AdminPageServlet extends HttpServlet {
  private UsersRepository repo;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    repo = (UsersRepository) getServletContext().getAttribute("users");
  }


  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    List<User> userList = repo.getAll();
    response.setContentType("text/html");

    response.getWriter().print(
      "<form action=\"premium\" method=\"post\">\n" +
      "    <label>Username</label>\n" +
      "    <input id=\"username\" name=\"username\">\n\n" +
      "    <select id=\"account_type\" name=\"account_type\">\n" +
      "        <option value=\"premium\">Premium</option>\n" +
      "        <option value=\"normal\">Normal</option>\n" +
      "    </select>\n" +
      "    <input type=\"submit\" value=\"Submit change\">\n" +
      "</form>\n" +
      "\n" +
      "<p>Available users:</p>");

    response.getWriter().print("<pre>");
    for (User anUserList : userList) {
      response.getWriter().println(
        anUserList.getUsername() + ":" +
        (anUserList.isPremium() ? "premium ": "standard ") +
        "account"
      );
    }
    response.getWriter().print("</pre>");
  }
}
