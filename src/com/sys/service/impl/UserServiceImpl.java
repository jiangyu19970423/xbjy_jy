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
    public List<User> listAll(String account, Page page,String startTime,String endTime) {

        return userDao.listAll(account, page,startTime,endTime);
    }

    @Override
    public Integer getCount(String account,String startTime,String endTime) {
        return userDao.getCount(account,startTime,endTime);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(MDUtil.md5(user.getPassword()));
        user.setCreateTime(DateUtil.getDateStr());
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

    @Override
    public List<User> checkLogin(User user) {
        return userDao.checkLogin(user);
    }
}
