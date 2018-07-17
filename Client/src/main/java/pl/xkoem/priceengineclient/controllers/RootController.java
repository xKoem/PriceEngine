package pl.xkoem.priceengineclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping(value = "/", produces = "text/html")
    public String getProductSelector() {
        return "redirect:/productselector";
    }
}
