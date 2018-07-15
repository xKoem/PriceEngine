package pl.xkoem.page.pages;

public class MorelePage extends Page {

    public MorelePage(String pageUrl) {
        pageLoaded = loadPage(pageUrl);
    }

    @Override
    public String getProductPrice() {
        return page.getElementById("product_price_brutto").attr("content");
    }

    @Override
    public String getProductName() {
        return page.getElementsByClass("page-title").get(0).ownText();
    }
}
