package pl.xkoem;

import pl.xkoem.database.model.Product;
import pl.xkoem.database.model.Products;
import pl.xkoem.page.EmptyPage;
import pl.xkoem.page.PageCreator;
import pl.xkoem.page.pages.Page;


class URLChecker {
    private int checked = 0;

    Products checkPrices(Products productsToCheck) {

        System.out.println("Checking " + productsToCheck.size() + " products"); //todo: logger
        //todo: insert into db
        for (Product product: productsToCheck.getProducts()) {
            Page page = PageCreator.getPage(product.getLink());
            if (page instanceof EmptyPage) {
                continue;
            }

            product.setPrice(page.getProductPrice());
            checked++;
            System.out.print("*");
        }
        System.out.println();
        if (checked != productsToCheck.size()) {
            System.out.println("Products to check: " + productsToCheck.size() + " Checked: " + checked); //todo: logger
        }

        return productsToCheck;
    }

    String checkName(String link) {
        Page p = PageCreator.getPage(link);
        return p.getProductName();
    }

}
