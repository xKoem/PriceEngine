package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.database.DatabaseConnection;
import pl.xkoem.database.InvalidDatabaseConnection;
import pl.xkoem.database.Query;
import pl.xkoem.database.QueryTranslator;
import pl.xkoem.database.model.Product;
import pl.xkoem.database.model.Products;

import java.util.List;

class PriceEngine {
    private Query query;
    private long checkingTime;

    PriceEngine(Config config) {
        try {
            this.query = new Query(new DatabaseConnection(config));
        } catch (InvalidDatabaseConnection invalidDatabaseConnection) {
            System.out.println("Cannot connect into database");
        }
    }

    void run() {
        URLChecker URLChecker = new URLChecker();
        Products productsToCheck = QueryTranslator.translateResultSetToListOfLinks(query.queryProductsForChecking());

        checkingTime = System.currentTimeMillis();
        Products checkedProducts = URLChecker.checkPrices(productsToCheck);
        insertPrices(checkedProducts);

        System.out.println(checkedProducts.toString());
    }

    private void insertPrices(Products checkedProducts) {
        checkedProducts.getProducts().stream()
                .filter(product -> !product.getPrice().equals(""))
                .forEach(this::insertPrice);
    }

    private void insertPrice(Product product) {
        int productID = product.getProductID();
        String productPrice = product.getPrice();
        query.insertPrice(productID, productPrice, checkingTime);
    }

    void insertNewProducts(List<String> links) {
        URLChecker urlChecker = new URLChecker();
        for (String link: links) {
            String name = urlChecker.checkName(link);
            if (name.isEmpty()) {
                System.out.println("Cannot find name for url: " + link);
                continue;
            }
            query.insertNewProduct(name, link);
        }
    }
}
