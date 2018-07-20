package pl.xkoem.priceengineclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.xkoem.pricecheckerlib.util.LoggerService;
import pl.xkoem.pricecheckerlib.model.FrontProduct;
import pl.xkoem.pricecheckerlib.model.FrontProducts;
import pl.xkoem.priceengineclient.models.DatabaseFrontConnector;
import pl.xkoem.priceengineclient.models.ProductHistory;

import java.sql.SQLException;

@Controller
public class ProductSelectController {

    private final LoggerService logger;
    private final DatabaseFrontConnector databaseFrontConnector;

    @Autowired
    public ProductSelectController(LoggerService logger, DatabaseFrontConnector databaseFrontConnector) {
        this.logger = logger;
        this.databaseFrontConnector = databaseFrontConnector;
    }

    @GetMapping(value = "/productselector", produces = "text/html")
    public String getProductSelector(Model model) {
        model.addAttribute("product", new FrontProduct());
        FrontProducts products = databaseFrontConnector.getProducts();
        model.addAttribute("products", products);
        return "productselector";
    }

    @PostMapping(value =  "/productselector", produces = "text/html")
    public String getProduct(@ModelAttribute("product") FrontProduct frontProduct, RedirectAttributes redirectAttributes) throws SQLException {
        ProductHistory productHistory = databaseFrontConnector.getProductHistory(frontProduct.getId());
        logger.logInfo(this.getClass(), productHistory.toString());
        redirectAttributes.addFlashAttribute("productHistory", productHistory);
        return "redirect:/price";
    }
}
