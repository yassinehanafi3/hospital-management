package config;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;

public class ConnectRedis {

    private static RedisConnection<String, String> connection;

    static {
        RedisClient redisClient = new RedisClient(RedisURI.Builder.redis("localhost", 6379).build());
        connection = redisClient.connect();
        System.out.println("Connected successfully to Redis");
    }

    public static RedisConnection<String, String> getConnection() {
        return connection;
    }

}
