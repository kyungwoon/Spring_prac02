package com.example.spring_prac02.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private Long id;
    private String title;   //제목
    private String name;    //작성자명
    private String content; //내용
}
