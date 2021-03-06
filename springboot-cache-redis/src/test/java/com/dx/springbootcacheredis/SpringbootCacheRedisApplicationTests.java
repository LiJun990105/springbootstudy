package com.dx.springbootcacheredis;

import com.dx.springbootcacheredis.domain.User;
import com.dx.springbootcacheredis.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheRedisApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CacheManager cacheManager;

	@Before
	public void before() {
		userRepository.save(new User("张三", 23));
		userRepository.save(new User("李四", 24));
		userRepository.save(new User("王五", 25));
		userRepository.save(new User("张小三", 3));
	}

	@Test
	public void t1() {
		User user1 = userRepository.findByName("张三");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findByName("张三");
		System.out.println("第二次查询：" + user2);

		user1.setAge(20);
		userRepository.save(user1);
		User user3 = userRepository.findByName("张三");
		System.out.println("第三次查询：" + user3);
	}

	@Test
	public void t2() {
		User user1 = userRepository.findByIdAndName(1L, "张三");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findByIdAndName(1L, "张三");
		System.out.println("第二次查询：" + user2);

		user1.setAge(20);
		userRepository.save(user1);
		User user3 = userRepository.findByIdAndName(1L, "张三");
		System.out.println("第三次查询：" + user3);

		userRepository.deleteById(1L);
		System.out.println("删除成功");
	}

	@Test
	public void t3() {
		User user1 = userRepository.findByName2("张三");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findByName2("张三");
		System.out.println("第二次查询：" + user2);

		userRepository.deleteByName("张三");

		User user3 = userRepository.findByName("张三");
		System.out.println("第三次查询：" + user3);
	}

	@Test
	public void t4() {
		User user1 = userRepository.findByName2("张三");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findByName2("张三");
		System.out.println("第二次查询：" + user2);

		userRepository.updateByName("张三", 99);

		User user3 = userRepository.findByName2("张三");
		System.out.println("第三次查询：" + user3);

		User user4 = userRepository.findByName2("张三");
		System.out.println("第四次查询：" + user4);
	}
}
