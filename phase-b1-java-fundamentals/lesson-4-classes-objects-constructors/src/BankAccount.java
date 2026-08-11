public class BankAccount {

    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance){

        if(accountNumber== null || accountNumber.isBlank()){
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        if(initialBalance< 0){
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber= accountNumber;
        this.balance= initialBalance;
    }

    public void deposit(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance+= amount;
    }

    public void withdraw(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if(amount> balance){
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance-= amount;
    }

    public double getBalance(){
        return balance;
    }

    public void displayInfo(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }

    public static void main(String[] args) {
        BankAccount account1= new BankAccount("123456", 1000);
        account1.displayInfo();
        account1.deposit(500);
        account1.displayInfo();
        account1.withdraw(200);
        account1.displayInfo();
    }
    
}
