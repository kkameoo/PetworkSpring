package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.ChatroomVo;
import com.himedia.serviceimpl.ChatService;
import com.himedia.services.ChatroomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
	
	private final ChatService chatService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageVo sendChatMessage(ChatMessageVo chatMessage) {
    	chatService.sendMessage("topic/chat", chatMessage);
        return chatMessage;
    }
    
    @GetMapping("")
    public ResponseEntity<?> getAllChats() {
    	List<Object> chatList = chatService.getRecentMessages();
    	return ResponseEntity.ok(chatList);
    }
    
//	@GetMapping("/{id}")
//	public ResponseEntity<?> selectOneChatroom(@PathVariable Integer id) {
//		ChatroomVo chatroomVo = ChatService.selectOneChatroom(id);
//		return ResponseEntity.ok(chatroomVo);
//	}
	
}
