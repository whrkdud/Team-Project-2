package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class cartDao {
    @Autowired
    JdbcTemplate jt;

    // 장바구니가 비었는지 확인
    public List<Map<String,Object>> selectCartCount(String id) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM cart WHERE id = '%s'",id);
        return jt.queryForList(sqlStmt);
    }
    // 본인 장바구니 확인
    public List<Map<String,Object>> selectCart(String id) {
        String sqlStmt = String.format("SELECT t.*, c.qty, c.cart_seq FROM tire_list t, cart c WHERE t.seq = c.seq AND c.id = '%s'",id);
        return jt.queryForList(sqlStmt);
    }
    // 장바구니 delete
    public void deleteCart(String cart_seq) {
        String sqlStmt = String.format("DELETE FROM cart WHERE cart_seq = '%s'", cart_seq);
        jt.execute(sqlStmt);
    }
}
