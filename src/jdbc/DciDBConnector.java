package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DciDBConnector {

  private static String url;
  private static String user;
  private static String password;

  private static Connection connection = null;
  private static DatabaseMetaData metaData = null;
  private static ResultSet resultSet = null;
    
  static {
    Properties prop = new Properties();
    InputStream inputStream = DciDBConnector.class.getClassLoader().getResourceAsStream( "jdbc/db.properties" );
    try {
      prop.load( inputStream );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    
    url = prop.getProperty( "db.url" );
    user = prop.getProperty( "db.username" );
    password = prop.getProperty( "db.password" );
    
    try {
      connection = DriverManager.getConnection( url, user, password );
      metaData = connection.getMetaData();
      resultSet = metaData.getTables( null, null, null, new String[] { "TABLE" } );
      
      System.out.println("Connected to the database successfully.");
    } catch ( SQLException e ) {
      e.printStackTrace();
    }
  }
  
  public static void displayTables() throws SQLException {
    while( resultSet.next() ) {
      String tableName = resultSet.getString( "TABLE_NAME" );
      System.out.printf( "- %s%n", tableName );
    }
  }
}
