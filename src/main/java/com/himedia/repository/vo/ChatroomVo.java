package com.himedia.repository.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
public class ChatroomVo {
	
	private String roomId;
	private String name;
	
	@Builder
	public ChatroomVo(String roomId, String name) {
		this.roomId = roomId;
		this.name = name;
	}
}