package pl.xkoem.page.pages;

import org.jsoup.select.Elements;
import pl.xkoem.page.CannotLoadPageException;
import pl.xkoem.page.NotValidPageException;

public class XKomPage extends Page {

    public XKomPage(String pageUrl) throws CannotLoadPageException {
        loadPage(pageUrl);
    }

    @Override
    public String getProductPrice() throws NotValidPageException {
        Elements price = page.getElementsByClass("price");
        if (price.size() == 0) {
            throw new NotValidPageException(page.baseUri());
        }

        Elements meta = price.get(0).getElementsByTag("meta");
        if (meta.size() == 0) {
            throw new NotValidPageException(page.baseUri());
        }

        String content = meta.get(0).attr("content");
        if (content == null) {
            throw new NotValidPageException(page.baseUri());
        }

        return content;
    }

    @Override
    public String getProductName() throws NotValidPageException {
        Elements productInfo = page.getElementsByClass("product-info");
        if (productInfo.size() == 0) {
            throw new NotValidPageException(page.baseUri());
        }

        Elements h1 = productInfo.get(0).getElementsByTag("h1");
        if (h1.size() == 0) {
            throw new NotValidPageException(page.baseUri());
        }

        String productName = h1.get(0).ownText();
        if (productName == null) {
            throw new NotValidPageException(page.baseUri());
        }

        return productName;
    }
}
