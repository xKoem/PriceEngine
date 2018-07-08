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
            System.out.println("connection failure");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean isConnectionEstablished() {
        return connection != null;
    }

    Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
