package task.system.utils;

import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    static Connection connection;

    public static void init () {

        try {
            JDBCDataSource dataSource = new JDBCDataSource();

            dataSource.setURL("mem://localhost/testdb");
            dataSource.setUser("SA");
            dataSource.setPassword("");

            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static Connection getConnection() {
        return connection;
    }
}
