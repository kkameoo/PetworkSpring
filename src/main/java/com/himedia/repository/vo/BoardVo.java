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
public class BoardVo {
	Integer boardId;
	Integer userId;
	String title;
	String content;
	Integer category;
	Integer reportCount;
	Integer clickCount;
	Integer boardType;
	Integer localSi;
	Integer localGu;
	Timestamp regDate;
	Timestamp update;
}
