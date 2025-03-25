package com.himedia.repository.vo;

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
	public enum MessageType {
		ENTER, TALK
	}
	private String roomId;  // 채팅방 ID
	private String sender;  // 보낸 사람
    private String content; // 메시지 내용
}
