package pl.xkoem.priceengine.database;

import pl.xkoem.priceengine.config.Config;
import pl.xkoem.priceengine.util.LoggerService;


import java.sql.*;


import static pl.xkoem.priceengine.config.ConfigType.*;

public class DatabaseConnection {

    private Config config;
    private Connection connection;
    private final static LoggerService logger = new LoggerService();

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
            logger.logError(this.getClass(), "Connection failure to: " + DBUrl);
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
            logger.logError(this.getClass(), e.getMessage());
            return null;
        }
    }
}
