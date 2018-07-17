package pl.xkoem.priceengineclient.models;

public class Price {

    private String price;
    private long time;

    public Price(String price, long time) {
        this.price = price;
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price='" + price + '\'' +
                ", time=" + time +
                '}';
    }
}
