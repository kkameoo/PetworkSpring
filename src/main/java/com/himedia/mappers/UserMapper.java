package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.himedia.repository.vo.UserVo;

@Mapper
public interface UserMapper {
	 int insertUser(UserVo user);
	 UserVo findByEmail(@Param("email") String email); // 이메일로 사용자 조회
	 int countNickname(@Param("nickname") String nickname);
	 int countEmail(@Param("email") String email);
	 int countTelNumber(@Param("telNumber") String telNumber);
	 UserVo selectUserById(Integer userId);
	 List<UserVo> selectAllUsers();
	 int updateUser(UserVo user);
	 int deleteUser(Integer userId);
}
