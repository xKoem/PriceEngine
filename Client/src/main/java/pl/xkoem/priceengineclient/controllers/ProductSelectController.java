package pl.xkoem.priceengineclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xkoem.pricecheckerlib.util.LoggerService;
import pl.xkoem.priceengineclient.models.Product;
import pl.xkoem.priceengineclient.models.DatabaseFrontConnector;
import pl.xkoem.priceengineclient.models.ProductHistory;

import java.sql.SQLException;

@Controller
public class ProductSelectController {

    private final LoggerService logger;
    private final DatabaseFrontConnector databaseFrontConnector;

    public ProductSelectController(LoggerService logger, DatabaseFrontConnector databaseFrontConnector) {
        this.logger = logger;
        this.databaseFrontConnector = databaseFrontConnector;
    }

    @GetMapping(value = "/productselector", produces = "text/html")
    public String getProductSelector(Model model) {
        model.addAttribute("product", new Product());
        databaseFrontConnector.getProducts();
        return "productselector";
    }

    @PostMapping(value =  "/productselector", produces = "text/html")
    public String getProduct(@ModelAttribute("product") Product product) throws SQLException {
        ProductHistory productHistory = databaseFrontConnector.getProductHistory(product.getId());
        logger.logInfo(this.getClass(), productHistory.toString());
        return "productselector";
    }

}
