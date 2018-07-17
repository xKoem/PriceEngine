package pl.xkoem.priceengine.page;

public class UnsupportedPageException extends Exception {

    public UnsupportedPageException(String page) {
        super(page);
    }
}
