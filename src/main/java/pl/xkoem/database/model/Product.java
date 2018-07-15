package pl.xkoem.database.model;

public class Product {
    private int productID;
    private String link;
    private String price;

    public Product(int productID, String link) {
        this.productID = productID;
        this.link = link;
    }

    public void setPrice(String price) {
        this.price = price;
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
                '}';
    }
}
