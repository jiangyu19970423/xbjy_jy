package com.sys.dao;

import com.Utils.DBUtil;
import com.sys.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Auther: jiangyu
 * @Company: 东方标准
 * @Date: 2019/11/29/15:56
 * @Description: 菜单Dao层
 */
public class MenuDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(DBUtil.getDataSource());

    public List<Menu> listAll(){
        String sql="select * from sys_menu order by order_by";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Menu.class));
    }
}
