package com.himedia.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.mappers.ChatMessageMapper;
import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.ChatroomUserVo;
import com.himedia.repository.vo.ChatroomVo;
import com.himedia.serviceimpl.ChatServiceImpl;
import com.himedia.services.ChatService;
import com.himedia.services.ChatroomService;
import com.himedia.services.ChatroomUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
	
	private final ChatService chatService;
	private final ChatroomService chatroomService;
	private final ChatroomUserService chatroomUserService;
	// /app/chat 으로 메세지 보냄
	// 메시지 브로커는 받은 메세지를 /topic/messages가 목적지
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageVo sendChatMessage(ChatMessageVo chatMessage) throws IOException {
    	chatService.sendMessage("topic/chat", chatMessage);
        return chatMessage;
    }
    
    @MessageMapping("/chat/join")
    @SendTo("/topic/userlist")
    public List<Object> joinUser(@Payload ChatroomUserVo chatroomUserVo) throws IOException {
        List<Object> list = chatService.sendUserList(chatroomUserVo);
        return list;
    }
    
    @MessageMapping("/chat/leave")
    @SendTo("/topic/userlist")
    public List<Object> leaveUser(@Payload ChatroomUserVo chatroomUserVo) throws IOException {
        List<Object> list = chatService.popUserList(chatroomUserVo);
        return list;
    }
    
    // redis저장소의 채팅 내역들을 출력
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllChats(@PathVariable Integer id) {
    	List<Object> chatList = chatService.getRecentMessages(id);
    	if (chatList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터가 존재하지 않습니다.");
		}
    	return ResponseEntity.ok(chatList);
    }
    
    // 채팅룸 하나 출력
	@GetMapping("/room/{id}")
	public ResponseEntity<?> selectOneChatroom(@PathVariable Integer id) {
		ChatroomVo chatroomVo = chatroomService.selectOneChatroom(id);
		if (chatroomVo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(chatroomVo);
	}
	
	// boardId로 채팅룸 하나 출력
	@GetMapping("/room/board/{id}")
	public ResponseEntity<?> selectOneChatroomByBoardId(@PathVariable Integer id) {
		ChatroomVo chatroomVo = chatroomService.selectOneChatroomByBoardId(id);
		if (chatroomVo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(chatroomVo);
	}
		
	// 채팅룸 여러개 출력
	@GetMapping("/room")
	public ResponseEntity<?> selectChatrooms() {
		List<ChatroomVo> chatroomVos = chatroomService.selectChatroom();
		if (chatroomVos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(chatroomVos);
	}
	// 채팅룸 하나 생성
	@PostMapping("/room")
	public ResponseEntity<?> insertChatroom(@RequestBody ChatroomVo chatroomVo) {
		int result = chatroomService.insertChatroom(chatroomVo);
		if (result != 1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("채팅방 입력중 오류가 발생했습니다.");
		}
		return ResponseEntity.ok(chatroomVo);
	}
	// mysql저장소의 채팅 내역들을 출력
	@GetMapping("/history/{roomId}")
	public ResponseEntity<?> selectAllChatHistory(@PathVariable Integer roomId) {
		List<ChatMessageVo> chatMessageVos = chatService.selectAllChatMessage(roomId);
		if (chatMessageVos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(chatMessageVos);
	}
	
	// 채팅룸 하나 생성
	@PostMapping("/room/user")
	public ResponseEntity<?> insertChatroomUser(@RequestBody ChatroomUserVo chatroomUserVo) {
		int result = chatroomUserService.insertChatroomUser(chatroomUserVo);
		if (result != 1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("채팅방 유저 입력중 오류가 발생했습니다.");
		}
		return ResponseEntity.ok(chatroomUserVo);
	}
	
	// 채팅룸 하나 생성
	@GetMapping("/room/byuser/{id}")
	public ResponseEntity<?> selectAllChatroomByUserid(@PathVariable Integer id) {
		List<ChatroomVo> chatroomVos = chatroomService.selectChatroomByUserId(id);
		if (chatroomVos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(chatroomVos);
	}
	
	// (테스트) 한꺼번에 메세지를 mysql에 입력
//	@PostMapping("/history")
//	public ResponseEntity<?> insertBatchChat(@RequestBody List<ChatMessageVo> chatMessageVos) {
//		for(ChatMessageVo chatMessageVo : chatMessageVos) {
//			System.out.println(chatMessageVo.getChatroomId());
//			System.out.println(chatMessageVo.getSender());
//			System.out.println(chatMessageVo.getContent());
//			System.out.println(chatMessageVo.getMessageType());
//		}
//		int result = chatService.insertChatMessages(chatMessageVos);
//		if (result == 0) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
//		}
//		return ResponseEntity.ok(chatMessageVos);
//	}
	
}
