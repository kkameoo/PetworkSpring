package com.himedia.repository.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MapVo {
	private Integer mapId;
	private Integer boardId;
	private double latitude;
	private double longitude;
}
