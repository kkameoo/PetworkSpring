package com.himedia.repository.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserVo {
	private Integer userId;
	private String name;
	private String nickname;
	private String password;
	private String telNumber;
	private String email;
	private Integer preference;
	private Integer localSi;
	private Integer localGu;
	private Timestamp regDate;
	private Timestamp update;
}