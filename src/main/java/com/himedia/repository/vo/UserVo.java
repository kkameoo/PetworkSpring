package com.himedia.repository.vo;

import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserVo {
	private Integer userId;

	@NotBlank(message = "이름을 입력해야 합니다.")
	@Size(max = 10, message = "이름은 최대 10자까지 입력 가능합니다.")
	private String name;  // 이름 (최대 10자)
	
	@NotBlank(message = "닉네임을 입력해야 합니다.")
	@Size(max = 20, message = "닉네임은 최대 20자까지 입력 가능합니다.")
	private String nickname; // 닉네임 (최대 20자)
	
	@NotBlank(message = "비밀번호를 입력해야 합니다.")
	@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$",
			message = "비밀번호는 최소 1개의 대문자 및 특수문자를 포함해야 합니다.")
	private String password; // 비밀번호 (특수문자 & 대문자 포함 필수)
	
	@NotBlank(message = "전화번호를 입력해야 합니다.")
	@Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 숫자만 입력해야 하며, 10~11자리여야 합니다.")
	private String telNumber; // 전화번호 (10~11자리 숫자)
	
	@NotBlank(message = "이메일을 입력해야 합니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$",
             message = "이메일 형식이 올바르지 않습니다. (예: example@domain.com)")
	private String email; // 이메일 (이메일 형식 검사)
	
	private Integer preference;
	private Integer localSi;
	private Integer localGu;
	private Timestamp regDate;
	private Timestamp update;
}