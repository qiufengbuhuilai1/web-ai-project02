package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询，参数：{}",studentQueryParam);
        PageResult<Student> pageResult =studentService.page(studentQueryParam);
        return Result.success(pageResult);

    }

    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("新增学生，参数：{}",student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询学生，id：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生，参数：{}",student);
        studentService.updateById(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除学生，ids：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violateOperation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("学生违规处理，id：{}，score：{}",id,score);
        studentService.violateOperation(id,score);
        return Result.success();
    }
}
