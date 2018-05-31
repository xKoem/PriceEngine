package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.database.DatabaseConnection;
import pl.xkoem.database.InvalidDatabaseConnection;
import pl.xkoem.file.FileReader;

class PriceEngine {
    private Config config;

    void run() throws InvalidDatabaseConnection {
        config = new Config();
        config.loadConfiguration(FileReader.readFile("/Users/koem/IdeaProjects/PriceEngine/config/config.cfg"));

        DatabaseConnection databaseConnection = new DatabaseConnection(config);
        databaseConnection.query();

        PriceChecker priceChecker = new PriceChecker();
        priceChecker.checkPrices();
    }
}
