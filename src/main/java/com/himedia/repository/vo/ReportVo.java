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
public class ReportVo {
	private Integer reportId;
	private Integer boardId;
	private Integer senderId;
	private Integer reportType;
	private String reportComment;
	private Timestamp regDate;
}
