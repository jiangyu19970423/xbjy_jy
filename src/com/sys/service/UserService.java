package com.sys.service;

import com.sys.entity.Page;
import com.sys.entity.User;

import java.util.List;

public interface UserService {
    public List<User> listAll(String account, Page page);
    public Integer getCount(String account);
    public void addUser(User user);
    public void deleteById(Integer id);
    public User getById(Integer id);
    public void updateUser(User user);
    public void updatePassWord(User user);
}
