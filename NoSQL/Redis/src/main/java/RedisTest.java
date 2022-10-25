import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {

        Jedis client = new Jedis("localhost", 6379);

        RedisStorage redis = new RedisStorage(client);

        redis.start();
    }
}