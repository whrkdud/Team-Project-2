package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class mypageDao {
    @Autowired
    JdbcTemplate jt;

    // 본인 구매 이력 확인
    public List<Map<String,Object>> selectPurchaseHistory(String id) {
        String sqlStmt = String.format("SELECT h.*, l.product_image, l.product_name, l.product_price, l.seq FROM purchase_history h, tire_list l WHERE h.id = '%s' AND h.seq = l.seq ORDER BY h.purchase_seq DESC;",id);
        return jt.queryForList(sqlStmt);
    }
    // 본인이 쓴 리뷰 확인
    public List<Map<String,Object>> selectReview(String id) {
        String sqlStmt = String.format("SELECT l.seq, r.review_seq, r.date, l.product_image, l.product_name, l.product_price, r.content FROM tire_list l, review r WHERE r.id = '%s' AND r.seq = l.seq ORDER BY r.review_seq DESC",id);
        return jt.queryForList(sqlStmt);
    }
    // 본인 검색 이력 확인
    public List<Map<String,Object>> selectSearchHistory(String id) {
        String sqlStmt = String.format("SELECT h.search_seq, l.product_name, l.product_image, l.seq FROM search_history h, tire_list l WHERE h.id = '%s' AND h.seq = l.seq ORDER BY h.search_seq DESC;",id);
        return jt.queryForList(sqlStmt);
    }

     // 회원 정보 수정
     public void updateUser(String pw, String name, String phonenum, String email, String id) {
        String sqlStmt = String.format("UPDATE user_list SET password='%s', name='%s', phonenumber='%s', email='%s' WHERE id = '%s'", pw, name, phonenum, email, id);
        jt.execute(sqlStmt);
    }

    // 리뷰 delete
    public void deleteReview(String review_seq) {
        String sqlStmt = String.format("DELETE FROM review WHERE review_seq = '%s'", review_seq);
        jt.execute(sqlStmt);
    }

    // 검색 이력 delete
    public void deleteSearchHistory(String search_seq) {
        String sqlStmt = String.format("DELETE FROM search_history WHERE search_seq = '%s'", search_seq);
        jt.execute(sqlStmt);
    }

}
