package com.sys.service.impl;

import com.sys.dao.DeptDao;
import com.sys.entity.Dept;
import com.sys.entity.Page;
import com.sys.service.DeptService;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/19:17
 * @Description:
 */
public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDao();

    @Override
    public List<Dept> listAll() {

        return deptDao.listAll();
    }

    @Override
    public List<Dept> listAll(String name, Page page) {
        return deptDao.listAll(name, page);
    }

    @Override
    public Integer getCount(String name) {
        return deptDao.getCount(name);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.addDept(dept);
    }

    @Override
    public void deleteById(Integer id) {
        deptDao.deleteById(id);
    }

    @Override
    public Integer ListUser(Integer id) {
        return deptDao.ListUser(id);
    }
}
