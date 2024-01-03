package com.example.sprint1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.cartDao;

@Controller
public class cartController {
    @Autowired
    cartDao cartdao;

    @GetMapping("/cart")
    public String cart(
        @RequestParam("id") String id,
        Model model
    ) {
        int check = Integer.parseInt(cartdao.selectCartCount(id).get(0).get("cnt").toString());
        if (check < 1) {
            String cart = "empty";
            model.addAttribute("cart", cart);
        } else {
            List<Map<String,Object>> cart = cartdao.selectCart(id);
            model.addAttribute("cart", cart);
        }
        return "/html/cart";
    }
    @PostMapping("/cart")
    public String cartPost(
        @RequestParam("cart_seq") String cart_seq,
        @RequestParam("id") String id
    ) {
        cartdao.deleteCart(cart_seq);
        return "redirect:/cart?id="+id;
    }
}
