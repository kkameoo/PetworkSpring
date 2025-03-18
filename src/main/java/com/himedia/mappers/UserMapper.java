package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.himedia.repository.vo.UserVo;

@Mapper
public interface UserMapper {
	 int insertUser(UserVo user);
	 int countNickname(@Param("nickname") String nickname);
	 int countEmail(@Param("email") String email);
	 int countTelNumber(@Param("telNumber") String telNumber);
	 UserVo selectUserById(Integer userId);
	 List<UserVo> selectAllUsers();
	 int updateUser(UserVo user);
	 int deleteUser(Integer userId);
}
