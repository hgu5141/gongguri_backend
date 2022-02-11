package com.example.gongguri.controller;


import com.example.gongguri.dto.SignupRequestDto;
import com.example.gongguri.model.User;
import com.example.gongguri.repository.UserRepository;
import com.example.gongguri.security.UserDetailsImpl;
import com.example.gongguri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            model.addAttribute("username", "null");
        } else {

            model.addAttribute("username", userDetails.getUsername());
        }
        return "login";
    }


    @GetMapping("/user/login/error")
    public String loginError(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            model.addAttribute("user", "null");
        } else {

            model.addAttribute("user", userDetails.getUser().getUsername());
        }
        model.addAttribute("loginError", true);
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            model.addAttribute("user", "null");
        } else {

            model.addAttribute("user", userDetails.getUser().getUsername());
        }
        model.addAttribute("requestDto", new SignupRequestDto());
        return "signup";
    }

    @PostMapping("/user/signup")
    public String registerUser(Model model, @Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult) {

        // 회원 ID 중복 확인
        Optional<User> found1 = userRepository.findByUsername(requestDto.getUsername()); // Optional을 쓰면 null을 받을 수 있다.
        if (found1.isPresent()) { // found가 null이 아니면 true를 출력한다.
            FieldError fieldError = new FieldError("requestDto", "username", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
        }


        if (!requestDto.getPassword().equals(requestDto.getPasswordcheck())) {
            FieldError fieldError = new FieldError("requestDto", "checkpw", "암호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if (requestDto.getPassword().indexOf(requestDto.getUsername()) != -1) { // indexof가 -1이면 안에 포함이 안되어있는거다.
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은값을 넣을수 없습니다.");
            bindingResult.addError(fieldError);
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("user", "null");
            return "signup";
        }

        userService.registerUser(requestDto);
        return "redirect:/user/loginView";

    }

}