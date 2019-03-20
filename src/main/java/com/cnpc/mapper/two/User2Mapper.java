package com.cnpc.mapper.two;

import com.cnpc.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User2Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}