package com.example.gongguri.controller;

import com.example.gongguri.security.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")

    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "index";
    }

    @GetMapping("/write")
    @PreAuthorize("isAuthenticated()")
    public String write(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "write";
    }

    @GetMapping("/index")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @GetMapping("/detail/param")
    public String detail(@RequestParam Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "detail";
    }
}