package com.example.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.findpwDao;

@Controller
public class findpwController {
    @Autowired
    findpwDao findpwdao;

    // 비밀번호 찾기
    @GetMapping("/findpw")
    public String findPw(@RequestParam(name = "error", required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "/html/findpw";
    }
    @PostMapping("/findpw")
    public String FindPwPost(
        @RequestParam("id") String id,
        @RequestParam("name") String name,
        @RequestParam("phonenum") String phonenum,
        @RequestParam("email") String email,
        Model model
    ){  
        int check = Integer.parseInt(findpwdao.findPwcheck(id, name, email, phonenum).get(0).get("cnt").toString());
        if (check < 1) {
            return "redirect:/findpw?error=true";
        } else {
            String pw = findpwdao.findPw(id, name, email, phonenum).get(0).get("password").toString();
            model.addAttribute("error", false);
            return "redirect:/findpwo?pw="+pw;
        }
    }
    
    @GetMapping("/findpwo")
    public String findpwo(
        @RequestParam("pw") String pw,
        Model model
    ){
        model.addAttribute("pw", pw);
        return "/html/findpwo";
    }
}
