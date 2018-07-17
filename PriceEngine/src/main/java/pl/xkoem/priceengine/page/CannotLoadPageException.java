package pl.xkoem.priceengine.page;

public class CannotLoadPageException extends Exception {

    public CannotLoadPageException(String pageUrl) {
        super(pageUrl);
    }
}
