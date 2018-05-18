package pl.xkoem.page;

import pl.xkoem.page.pages.MorelePage;
import pl.xkoem.page.pages.Page;
import pl.xkoem.page.pages.XKomPage;

import java.security.InvalidParameterException;

public class PageCreator {

    public static Page getPage(String pageUrl) {
        if (pageUrl.contains("x-kom.pl")) {
            return new XKomPage(pageUrl);
        } else if (pageUrl.contains("morele.net")) {
            return new MorelePage(pageUrl);
        }
        throw new InvalidParameterException("Cannot load url: " + pageUrl);
    }
}
