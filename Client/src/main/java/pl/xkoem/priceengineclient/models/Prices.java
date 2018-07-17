package pl.xkoem.priceengineclient.models;

import java.util.ArrayList;
import java.util.List;

public class Prices {

    private List<Price> priceList;

    public Prices() {
        priceList = new ArrayList<>();
    }

    public void add(Price price) {
        priceList.add(price);
    }

    @Override
    public String toString() {
        return "Prices{" +
                "priceList=" + priceList +
                '}';
    }
}
