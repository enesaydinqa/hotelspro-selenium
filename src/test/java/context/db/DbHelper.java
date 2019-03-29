package context.db;

import context.objects.Configuration;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper implements DbImpl {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    @Override
    public ResultSet executeResultSet(String query) throws IOException {

        try {
            statement = createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultSet;
    }

    @Override
    public Integer executeInteger(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Integer result = resultSet.getInt(columnName);

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public String executeString(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            String result = resultSet.getString(columnName);

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public Long executeLong(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Long result = resultSet.getLong(columnName);

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public <T> T execute(Class<T> clazz, String query, String columnName) {
        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return clazz.cast(resultSet.getObject(columnName));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public List<String> executeListString(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<String> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(resultSet.getString(columnName));
            }

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public List<Integer> executeListInteger(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Integer> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(resultSet.getInt(columnName));
            }

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public List<Long> executeListLong(String query, String columnName) throws IOException {

        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Long> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(resultSet.getLong(columnName));
            }

            return result;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public Integer executeUpdate(String query) throws IOException {

        try {
            statement = createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private Statement createStatement() throws IOException, ClassNotFoundException, SQLException {

        Configuration conf = new Configuration();

        String dbConnectionString = conf.getDbConnectionString();
        String dbUser = conf.getDbUser();
        String dbUserPassword = conf.getDbUserPassword();

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(dbConnectionString, dbUser, dbUserPassword);
        return connection.createStatement();
    }
}
