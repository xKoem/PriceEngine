package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.database.InvalidDatabaseConnection;
import pl.xkoem.file.FileReader;

public class Main {
    public static void main(String[] args) throws InvalidDatabaseConnection {
        PriceEngine priceEngine = new PriceEngine(loadConfig());
        priceEngine.run();
    }

    private static Config loadConfig() {
        Config config = new Config();
        config.loadConfiguration(FileReader.readFile("/Users/koem/IdeaProjects/PriceEngine/config/config.cfg"));
        return config;
    }

}

