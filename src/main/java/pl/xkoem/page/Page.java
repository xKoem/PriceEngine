package pl.xkoem.page;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;

public class Page {

    private boolean pageLoaded;
    private Document page;

    public Page(String pageLink) {
        pageLoaded = loadPage(pageLink);
    }

    private boolean loadPage(String pageLink) {
        Optional<Document> loadedPage = PageLoader.loadPage(pageLink);
        if (loadedPage.isPresent()) {
            this.page = loadedPage.get();
            return true;
        } else {
            return false;
        }
    }

    boolean isPageLoaded() {
        return pageLoaded;
    }

    public Element getElementById(String id) {
        if (isPageLoaded()) {
            return page.getElementById(id);
        } else {
            return new Element("<div></div>");
        }
    }
}
