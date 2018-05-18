package pl.xkoem;

import pl.xkoem.page.Page;

public class Main {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Page page = new Page("https://www.morele.net/monitor-aoc-ag241qx-1060690/");
            System.out.println(page.getElementById("product_price_brutto").attr("content"));
        }
        System.out.println("Minelo s: " + (System.currentTimeMillis() - time)/1000);
    }
}

