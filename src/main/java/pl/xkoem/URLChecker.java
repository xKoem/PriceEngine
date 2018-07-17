package pl.xkoem;

import pl.xkoem.database.model.Product;
import pl.xkoem.database.model.Products;
import pl.xkoem.page.pages.EmptyPage;
import pl.xkoem.page.PageCreator;
import pl.xkoem.page.NotValidPageException;
import pl.xkoem.page.pages.Page;
import pl.xkoem.util.LoggerService;


class URLChecker {
    private int checked = 0;
    private static final LoggerService logger = new LoggerService();

    Products checkPrices(Products productsToCheck) {
        logger.logInfo(this.getClass(), "Checking " + productsToCheck.size() + " products");

        for (Product product : productsToCheck.getProducts()) {
            Page page = PageCreator.getPage(product.getLink());
            if (page instanceof EmptyPage) {
                continue;
            }

            try {
                product.setPrice(page.getProductPrice());
                checked++;
            } catch (NotValidPageException e) {
                logger.logError(this.getClass(), "Not valid page: " + e.getMessage());
            }
        }

        logger.logInfo(this.getClass(), "Products to check: " + productsToCheck.size() + " Checked: " + checked);


        return productsToCheck;
    }

    String checkName(String link) throws NotValidPageException {
        Page p = PageCreator.getPage(link);
        return p.getProductName();

    }

}
