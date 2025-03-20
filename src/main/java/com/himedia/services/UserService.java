package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.UserVo;

@Service
public interface UserService {
	void registerUser(UserVo user) throws Exception;  // 회원가입 (중복 체크 포함)
	UserVo login(String email, String password); // 로그인 기능
	String encodePassword(String password); // 비밀번호 암호화 
	UserVo getUserById(Integer userId); // READ (단일 조회)
	List<UserVo> selectAllUsers(); // READ (전체 조회)
	int updateUser(UserVo user); // UPDATE
	int deleteUser(Integer userId); //DELETE

}
