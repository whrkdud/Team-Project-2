package com.example.sprint1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.detailDao;
import com.example.sprint1.dao.reviewDao;

@Controller
public class reviewController {
    @Autowired
    reviewDao reviewdao;
    @Autowired
    detailDao detaildao;

    @GetMapping("/review")
    public String review(
        @RequestParam("seq") String seq,
        Model model
    ) {
        Map<String,Object> tire = detaildao.selectProduct(seq).get(0);
        model.addAttribute("tire", tire);
        return "/html/review";
    }

    @PostMapping("/review")
    public String reviewPost(
        @RequestParam("id") String id,
        @RequestParam("seq") String seq,
        @RequestParam("content") String content
    ) {
        reviewdao.insertReview(seq, id, content);
        return "redirect:/mypage?id="+id+"#jumpingboxmyreview";
    }
}
