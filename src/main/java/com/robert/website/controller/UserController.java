package com.robert.website.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/")
    public String home(ModelMap mp) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        mp.addAttribute("host", username);
        return "index";
    }

    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "index";
    }

}
