package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student getById(Integer id);

    void updateById(Student student);

    void delete(List<Integer> ids);

    void violateOperation(Integer id, Integer score);
}
