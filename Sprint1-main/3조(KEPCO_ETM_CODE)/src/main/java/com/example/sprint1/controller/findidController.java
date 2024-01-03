package com.example.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.findidDao;

@Controller
public class findidController {
    @Autowired
    findidDao findiddao;

    // 아이디 찾기
    @GetMapping("/findid")
    public String findId(@RequestParam(name = "error", required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "/html/findid";
    }
    @PostMapping("/findid")
    public String FindIdPost(
        @RequestParam("name") String name,
        @RequestParam("phonenum") String phonenum,
        @RequestParam("email") String email,
        Model model
    ){  
        int check = Integer.parseInt(findiddao.findIdcheck(name, email, phonenum).get(0).get("cnt").toString());
        if (check < 1) {
            return "redirect:/findid?error=true";
        } else {
            String id = findiddao.findId(name,email,phonenum).get(0).get("id").toString();
            model.addAttribute("error", false);
            return "redirect:/findido?id="+id;
        }
    }
    @GetMapping("/findido")
    public String findido(
        @RequestParam("id") String id,
        Model model
    ){
        model.addAttribute("id", id);
        return "/html/findido";
    }

}
