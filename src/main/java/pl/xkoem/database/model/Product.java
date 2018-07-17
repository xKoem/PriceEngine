package pl.xkoem.database.model;

public class Product {
    private int productID;
    private String link;
    private String price;
    private Boolean priceChanged;

    public Product(int productID, String link, String price) {
        this.productID = productID;
        this.link = link;
        this.price = price == null? "": price;
        this.priceChanged = false;
    }

    public void setPrice(String price) {
        if (!(this.price.equals(price))) {
            this.price = price;
            this.priceChanged = true;
        }
    }

    public boolean isPriceChanged() {
        return priceChanged;
    }

    public int getProductID() {
        return productID;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", link='" + link + '\'' +
                ", price='" + price + '\'' +
                ", priceChanged=" + priceChanged +
                '}';
    }
}
