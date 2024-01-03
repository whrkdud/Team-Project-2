package com.example.sprint1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sprint1.dao.mainDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class mainController {
    @Autowired
    mainDao maindao;
    @GetMapping("/main")
    public String Main(
        Model model
    ) {
        List<Map<String,Object>> tire = maindao.selectAll();
        model.addAttribute("tire", tire);
        return "/html/main";
    }

    // 로그아웃
    @PostMapping("/main")
    public String logout(
        HttpSession session
    ) {
        session.invalidate();
        return "redirect:/main";
    }
}
