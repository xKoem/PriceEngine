package pl.xkoem.priceengine.page.pages;

import org.jsoup.nodes.Document;
import pl.xkoem.priceengine.page.CannotLoadPageException;
import pl.xkoem.priceengine.page.NotValidPageException;
import pl.xkoem.priceengine.page.loader.PageLoader;

public abstract class Page {
    Document page;

    void loadPage(String pageUrl) throws CannotLoadPageException {
        this.page = PageLoader.loadPage(pageUrl);
    }

    public abstract String getProductPrice() throws NotValidPageException;

    public abstract String getProductName() throws NotValidPageException;
}
