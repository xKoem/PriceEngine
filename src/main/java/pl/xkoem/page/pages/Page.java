package pl.xkoem.page.pages;

import org.jsoup.nodes.Document;
import pl.xkoem.page.CannotLoadPageException;
import pl.xkoem.page.NotValidPageException;
import pl.xkoem.page.loader.PageLoader;

public abstract class Page {
    Document page;

    void loadPage(String pageUrl) throws CannotLoadPageException {
        this.page = PageLoader.loadPage(pageUrl);
    }

    public abstract String getProductPrice() throws NotValidPageException;

    public abstract String getProductName() throws NotValidPageException;
}
