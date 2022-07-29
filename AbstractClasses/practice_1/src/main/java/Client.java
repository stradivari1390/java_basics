public abstract class Client {

    protected static final double START_BALANCE = 0D;
    protected double accountBalance = START_BALANCE;

    public double getAmount() {
        return accountBalance;
    }

    public void put(double amount) {
        if (amount > 0) {
            accountBalance += amount;
        }
    }

    public void take(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
        }
    }

    abstract String getName(); /*entails duplication of code,
                                but this is a condition of the task*/
}
