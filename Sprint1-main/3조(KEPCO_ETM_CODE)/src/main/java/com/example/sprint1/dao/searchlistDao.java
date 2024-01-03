package com.example.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class searchlistDao {
    @Autowired
    JdbcTemplate jt;

    // 상세 검색 상품 조회
    public List<Map<String, Object>> searchTires(
        String[] manufacturers,
        String[] carTypes,
        String[] tireCharacteristics,
        String[] lownoise,
        String[] dimensions,
        String[] percentages,
        String[] inches
    ) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM tire_list WHERE 1=1");
        appendConditions(queryBuilder, "manufacturer", manufacturers);
        appendConditions(queryBuilder, "car_type", carTypes);
        appendConditions(queryBuilder, "tire_type", tireCharacteristics);
        appendConditions(queryBuilder, "low_noise", lownoise);
        appendConditions(queryBuilder, "width", dimensions);
        appendConditions(queryBuilder, "aspect_ratio", percentages);
        appendConditions(queryBuilder, "wheel_diameter", inches);
        queryBuilder.append(" ORDER BY product_price");
        return jt.queryForList(queryBuilder.toString());
    }

    private void appendConditions(StringBuilder queryBuilder, String columnName, String[] values) {
        if (values != null && values.length > 0) {
            queryBuilder.append(" AND ").append(columnName).append(" IN (");
            for (int i = 0; i < values.length; i++) {
                queryBuilder.append("'").append(values[i]).append("'");
                if (i < values.length - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");
        }
    }
}
