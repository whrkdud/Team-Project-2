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
import com.example.sprint1.dao.detailDao;
import com.example.sprint1.dao.productDao;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class productController extends HttpServlet {
    @Autowired
    productDao productdao;
    @Autowired
    detailDao detaildao;
    @Autowired
    cartDao cartdao;

    @GetMapping("/product")
    public String product(
        @RequestParam(name = "seq",required = false) String seq,
        @RequestParam(name = "qty",required = false) String qty,
        @RequestParam(name = "detail",required = false) String detail,
        @RequestParam(name = "modal",required = false) String modal,
        @RequestParam(name = "cart",required = false) String cart,
        @RequestParam(name = "id",required = false) String id,
        @RequestParam(name = "size",required = false) String size,
        Model model
    ) {
        if (size != null) {
            List<Map<String,Object>> cartlist = productdao.selectPurchaseHistoryProduct(id, size);
            model.addAttribute("cartlist", cartlist);
            model.addAttribute("size",size);
            model.addAttribute("modal", modal);
        } else if (detail != null) {
            Map<String,Object> product = detaildao.selectProduct(seq).get(0);
            model.addAttribute("detail",detail);
            model.addAttribute("qty",qty);
            model.addAttribute("product", product);
            model.addAttribute("modal", modal);
        } else if (cart != null) {
            List<Map<String,Object>> cartlist = cartdao.selectCart(id);
            model.addAttribute("cartlist", cartlist);
            model.addAttribute("cart", cart);
            model.addAttribute("modal", modal);
        }
        return "/html/product";
    }

    @PostMapping("/product")
    public String productPost (
        @RequestParam(name = "seq",required = false) String seq,
        @RequestParam(name = "qty",required = false) String qty,
        @RequestParam(name = "detail",required = false) String detail,
        @RequestParam(name = "modal",required = false) String modal,
        @RequestParam(name = "id",required = false) String id,
        @RequestParam(name = "cartsize",required = false) String cartsize,
        @RequestParam(name = "cart", required = false) String cart,
        HttpServletRequest request
    ) {
        if (detail != null) {
            productdao.insertPurchaseHistory(seq, id, qty);
            return String.format("redirect:/product?seq=%s&qty=%s&detail=%s&modal=%s",seq,qty,detail,modal);
        }
        
        if (cart != null) {
            int cartsize1 = Integer.parseInt(cartsize);
            for (int j = 0; j < cartsize1; j++) {
                String cartseq = request.getParameter(String.format("seq%s",j));
                String cartqty = request.getParameter(String.format("qty%s",j));
                productdao.insertPurchaseHistory(cartseq, id, cartqty);
                productdao.deleteCartByPurchase(id);
            }
        }
        return String.format("redirect:/product?size=%s&modal=%s&id=%s",cartsize,modal,id );
    }
}
