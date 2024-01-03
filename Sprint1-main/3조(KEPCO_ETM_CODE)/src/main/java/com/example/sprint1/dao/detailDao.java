package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class detailDao {
    @Autowired
    JdbcTemplate jt;
    // 검색 이력 insert
    public void insertSearchHistory(String seq, String id) {
        String sqlStmt = String.format("INSERT INTO search_history(seq, id) VALUES ('%s', '%s')", seq, id);
        jt.execute(sqlStmt);
    }
    // 중복 방지를 위한 해당 상품의 검색 이력 조회
    public List<Map<String,Object>> selectSearchHistoryBySeq(String id, String seq) {
        String sqlStmt = String.format("SELECT * FROM search_history WHERE id = '%s' AND seq = '%s'", id, seq);
        return jt.queryForList(sqlStmt);
    }
    // 최신화를 위해 전의 검색 이력을 삭제
    public void deleteSearchHistoryBySeq(String id, String seq) {
        String sqlStmt = String.format("DELETE FROM search_history WHERE id = '%s' AND seq = '%s'", id, seq);
        jt.execute(sqlStmt);
    }
    // 단일 상품 조회
    public List<Map<String,Object>> selectProduct(String seq) {
        String sqlStmt = String.format("SELECT * FROM tire_list WHERE seq = '%s'", seq);
        return jt.queryForList(sqlStmt);
    }

    // 상품에 달린 리뷰 개수 확인
    public List<Map<String,Object>> countReview(String seq) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM review WHERE seq = '%s'",seq);
        return jt.queryForList(sqlStmt);
    }

    // 상품에 달린 리뷰 확인
    public List<Map<String,Object>> Review(String seq) {
        String sqlStmt = String.format("SELECT * FROM review WHERE seq = '%s'",seq);
        return jt.queryForList(sqlStmt);
    }

    // 장바구니 insert
    public void insertCart(String seq, String id, String qty) {
        String sqlStmt = String.format("INSERT INTO cart(seq, id, qty) VALUES ('%s', '%s', '%s')", seq, id, qty);
        jt.execute(sqlStmt);
    }
}
