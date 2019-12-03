package com.sys.service.impl;

import com.Utils.DateUtil;
import com.Utils.MDUtil;
import com.sys.dao.UserDao;
import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.service.UserService;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/16:37
 * @Description:
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();

    @Override
    public List<User> listAll(String account, Page page) {

        return userDao.listAll(account, page);
    }

    @Override
    public Integer getCount(String account) {
        return userDao.getCount(account);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(MDUtil.md5(user.getPassword()));
        user.setCreateTime(DateUtil.getDateStr());
        user.setCreateBy(null);
        userDao.addUser(user);
    }

    @Override
    public void deleteById(Integer id) {

        userDao.deleteById(id);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updatePassWord(User user) {
        userDao.updatePassWord(user);
    }
}
