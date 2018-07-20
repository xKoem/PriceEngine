package pl.xkoem.pricecheckerlib.model;

import java.util.ArrayList;
import java.util.List;

public class FrontProducts {
    List<FrontProduct> frontProducts;

    public FrontProducts() {
        frontProducts = new ArrayList<>();
    }

    public void addProduct(FrontProduct frontProduct) {
        frontProducts.add(frontProduct);
    }

    public List<FrontProduct> getFrontProducts() {
        return frontProducts;
    }
}
