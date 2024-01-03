package com.example.sprint1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.loginDao;
import com.example.sprint1.dao.mypageDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class mypageController {
    @Autowired
    mypageDao mypagedao;
    @Autowired
    loginDao logindao;

    @GetMapping("/mypage")
    public String mypage(
        @RequestParam("id") String id,
        Model model
    ) {
        List<Map<String,Object>> purchase = mypagedao.selectPurchaseHistory(id);
        List<Map<String,Object>> review = mypagedao.selectReview(id);
        List<Map<String,Object>> search = mypagedao.selectSearchHistory(id);
        model.addAttribute("purchase", purchase);
        model.addAttribute("review", review);
        model.addAttribute("search", search);
        return "/html/mypage";
    }

    @PostMapping("/mypage")
    public String mypagePost(
        @RequestParam(name ="pw",required = false) String pw,
        @RequestParam(name = "name",required = false) String name,
        @RequestParam(name = "phonenum",required = false) String phonenum,
        @RequestParam(name = "email",required = false) String email,
        @RequestParam(name = "id",required = false) String id,
        @RequestParam(name = "review_seq",required = false) String review_seq,
        @RequestParam(name = "search_seq",required = false) String search_seq,
        HttpSession session
    ) {
        if (pw != null && name != null && phonenum != null && email != null) {
            mypagedao.updateUser(pw, name, phonenum, email, id);
            Map<String,Object> user = logindao.login(id,pw).get(0);
            session.setAttribute("user", user);
        }
        if (review_seq != null) {
            mypagedao.deleteReview(review_seq);
            return "redirect:/mypage?id="+id+"#jumpingboxmyreview";
        }
        if (search_seq != null) {
            mypagedao.deleteSearchHistory(search_seq);
            return "redirect:/mypage?id="+id+"#jumpingboxmysearch";
        }
        return "redirect:/mypage?id="+id;
    }
}
