package com.himedia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.UserMapper;
import com.himedia.repository.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public UserVo selectAllUser() {
		UserVo user = userMapper.selectAllUser();
		return user;
	}

}
