package pl.xkoem.page;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class PageLoader {

    static Optional<Document> loadPage(String pageLink)  {
        try {
            return Optional.of(Jsoup.connect(pageLink).get());
        } catch (IOException e) {
            Logger.getLogger("ERROR").log(Level.WARNING, "Cannot load page: " + pageLink);
        }
        return Optional.empty();
    }

}
