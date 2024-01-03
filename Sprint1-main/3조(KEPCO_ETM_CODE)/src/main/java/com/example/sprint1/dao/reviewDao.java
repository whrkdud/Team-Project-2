package com.example.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class reviewDao {
    @Autowired
    JdbcTemplate jt;

    // 리뷰 insert
    public void insertReview(String seq, String id, String content) {
        String sqlStmt = String.format("INSERT INTO review(seq, id, content, date) VALUES ('%s', '%s', '%s', NOW())", seq, id, content);
        jt.execute(sqlStmt);
    }
}
