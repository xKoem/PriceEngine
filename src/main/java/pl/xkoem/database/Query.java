package pl.xkoem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private final DatabaseConnection databaseConnection;

    public Query(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void printResults(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewProduct(String productName, String productURL) {
        if (!productExist(productURL)) {
            Statement statement = databaseConnection.createStatement();
            exec("insert into products (name, link) values ('" + productName + "', '" + productURL + "')", statement);
        }
    }

    private boolean productExist(String productURL) {
        try {
            return query("select * from products where link='" + productURL + "'").next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public ResultSet queryProductsForChecking() {
        return query("select * from products where is_checking=1");
    }

    private ResultSet query(String query) {
        if (!databaseConnection.isConnectionEstablished()) {
            return null;
        }
        Statement statement = databaseConnection.createStatement();
        return execQuery(query, statement);
    }

    private void exec(String query, Statement statement) {
        try {
            statement.execute(query);
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
}