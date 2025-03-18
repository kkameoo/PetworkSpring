package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.himedia.mappers.UserMapper;
import com.himedia.repository.vo.UserVo;
import com.himedia.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser (UserVo user) throws Exception {
		if (userMapper.countNickname(user.getNickname()) > 0) {
			throw new Exception("닉네임이 이미 사용 중입니다.");
		}
		if (userMapper.countEmail(user.getEmail()) > 0) {
            throw new Exception("이메일이 이미 사용 중입니다.");
        }
        if (userMapper.countTelNumber(user.getTelNumber()) > 0) {
            throw new Exception("전화번호가 이미 사용 중입니다.");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        
        userMapper.insertUser(user); // 회원가입 진행
	}
	
	@Override
	public String encodePassword(String password) {
		return passwordEncoder.encode(password); // 비밀번호 암호화 구현
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
