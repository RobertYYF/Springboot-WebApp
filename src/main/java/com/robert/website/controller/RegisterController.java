package com.robert.website.controller;

import com.robert.website.dao.UserMapper;
import com.robert.website.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/register-error")
    public String registerError(Model model) {
        model.addAttribute("error", true);
        return "register";
    }

    @RequestMapping("/register-save")
    public String registerSave(@ModelAttribute User user,
                               Model model) {
        // 判断 username password 不能为空
        if (user.getUsername() == null || user.getPassword() == null || user.getUserRole() == null) {
            model.addAttribute("error", true);
            return "register";
        }
        try {
            // 密码加密存储
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            // 写入数据库
            userMapper.insert(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "register";
        }
    }
}
