package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.UserVo;

@Service
public interface UserService {
	int createUser(UserVo user); // CREATE
	UserVo getUserById(Integer userId); // READ (단일 조회)
	List<UserVo> selectAllUsers(); // READ (전체 조회)
	int updateUser(UserVo user); // UPDATE
	int deleteUser(Integer userId); //DELETE

}
