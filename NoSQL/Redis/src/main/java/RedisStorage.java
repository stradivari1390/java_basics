import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.resps.Tuple;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RedisStorage {

    private static final int USERS_AMOUNT = 20;

    private Jedis client;
    
    List<Tuple> usersList;

    public RedisStorage(Jedis client) {
        this.client = client;
    }

    public void start() {
        init();
        printLog();
    }

    private void init() {
        removeKey();
        for (int i = 1; i <= USERS_AMOUNT; i++) {
            client.zadd("Users", new Date().getTime(), (new DecimalFormat("##").format(i)));
        }
        ScanResult<Tuple> users = client.zscan("Users", "0");
        usersList = users.getResult();
        usersList.sort(Comparator.comparing(o -> Integer.valueOf(o.getElement())));
    }

    private void printLog() {
        for (Tuple user : usersList) {
            System.out.println(" — Main page shows user " + user.getElement());
            try {
                Thread.sleep(1000);
                if (Math.random() > 0.9) {
                    promotion();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printLog();
    }

    private void promotion() throws InterruptedException {
        int randomUserId = (int) (usersList.size() * Math.random());
        String userId = usersList.get(randomUserId).getElement();
        System.out.println(" > User " + userId + " paid for one-time improve");
        System.out.println(" — Main page shows user " + userId);
        Thread.sleep(1000);
    }

    private void removeKey() {
        client.del("Users");
    }
}