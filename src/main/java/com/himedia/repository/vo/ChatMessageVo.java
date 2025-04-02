package com.himedia.repository.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageVo {
	
	private Integer chatId;
	private Integer chatroomId;  // 채팅방 ID
	private String sender;  // 보낸 사람
    private String content; // 메시지 내용
    public Integer MessageType;	// 1. enter 2.exit 3.talk 
    private Timestamp regDate;
}
