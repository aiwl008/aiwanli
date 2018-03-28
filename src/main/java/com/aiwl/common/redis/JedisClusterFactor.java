package com.aiwl.common.redis;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/***
 * redis集群操作类
 * @author aiwl
 *
 */

public class JedisClusterFactor implements FactoryBean<JedisCluster>,InitializingBean {
	private JedisCluster jedisCluster;
	private GenericObjectPoolConfig genericObjectPoolConfig;
	private Resource redisConfig;
	private Integer timeout;
	
	public void afterPropertiesSet() throws Exception {
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		
		Properties pro = new Properties();
		pro.load(this.redisConfig.getInputStream());
		Map<Object,Object> param = pro;
		for(Object obj:param.keySet()){
			if(obj.toString().startsWith("redis.node")){
				String[] arr = param.get(obj).toString().split(":");
				HostAndPort had = new HostAndPort(arr[0],Integer.parseInt(arr[1]));
				set.add(had);
			}
		}
		jedisCluster = new JedisCluster(set, timeout, genericObjectPoolConfig);
		if(jedisCluster!=null){
			String result=jedisCluster.echo("[Redis Cluster] I am alive ? Yes!");
			System.out.println(result);
		}
	}

	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	public Class<?> getObjectType() {
		return (this.jedisCluster!=null?this.jedisCluster.getClass():JedisCluster.class);
	}

	public boolean isSingleton() {
		return true;
	}

	public void setGenericObjectPoolConfig(
			GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

	public void setRedisConfig(Resource redisConfig) {
		this.redisConfig = redisConfig;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}



}
