package processor;

import java.sql.PreparedStatement;

public interface ProcessSqlRequest <T>{
    T run (PreparedStatement preparedStatement);
}
