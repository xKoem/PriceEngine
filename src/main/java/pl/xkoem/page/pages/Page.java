package pl.xkoem.page.pages;

import org.jsoup.nodes.Document;
import pl.xkoem.page.loader.PageLoader;

import java.util.Optional;

public abstract class Page {

    Document page;

    void loadPage(String pageUrl) throws CannotLoadPageException {
        Optional<Document> loadedPage = PageLoader.loadPage(pageUrl);
        if (loadedPage.isPresent()) {
            this.page = loadedPage.get();
        } else {
            throw new CannotLoadPageException(pageUrl);
        }
    }

    public abstract String getProductPrice();

    public abstract String getProductName();
}
