package pl.xkoem.priceengineclient.models;

import java.util.List;

public class PriceHistory {

    private Prices prices;

    public PriceHistory(ProductHistory productHistory) {
        prices = productHistory.getPrices();
        fetchPrices();
    }

    private void fetchPrices() {
        Prices filledPrices = new Prices();

        for (int i = 0; i < prices.getPrices().size() - 1; i++) {
            Price price = prices.getPrice(i);
            Price nextPrice = prices.getPrice(i + 1);

            String priceString = price.getPrice();

            int pricesTimeDiff = nextPrice.hoursDiff(price);
            for (int j = 0; j < pricesTimeDiff; j++) {
                filledPrices.add(new Price(priceString, price.getHours() + j));
            }
        }

        filledPrices.add(prices.getPrice(prices.size() -1));
        prices = filledPrices;
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "prices=" + prices +
                '}';
    }

    public List<Price> getPrices() {
        return prices.getPrices();
    }
}
