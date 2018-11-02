package springcloud.servicehi.service.myRedis;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/6.
 */
public interface MyRedisService {
    Boolean setConcurrentLock(String key, Long expireTime) throws InterruptedException;

    void deleteConcurrentLock(String key);
}
