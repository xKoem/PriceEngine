package pl.xkoem;

import pl.xkoem.data.Links;
import pl.xkoem.page.PageCreator;
import pl.xkoem.page.pages.Page;

import java.util.List;

class URLChecker {
    private long checkingTime;
    private int checked;

    URLChecker() {
        checkingTime = 0;
        checked = 0;
    }

    void checkPrices(List<String> linksToCheck) {
        System.out.println("Checking " + linksToCheck.size() + " products");

        for (String link: linksToCheck) {
            Page page = PageCreator.getPage(link);
            System.out.println(page.getProductName() + " " + page.getProductPrice());
            checked++;
        }

    }

    String checkName(String link) {
        Page p = PageCreator.getPage(link);
        return p.getProductName();
    }

    long getCheckingTime() {
        return checkingTime;
    }

    int getChecked() {
        return checked;
    }
}
