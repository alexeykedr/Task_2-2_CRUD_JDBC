package sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSql {
    private static final String PROPS = "src/main/resources/jdbc.properties";
    private final Connection connection;

    private ConnectionSql() {
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

    private static class ConnectionToSql{
        private static final ConnectionSql INSTANCE = new ConnectionSql();
    }

    public static Connection getConnection() {
        return ConnectionToSql.INSTANCE.connection;
    }
}
