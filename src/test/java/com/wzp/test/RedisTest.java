package com.wzp.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wzp.entity.User;

import utils.DateUtil;
import utils.RandomUtil;
import utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class RedisTest {
	@Resource 
	private RedisTemplate redisTemplate;
	@Test
	public void userJDK() {
		List<User> userlist = new ArrayList<>();
		for (int i = 1; i <= 50000; i++) {
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间。
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(1949, 0, 1);
			//设置d1为1949开始时间
			Date d1 = calendar1.getTime();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2001, 0, 1);
			//设置d2为2001结束时间
			Date d2 = calendar2.getTime();
			Date birthday = DateUtil.randomDate(d1, d2);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com 
			//| @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
			String e[] = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String emal = RandomUtil.randomString1(RandomUtil.random(3, 20))+e[RandomUtil.random(0, 6)];
			//手机以13开头+9位随机数模拟。
			String phone = "13"+RandomUtil.randomString(9);
			//性别在女和男两个值中随机
			String s[] = {"男","女"};
			String sex = s[RandomUtil.random(0, 1)];
			//姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法
			String name = StringUtil.randomChineseString(3);
			//ID使用1-5万的顺序号
			User user =new User(i, name, sex, phone, emal, birthday);
			userlist.add(user);
		}
		ListOperations opsForList = redisTemplate.opsForList();
		//打印开始毫秒值
		long start = System.currentTimeMillis();
		//存入redis
		opsForList.leftPushAll("user_jdk", userlist);
		//打印结束毫秒值
		long end = System.currentTimeMillis();
		//计算所耗时
		System.out.println("所耗时间"+(end-start));
		//输出系列化方式
		System.out.println("JDK");
		//输出保存数量
		int size = userlist.size();
		System.out.println("保存数量"+size);
	}
	
	@Test
	public void userJSON() {
		List<User> userlist = new ArrayList<>();
		for (int i = 1; i <= 50000; i++) {
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间。
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(1949, 0, 1);
			//设置d1为1949开始时间
			Date d1 = calendar1.getTime();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2001, 0, 1);
			//设置d2为2001结束时间
			Date d2 = calendar2.getTime();
			Date birthday = DateUtil.randomDate(d1, d2);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com 
			//| @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
			String e[] = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String emal = RandomUtil.randomString1(RandomUtil.random(3, 20))+e[RandomUtil.random(0, 6)];
			//手机以13开头+9位随机数模拟。
			String phone = "13"+RandomUtil.randomString(9);
			//性别在女和男两个值中随机
			String s[] = {"男","女"};
			String sex = s[RandomUtil.random(0, 1)];
			//姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法
			String name = StringUtil.randomChineseString(3);
			//ID使用1-5万的顺序号
			User user =new User(i, name, sex, phone, emal, birthday);
			userlist.add(user);
		}
		ListOperations opsForList = redisTemplate.opsForList();
		//打印开始毫秒值
		long start = System.currentTimeMillis();
		//存入redis
		opsForList.leftPushAll("user_json", userlist);
		//打印结束毫秒值
		long end = System.currentTimeMillis();
		//计算所耗时
		System.out.println("所耗时间"+(end-start));
		//输出系列化方式
		System.out.println("JSON");
		//输出保存数量
		int size = userlist.size();
		System.out.println("保存数量"+size);
	}
	@Test
	public void userHash() {
		Map<String, User> map = new HashMap<String, User>();
		for (int i = 1; i <= 50000; i++) {
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间。
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(1949, 0, 1);
			//设置d1为1949开始时间
			Date d1 = calendar1.getTime();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2001, 0, 1);
			//设置d2为2001结束时间
			Date d2 = calendar2.getTime();
			Date birthday = DateUtil.randomDate(d1, d2);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com 
			//| @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
			String e[] = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String emal = RandomUtil.randomString1(RandomUtil.random(3, 20))+e[RandomUtil.random(0, 6)];
			//手机以13开头+9位随机数模拟。
			String phone = "13"+RandomUtil.randomString(9);
			//性别在女和男两个值中随机
			String s[] = {"男","女"};
			String sex = s[RandomUtil.random(0, 1)];
			//姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法
			String name = StringUtil.randomChineseString(3);
			//ID使用1-5万的顺序号
			User user =new User(i, name, sex, phone, emal, birthday);
			map.put(i+"", user);
		}
		HashOperations opsForHash = redisTemplate.opsForHash();
		//打印开始毫秒值
		long start = System.currentTimeMillis();
		//存入redis
		opsForHash.putAll("user_hash", map);
		//打印结束毫秒值
		long end = System.currentTimeMillis();
		//计算所耗时
		System.out.println("所耗时间"+(end-start));
		//输出系列化方式
		System.out.println("JDK");
		//输出保存数量
		int size = map.size();
		System.out.println("保存数量"+size);
	}
}
