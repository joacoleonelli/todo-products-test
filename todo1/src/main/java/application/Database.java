package application;

import application.model.Product;

import java.util.Map;

public class Database {
    private Map<Integer, Product> products;

    public Database() {

    }

    public Database(Map<Integer, Product> products) {
        this.products = products;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }
}
