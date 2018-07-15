package pl.xkoem.page;

public class UnsupportedPageException extends Exception {
    public UnsupportedPageException(String page) {
        super(page);
    }
}
