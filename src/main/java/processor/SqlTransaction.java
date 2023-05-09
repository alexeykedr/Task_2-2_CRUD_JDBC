package processor;

import java.sql.Connection;

public interface SqlTransaction <T>{
    T execute(Connection connection);
}
