package com.xwx.redis.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwx.entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("Classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	RedisTemplate redisTemplate;
	@Test
	public void JdkTest() {
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {
			User user = new User(i,"李四","男","15162902325","@qq2653777809.com","2019-09-08");
			list.add(user);
			System.out.println(list);
		}
		
		long start = System.currentTimeMillis();
		
		redisTemplate.opsForList().leftPushAll("userss", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("序列化的方式事JDK");
		System.out.println("保存数量是十万个对象");
		System.out.println("消耗时间是:"+(end-start)+"毫秒");
		
		
	}
	
	@Test
	public void JsonTest() {
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {
			User user = new User(i,"李四","男","15162902325","@qq2653777809.com","2019-09-08");
			list.add(user);
			System.out.println(list);
		}
		
		long start = System.currentTimeMillis();
		
		redisTemplate.opsForList().leftPushAll("userss", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("序列化的方式事JDK");
		System.out.println("保存数量是十万个对象");
		System.out.println("消耗时间是:"+(end-start)+"毫秒");
		
		
	}
	
	
	@Test
	public void HashTest() {
		HashMap<String, String> map = new HashMap<String ,String>();
		for (int i = 0; i < 100000; i++) {
			User user = new User(i,"李四","男","15162902325","@qq2653777809.com","2019-09-08");
			map.put(user.getId()+"",user.toString());
		}
		
		long start = System.currentTimeMillis();
		
		redisTemplate.opsForHash().putAll("user-map", map);
		long end = System.currentTimeMillis();
		System.out.println("序列化的方式事JDK");
		System.out.println("保存数量是十万个对象");
		System.out.println("消耗时间是:"+(end-start)+"毫秒");
		
		
	}
}
