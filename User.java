abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String bankPassword;
    protected BankAccount bankAccount;


    // Constructor: called when creating a Maker or Buyer
    public User(String userId, String name, String email, String password,String bankPassword) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankPassword=bankPassword;
        this.bankAccount=new BankAccount(bankPassword);
    }

    // Common getter methods (you can add setters if needed)
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // This is an abstract method. Maker and Buyer will implement this in their own way.
    public abstract void showMenu();
}
 class BankAccount {
    private double balance;
    private String password;

    public BankAccount(String password) {
        this.balance = 0.0;
        this.password=password;
    }

    public double getBalance() {
        return balance;
    }

    // Add money to account (e.g., after a buyer purchases an item)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Rs. " + amount + " successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from account (optional, may not be needed now)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew Rs. " + amount + " successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
