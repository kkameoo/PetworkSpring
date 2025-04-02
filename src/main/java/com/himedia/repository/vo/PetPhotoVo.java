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
public class PetPhotoVo {
	// pk
	private Integer petPhotoId;
	// fk
	private Integer petId;
	// 파일 이름
	private String petPhotoName;
	// 파일 경로
	private String petPhotoSrc;
	// 가입 날짜
	private Timestamp regDate;

}
