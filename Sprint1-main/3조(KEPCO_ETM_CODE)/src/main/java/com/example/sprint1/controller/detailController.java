package com.example.sprint1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.detailDao;

@Controller
public class detailController {
    @Autowired
    detailDao detaildao;

    @GetMapping("/detail")
    public String Detail(
        @RequestParam("seq") String seq,
        @RequestParam(name = "id",required = false) String id,
        @RequestParam(name = "modal",required = false) String modal,
        @RequestParam(name = "qty",required = false) String qty,
        Model model) {
        if (id != null) {
            int ph = detaildao.selectSearchHistoryBySeq(id, seq).size();
            if (ph > 0) {
                detaildao.deleteSearchHistoryBySeq(id, seq);
                detaildao.insertSearchHistory(seq, id);
            } else {
                detaildao.insertSearchHistory(seq, id);
            }
        }
        Map<String,Object> product = detaildao.selectProduct(seq).get(0);
        int cnt = Integer.parseInt(detaildao.countReview(seq).get(0).get("cnt").toString());
        List<Map<String,Object>> review = detaildao.Review(seq);
        String company = product.get("product_name").toString().split(" ")[0];
        model.addAttribute("product", product);
        model.addAttribute("cnt", cnt);
        model.addAttribute("review", review);
        model.addAttribute("company", company);
        model.addAttribute("modal", modal);
        model.addAttribute("qty", qty);
        return "/html/detail";
    }
    @PostMapping("/detail")
    public String DetailPost(
        @RequestParam(name = "seq",required = false) String seq,
        @RequestParam(name = "qty",required = false) String qty,
        @RequestParam(name = "modal",required = false) String modal,
        @RequestParam(name = "id",required = false) String id
    ) {
        detaildao.insertCart(seq, id, qty);
        return String.format("redirect:/detail?id=%s&seq=%s&modal=%s&qty=%s",id,seq,modal,qty);
    }
}
