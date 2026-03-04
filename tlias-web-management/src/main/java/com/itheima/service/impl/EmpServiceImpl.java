package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
////        //调用mapper查询数据
////        Long total=empMapper.count();
////        //调用mapper查询数据
////        List<Emp> rows=empMapper.list((page-1)*pageSize,pageSize);
////        //封装PageResult
////
////        return new PageResult<Emp>(total, rows);
//
//        PageHelper.startPage(page,pageSize);
//
//        List<Emp> rows=empMapper.list( name, gender, begin, end);
//
//        Page<Emp> p=(Page<Emp>) rows;
//        return new PageResult<>(p.getTotal(), p.getResult());
//
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {


        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> rows=empMapper.list(empQueryParam);

        Page<Emp> p=(Page<Emp>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            log.info("!!!!保存员工数据：{}",emp.getId());


            List<EmpExpr> exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(expr->{
                    expr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"添加员工:"+emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    @Override
    public Integer getempCountByDeptId(Integer id) {
        return empMapper.getempCountByDeptId(id);
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据用户名查询员工数据
        Emp e=empMapper.selectByUsernameAndPassword(emp);
        if(e==null) return null;

        Map<String, Object> claims=new HashMap<>();
        claims.put("id",e.getId());
        claims.put("username",e.getUsername());
        String jwt = JwtUtils.generateJwt(claims);

        LoginInfo loginInfo=new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        log.info("登录成功{}",loginInfo);
        return loginInfo;
    }
}
