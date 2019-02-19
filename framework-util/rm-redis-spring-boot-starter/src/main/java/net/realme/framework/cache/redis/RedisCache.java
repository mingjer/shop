package net.realme.framework.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 91000044
 */
@Component
public class RedisCache<T> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    public T get(String key) {
        return redisTemplate.opsForValue().get(getKey(key));
    }
    /**
     * 默认1天过期
     * @param key
     * @param o
     */
    public void set(String key, T o) {
        set(key, o, 86400);
    }

    public void set(String key, T o, long ttlSecond) {
        redisTemplate.opsForValue().set(getKey(key), o);
        expireSecond(key, ttlSecond);
    }
    /**
     * @param key
     * @return 返回自增后的值
     */
    public long incr(String key){
        return this.redisTemplate.opsForValue().increment(getKey(key),1L);
    }
    /**
     * @param key
     * @param increment 增量值
     * @return 返回自增后的值
     */
    public long incr(String key, long increment){
        return this.redisTemplate.opsForValue().increment(getKey(key), increment);
    }
    /**
     * @param key
     * @return 返回自减后的值
     */
    public long decr(String key){
        return this.redisTemplate.opsForValue().increment(getKey(key),-1L);
    }

    /**
     * @param key
     * @param decrement 减量值
     * @return 返回自减后的值
     */
    public long decr(String key, long decrement){
        return this.redisTemplate.opsForValue().increment(getKey(key), - decrement);
    }
    /**
     *
     * @param key
     * @param ttl
     */
    public boolean expireSecond(String key, long ttl) {
        return redisTemplate.expire(getKey(key), ttl, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @param ttl
     */
    public boolean expireMillisecond(String key, long ttl) {
        return redisTemplate.expire(getKey(key), ttl, TimeUnit.MILLISECONDS);
    }

    public void delete(String key) {
        redisTemplate.delete(getKey(key));
    }

    public boolean setnx(String key, T o) {
        return redisTemplate.opsForValue().setIfAbsent(getKey(key), o);
    }

    public long lSize(String key) {
        return redisTemplate.opsForList().size(getKey(key));
    }

    public List<T> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(getKey(key), start, end);
    }

    public long leftPush(String key, T o) {
        return redisTemplate.opsForList().leftPush(getKey(key), o);
    }

    public long leftPushAll(String key, T... o) {
        return redisTemplate.opsForList().leftPushAll(getKey(key), o);
    }

    public T leftPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(getKey(key));
    }

    public T rightPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(getKey(key));
    }

    private String getKey(String key) {
        return key;
    }

    /**
     * 添加对象到集合中
     *
     * @param key
     * @param o
     * @return
     */
    public long addValueToSet(String key, T o) {
        return redisTemplate.opsForSet().add(key, o);
    }

    /**
     * 从集合中删除指定元素
     *
     * @param key
     * @param o
     * @return
     */
    public long popValueFromSet(String key, T o) {
        return redisTemplate.opsForSet().remove(key, o);
    }

    /**
     * 对象成员是否在集合之中
     *
     * @param key
     * @param o
     * @return
     */
    public boolean isMemberInSet(String key, T o) {
        return redisTemplate.opsForSet().isMember(key, o);
    }

    /**
     * 获取集合中的所有成员
     *
     * @param key
     * @return
     */
    public Set<T> getAllMembersFromSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 模糊查询根据模糊匹配查询key
     *
     * @param pattern
     * @return
     */
    public Set<String> getKeysByPrefix(String pattern){
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除一组Key
     *
     * @param keys
     * @return
     */
    public Long deleteKeyByPrefix(Set<String> keys) {
        return redisTemplate.delete(keys);
    }
}
