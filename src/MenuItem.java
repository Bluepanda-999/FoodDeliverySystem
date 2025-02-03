public class MenuItem {
    private int id;
    private int restaurantId;
    private String name;
    private double price;

    public MenuItem(int id, int restaurantId, String name, double price) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MenuItem ID: " + id + ", Name: " + name + ", Price: " + price;
    }
}