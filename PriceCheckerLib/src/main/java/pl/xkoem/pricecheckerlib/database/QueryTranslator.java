package pl.xkoem.pricecheckerlib.database;

import pl.xkoem.pricecheckerlib.model.Product;
import pl.xkoem.pricecheckerlib.model.Products;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTranslator {

    public static Products translateResultSetToListOfLinks(ResultSet resultSet) {
        Products products = new Products();
        try {
            while (resultSet.next()) {
                int productID = resultSet.getInt("product_id");
                String link = resultSet.getString("link");
                String price = resultSet.getString("price");

                Product product = new Product(productID, link, price);
                products.addProduct(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
