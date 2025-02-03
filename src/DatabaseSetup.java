import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
    public static void setupDatabase() {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                balance NUMERIC(10, 2) NOT NULL,
                isPremium BOOLEAN NOT NULL
            );
        """;

        String createRestaurantsTable = """
            CREATE TABLE IF NOT EXISTS restaurants (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                location VARCHAR(150) NOT NULL
            );
        """;

        String createMenuItemsTable = """
            CREATE TABLE IF NOT EXISTS menu_items (
                id SERIAL PRIMARY KEY,
                restaurant_id INT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                name VARCHAR(100) NOT NULL,
                price NUMERIC(10, 2) NOT NULL
            );
        """;

        String createOrdersTable = """
            CREATE TABLE IF NOT EXISTS orders (
                id SERIAL PRIMARY KEY,
                user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                restaurant_id INT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                total_price NUMERIC(10, 2) NOT NULL,
                order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createUsersTable);
            stmt.execute(createRestaurantsTable);
            stmt.execute(createMenuItemsTable);
            stmt.execute(createOrdersTable);
            System.out.println("Database setup complete.");
        } catch (Exception e) {
            System.err.println("Error setting up the database: " + e.getMessage());
        }
    }
}