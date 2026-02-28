package com.itheima.service.impl;

import com.itheima.mapper.EmpLogMapper;
import com.itheima.pojo.EmpLog;
import com.itheima.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(rollbackFor = {Exception.class},propagation = Propagation.REQUIRES_NEW)//需要在新的事务中执行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
