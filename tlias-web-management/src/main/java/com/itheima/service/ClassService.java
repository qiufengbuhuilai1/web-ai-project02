package com.itheima.service;

import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClassService {

    PageResult<Clazz> page(ClassQueryParam classQueryParam);

    void addClass(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> listAll();
}
