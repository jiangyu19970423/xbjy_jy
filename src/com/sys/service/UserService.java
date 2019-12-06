package com.sys.service;

import com.sys.entity.Page;
import com.sys.entity.User;

import java.util.List;

public interface UserService {
    public List<User> listAll(String account, Page page, String startTime, String endTime);

    public Integer getCount(String account, String startTime, String endTime);

    public void addUser(User user);

    public void deleteById(Integer id);

    public User getById(Integer id);

    public void updateUser(User user);

    public void updatePassWord(User user);

    public List<User> checkLogin(User user);
}
