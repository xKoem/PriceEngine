package pl.xkoem.priceengineclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xkoem.priceengineclient.models.Product;

@Controller
public class ProductSelectController {

    @GetMapping(value = "/productselector", produces = "text/html")
    public String getProductSelector(Model model) {
        model.addAttribute("product", new Product());
        return "productselector";
    }

    @PostMapping(value =  "/productselector", produces = "text/html")
    public String getProduct(@ModelAttribute("product") Product product) {
        System.out.println(product);
        return "productselector";
    }

}
