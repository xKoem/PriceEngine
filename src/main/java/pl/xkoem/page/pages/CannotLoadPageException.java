package pl.xkoem.page.pages;

public class CannotLoadPageException extends Exception {
    public CannotLoadPageException(String pageUrl) {
        super(pageUrl);
    }
}
