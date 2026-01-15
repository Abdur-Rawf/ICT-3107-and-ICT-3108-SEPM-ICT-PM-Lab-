import java.util.Scanner;

public class ATM {
    private double balance = 1000;

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount;
        else
            System.out.println("Insufficient balance");
    }

    void checkBalance() {
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1.Deposit 2.Withdraw 3.Balance 4.Exit");
            int choice = sc.nextInt();

            if (choice == 1)
                atm.deposit(sc.nextDouble());
            else if (choice == 2)
                atm.withdraw(sc.nextDouble());
            else if (choice == 3)
                atm.checkBalance();
            else
                break;
        }
    }
}
