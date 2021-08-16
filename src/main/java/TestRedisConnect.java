import redis.clients.jedis.Jedis;

public class TestRedisConnect {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.11.128",6379);
        //Jedis jedis = new Jedis("localhost",6379);
        jedis.set("moxin", "2020");
        String value = jedis.get("moxin");
        System.out.println(value);
    }
}
