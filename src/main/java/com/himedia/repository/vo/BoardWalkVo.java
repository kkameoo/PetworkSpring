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
public class BoardWalkVo {
	
	private Integer boardId;
	private Integer userId;
	private Integer boardType;
	private String title;
	private String content;
	private Integer localSi;
	private Integer localGu;
	
	private Integer boardWalkId;
	private Integer walkCategory;
}
