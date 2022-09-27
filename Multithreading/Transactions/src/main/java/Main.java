import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Bank bank = new Bank();

        List<Runnable> threadList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            threadList.add(new TransactionsThread(bank, start));
        }
        
        threadList.forEach(Runnable::run);
    }
}
