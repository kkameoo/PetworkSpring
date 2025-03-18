package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.UserMapper;
import com.himedia.repository.vo.UserVo;
import com.himedia.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int createUser(UserVo user) {
		return userMapper.insertUser(user);
	}
	
	@Override
	public UserVo getUserById(Integer userId) {
		return userMapper.selectUserById(userId);
	}

	@Override
	public List<UserVo> selectAllUsers() {
		return userMapper.selectAllUsers();
	}
	
	@Override
	public int updateUser(UserVo user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(Integer userId) {
		return userMapper.deleteUser(userId);
	}
	
}
