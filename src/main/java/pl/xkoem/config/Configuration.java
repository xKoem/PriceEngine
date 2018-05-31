package pl.xkoem.config;

import pl.xkoem.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

class Configuration {
    private HashMap<ConfigType, String> configuration;

    Configuration() {
        configuration = new HashMap<>();
    }

    private boolean configTypeExist(String type) {
        return Arrays.stream(ConfigType.values()).map(Enum::toString).anyMatch(n -> n.equals(type));
    }

    void add(Pair<String> configStrings) {
        if (!configTypeExist(configStrings.first)) {
            System.out.println("Config type: " + configStrings.first + " does not exist");
            return;
        }
        configuration.put(ConfigType.valueOf(configStrings.first), configStrings.second);
    }


    public String get(ConfigType configType) {
        return configuration.get(configType);
    }
}
