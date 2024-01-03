package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class mainDao {
    @Autowired
    JdbcTemplate jt;

    // 전체 상품 조회
    public List<Map<String,Object>> selectAll() {
        String sqlStmt = "SELECT * FROM tire_list";
        return jt.queryForList(sqlStmt);
    }
}
