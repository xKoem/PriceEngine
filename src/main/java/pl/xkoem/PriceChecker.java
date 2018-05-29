package pl.xkoem;

import pl.xkoem.data.Links;
import pl.xkoem.page.PageCreator;
import pl.xkoem.page.pages.Page;

class PriceChecker {
    private long checkingTime;
    private int checked;

    PriceChecker() {
        checkingTime = 0;
        checked = 0;
    }

    void checkPrices() {
        long time = System.currentTimeMillis();

        Page p = PageCreator.getPage("https://www.x-kom.pl/p/427826-sluchawki-przewodowe-logitech-pro-gaming-headset.html");
        checked++;
        System.out.println(p.getProductName() + " "+ p.getProductPrice());

        for (String link: Links.getLinks()) {
            Page page = PageCreator.getPage(link);
            System.out.println(page.getProductName() + " " + page.getProductPrice());
            checked++;
        }

        checkingTime =  (System.currentTimeMillis() - time)/1000;
    }

    long getCheckingTime() {
        return checkingTime;
    }

    int getChecked() {
        return checked;
    }
}
