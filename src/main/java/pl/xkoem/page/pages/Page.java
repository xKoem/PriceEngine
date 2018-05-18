package pl.xkoem.page.pages;

import org.jsoup.nodes.Document;
import pl.xkoem.page.loader.PageLoader;

import java.util.Optional;

public abstract class Page {

    Document page;
    boolean pageLoaded;

    boolean loadPage(String pageUrl) {
        Optional<Document> loadedPage = PageLoader.loadPage(pageUrl);
        if (loadedPage.isPresent()) {
            this.page = loadedPage.get();
            return true;
        } else {
            return false;
        }
    }

    public abstract Double getProductPrice();

    public abstract String getProductName();
}
