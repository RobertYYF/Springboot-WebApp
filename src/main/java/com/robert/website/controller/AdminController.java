package com.robert.website.controller;

import com.robert.website.dao.UserMapper;
import com.robert.website.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String admin(Model m) {

        List<User> users = userMapper.findAll();
        m.addAttribute("users", users);
        return "admin";
    }

}
