package pl.xkoem.priceengine.page.pages;

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
