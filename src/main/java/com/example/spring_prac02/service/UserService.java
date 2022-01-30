package com.example.spring_prac02.service;


import com.example.spring_prac02.dto.SignupRequestDto;
import com.example.spring_prac02.model.User;
import com.example.spring_prac02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public User registerUser(SignupRequestDto requestDto) throws Exception {

        // 닉네임 중복 확인
        String nickname = requestDto.getUsername();
        Optional<User> duplicatedNickname = userRepository.findByNickname(nickname);
        if (duplicatedNickname.isPresent()) {
            throw new Exception("중복된 사용자 닉네임이 존재합니다.");
        }
        // 패스워드 암호화
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());
        // 비밀번호 일치 여부 확인, matches를 사용하여 비교 하며 암호화되지 않은 비밀번호와 암호화된 비밀번호 일치 여부를 확인한다.
        boolean samePassword = passwordEncoder.matches(requestDto.getPassword(), encodePassword);
        if(!samePassword){
            throw new Exception("입력하신 비밀번호가 일치하지 않습니다.");
        }

        //비밀번호에 닉네임값이 포함 되어있는지 확인
        boolean nicknameInPassword = requestDto.getPassword().contains(requestDto.getNickname());
        if(nicknameInPassword){
            throw new Exception("비밀번호에 닉네임과 같은 값이 포함되어 있습니다.");
        }

        String email = requestDto.getEmail();

        String username = requestDto.getUsername();

        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(nickname, username, encodePassword, email,role);
        userRepository.save(user);

        return user;
    }
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


}

