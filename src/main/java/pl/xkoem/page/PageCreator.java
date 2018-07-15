package pl.xkoem.page;

import pl.xkoem.page.pages.CannotLoadPageException;
import pl.xkoem.page.pages.MorelePage;
import pl.xkoem.page.pages.Page;
import pl.xkoem.page.pages.XKomPage;

import java.security.InvalidParameterException;

public class PageCreator {

    public static Page getPage(String pageUrl) {
        try {
            if (pageUrl.contains("x-kom.pl")) {
                return new XKomPage(pageUrl);
            } else if (pageUrl.contains("morele.net")) {
                return new MorelePage(pageUrl);
            }
        } catch (CannotLoadPageException e) {
            System.out.println("Cannot load page: " + e.getMessage());
            return new EmptyPage();
        }

        try {
            throw new UnsupportedPageException(pageUrl);
        } catch (UnsupportedPageException e) {
            System.out.println("Unsupported page: " + e.getMessage());
            return new EmptyPage();
        }
    }
}
