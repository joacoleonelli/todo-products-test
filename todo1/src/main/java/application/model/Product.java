package application.model;

public class Product {
    private Integer id;
    private String type;
    private String superhero;
    private Integer price;
    private String brand;
    private Integer quantity;

    public Product(Integer id, String type, String superhero, Integer price, String brand, Integer quantity) {
        this.id = id;
        this.type = type;
        this.superhero = superhero;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuperhero() {
        return superhero;
    }

    public void setSuperhero(String superhero) {
        this.superhero = superhero;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
