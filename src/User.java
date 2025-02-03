public interface User {
    int getId();
    String getName();
    double getBalance();
    void setBalance(double balance);
    void addTransaction(String transaction);
    double calculateDiscount(double amount);
    void showTransactionHistory();
}