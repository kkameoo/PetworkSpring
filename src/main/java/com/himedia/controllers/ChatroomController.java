package com.himedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.ChatroomVo;
import com.himedia.services.ChatroomService;

@RestController
@RequestMapping("/api/chatroom")
public class ChatroomController {
	
	@Autowired
	ChatroomService chatroomService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectOneChatroom(@PathVariable Integer id) {
		ChatroomVo chatroomVo = chatroomService.selectOneChatroom(id);
		return ResponseEntity.ok(chatroomVo);
	}
}
