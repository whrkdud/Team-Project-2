package com.example.sprint1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.loginDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    loginDao logindao;
    // 로그인
    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "/html/login";
    }
    @PostMapping("/login")
    public String loginPost(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        Model model,
        HttpSession session
    ){  
        int check = logindao.login(id, pw).size();
        if (check < 1) {
            return "redirect:/login?error=true";
        } else {
            Map<String,Object> user = logindao.login(id,pw).get(0);
            session.setAttribute("user", user);
            model.addAttribute("error", false);
            return "redirect:/main";
        }
    }
}
