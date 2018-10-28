package com.qinglinJiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.qinglinJiang.dao.SysLogDao;
import com.qinglinJiang.domain.SysLog;
import com.qinglinJiang.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return sysLogDao.findAll();
    }
}
