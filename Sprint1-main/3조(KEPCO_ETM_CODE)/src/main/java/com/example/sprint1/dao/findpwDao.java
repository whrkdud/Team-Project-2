package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class findpwDao {
    @Autowired
    JdbcTemplate jt;

    // Password 찾기
    public List<Map<String,Object>> findPw(String id, String name, String email, String phonenum) {
        String sqlStmt = String.format("SELECT password FROM user_list WHERE id = '%s' AND name = '%s' AND email = '%s' AND phonenumber = '%s'",id, name, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
    public List<Map<String,Object>> findPwcheck(String id, String name, String email, String phonenum) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM user_list WHERE id = '%s' AND name = '%s' AND email = '%s' AND phonenumber = '%s'",id, name, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
}
