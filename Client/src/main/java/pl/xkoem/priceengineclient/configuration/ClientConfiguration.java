package pl.xkoem.priceengineclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.xkoem.pricecheckerlib.util.LoggerService;
import pl.xkoem.priceengineclient.models.DatabaseFrontConnector;

@Configuration
public class ClientConfiguration {

    @Bean
    public LoggerService getLoggerService() {
        return new LoggerService();
    }

    @Bean
    public DatabaseFrontConnector getProductList() {
        return new DatabaseFrontConnector();
    }

}
