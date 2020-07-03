package utils;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.hsqldb.util.DatabaseManagerSwing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

  private Connection connection;

  public DBConnectionManager () throws SQLException, IOException, ServerAcl.AclFormatException {
    String URL = "jdbc:hsqldb:hsql://localhost/workdb";

    HsqlProperties p = new HsqlProperties();
    p.setProperty("server.database.0","mem:mydb");
    p.setProperty("server.dbname.0","workdb");

    Server server = new Server();
    server.setProperties(p);
    server.start();

    DatabaseManagerSwing manager = new DatabaseManagerSwing();
    manager.main(new String[] { "--url", URL, "--noexit" });

    connection = DriverManager.getConnection(URL);
  }

  public Connection getConnection(){
    return connection;
  }

}
