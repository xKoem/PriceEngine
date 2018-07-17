package pl.xkoem.priceengine.config;

import pl.xkoem.priceengine.util.LoggerService;
import pl.xkoem.priceengine.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

class Configuration {
    private HashMap<ConfigType, String> configuration = new HashMap<>();
    private static final LoggerService logger = new LoggerService();

    private boolean configTypeExist(String type) {
        return Arrays.stream(ConfigType.values()).map(Enum::toString).anyMatch(n -> n.equals(type));
    }

    void add(Pair<String> configStrings) {
        if (!configTypeExist(configStrings.first)) {
            logger.logError(this.getClass(), "Config type: " + configStrings.first + " does not exist");
            return;
        }
        configuration.put(ConfigType.valueOf(configStrings.first), configStrings.second);
    }


    public String get(ConfigType configType) {
        return configuration.get(configType);
    }
}
