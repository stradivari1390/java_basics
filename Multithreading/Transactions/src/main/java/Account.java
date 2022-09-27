import lombok.Data;

@Data
public class Account {

    private int accountNum;

    private volatile long balance;

    private boolean isBlocked = false;

    public Account(int accountNum, long balance) {
        this.accountNum = accountNum;
        this.balance = balance;
    }
}