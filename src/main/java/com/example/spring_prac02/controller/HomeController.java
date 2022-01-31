package com.example.spring_prac02.controller;

import com.example.spring_prac02.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            try {
                String username = userDetails.getUsername();
                if(username != null) {
                    model.addAttribute("nickname", username);
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            return "index";
        }
    }