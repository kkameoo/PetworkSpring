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
public class BoardHireVo {
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
	//hire 필드
	private Integer boardHireId;
	private String hireCondition;
	private Timestamp hireDate;
	private Integer hirePrice;
	private Integer hireCategory;
	//user 필드
	private String nickname;

}
