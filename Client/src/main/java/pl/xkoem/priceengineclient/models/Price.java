package pl.xkoem.priceengineclient.models;

import java.util.Date;

public class Price {

    private String price;
    private int hours;

    public Price(String price, long time) {
        this.price = price;
        this.hours = (int) (time/3_600_000);
    }

    public Price(String price, int hours) {
        this.price = price;
        this.hours = hours;
    }

    public long getTime() {
        return (long) hours * 3_600_000L;
    }

    public int getHours() {
        return hours;
    }

    public Date getDate() {
        return new Date(getTime());
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
                ", time=" + getDate() +
                '}';
    }

    public int hoursDiff(Price price) {
        return (int) (getHours() - price.getHours());
    }

    public void addHours(int i) {
        hours += i;
    }
}
