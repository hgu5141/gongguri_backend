package com.example.gongguri.controller;


import com.example.gongguri.dto.SignupRequestDto;
import com.example.gongguri.repository.UserRepository;
import com.example.gongguri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public ResponseEntity<String> login() {
    return ResponseEntity.ok()
                .body("로그인 완료");
    }


    @GetMapping("/user/login/error")
    public String loginError() {
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {


        System.out.println(requestDto);
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";

    }

}