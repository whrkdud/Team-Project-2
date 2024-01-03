package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class productDao {
    @Autowired
    JdbcTemplate jt;

    // 구매 완료 후 product 페이지에 띄우는 용도
    public List<Map<String,Object>> selectPurchaseHistoryProduct(String id, String size) {
        String sqlStmt = String.format("SELECT h.*, l.product_image, l.product_name, l.product_price FROM purchase_history h, tire_list l WHERE h.id = '%s' AND h.seq = l.seq ORDER BY h.purchase_seq DESC LIMIT %s;", id, size);
        return jt.queryForList(sqlStmt);
    }
    // 구매 이력 insert
    public void insertPurchaseHistory(String seq, String id, String qty) {
        String sqlStmt = String.format("INSERT INTO purchase_history(seq, id, purchase_qty, purchase_date) VALUES ('%s', '%s', '%s', NOW())", seq, id, qty);
        jt.execute(sqlStmt);
    }
    // 구매완료 했을 때 장바구니 delete
    public void deleteCartByPurchase(String id) {
        String sqlStmt = String.format("DELETE FROM cart WHERE id = '%s'", id);
        jt.execute(sqlStmt);
    }
}
