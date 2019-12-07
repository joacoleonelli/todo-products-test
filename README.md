# todo-products-test
Test para Clovinn

Instrucciones para correr el proyecto:

La carpeta todo1 contiene ambos proyectos, servidor y cliente.

1) Ejecutar servidor:
  - Importar el proyecto en IDE como proyecto Maven.
  - En una consola moverse hacia la ubicación del proyecto dentro de /todo1 ejecutar:
        mvn clean install
    Para bajar compilar y bajar dependencias del proyecto servidor.
  - Luego ejecutar la clase todo1/src/main/java/application/Server que contiene le método main() que ejecuta la applicación.
  - El servidor debe estar corriendo en localhost:4567. Puede probar que funciona correctamente en un browser navegando a         localhost:4567/ping. Si todo es correcto debe responder con "pong".
  
2) Ejecutar el cliente:
  - En una consola moverse hacia todo1/todo1-front y ejecutar:
        npm install
    Para bajar ReactJS y las dependencias del proyecto cliente.
  - Luego en la misma consola ejecutar:
        npm run start
    Para levantar el cliente en un browser en localhost:3000.
 
Ya puede probarse el proyecto entero haciendo click en Productos y ejecutando las funcionalidades pedidas.
