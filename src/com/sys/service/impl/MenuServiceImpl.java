package com.sys.service.impl;

import com.sys.dao.MenuDao;
import com.sys.entity.Menu;
import com.sys.service.MenuService;

import java.util.List;

/**
 * @Auther: jiangyu
 * @Company: 东方标准
 * @Date: 2019/11/29/16:36
 * @Description: 菜单ServiceImpl
 */
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao=new MenuDao();
    @Override
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
