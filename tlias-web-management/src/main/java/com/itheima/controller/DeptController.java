package com.itheima.controller;

import com.itheima.exception.BusinessException;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr =request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门数据，id：" + id);
////        deptService.deleteById(id);
//        return Result.success();
//    }



//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id" ,required = true) Integer deptId) {
//        System.out.println("删除部门数据，id：" + deptId);
//    //        deptService.deleteById(id);
//        return Result.success();
//    }

    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println("删除部门数据，id：" + id);
        log.info("删除部门数据，id：{}",id);

        //查询班级中是否有学生
        if(empService.getempCountByDeptId(id)>0){
            throw new BusinessException("对不起, 该部门下有学生, 不能直接删除");
        }

        deptService.deleteById(id);
        return Result.success();
    }


    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("添加部门数据：" + dept);
        log.info("添加部门数据：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer id) {
//        System.out.println("查询部门数据，id：" + id);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        System.out.println("查询部门数据，id：" + id);
        log.info("查询部门数据，id：{}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门:"+dept);
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
