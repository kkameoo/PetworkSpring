package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.UserVo;
import com.himedia.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// 생성 (CREATE)
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserVo user, BindingResult bindingResult) {
		// 유효성 검사 실패 시 에러
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
		}
		
		try {
			// 비밀번호 암호화
	        user.setPassword(userService.encodePassword(user.getPassword()));

			userService.registerUser(user);
			return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// 단일 조회 (READ)
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id) {
        return "유저 조회 성공";
    }
    
	// 전체 조회 (READ)
	@GetMapping("")
	public ResponseEntity<List<UserVo>> SelectAllUserList() {
		 List<UserVo> user = userService.selectAllUsers();
		 
	     return ResponseEntity.ok(user);
	 }
	
	 // 수정 (UPDATE)
	 @PutMapping("/{id}")
	 public String updateUser(@PathVariable("id") String id, @RequestBody UserVo user) {
		 return "수정 성공";
	 }
	 
	 // 삭제 (DELETE)
	 @DeleteMapping("/{id}")
	 public String deleteUser(@PathVariable("id") String id) {
		 return "삭제 성공";
	 }
}
