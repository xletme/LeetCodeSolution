import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisCluster {

    public static void main(String[] args) throws IOException {
        Set<HostAndPort> jedisCLusterNode = new HashSet<HostAndPort>();
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8001));
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8002));
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8003));
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8004));
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8005));
        jedisCLusterNode.add(new HostAndPort("192.168.11.128",8006));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);

        JedisCluster jedisCluster = new JedisCluster(jedisCLusterNode,6000,10,config);

        System.out.println(jedisCluster.set("monkey","kaer"));
        System.out.println(jedisCluster.set("tiger","sima"));
        System.out.println(jedisCluster.get("monkey"));
        System.out.println(jedisCluster.get("sima"));

        jedisCluster.close();

    }
}
