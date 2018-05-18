package pl.xkoem.page.pages;

public class MorelePage extends Page {

    public MorelePage(String pageUrl) {
        pageLoaded = loadPage(pageUrl);
    }

    @Override
    public Double getProductPrice() {
        String price = page.getElementById("product_price_brutto").attr("content");
        return Double.parseDouble(price);
    }

    @Override
    public String getProductName() {
        return page.getElementsByClass("page-title").get(0).ownText();
    }
}
