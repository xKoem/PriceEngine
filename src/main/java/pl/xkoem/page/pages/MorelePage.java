package pl.xkoem.page.pages;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import pl.xkoem.page.CannotLoadPageException;
import pl.xkoem.page.NotValidPageException;

public class MorelePage extends Page {

    public MorelePage(String pageUrl) throws CannotLoadPageException {
       loadPage(pageUrl);
    }

    @Override
    public String getProductPrice() throws NotValidPageException {
        Element productPriceBrutto = page.getElementById("product_price_brutto");
        if(productPriceBrutto == null) {
            throw new NotValidPageException(page.baseUri());
        }

        String price = productPriceBrutto.attr("content");
        if (price == null) {
            throw new NotValidPageException(page.baseUri());
        }
        return price;

    }

    @Override
    public String getProductName() throws NotValidPageException {
        Elements pageTitle = page.getElementsByClass("page-title");
        if (pageTitle.size() == 0) {
            throw new NotValidPageException(page.baseUri());
        }

        String productName = pageTitle.get(0).ownText();
        if (productName == null) {
            throw new NotValidPageException(page.baseUri());
        }

        return productName;
    }
}
