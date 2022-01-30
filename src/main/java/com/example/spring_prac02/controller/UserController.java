package com.example.spring_prac02.controller;

import com.example.spring_prac02.dto.SignupRequestDto;
import com.example.spring_prac02.model.User;
import com.example.spring_prac02.security.UserDetailsImpl;
import com.example.spring_prac02.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(SignupRequestDto signupRequestDto, Model model,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "sign_up";
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto signupRequestDto,
                               Errors errors, Model model) throws Exception{

        if(errors.hasErrors()) {
            //회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("signupRequestDto", signupRequestDto);

            //유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key: validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "sign_up";
        }

        try {
            //유효성 검사 & 회원가입
            User user = userService.registerUser(signupRequestDto);
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "sign_up";
        }
        return "redirect:/user/login";
    }

}