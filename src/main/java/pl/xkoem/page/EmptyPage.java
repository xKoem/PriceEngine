package pl.xkoem.page;

import pl.xkoem.page.pages.Page;

public class EmptyPage extends Page {

    @Override
    public String getProductPrice() {
        return "";
    }

    @Override
    public String getProductName() {
        return "";
    }
}
