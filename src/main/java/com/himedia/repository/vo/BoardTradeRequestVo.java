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
public class BoardTradeRequestVo {
	// board필드
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
	// trade필드
	private Integer boardTradeId;
	private Integer tradePrice;
	private Integer tradeCategory;
	//photo 필드
	private Integer boardPhotoId;
	private String boardPhotoName;
	private String boardPhotoSrc;
	//map 필드
	private double latitude;
	private double longitude;
	// chatroomuser 필드
	private String nickname;
}
