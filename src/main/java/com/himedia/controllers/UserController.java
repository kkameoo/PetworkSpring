package com.himedia.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.handlers.JwtUtil;
import com.himedia.repository.vo.UserVo;
import com.himedia.services.EmailService;
import com.himedia.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final BoardController boardController;
    private final JwtUtil jwtUtil;
    
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

 
	
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
	
	// jwt방식 로그인
	@PostMapping("/login/jwt")
	public ResponseEntity<?> loginJwtUser(@RequestBody UserVo user) {
		try {
			UserVo authenticatedUser = userService.login(user.getEmail(), user.getPassword());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(authenticatedUser);

			if (authenticatedUser != null) {
					
				String token = jwtUtil.generateToken(authenticatedUser.getEmail());
				authenticatedUser.setPassword("");
				authenticatedUser.setToken(token);
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
//	    UserVo user = (UserVo) session.getAttribute("user");
		UserVo user = UserVo.builder()
				.userId(4)
				.name("관리자")
				.nickname("관리자유저")
				.password("$2a$10$nNePwkjGIVTJ00uXMA9kSOVA9M5faEX5wt0dyoJLChksWNyTZcdUu")
				.telNumber("00011112222")
				.email("khdg1202@naver.com")
				.localSi(0)
				.localGu(0)
				.preference(1)
				.regDate(Timestamp.valueOf(LocalDateTime.now()))
				.update(Timestamp.valueOf(LocalDateTime.now()))
				.isAdmin(true)
				.build();
				
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	    }

	    return ResponseEntity.ok(user);
	}
	
	// 토큰 유효성 체크
	@PostMapping("/token")
	public ResponseEntity<?> validateToken(@RequestBody UserVo userVo) {
		System.out.println(userVo);
		 try {
             String username = jwtUtil.validateTokenAndGetUsername(userVo.getToken());
             if(username != null) {
            	 return ResponseEntity.ok(userVo);
             }
             
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
         }
		return ResponseEntity.ok(null);
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
	    session.invalidate(); //  세션 삭제
	    return ResponseEntity.ok("로그아웃 성공");
	}
	
	
	// 단일 조회 (READ)
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id) {
        return "유저 조회 성공";
    }
    
    @GetMapping("/info/{id}")
    @ResponseBody
    public UserVo getUserInfo(@PathVariable("id") Integer id) {
        return userService.getUserInfoById(id);
    }

    
	// 전체 조회 (READ)
	@GetMapping("")
	public ResponseEntity<List<UserVo>> SelectAllUserList() {
		 List<UserVo> user = userService.selectAllUsers();
		 
	     return ResponseEntity.ok(user);
	 }
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody UserVo user) {
	    try {
	        userService.updateUser(user);
	        return ResponseEntity.ok("회원 정보 수정 완료");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("회원 정보 수정 실패: " + e.getMessage());
	    }
	}
	 
	 // 이메일 인증 후 비밀번호 변경
	 @PostMapping("/reset-password")
	 public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
	     String email = request.get("email");
	     String password = request.get("password");
	     String code = request.get("code");

	     if (!emailService.verifyCode(email, code)) {
	         return ResponseEntity.badRequest().body("이메일 인증 코드가 유효하지 않습니다.");
	     }

	     UserVo user = new UserVo();
	     user.setEmail(email);
	     user.setPassword(userService.encodePassword(password));

	     userService.updatePasswordByEmail(user);
	     return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
	 }	 
	 
	 
	 @DeleteMapping("/delete")
	 public ResponseEntity<String> deleteUser(HttpSession session) {
	     UserVo sessionUser = (UserVo) session.getAttribute("user");

	     // 세션이 없으면 로그인 필요
	     if (sessionUser == null) {
	         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	     }

	     try {
	         //  로그인한 유저 삭제 (본인 계정만 삭제 가능)
	         userService.deleteUser(sessionUser.getUserId());

	         //  계정 삭제 후 자동 로그아웃 세션 삭제
	         session.invalidate();
	         return ResponseEntity.ok("회원 탈퇴 완료");
	     } catch (Exception e) {
	         return ResponseEntity.badRequest().body(e.getMessage());
	     }
	 }
}
