package springcloud.servicehi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import springcloud.servicehi.service.MyRedisService;


/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/6.
 */
@Service
public class myRedisImpl implements MyRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    //http://blog.liuhongnan.com/2017/07/03/Redis的并发控制/
    public Boolean setConcurrentLock(String key, Long expireTime) throws InterruptedException {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.setIfAbsent(key, "lock");//setIfAbsent  不存在key  则插入  返回true     存在key   返回false

    }

    public void deleteConcurrentLock(String key) {
        stringRedisTemplate.delete(key);
    }
}
