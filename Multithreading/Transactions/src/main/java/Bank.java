import lombok.Data;
import lombok.Synchronized;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class Bank {

    private static Logger logger;

    private List<Account> accounts = new ArrayList<>();

    private final Account defaultAccount = new Account(0, 0);

    public Bank() {
        for (int i = 1; i <= 100; i++) {
            Account account = new Account(i, ThreadLocalRandom.current().nextLong(0L, 300_000_001L));
            accounts.add(account);
        }
        for (int i = 101; i <= 200; i++) {
            Account account = new Account(i, ThreadLocalRandom.current().nextLong(0L, 10_000_001L));
            accounts.add(account);
        }
        for (int i = 201; i <= 50_000; i++) {
            Account account = new Account(i, ThreadLocalRandom.current().nextLong(0L, 300_001L));
            accounts.add(account);
        }
        for (int i = 50_001; i <= 100_000; i++) {
            Account account = new Account(i, ThreadLocalRandom.current().nextLong(0L, 55_001L));
            accounts.add(account);
        }
    }

    public boolean isFraud() throws InterruptedException {
        Thread.sleep(1000);
        return ThreadLocalRandom.current().nextBoolean();
    }

    public void transfer(int fromAccountNum, int toAccountNum, long amount) throws IllegalArgumentException, InterruptedException {

        logger = LogManager.getRootLogger();

        Account fromAccount;

        Account toAccount;

        try {
            fromAccount = accounts.stream()
                    .filter(a -> a.getAccountNum() == fromAccountNum)
                    .findFirst().orElseThrow(IllegalArgumentException::new);
            toAccount = accounts.stream()
                    .filter(a -> a.getAccountNum() == toAccountNum)
                    .findFirst().orElseThrow(IllegalArgumentException::new);

            if (amount <= fromAccount.getBalance() && !fromAccount.isBlocked() && !toAccount.isBlocked()) {
                logger.info("Account # " + fromAccountNum + " Balance: " + fromAccount.getBalance() +
                        ", Account # " + toAccountNum + " Balance: " + toAccount.getBalance());
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        fromAccount.setBalance(fromAccount.getBalance() - amount);
                        toAccount.setBalance(toAccount.getBalance() + amount);
                    }
                }
                logger.info("Account # " + fromAccountNum + " Balance: " + fromAccount.getBalance() +
                        ", Account # " + toAccountNum + " Balance: " + toAccount.getBalance());
                if (amount >= 50_000L && isFraud()) {
                    fromAccount.setBlocked(true);
                    toAccount.setBlocked(true);
                    logger.warn("Accounts ## " + fromAccountNum + ", " + toAccountNum + " are blocked");
                }
            }

            if (amount > fromAccount.getBalance()) {
                logger.warn("Account # " + fromAccount.getAccountNum() +
                        " has not sufficient balance for transaction (" + amount + ")");
            }
        } catch (IllegalArgumentException exception) {
            logger.error("Account not found");
        }
    }

    @Synchronized
    public long getBalance(int accountNum) {
        Account account = accounts.stream()
                .filter(a -> a.getAccountNum() == accountNum)
                .findFirst().orElse(defaultAccount);
        return account.getBalance();
    }

    public long getSumAllAccounts() {
        long result = 0L;
        synchronized (accounts) {
            for (Account account : accounts) {
                result += account.getBalance();
            }
        }
        return result;
//        return accounts.stream().map(a -> a.getBalance()).reduce((m1, m2) -> m1 + m2).orElse(0L);
    }
}