package pl.xkoem.priceengine.page;

import pl.xkoem.priceengine.page.pages.*;
import pl.xkoem.pricecheckerlib.util.LoggerService;

public class PageCreator {
    private static final LoggerService logger = new LoggerService();

    public static Page getPage(String pageUrl) {
        try {
            if (pageUrl.contains("x-kom.pl")) {
                return new XKomPage(pageUrl);
            } else if (pageUrl.contains("morele.net")) {
                return new MorelePage(pageUrl);
            }
        } catch (CannotLoadPageException e) {
            logger.logError(PageCreator.class, "Cannot load page: " +  e.getMessage());
            return new EmptyPage();
        }

        try {
            throw new UnsupportedPageException(pageUrl);
        } catch (UnsupportedPageException e) {
            logger.logError(PageCreator.class, "Unsupported page: " + e.getMessage());
            return new EmptyPage();
        }
    }
}
