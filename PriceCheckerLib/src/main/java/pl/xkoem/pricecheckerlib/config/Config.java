package pl.xkoem.pricecheckerlib.config;

import pl.xkoem.pricecheckerlib.util.Pair;

import javax.activation.MimeType;
import java.util.HashMap;
import java.util.List;

public class Config {

    private Configuration configuration;

    public Config() {
        configuration = new Configuration();
    }
    public void loadConfiguration(List<String> strings) {
        strings.stream()
                .map(n -> new Pair<>(n.substring(0, n.indexOf(":")).trim(), n.substring(n.indexOf(":") + 1).trim()) )
                .forEach(configuration::add);
    }

    public String get(ConfigType configType) {
        return configuration.get(configType);
    }
}
