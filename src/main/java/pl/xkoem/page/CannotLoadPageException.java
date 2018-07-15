package pl.xkoem.page;

public class CannotLoadPageException extends Exception {

    public CannotLoadPageException(String pageUrl) {
        super(pageUrl);
    }
}
