package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;




@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10")Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询，参数：{}，{}，{}，{}，{}，{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult =empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询，参数：{}",empQueryParam);
        PageResult<Emp> pageResult =empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存员工数据：{}",emp);
        empService.save(emp);
        return Result.success();
    }
}
