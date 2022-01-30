package com.example.spring_prac02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @NotBlank(message="이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 3, message = "닉네임은 3글자 이상 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$",
            message = "닉네임은 최소 3글자 이상, 알파벳 대소문자, 숫자로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, message = "비밀번호를 4글자 이상 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    @Size(min = 4, message = "비밀번호를 4글자 이상 입력해주세요.")
    private String checkPassword;

    private boolean admin = false;
    private String adminToken = "";
}
