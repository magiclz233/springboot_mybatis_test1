package com.cnpc.mapper;

import com.cnpc.enums.UserSexEnum;
import com.cnpc.mapper.two.User2Mapper;
import com.cnpc.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User2MapperTest {

	@Autowired
	private User2Mapper userMapper;

	@Test
	public void testInsert() throws Exception {
		for (int i = 20; i <25 ; i++) {
			userMapper.insert(new User("magic"+i, "password"+i, UserSexEnum.MAN));
		}
		for (int i = 25; i <30 ; i++) {
			userMapper.insert(new User("magic"+i, "password"+i, UserSexEnum.WOMAN));
		}
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.toString());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = userMapper.getOne(30l);
		System.out.println(user.toString());
		user.setNickName("neo");
		userMapper.update(user);
		Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
	}

}