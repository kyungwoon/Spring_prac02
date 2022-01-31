package com.example.spring_prac02.controller;

import com.example.spring_prac02.dto.SignupRequestDto;
import com.example.spring_prac02.model.User;
import com.example.spring_prac02.security.UserDetailsImpl;
import com.example.spring_prac02.service.KakaoUserService;
import com.example.spring_prac02.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        isLogined(model, userDetails);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(SignupRequestDto signupRequestDto, Model model,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "sign_up";
    }
    //로그인 여부 판단
    public void isLogined(Model model, UserDetailsImpl userDetails) {
        try {
            String nickname = userDetails.getUser().getNickname();
            System.out.println("nickname: " + nickname);
            if(nickname != null) {
                model.addAttribute("alreadyLoginedMessage", "이미 로그인이 되어있습니다.");
            }
        } catch(Exception e) {
        }
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

    //카카오 로그인 요청
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

}