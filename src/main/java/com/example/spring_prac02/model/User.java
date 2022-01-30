package com.example.spring_prac02.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;    //회원번호

    @Column(nullable = false, unique = true)
    private String nickname;    //닉네임

    @Column(nullable = false)
    private String name;    //이름

    @Column(nullable = false)
    private String email;    //이메일

    @Column(nullable = false)
    private String password;    //비밀번호

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String nickname, String name, String email, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
