package com.himedia.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.mappers.ChatMessageMapper;
import com.himedia.mappers.NotificationMapper;
import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.NotificationVo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class NotificationController {
	 private final SimpMessagingTemplate messagingTemplate;
	 private final NotificationMapper notificationMapper;
	
	 // 알림 목록 조회 API
//	 @GetMapping("/list")
//	 public List<NotificationVo> getNotificationList(@RequestParam("userId") int userId) {
//	     return notificationMapper.selectByReceiverId(userId);
//	 }
	
	 // 알림 저장 + WebSocket 전송
	 @PostMapping("/save")
	 public void saveNotification(@RequestBody NotificationVo noti) {
		 notificationMapper.insertNotification(noti);
	     messagingTemplate.convertAndSend("/user/" + noti.getChatroomId() + "/notification", noti);
	 }
	 
	 // userid로 알림 불러오기
	 @GetMapping("/list/byuser/{id}")
	 public List<NotificationVo> getNotificationByUserId(@PathVariable Integer id) {
	     return notificationMapper.selectNotificationsByUserId(id);
	 }
	 
	
//	@PostMapping("/room/notification")
//	public ResponseEntity<?> insertNotification(@RequestBody ChatMessageVo chatMessageVo) {
//		int result = chatMessageMapper.insertNotification(chatMessageVo);
//		if (result == 0) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생");
//		}
//		return ResponseEntity.ok("200 ok");
//	}
}