import basic.JunitSpringContext;
import org.apache.shiro.session.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by zxj on 2017/2/28.
 */
public class RedisTest extends JunitSpringContext {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    RedisTemplate<String, Session> redisTemplate1;

    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    @Test
    public void inset() {
        final String key1 = "a";
        final String value = "b";
        Boolean execute = redisTemplate.execute(new RedisCallback<Boolean>() {

            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(key1);
                byte[] name = serializer.serialize(value);
                return redisConnection.setNX(key, name);
            }
        });
    }

}
