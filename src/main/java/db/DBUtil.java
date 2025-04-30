package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Made by Abhishek: Utility class for database connections
public class DBUtil {
        // Made by Abhishek: Returns a connection to the MySQL database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
    }
}
