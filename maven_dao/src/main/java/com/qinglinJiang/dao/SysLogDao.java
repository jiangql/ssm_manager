package com.qinglinJiang.dao;

import com.qinglinJiang.domain.SysLog;

import java.util.List;

public interface SysLogDao {
    void saveLog(SysLog sysLog);

    List<SysLog> findAll();
}
