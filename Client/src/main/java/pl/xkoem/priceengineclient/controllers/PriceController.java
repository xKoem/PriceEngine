package pl.xkoem.priceengineclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.xkoem.pricecheckerlib.util.LoggerService;
import pl.xkoem.priceengineclient.models.PriceHistory;
import pl.xkoem.priceengineclient.models.ProductHistory;

@Controller
public class PriceController {

    private final LoggerService logger;

    @Autowired
    public PriceController(LoggerService logger) {
        this.logger = logger;
    }

    @GetMapping(value = "/price", produces = "text/html")
    public String getPrice(@ModelAttribute("productHistory") ProductHistory productHistory, Model model) {
        PriceHistory priceHistory = new PriceHistory(productHistory);

        String productName = productHistory.getProductName();

        model.addAttribute("priceHistory", priceHistory);
        model.addAttribute("productName", productName);
        return "price";
    }
}
