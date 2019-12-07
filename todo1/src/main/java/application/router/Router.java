package application.router;

import application.Database;
import application.controller.ProductController;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

import javax.servlet.http.HttpServletResponse;

/**
 * Router de la app que recibe las api calls correspondientes a cada endpoint con su controller.
 */
public class Router implements SparkApplication {
        private ProductController productController = ProductController.INSTANCE;

        public Database database;

    /**
     * Inicia el servidor con los endpoints proporcionados y un ping para verificar que esta funcionando correctamente.
     */
    @Override
        public void init() {

            Spark.before((request, response) -> {
            });

            Spark.after((request, response) -> {
                setHeaders(response);
            });

            Spark.notFound((request, response) -> {
                response.status(HttpServletResponse.SC_NOT_FOUND);
                return "404";
            });

            Spark.get("/ping", (request, response) -> {
                response.status(HttpServletResponse.SC_OK);
                return "pong";
            });

            Spark.get("/shirts", productController::getAllProducts);
            Spark.get("/shirts/:id", productController::getProductById);
            Spark.post("/shirts", productController::addProduct);
            Spark.put("/shirts/:id", productController::updateProduct);
            Spark.delete("/shirts/:id", productController::deleteProduct);
            Spark.options("/shirts", (request, response) -> {
                    response.status(HttpServletResponse.SC_OK);
                    return "OK";
            });

            Spark.options("/shirts/:id", (request, response) -> {
                response.status(HttpServletResponse.SC_OK);
                return "OK";
            });

        }

    /**
     * Agrega los headers necesarios al objeto response para que la api pueda ser consumida correctamente por el cliente proporcionado.
     * @param response
     */
    private void setHeaders(Response response) {
            response.header("Content-Type", "application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Credentials", "true");
            response.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT, DELETE");
            response.header("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        }
}
