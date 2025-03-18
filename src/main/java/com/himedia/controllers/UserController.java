package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	

	 @GetMapping("")
	    public ResponseEntity<UserVo> SelectAllUserList() {
		 UserVo user = userService.selectAllUser();
	        return ResponseEntity.ok(user);
	    }

	// 생성 (CREATE)
	@PostMapping("/")
	public String createUser(@RequestBody UserVo user) {
		return "생성 성공";
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
