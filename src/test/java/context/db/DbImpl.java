package context.db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbImpl {

    ResultSet executeResultSet(String query) throws SQLException, IOException;

    Integer executeInteger(String query, String columnName) throws IOException;

    String executeString(String query, String columnName) throws IOException;

    Long executeLong(String query, String columnName) throws IOException;

    <T> T execute(Class<T> clazz, String query, String columnName);

    List<String> executeListString(String query, String columnName) throws IOException;

    List<Integer> executeListInteger(String query, String columnName) throws IOException;

    List<Long> executeListLong(String query, String columnName) throws IOException;

    Integer executeUpdate(String query) throws SQLException, IOException;
}
