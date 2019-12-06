package com.sys.dao;

import com.Utils.DBUtil;
import com.sys.entity.Dept;
import com.sys.entity.Page;
import com.sys.entity.User;
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

    public List<Dept> listAll() {
        String sql = "select * from sys_dept";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    public List<Dept> listAll(String name, Page page) {
        String sql = "select " +
                "u.name userName," +
                "d.id id," +
                "d.name name," +
                "d.create_time createTime," +
                "ifnull (a.counts , 0 ) countUser" +
                " from " +
                "sys_dept d " +
                "LEFT JOIN ( SELECT count(*) counts, dept_id deptId FROM sys_user GROUP BY dept_id ) a ON d.id = a.deptId " +
                "left join sys_user u on d.create_by=u.id" +
                " where d.name like ?" +
                "order by d.create_time desc " +
                "limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class), "%" + name + "%", (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer getCount(String name) {
        String sql = "select count(*) from sys_dept where name like ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, "%" + name + "%");
    }

    public void addDept(Dept dept) {
        String sql = "insert into sys_dept(id,name,create_time,create_by) values(null,?,?,?)";
        jdbcTemplate.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy());
    }

    public void deleteById(Integer id) {
        String sql = "delete from sys_dept where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Integer ListUser(Integer id) {
        String sql = "select count(*) from sys_dept d join sys_user u on u.dept_id=d.id where d.id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

}
