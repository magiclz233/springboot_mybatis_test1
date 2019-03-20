package com.cnpc.mapper.one;

import com.cnpc.model.User;
import com.cnpc.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User1Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

	List<User> getList(UserParam userParam);

	int getCount(UserParam userParam);
}