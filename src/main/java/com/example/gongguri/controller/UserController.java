package com.example.gongguri.controller;


import com.example.gongguri.dto.SignupRequestDto;
import com.example.gongguri.dto.UserInfoDto;
import com.example.gongguri.exception.RestApiException;
import com.example.gongguri.security.UserDetailsImpl;
import com.example.gongguri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
    return "login";
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        System.out.println(requestDto);
        userService.registerUser(requestDto);
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    @PostMapping("/user/userinfo")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        String nickname = userDetails.getUser().getNickname();
        System.out.println(username + nickname);
        return new UserInfoDto(username, nickname);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestApiException> processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest()
                .body(new RestApiException(builder.toString(), HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<RestApiException> exceptionHandler(Exception e) {
        return ResponseEntity.badRequest()
                .body(new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}