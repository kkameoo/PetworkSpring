package com.himedia.repository.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardVo {
	private Integer boardId;
	private Integer userId;
	private String title;
	private String content;
	private Integer reportCount;
	private Integer clickCount;
	private Integer boardType; 
	private Integer localSi;
	private Integer localGu;
	private Timestamp regDate;
	private Timestamp update;
	private String nickname;
}
