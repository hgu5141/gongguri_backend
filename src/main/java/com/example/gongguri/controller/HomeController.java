package com.example.gongguri.controller;

import com.example.gongguri.security.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins="http://localhost:8010")
public class HomeController {
    @GetMapping("/")

    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "index";
    }

    @GetMapping("/index")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "index";
    }
}
