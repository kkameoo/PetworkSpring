package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.UserVo;
import com.himedia.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final BoardController boardController;
	@Autowired
	private UserService userService;

    UserController(BoardController boardController) {
        this.boardController = boardController;
    }
	
	// 회원가입 (CREATE)
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserVo user, BindingResult bindingResult) {
		// 유효성 검사 실패 시 에러
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
		}
		
		try {
			userService.registerUser(user);
			return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserVo user, HttpSession session) {
		try {
			UserVo authenticatedUser = userService.login(user.getEmail(), user.getPassword());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(authenticatedUser);
			
			if (authenticatedUser != null) {
				session.setAttribute("user", authenticatedUser);
				return ResponseEntity.ok(authenticatedUser);
				}else {
					return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 오류 발생");
			}
		}
	
	// 세션 유지 기능
	@GetMapping("/session")
	public ResponseEntity<?> getSessionUser(HttpSession session) {
	    UserVo user = (UserVo) session.getAttribute("user");

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	    }

	    return ResponseEntity.ok(user);
	}
	
	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
	    session.invalidate(); //  세션 삭제
	    return ResponseEntity.ok("로그아웃 성공");
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
