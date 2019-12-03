package com.sys.dao;

import com.Utils.DBUtil;
import com.sys.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/19:11
 * @Description:
 */
public class DeptDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());

    public List<Dept> listAll(){
        String sql="select * from sys_dept";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }
}
