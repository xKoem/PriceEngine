package pl.xkoem;

import pl.xkoem.database.InvalidDatabaseConnection;

public class Main {
    public static void main(String[] args) throws InvalidDatabaseConnection {
        PriceEngine priceEngine = new PriceEngine();
        priceEngine.run();
    }
}

