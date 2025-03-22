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
public class BoardPhotoVo {
	// pk
	private Integer boardPhotoId;
	// fk
	private Integer boardId;
	// 파일 이름
	private String boardPhotoName;
	// 파일 경로
	private String boardPhotoSrc;
	// 가입 날짜
	private Timestamp regDate;
}
