package pl.xkoem.page.pages;

import pl.xkoem.page.CannotLoadPageException;

public class MorelePage extends Page {

    public MorelePage(String pageUrl) throws CannotLoadPageException {
       loadPage(pageUrl);
    }

    @Override
    public String getProductPrice() {
        return page.getElementById("product_price_brutto").attr("content");
    }

    @Override
    public String getProductName() {
        return page.getElementsByClass("page-title").get(0).ownText();
    }
}
