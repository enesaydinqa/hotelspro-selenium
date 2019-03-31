package com.selenium.context.db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbImpl {

    ResultSet executeResultSet(String query) throws SQLException, IOException;

    <T> T execute(Class<T> clazz, String query, String columnName);

    List<String> executeForListString(String query, String columnName) throws IOException;

    List<Integer> executeForListInteger(String query, String columnName) throws IOException;

    List<Long> executeForListLong(String query, String columnName) throws IOException;

    Integer executeUpdate(String query) throws SQLException, IOException;
}
