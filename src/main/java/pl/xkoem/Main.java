package pl.xkoem;

import pl.xkoem.config.Config;
import pl.xkoem.file.FileReader;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PriceEngine priceEngine = new PriceEngine(loadConfig());

        while(true) {
            TimeUnit.SECONDS.sleep(55);
            Calendar now = Calendar.getInstance();
            if (now.get(Calendar.MINUTE) == 0) {
                priceEngine.run();
            }
        }
    }

    private static Config loadConfig() {
        Config config = new Config();
        config.loadConfiguration(FileReader.readFile("config/config.cfg"));
        return config;
    }

}

