package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static String dbUrl;
    private static String dbLogin;
    private static String dbPassword;


    public static Connection getConnection(){
        Connection connection;

        Properties properties = new Properties();
        dbUrl = properties.getProperty("db.host");
        dbLogin = properties.getProperty("db.login");
        dbPassword = properties.getProperty("db.password");

        try {
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
