package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClassMapper;
import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult<Clazz> page(ClassQueryParam classQueryParam) {
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());
        List<Clazz> rows=classMapper.list(classQueryParam);

        Page<Clazz> p=(Page<Clazz>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void addClass(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.addClass(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return classMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.updateById(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        classMapper.deleteById(id);
    }

    @Override
    public List<Clazz> listAll() {
        return classMapper.listAll();
    }
}
