package task.system.utils;

import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.*;

public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() {
        init();
    }

    public void init () {

        Connection con = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }



    public Connection getConnection() {
        return connection;
    }
}
