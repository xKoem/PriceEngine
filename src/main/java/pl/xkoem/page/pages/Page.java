package pl.xkoem.page.pages;

import org.jsoup.nodes.Document;
import pl.xkoem.page.CannotLoadPageException;
import pl.xkoem.page.loader.PageLoader;

public abstract class Page {
    Document page;

    void loadPage(String pageUrl) throws CannotLoadPageException {
        Document loadedPage = PageLoader.loadPage(pageUrl);
        this.page = loadedPage;
    }

    public abstract String getProductPrice();

    public abstract String getProductName();
}
