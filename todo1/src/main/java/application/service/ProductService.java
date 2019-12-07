package application.service;

import application.exception.ApiException;
import application.model.Product;
import application.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum ProductService {

    INSTANCE;

    /**
     * Obtiene todos los productos como una Lista de productos.
     * @return Lista de productos.
     */
    public List<Product> getAllProducts()  {
        Map<Integer, Product> repositoryResponse = ProductRepository.INSTANCE.getAll();
        return new ArrayList<>(repositoryResponse.values());
    }

    /**
     * Obtiene un producto dado un id
     * @param id
     * @return Producto.
     */
    public Product getProductById(String id)  {
        return ProductRepository.INSTANCE.getById(id);
    }

    /**
     * Guarda un nuevo producto.
     * @param product
     * @return Producto guardado.
     */
    public Map<Integer, Product> addProduct(Product product)  {
        return ProductRepository.INSTANCE.add(product);
    }

    /**
     * Actualiza un producto dado un id.
     * @param id
     * @param product
     * @return Producto actualizado.
     */
    public Map<Integer, Product> updateProduct(String id, Product product)  {
        return ProductRepository.INSTANCE.update(id, product);
    }

    /**
     * Actualiza el stock del producto de manera incremental o decremental dado un id.
     * @param id
     * @param operation
     *      sell para ventas de producto.
     *      addStock para agregar stock del mismo.
     * @return Producto con stock actualizado o un mensaje de error si el stock
     * @throws ApiException
     */
    public Map<Integer, Product> updateProductStock(String id, String operation) throws ApiException {
        Product product = ProductRepository.INSTANCE.getById(id);;
        if (operation.equals("addStock")) {
            product.setQuantity(product.getQuantity() + 1);
        }

        if (operation.equals("sell")) {
            if (product.getQuantity() == 0) {
                throw new ApiException("Bad Request.",  "No hay mas stock del producto.");
            } else {
                product.setQuantity(product.getQuantity() - 1);
            }
        }

        return ProductRepository.INSTANCE.update(id, product);
    }

    public void deleteProduct(String id)  {
        ProductRepository.INSTANCE.delete(id);
    }
}
