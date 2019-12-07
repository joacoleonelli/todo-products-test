package application.repository;

import application.Server;
import application.model.Product;
import application.utils.Utilities;

import java.util.HashMap;
import java.util.Map;

public enum ProductRepository {

    INSTANCE;

    /**
     * Obtiene los productos de la base de datos en memoria
     * @return Map de <Id, Producto>
     */
    public Map<Integer,Product> getAll()  {
        return Server.database.getProducts();
    }

    /**
     * Obtiene un producto de la base de datos en memoria dado un id.
     * @return Producto
     */
    public Product getById(String id) {
        return Server.database.getProducts().get(Integer.parseInt(id));
    }

    /**
     * Guarda un producto en la base de datos en memoria.
     * @param product
     * @return Producto guardado.
     */
    public Map<Integer,Product> add(Product product) {
        Integer id = Utilities.getRandomId();
        product.setId(id);
        Server.database.getProducts().put(id, product);
        Map<Integer, Product> response = new HashMap<>();
        response.put(id, Server.database.getProducts().get(id));
        return response;
    }

    /**
     * Actualiza un producto en la base de datos en memoria dado un id y atributos a actualizar.
     * @param product
     * @return Producto actualizado.
     */
    public Map<Integer,Product> update(String id, Product product) {
        product.setId(Integer.parseInt(id));
        Server.database.getProducts().put(Integer.parseInt(id), product);
        Map<Integer, Product> response = new HashMap<>();
        response.put(Integer.parseInt(id), Server.database.getProducts().get(Integer.parseInt(id)));
        return response;
    }

    /**
     * Elimina un producto de la base de datos en memoria dado un id.
     * @param id
     */
    public void delete(String id) {
        Server.database.getProducts().remove(Integer.parseInt(id));
    }
}
