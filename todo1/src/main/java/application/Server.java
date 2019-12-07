package application;

import application.model.Product;
import application.router.Router;

import java.util.HashMap;
import java.util.Map;

public class Server {

    public static Database database;

    /**
     * Metodo main para correr el proyecto en localhost:4567
     * @param args
     */
    public static void main(String[] args) {
        initInMemoryDatabase();
        new Router().init();
    }

    /**
     * Inicia la base de datos en memoria con 2 productos para proporcionar datos iniciales y ejemplos.
     *
     */
    private static void initInMemoryDatabase() {
        Product flashShirt = new Product(1,"Remera","Flash",30, "Marvel", 1);
        Product batmanComic = new Product(2,"Comic","Batman",50,"DC",1);
        Map<Integer, Product> products = new HashMap<>();
        products.put(1, flashShirt);
        products.put(2, batmanComic);

        database = new Database();

        database.setProducts(products);
    }
}