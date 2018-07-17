package pl.xkoem.priceengine;

import pl.xkoem.pricecheckerlib.config.Config;
import pl.xkoem.pricecheckerlib.database.DatabaseConnection;
import pl.xkoem.pricecheckerlib.database.InvalidDatabaseConnection;
import pl.xkoem.pricecheckerlib.database.Query;
import pl.xkoem.pricecheckerlib.database.QueryTranslator;
import pl.xkoem.pricecheckerlib.model.Product;
import pl.xkoem.pricecheckerlib.model.Products;
import pl.xkoem.priceengine.page.NotValidPageException;
import pl.xkoem.pricecheckerlib.util.LoggerService;

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
            String name = null;
            try {
                name = urlChecker.checkName(link);
                query.insertNewProduct(name, link);
            } catch (NotValidPageException e) {
                logger.logError(this.getClass(), "Not valid page: " + e.getMessage());
            }


        }
    }
}
