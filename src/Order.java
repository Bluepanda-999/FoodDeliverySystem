public class Order {
    private int id;
    private int userId;
    private int restaurantId;
    private double totalPrice;

    public Order(int id, int userId, int restaurantId, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", User ID: " + userId + ", Total Price: " + totalPrice;
    }
}