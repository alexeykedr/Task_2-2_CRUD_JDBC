package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtils {
    private static final String PROPS = "src/main/resources/jdbc.properties";
    private static Connection connection;

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try (FileInputStream inputStream = new FileInputStream(PROPS)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                final String dbDriver = properties.getProperty("driver");
                final String dbUrl = properties.getProperty("url");
                final String dbUser = properties.getProperty("username");
                final String dbPassword = properties.getProperty("password");

                Class.forName(dbDriver);
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            } catch (IOException | ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    public static PreparedStatement prepareStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement prepareStatementWithGenerateKeys(String sql) {
        try {
            return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void closeConnection(){
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
