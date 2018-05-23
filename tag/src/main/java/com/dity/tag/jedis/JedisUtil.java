package com.dity.tag.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@EnableCaching
public class JedisUtil {

	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

	// 连接池对象
	private static JedisPool jedisPool;

	//主机
    private static String host;

    //端口
    private static int port;

    //最大连接数
    private static int maxIdle;

    //最小连接数
    private static int minIdle;

    //最大等待时间
    private static long maxWaitMillis;
    
    private static int maxTotal;

    private static String password;
	
    public static JedisPool getJedisPool(){
    	if(jedisPool == null){
    		JedisPoolConfig config = new JedisPoolConfig();
    		config.setMaxTotal(maxTotal);
    		config.setMaxIdle(maxIdle);
    		config.setMinIdle(minIdle);
    		config.setMaxWaitMillis(maxWaitMillis);
    		config.setTestOnBorrow(true);
    		config.setTestOnReturn(true);
    		config.setTestWhileIdle(true);
    		config.setTimeBetweenEvictionRunsMillis(30000);
    		config.setNumTestsPerEvictionRun(10);
    		config.setMinEvictableIdleTimeMillis(60000);
    		if(!StringUtils.isEmpty(password)){
    			jedisPool = new JedisPool(config, host, port, 10000, password);
    		} else {
    			jedisPool = new JedisPool(config, host, port);
    		}
    	}
    	return jedisPool;
    }



	/**
     * 获取Jedis连接客户端
     * 
     * @return
     */
	public static Jedis getJedisClient() {
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		} catch (Exception e) {
			logger.error("获取Redis客户端连接失败。");
			e.printStackTrace();
		}
		if (jedis == null) {
			logger.warn("没有获取到Redis客户端连接。");
		}
		return jedis;
	}

	/**
	 * 安全回收资源
	 * 
	 * @param jedis
	 */
	public static void close(Jedis jedis) {
		try {
			jedis.close();
		} catch (Exception e) {
			if (jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}

	/**
	 * 设置字符串型数据
	 * 
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 * @param timeout
	 *            超时时间(单位：秒） 设置为0，则无时效性。
	 * @return
	 */
	public static void setString(String key, String value, int timeout) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.set(key, value);
			if (timeout > 0) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 设置hashmap
	 * @param key
	 * @param filed
	 * @param value
	 */
	public static void setMap(String key,String filed,String value){
		Jedis jedis=null;
		try {
			jedis=getJedisClient();
			jedis.hset(key,filed,value);
		}catch (Exception e){
			jedis.close();
			logger.error("操作Redis失败", e);
		}finally {
			close(jedis);
		}
	}

	public static List<String> getValueFromMap(String key){
		Jedis jedis=null;
		List<String> result=new ArrayList<>();
		try {
			jedis=getJedisClient();
			Iterator<String> iter=jedis.hkeys(key).iterator();
			while (iter.hasNext()){
				String code = iter.next();
				List<String> hmget = jedis.hmget(key, code);
				result.addAll(hmget);
			}
			return result;
		}catch (Exception e){
			jedis.close();
			logger.error("操作Redis失败", e);
		}finally {
			close(jedis);
		}
		return null;
	}
	
	/**
	 * 设置字符串型数据过期时间
	 * 
	 * @param key
	 *            存储键
	 * @param timeout
	 *            超时时间(单位：秒）
	 * @param key
	 */
	public static void exprString(String key, int timeout) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.expire(key, timeout);
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 设置序列化对象数据
	 * 
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 * @param timeout
	 *            超时时间(单位：秒） 设置为0，则无时效性。
	 * @return
	 */
	public static void setObj(String key, byte[] value, int timeout) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.set(key.getBytes(), value);
			if (timeout > 0) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 获取字符串型数据
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			value = jedis.get(key);
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 获取序列化对象数据
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] getObj(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			value = jedis.get(key.getBytes());
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 删除对象数据
	 * 
	 * @param key
	 */
	public static void delObj(String key) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 删除字符串数据
	 * 
	 * @param key
	 */
	public static void delString(String key) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.del(key);
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}
	
	/**
	 * 清除DB
	 * 
	 */
	public static void flushDB() {
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.flushDB();
			logger.info("Redsis缓存DB重置成功。");
		} catch (Exception e) {
			jedis.close();
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}



	@Value("${spring.redis.host}")
	public void setHost(String host) {
		JedisUtil.host = host;
	}

	@Value("${spring.redis.port}")
	public void setPort(int port) {
		JedisUtil.port = port;
	}

	@Value("${spring.redis.pool.max-idle}")
	public void setMaxIdle(int maxIdle) {
		JedisUtil.maxIdle = maxIdle;
	}

	@Value("${spring.redis.pool.min-idle}")
	public void setMinIdle(int minIdle) {
		JedisUtil.minIdle = minIdle;
	}

	@Value("${spring.redis.pool.max-wait}")
	public void setMaxWaitMillis(long maxWaitMillis) {
		JedisUtil.maxWaitMillis = maxWaitMillis;
	}

	@Value("${spring.redis.pool.max-total}")
	public void setMaxTotal(int maxTotal) {
		JedisUtil.maxTotal = maxTotal;
	}

	public void setPassword(String password) {
		JedisUtil.password = password;
	}

	
}
