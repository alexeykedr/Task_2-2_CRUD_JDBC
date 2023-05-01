package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcOperator {
    static Connection connection;

    public JdbcOperator() {
    }

    private static Connection getConnection() {
        if (connection == null) {
            connection = ConnectionSql.getConnection();
        }
        return connection;
    }

    public static <T> T execute(String sql, ProcessSqlRequest<T> processSqlRequest) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return processSqlRequest.run(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T transactionalExecute(SqlTransaction<T> executor) {
        try {
            Connection connection = getConnection();

            try {
                connection.setAutoCommit(false);
                T result = executor.execute(connection);
                connection.commit();
                return result;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
