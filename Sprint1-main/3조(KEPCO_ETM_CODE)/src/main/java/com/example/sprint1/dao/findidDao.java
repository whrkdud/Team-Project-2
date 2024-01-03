package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class findidDao {
    @Autowired
    JdbcTemplate jt;
    // ID 찾기
    public List<Map<String,Object>> findId(String name, String email, String phonenum) {
        String sqlStmt = String.format("SELECT id FROM user_list WHERE name = '%s' AND email = '%s' AND phonenumber = '%s'", name, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
    public List<Map<String,Object>> findIdcheck(String name, String email, String phonenum) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM user_list WHERE name = '%s' AND email = '%s' AND phonenumber = '%s'", name, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
}
