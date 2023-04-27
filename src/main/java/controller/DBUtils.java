package controller;

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
//       dbUrl = "jdbc:postgresql://localhost:5432/postgres;INIT=RUNSCRIPT FROM 'classpath:init.sql';";
       dbUrl = "jdbc:postgresql://localhost:5432/postgres";
       dbLogin = "maxim";
       dbPassword = "1";


        try {
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;


    }
}
