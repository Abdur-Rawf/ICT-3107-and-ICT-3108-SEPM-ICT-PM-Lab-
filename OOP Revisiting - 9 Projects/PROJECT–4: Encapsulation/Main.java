public class Main {
    public static void main(String[] args) {
        Account acc = new Account();
        acc.deposit(500);
        System.out.println(acc.getBalance());
    }
}
