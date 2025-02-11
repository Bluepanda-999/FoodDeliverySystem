import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodDeliverySystem {

    public FoodDeliverySystem() {
        System.out.println("Food Delivery System Initialized.");
    }

    //  Add a new user
    public void addUser(String name, double balance, boolean isPremium) {
        String sql = "INSERT INTO users (name, balance, isPremium) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setDouble(2, balance);
            stmt.setBoolean(3, isPremium);

            // RETURNING id
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt(1); 
                    System.out.println("User added successfully! ID: " + userId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    // Add a new restaurant
    public void addRestaurant(String name, String location) {
        String sql = "INSERT INTO restaurants (name, location) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.executeUpdate();
            System.out.println("Restaurant added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding restaurant: " + e.getMessage());
        }
    }

    //  Add a new menu item
    public void addMenuItem(int restaurantId, String name, double price) {
        String sql = "INSERT INTO menu_items (restaurant_id, name, price) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restaurantId);
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
            System.out.println("Menu item added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
        }
    }

    //  Place an order
    public void placeOrder(int userId, int restaurantId, double totalPrice) {
        String checkUserBalanceSql = "SELECT balance FROM users WHERE id = ?";
        String updateUserBalanceSql = "UPDATE users SET balance = balance - ? WHERE id = ?";
        String insertOrderSql = "INSERT INTO orders (user_id, restaurant_id, total_price) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Check user balance
            try (PreparedStatement checkStmt = conn.prepareStatement(checkUserBalanceSql)) {
                checkStmt.setInt(1, userId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    if (balance < totalPrice) {
                        System.out.println("Insufficient balance.");
                        conn.rollback();
                        return;
                    }
                } else {
                    System.out.println("User not found.");
                    conn.rollback();
                    return;
                }
            }

            // Deduct balance
            try (PreparedStatement updateStmt = conn.prepareStatement(updateUserBalanceSql)) {
                updateStmt.setDouble(1, totalPrice);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
            }

            // Insert order
            try (PreparedStatement insertStmt = conn.prepareStatement(insertOrderSql)) {
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, restaurantId);
                insertStmt.setDouble(3, totalPrice);
                insertStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Order placed successfully!");
        } catch (SQLException e) {
            System.err.println("Error placing order: " + e.getMessage());
        }
    }

    // Show all restaurants 
    public void showRestaurants() {
        String sql = "SELECT id, name, location FROM restaurants"; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No restaurants found.");
                return;
            }
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Location: " + rs.getString("location")); 
            }
        } catch (SQLException e) {
            System.err.println("Error fetching restaurants: " + e.getMessage());
        }
    }

    // Show user transaction history
    public void showUserTransactions(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No transactions found for this user.");
                return;
            }
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("id") + ", Restaurant ID: " + rs.getInt("restaurant_id") +
                        ", Total Price: " + rs.getDouble("total_price") +
                        ", Order Date: " + rs.getTimestamp("order_date"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user transactions: " + e.getMessage());
        }
    }
}
