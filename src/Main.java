import java.util.Scanner;

public class Main {

    public static  void main(String[] args) {
        DatabaseSetup.setupDatabase();
        FoodDeliverySystem system = new FoodDeliverySystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Food Delivery System!");

        do {

            System.out.println("\nMenu:");
            System.out.println("1) Add a new user");
            System.out.println("2) Add a restaurant");
            System.out.println("3) Add a menu item");
            System.out.println("4) Place an order");
            System.out.println("5) Show all restaurants");
            System.out.println("6) Show user transaction history");
            System.out.println("7) Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Is the user premium? (true/false): ");
                    boolean isPremium = scanner.nextBoolean();
                    system.addUser(name, balance, isPremium);
                    break;

                case 2:
                    System.out.print("Enter restaurant name: ");
                    String restaurantName = scanner.nextLine();
                    System.out.print("Enter restaurant location: ");
                    String location = scanner.nextLine();
                    system.addRestaurant(restaurantName, location);
                    break;

                case 3:
                    System.out.print("Enter restaurant ID: ");
                    int restaurantId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter menu item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter menu item price: ");
                    double price = scanner.nextDouble();
                    system.addMenuItem(restaurantId, itemName, price);
                    break;

                case 4:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter restaurant ID: ");
                    restaurantId = scanner.nextInt();
                    System.out.print("Enter total price of the order: ");
                    double totalPrice = scanner.nextDouble();
                    system.placeOrder(userId, restaurantId, totalPrice);
                    break;

                case 5:
                    system.showRestaurants();
                    break;

                case 6:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextInt();
                    system.showUserTransactions(userId);
                    break;

                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}