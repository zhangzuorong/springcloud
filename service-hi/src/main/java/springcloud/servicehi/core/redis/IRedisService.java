package springcloud.servicehi.core.redis;

import java.util.List;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/6.
 */
public interface IRedisService {
    public boolean set(String key, String value);
    public String get(String key);
    public boolean expire(String key, long expire);
    public <T> boolean setList(String key, List<T> list);
    public <T> List<T> getList(String key, Class<T> clz);
    public long lpush(String key, Object obj);
    public long rpush(String key, Object obj);
    public String lpop(String key);
}
