package pl.xkoem.page.pages;

public class XKomPage extends Page {

    public XKomPage(String pageUrl) {
        pageLoaded = loadPage(pageUrl);
    }

    @Override
    public String getProductPrice() {
        return page.getElementsByClass("price").get(0).getElementsByTag("meta").get(0).attr("content");
    }

    @Override
    public String getProductName() {
        return page.getElementsByClass("product-info").get(0).getElementsByTag("h1").get(0).ownText();
    }
}
