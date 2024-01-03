package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class registerDao {
    @Autowired
    JdbcTemplate jt;

    // 회원가입
    public void register(String id, String pw, String name, String email, String phonenum) {
        String sqlStmt = String.format("INSERT INTO user_list (id, password, name, email, phonenumber) VALUES ('%s', '%s', '%s', '%s', '%s')", id, pw, name, email, phonenum);
        jt.execute(sqlStmt);
    }
    // 가입된 회원 확인
    public List<Map<String,Object>> checkdup(String id) {
        String sqlStmt = String.format("SELECT * FROM user_list WHERE id = '%s'", id);
        return jt.queryForList(sqlStmt);
    }
}
