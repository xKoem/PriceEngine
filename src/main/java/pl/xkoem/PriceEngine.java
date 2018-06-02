package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.database.DatabaseConnection;
import pl.xkoem.database.InvalidDatabaseConnection;
import pl.xkoem.database.Query;
import pl.xkoem.database.QueryTranslator;

import java.util.List;

class PriceEngine {
    private Query query;

    PriceEngine(Config config) {
        try {
            this.query = new Query(new DatabaseConnection(config));
        } catch (InvalidDatabaseConnection invalidDatabaseConnection) {
            System.out.println("Cannot connect into database");
        }
    }

    void run() {
        URLChecker URLChecker = new URLChecker();
        List<String> linksToCheck = QueryTranslator.translateResultSetToListOfLinks(query.queryProductsForChecking());

        URLChecker.checkPrices(linksToCheck);
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
