package pl.xkoem;


import pl.xkoem.util.Logger;

public class Main {
    public static void main(String[] args) {


        PriceChecker priceChecker = new PriceChecker();
        priceChecker.checkPrices();


        Logger.logCheckingInfo(priceChecker.getCheckingTime(), priceChecker.getChecked());
    }
}

