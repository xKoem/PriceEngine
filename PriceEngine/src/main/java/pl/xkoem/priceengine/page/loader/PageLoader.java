package pl.xkoem.priceengine.page.loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.xkoem.priceengine.page.CannotLoadPageException;

import java.io.IOException;

public class PageLoader {

    public static Document loadPage(String pageLink) throws CannotLoadPageException {
        try {
            return Jsoup.connect(pageLink).get();
        } catch (IOException e) {
            throw new CannotLoadPageException(pageLink);
        }
    }

}
