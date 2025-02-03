import java.util.ArrayList;

public class OrdinaryUser implements User {
    private int id;
    private String name;
    private double balance;
    private ArrayList<String> transactions;

    public OrdinaryUser(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    @Override
    public double calculateDiscount(double amount) {
        return amount;
    }

    @Override
    public void showTransactionHistory() {
        System.out.println("Transaction History for " + name + ":");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}