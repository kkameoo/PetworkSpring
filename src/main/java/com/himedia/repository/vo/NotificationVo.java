package com.himedia.repository.vo;

import java.time.LocalDateTime;

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
public class NotificationVo {
	private int notificationId;     // 알림 고유 ID
	private int receiverId;         // 알림 받을 사용자 ID
	private String content;         // 알림 내용
	private String link;            // 클릭 시 이동할 URL (선택)
	private boolean isRead;         // 읽음 여부
	private LocalDateTime createdAt;// 생성 시각
}