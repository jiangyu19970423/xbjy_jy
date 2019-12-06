package com.sys.service;

import com.sys.entity.Dept;
import com.sys.entity.Page;

import java.util.List;

public interface DeptService {
    public List<Dept> listAll();
    public List<Dept> listAll(String name, Page page);
    public Integer getCount(String name);
    public void addDept(Dept dept);
    public void deleteById(Integer id);
    public Integer ListUser(Integer id);
}
