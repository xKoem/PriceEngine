package pl.xkoem.priceengineclient.models;

import pl.xkoem.pricecheckerlib.config.Config;
import pl.xkoem.pricecheckerlib.database.DatabaseConnection;
import pl.xkoem.pricecheckerlib.database.InvalidDatabaseConnection;
import pl.xkoem.pricecheckerlib.database.Query;
import pl.xkoem.pricecheckerlib.file.FileReader;
import pl.xkoem.pricecheckerlib.model.Products;
import pl.xkoem.pricecheckerlib.util.LoggerService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseFrontConnector {

    private final LoggerService logger = new LoggerService();
    private Query query;

    public DatabaseFrontConnector() {
        try {
            Config config = new Config();
            config.loadConfiguration(FileReader.readFile("config/config.cfg"));

            this.query = new Query(new DatabaseConnection(config));
            query.createTables();
            logger.logInfo(this.getClass(), "Database front connector successfully loaded");
        } catch (InvalidDatabaseConnection invalidDatabaseConnection) {
            logger.logError(this.getClass(), "Cannot connect into database");
        }
    }

    public Products getProducts() {
        Products products = new Products();
        //todo: list of available products
        return products;
    }

    public ProductHistory getProductHistory(String productID) throws SQLException {
        ResultSet productName = query.getProductName(productID);
        productName.next();
        ProductHistory productHistory = new ProductHistory(Integer.valueOf(productID), productName.getString("name"));

        ResultSet allProductPrices = query.getAllProductPrices(productID);
        while(allProductPrices.next()) {
            String price = allProductPrices.getString("price");
            long time = allProductPrices.getLong("checking_time");

            productHistory.addPrice(new Price(price, time));
        }
        return productHistory;
    }
}
