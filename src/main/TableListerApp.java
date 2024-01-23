package main;

import java.sql.SQLException;

import jdbc.DciDBConnector;

public class TableListerApp {

  public static void main( String[] args ) {
    try {
      DciDBConnector.displayTables();
    } catch ( SQLException e ) {
      e.printStackTrace();
    } 

  }

}
