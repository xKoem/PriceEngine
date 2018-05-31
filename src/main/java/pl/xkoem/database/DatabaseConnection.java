package pl.xkoem.database;

import pl.xkoem.config.Config;

import java.sql.*;

import static pl.xkoem.config.ConfigType.*;

public class DatabaseConnection {

    private Config config;
    private Connection connection;

    public DatabaseConnection(Config config) throws InvalidDatabaseConnection {
        this.config = config;
        if (!connect()) {
            throw new InvalidDatabaseConnection();
        }
    }

    private boolean connect() {
        try {
            connection = DriverManager.getConnection(config.get(DBUrl), config.get(DBUser), config.get(DBPassword));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean isConnectionEstablished() {
        return connection != null;
    }

    public void query() {
        if (!isConnectionEstablished()) {
            return;
        }
        Statement statement = createStatement();
        ResultSet resultSet = execQuery("select * from products", statement);
        printResults(resultSet);
    }

    private void printResults(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet execQuery(String query, Statement statement) {
        try {
             return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
