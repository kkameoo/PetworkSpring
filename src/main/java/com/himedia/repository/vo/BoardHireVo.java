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
public class BoardHireVo {
	
	private Integer boardId;
	private Integer userId;
	private Integer boardType;
	private String title;
	private String content;
	private Integer localSi;
	private Integer localGu;
	
	private Integer boardHireId;
	private String hireCondition;
	private Timestamp hireDate;
	private Integer hirePrice;
	private Integer hireCategory;

}
