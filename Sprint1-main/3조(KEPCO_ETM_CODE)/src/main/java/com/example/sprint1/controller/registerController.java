package com.example.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sprint1.dao.registerDao;

@Controller
public class registerController {
    @Autowired
    registerDao registerdao;

    // 회원가입
    @GetMapping("/register")
    public String register(){
        return "/html/register";
    }
    @PostMapping("/register")
    public String registerPost(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum
        ) {
            int check = registerdao.checkdup(id).size();
            if (check > 0) {
                return "redirect:/register";
            }
            registerdao.register(id, pw, name, email, phonenum);
            return "redirect:/registero";
        }
    
    // 중복검사
    @GetMapping("/check-duplicate")
    @ResponseBody
    public boolean checkDuplicate(@RequestParam("id") String id) {
        int check = registerdao.checkdup(id).size();
        return check > 0;
    }

    // 가입완료 페이지
    @GetMapping("/registero")
    public String registero() {
        return "/html/registero";
    }
}
