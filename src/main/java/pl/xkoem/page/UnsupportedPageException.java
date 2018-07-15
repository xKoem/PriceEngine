package pl.xkoem.page;

class UnsupportedPageException extends Exception {

    UnsupportedPageException(String page) {
        super(page);
    }
}
