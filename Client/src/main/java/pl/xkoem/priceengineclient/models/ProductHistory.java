package pl.xkoem.priceengineclient.models;

import java.util.List;

public class ProductHistory {
    private int id;
    private String name;
    private Prices prices;

    public ProductHistory(int id, String name) {
        this.id = id;
        this.name = name;
        this.prices = new Prices();
    }

    public Prices getPrices() {
        return prices;
    }

    public void addPrice(Price price) {
        prices.add(price);
    }

    @Override
    public String toString() {
        return "ProductHistory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prices=" + prices +
                '}';
    }

    public String getProductName() {
        return name;
    }
}
