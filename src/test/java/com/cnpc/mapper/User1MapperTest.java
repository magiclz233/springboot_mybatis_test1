package com.cnpc.mapper;

import com.cnpc.mapper.one.User1Mapper;
import com.cnpc.enums.UserSexEnum;
import com.cnpc.model.User;
import com.cnpc.param.PageParam;
import com.cnpc.param.UserParam;
import com.cnpc.result.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User1MapperTest {

	@Autowired
	private User1Mapper userMapper;

	@Test
	public void testInsert() throws Exception {
		for (int i = 0; i <10 ; i++) {
			userMapper.insert(new User("magic"+i, "password"+i, UserSexEnum.MAN));
		}
		for (int i = 10; i <20 ; i++) {
			userMapper.insert(new User("magic"+i, "password"+i, UserSexEnum.WOMAN));

		}


//		Assert.assertEquals(3, userMapper.getAll().size());
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
		User user = userMapper.getOne(40L);
		System.out.println(user.toString());
		user.setNickName("smile");
		userMapper.update(user);
		Assert.assertTrue(("smile".equals(userMapper.getOne(40l).getNickName())));
	}

	@Test
	public void delete(){
		userMapper.delete( 1L );
	}

	/**userParam.
	 * beginLine当前页第一行
	 * pageSize 给定每页3行，也可以传
	 * currentPage 前台传入当前第几页（0）
	 * beginline = currentPage * pageSize
	 *
	 * 传进mysql limit beginLine ,pageSize 就取到了需要的值
	 *
	 * Page中参数
	 * currentPage 当前页 = userParam中currentPage
	 * totlNumber 总条数 = count
	 * totlePage 总页数 = totleNumber/pageParam.pageSize == 0 ?
	 * totleNumber/userParam.pageSize : totleNumber/userParam.pageSize+1
	 * 数据集合 = personlist
	 *
	 */
	@Test
	public void pageTest(){
		UserParam userParam = new UserParam();
		userParam.setCurrentPage( 0 );
		List<User> users = userMapper.getList( userParam );
		long count = userMapper.getCount( userParam );
		Page page = new Page( userParam,count,users );
		System.out.println(page.getList());

	}

}