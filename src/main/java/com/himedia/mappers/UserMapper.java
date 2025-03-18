package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.UserVo;

@Mapper
public interface UserMapper {
	 int insertUser(UserVo user);
	 UserVo selectUserById(Integer userId);
	 List<UserVo> selectAllUsers();
	 int updateUser(UserVo user);
	 int deleteUser(Integer userId);
}
