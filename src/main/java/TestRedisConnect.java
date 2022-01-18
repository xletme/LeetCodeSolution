import redis.clients.jedis.Jedis;

public class TestRedisConnect {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.210.241.128",6379);
        jedis.auth("123456");

        //Jedis jedis = new Jedis("localhost",6379);
        jedis.set("moxin", "2020");
        String value = jedis.get("moxin");
        System.out.println(value);
    }
}
