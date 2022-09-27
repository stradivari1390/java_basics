import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.ThreadLocalRandom;


public class TransactionsThread implements Runnable {
    @Getter
    private static Logger logger;

    private Bank bank;

    private long start;

    public TransactionsThread(Bank bank, long start) {
        this.bank = bank;
        this.start = start;
    }

    @Override
    public void run() {

        logger = LogManager.getRootLogger();

        Bank bank = new Bank();

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        logger.warn("Money in the bank: " + bank.getSumAllAccounts());
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

        for (int i = 0; i < 100; i++) {
            int accountNum1 = ThreadLocalRandom.current().nextInt(0, 100_100);
            int accountNum2 = ThreadLocalRandom.current().nextInt(0, 100_100);
            long money;
            if(i%20 != 0) {
                money = ThreadLocalRandom.current().nextLong(0L, 50_000L);
            } else {
                money = ThreadLocalRandom.current().nextLong(50_001L, 100_000L);
            }
            try {
                bank.transfer(accountNum1, accountNum2, money);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        logger.warn("Money in the bank: " + bank.getSumAllAccounts());
        logger.info("processed within " + (System.currentTimeMillis() - start) + "ms");
    }
}