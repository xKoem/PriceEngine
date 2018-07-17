package pl.xkoem.priceengine.database;

import pl.xkoem.priceengine.util.LoggerService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private final DatabaseConnection databaseConnection;
    private static final LoggerService logger = new LoggerService();

    public Query(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void printResults(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                logger.logInfo(this.getClass(), resultSet.getInt("id") + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        Statement statement = databaseConnection.createStatement();
        exec("create table if not exists products (" +
                "product_id serial primary key," +
                "name varchar(250)," +
                "link varchar(250)," +
                "is_checking bool" +
                ")", statement);

        exec("create table if not exists prices(" +
                "price_id serial primary key," +
                "product_id integer references products," +
                "price varchar(15)," +
                "checking_time bigint" +
                ");", statement);
    }

    public void insertPrice(int productID, String price, long checkingTime) {
        Statement statement = databaseConnection.createStatement();
        exec("insert into prices (product_id, price, checking_time) values ('" + productID + "', '" + price + "', '" + checkingTime + "');", statement);
    }

    public void insertNewProduct(String productName, String productURL) {
        if (!productExist(productURL)) {
            Statement statement = databaseConnection.createStatement();
            exec("insert into products (name, link, is_checking) values ('" + productName + "', '" + productURL + "', 'true')", statement);
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
        return query("select prod.product_id, prod.link, pric.price from products prod " +
                "left join prices pric on pric.product_id = prod.product_id " +
                "where prod.is_checking = true " +
                "and ( " +
                "pric.checking_time = ( " +
                "select max(p.checking_time) from prices p " +
                "where p.product_id = prod.product_id " +
                ") " +
                "or pric.checking_time is null " +
                "); ");
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
