package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentMapper studentMapper;
    @GetMapping
    public Result page(ClassQueryParam classQueryParam){
        log.info("查询全部班级数据{}",classQueryParam);
        PageResult<Clazz> pageResult =classService.page(classQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addClass(@RequestBody Clazz clazz){
        log.info("添加班级数据{}",clazz);
        classService.addClass(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("查询班级数据{}",id);
        Clazz clazz = classService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级数据{}",clazz);
        classService.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级数据{}",id);

        //查询班级下是否有学生
        if(studentMapper.getStudentCountByClazzId(id)>0){
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        classService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listAll(){
        log.info("查询全部班级数据");
        List<Clazz> clazzList = classService.listAll();
        return Result.success(clazzList);
    }
}
