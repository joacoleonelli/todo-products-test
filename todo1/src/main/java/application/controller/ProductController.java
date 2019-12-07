package application.controller;

import application.dto.ApiErrorDto;
import application.exception.ApiException;
import application.model.Product;
import application.service.ProductService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public enum ProductController {

    INSTANCE;

    Gson gson = new Gson();

    /**
     * Obtiene todos los productos de la base de datos en memoria.
     *
     * @param request
     * @param response
     * @return Lista de Productos como Json String.
     */
    public String getAllProducts(final Request request, final Response response)  {
        final List productServiceResponse = ProductService.INSTANCE.getAllProducts();
        return gson.toJson(productServiceResponse);
    }

    /**
     * Obtiene un producto da la base de datos en memoria dado un id como path param.
     *
     * @param request
     * @param response
     * @return Producto como Json String.
     */
    public String getProductById(final Request request, final Response response)  {
        final Product productServiceResponse = ProductService.INSTANCE.getProductById(request.params("id"));
        return gson.toJson(productServiceResponse);
    }

    /**
     * Guarda un nuevo producto en la base de datos en memoria.
     *
     * @param request
     * @param response
     * @return Producto guardado como Json String.
     */
    public String addProduct(final Request request, final Response response)  {
        final Map<Integer, Product> productServiceResponse = ProductService.INSTANCE.addProduct(gson.fromJson(request.body(), Product.class));
        response.status(HttpServletResponse.SC_CREATED);
        return gson.toJson(productServiceResponse);
    }

    /**
     * Actualiza un producto existente en la base de datos en memoria dado un id como path param.
     *
     * @param request
     * @param response
     * @return El producto actualizado o un mensaje de error en el caso de error.
     * @throws ApiException
     */
    public String updateProduct(final Request request, final Response response) throws ApiException {
        Map<Integer, Product> productServiceResponse = null;
        String productId = request.params("id");
        String operation = request.queryParams("operation");
        try {
            if (request.queryParams("operation") != null) {
                productServiceResponse= ProductService.INSTANCE.updateProductStock(productId, operation);
            } else {
                productServiceResponse = ProductService.INSTANCE.updateProduct(productId, gson.fromJson(request.body(), Product.class));
            }
            response.status(HttpServletResponse.SC_OK);
            return gson.toJson(productServiceResponse);
        } catch (ApiException e) {
            response.status(HttpServletResponse.SC_OK);
            return gson.toJson(new ApiErrorDto(400, e.getDescription()));
        }
    }

    /**
     * Elimina un producto existente en la base de datos en memoria dado un id como path param.
     *
     * @param request
     * @param response
     * @return El mensaje de deleted.
     */
    public String deleteProduct(final Request request, final Response response)  {
        ProductService.INSTANCE.deleteProduct(request.params("id"));
        response.status(HttpServletResponse.SC_NO_CONTENT);
        return "deleted";
    }
}
