package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.database.DatabaseConnection;
import pl.xkoem.database.InvalidDatabaseConnection;
import pl.xkoem.database.Query;
import pl.xkoem.database.QueryTranslator;
import pl.xkoem.database.model.Product;
import pl.xkoem.database.model.Products;
import pl.xkoem.util.LoggerService;

import java.util.List;

class PriceEngine {
    private Query query;
    private long checkingTime;
    private int insertedProducts;

    private static final LoggerService logger = new LoggerService();

    PriceEngine(Config config) {
        try {
            this.query = new Query(new DatabaseConnection(config));
            query.createTables();
            logger.logInfo(this.getClass(), "PriceEngine successfully loaded");
        } catch (InvalidDatabaseConnection invalidDatabaseConnection) {
            logger.logError(this.getClass(), "Cannot connect into database");
        }
    }

    void run() {
        URLChecker URLChecker = new URLChecker();
        Products productsToCheck = QueryTranslator.translateResultSetToListOfLinks(query.queryProductsForChecking());

        checkingTime = System.currentTimeMillis();
        Products checkedProducts = URLChecker.checkPrices(productsToCheck);
        insertPrices(checkedProducts);
    }

    private void insertPrices(Products checkedProducts) {
        insertedProducts = 0;
        checkedProducts.getProducts().stream()
                .filter(product -> !product.getPrice().equals(""))
                .filter(Product::isPriceChanged)
                .forEach(this::insertPrice);
        logger.logInfo(this.getClass(), "Updated prices for " + insertedProducts + " products");
    }

    private void insertPrice(Product product) {
        int productID = product.getProductID();
        String productPrice = product.getPrice();
        query.insertPrice(productID, productPrice, checkingTime);
        insertedProducts++;
    }

    void insertNewProducts(List<String> links) {
        URLChecker urlChecker = new URLChecker();
        for (String link: links) {
            String name = urlChecker.checkName(link);
            if (name.isEmpty()) {
                logger.logError(this.getClass(), "Cannot find name for url: " + link);
                continue;
            }
            query.insertNewProduct(name, link);
        }
    }
}
