package com.himedia.repository.vo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
public class NotificationVo {
	private int notificationId;     // 알림 고유 ID
	private int chatroomId;         // 알림 받을 사용자 ID
	private String content;         // 알림 내용
	private boolean isRead;         // 읽음 여부
	private Timestamp createdAt;// 생성 시각
}