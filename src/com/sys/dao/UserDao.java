package com.sys.dao;

import com.Utils.DBUtil;
import com.sys.entity.Page;
import com.sys.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/11:10
 * @Description: 用户Dao层
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());

    public List<User> listAll(String account, Page page, String startTime, String endTime) {
        String sql = "select " +
                "d.name deptName," +
                "u.id id," +
                "u.account account," +
                "u.name name," +
                "u.age age," +
                "u.sex sex," +
                "u.birth_date birthDate," +
                "u.create_time createTime," +
                "u.create_by createBy" +
                " from " +
                "sys_user u " +
                "left join sys_dept d on u.dept_id=d.id" +
                " where u.account like ? and  u.create_time between ? and ? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + account + "%",
                startTime, endTime, (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());

    }

    public Integer getCount(String account, String startTime, String endTime) {
        String sql = "select count(*) from sys_user where account like ? and create_time between ? and ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, "%" + account + "%", startTime, endTime);
    }


    public void addUser(User user) {
        String sql = "insert into sys_user(id,dept_id,account,password,name,age,sex,email,birth_date,create_time,create_by) values(null,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getAge(), user.getSex(), user.getEmail(), user.getBirthDate(), user.getCreateTime(),user.getCreateBy());

    }

    public void deleteById(Integer id) {
        String sql = "delete from sys_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getById(Integer id) {
        String sql = "select * from sys_user where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(User user) {
        String sql = "update sys_user set dept_id=?,account=?,password=?,name=?,age=?,sex=?,email=?,birth_date=?,create_time=? where id=?";
        jdbcTemplate.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getAge(), user.getSex(), user.getEmail(), user.getBirthDate(), user.getCreateTime(), user.getId());
    }


    public void updatePassWord(User user) {
        //系统账号account唯一
        String sql = "update sys_user set password=? where account=?";
        jdbcTemplate.update(sql, user.getPassword(), user.getAccount());
    }

    public List<User> checkLogin(User user) {
        String sql = "select * from sys_user where account=? and password=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), user.getAccount(), user.getPassword());

    }
}
