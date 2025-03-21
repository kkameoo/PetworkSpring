package com.himedia.repository.vo;

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
public class BoardTradeVo {
	private Integer boardId;
	private Integer userId;
	private Integer boardType;
	private String title;
	private String content;
	private Integer localSi;
	private Integer localGu;
	
	private Integer boardTradeId;
	private Integer tradePrice;
	private Integer tradeCategory;

}
