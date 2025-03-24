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
public class BoardWalkRequestVo {
	//board 필드
		private Integer boardId;
		private Integer userId;
		private Integer boardType;
		private String title;
		private String content;
		private Integer reportCount;
		private Integer clickCount;
		private Integer localSi;
		private Integer localGu;
		private Timestamp regDate;
		private Timestamp update;
		//walk 필드
		private Integer boardWalkId;
		private Integer walkCategory;
		// pk
		private Integer boardPhotoId;
		// 파일 이름
		private String boardPhotoName;
		// 파일 경로
		private String boardPhotoSrc;

}
