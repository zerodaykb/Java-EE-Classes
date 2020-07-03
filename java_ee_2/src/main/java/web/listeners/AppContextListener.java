package web.listeners;

import dao.repositories.Repository;
import dao.repositories.RepositoryCatalog;
import org.hsqldb.server.ServerAcl;
import utils.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    ServletContext ctx = servletContextEvent.getServletContext();

    try {
      DBConnectionManager connectionManager = new DBConnectionManager();
      RepositoryCatalog repositoryCatalog = new RepositoryCatalog(connectionManager.getConnection());

      Repository usersRepository = repositoryCatalog.getUsersRepository();
      usersRepository.createTable();

      ctx.setAttribute("users", usersRepository);

      System.out.println("DB initialized successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ServerAcl.AclFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}